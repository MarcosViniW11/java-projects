package com.loja02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    // CADASTRAR PEDIDO (transacional: insere pedido + atualiza estoque)
    public boolean CadastrarPedido(Pedido pedido) {
        String sqlSelect = "SELECT estoque FROM produto WHERE id = ?";
        String sqlInsert = "INSERT INTO pedido (cliente_id, produto_id, quantidade, data_pedido) VALUES (?,?,?,?)";
        String sqlUpdate = "UPDATE produto SET estoque = estoque - ? WHERE id = ?";

        try (Connection conn = Conexao.getConexao()) {
            try {
                conn.setAutoCommit(false);

                // 1) verificar estoque
                try (PreparedStatement psSel = conn.prepareStatement(sqlSelect)) {
                    psSel.setInt(1, pedido.getIdProduto());
                    try (ResultSet rs = psSel.executeQuery()) {
                        if (!rs.next()) {
                            System.out.println("Produto não encontrado (id=" + pedido.getIdProduto() + ")");
                            conn.rollback();
                            return false;
                        }
                        int estoque = rs.getInt("estoque");
                        if (estoque < pedido.getQuantidade()) {
                            System.out.println("Estoque insuficiente. Disponível: " + estoque + ", solicitado: " + pedido.getQuantidade());
                            conn.rollback();
                            return false;
                        }
                    }
                }

                // 2) inserir pedido
                try (PreparedStatement psIns = conn.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    psIns.setInt(1, pedido.getIdCliente());
                    psIns.setInt(2, pedido.getIdProduto());
                    psIns.setInt(3, pedido.getQuantidade());
                    // converter java.util.Date -> java.sql.Date
                    psIns.setDate(4, new java.sql.Date(pedido.getDataPedido().getTime()));
                    psIns.executeUpdate();

                    try (ResultSet rs = psIns.getGeneratedKeys()) {
                        if (rs.next()) {
                            pedido.setId(rs.getInt(1));
                        }
                    }
                }

                // 3) atualizar estoque do produto
                try (PreparedStatement psUpd = conn.prepareStatement(sqlUpdate)) {
                    psUpd.setInt(1, pedido.getQuantidade());
                    psUpd.setInt(2, pedido.getIdProduto());
                    psUpd.executeUpdate();
                }

                conn.commit();
                System.out.println("Pedido cadastrado e estoque atualizado com sucesso.");
                return true;

            } catch (Exception ex) {
                try { conn.rollback(); } catch (Exception e) { /* ignorar */ }
                ex.printStackTrace();
                return false;
            } finally {
                try { conn.setAutoCommit(true); } catch (Exception e) { /* ignorar */ }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // LISTAR PEDIDOS SIMPLES
    public List<Pedido> ListarPedidos() {
        String sql = "SELECT * FROM pedido";
        List<Pedido> listaPedidos = new ArrayList<>();

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("cliente_id"),
                        rs.getInt("produto_id"),
                        rs.getInt("quantidade"),
                        rs.getDate("data_pedido")
                );
                pedido.setId(rs.getInt("id"));
                listaPedidos.add(pedido);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listaPedidos;
    }

    // LISTAR PEDIDOS DETALHADOS (com nome do cliente, produto e estoque atual)
    public List<PedidoDetalhe> ListarPedidosComDetalhes() {
        String sql = "SELECT p.id, p.quantidade, p.data_pedido, " +
                "c.id as cliente_id, c.nome as cliente_nome, " +
                "pr.id as produto_id, pr.nome as produto_nome, pr.estoque as estoque_atual " +
                "FROM pedido p " +
                "JOIN clientes c ON p.cliente_id = c.id " +
                "JOIN produto pr ON p.produto_id = pr.id " +
                "ORDER BY p.id";

        List<PedidoDetalhe> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PedidoDetalhe d = new PedidoDetalhe();
                d.setId(rs.getInt("id"));
                d.setClienteId(rs.getInt("cliente_id"));
                d.setClienteNome(rs.getString("cliente_nome"));
                d.setProdutoId(rs.getInt("produto_id"));
                d.setProdutoNome(rs.getString("produto_nome"));
                d.setQuantidade(rs.getInt("quantidade"));
                d.setDataPedido(rs.getDate("data_pedido"));
                d.setEstoqueAtual(rs.getInt("estoque_atual"));

                lista.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Boolean ExcluirPedido(int id) {
        String sqlSelect = "SELECT produto_id, quantidade FROM pedido WHERE id = ?";
        String sqlDelete = "DELETE FROM pedido WHERE id = ?";
        String sqlUpdate = "UPDATE produto SET estoque = estoque + ? WHERE id = ?";

        try (Connection conn = Conexao.getConexao()) {
            conn.setAutoCommit(false);

            int produtoId;
            int qtd;

            // 1) Buscar informações do pedido
            try (PreparedStatement ps = conn.prepareStatement(sqlSelect)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("Pedido não encontrado!");
                        conn.rollback();
                        return false;
                    }
                    produtoId = rs.getInt("produto_id");
                    qtd = rs.getInt("quantidade");
                }
            }

            // 2) Excluir pedido
            try (PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
                ps.setInt(1, id);
                int afetadas = ps.executeUpdate();
                if (afetadas <= 0) {
                    System.out.println("Erro ao excluir pedido!");
                    conn.rollback();
                    return false;
                }
            }

            // 3) Atualizar estoque do produto
            try (PreparedStatement ps = conn.prepareStatement(sqlUpdate)) {
                ps.setInt(1, qtd);
                ps.setInt(2, produtoId);
                ps.executeUpdate();
            }

            conn.commit();
            System.out.println("Pedido excluído e estoque restaurado!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean AtualizarQuantidadePedido(int id, int novaQuantidade) {
        try (Connection conn = Conexao.getConexao()) {
            conn.setAutoCommit(false);

            String sqlSelectPedido = "SELECT produto_id, quantidade FROM pedido WHERE id = ?";
            String sqlSelectProduto = "SELECT estoque FROM produto WHERE id = ?";
            String sqlUpdatePedido = "UPDATE pedido SET quantidade = ? WHERE id = ?";
            String sqlUpdateProduto = "UPDATE produto SET estoque = ? WHERE id = ?";

            int produtoId;
            int quantidadeAntiga;
            int diferenca;
            int estoqueAtual;

            // 1. Buscar o pedido
            try (PreparedStatement ps = conn.prepareStatement(sqlSelectPedido)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("❌ Pedido não encontrado!");
                        conn.rollback();
                        return false;
                    }
                    produtoId = rs.getInt("produto_id");
                    quantidadeAntiga = rs.getInt("quantidade");
                }
            }

            // 2. Buscar o estoque atual do produto
            try (PreparedStatement ps = conn.prepareStatement(sqlSelectProduto)) {
                ps.setInt(1, produtoId); // 🔹 aqui antes você estava passando o "id do pedido" errado!
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("❌ Produto não encontrado!");
                        conn.rollback();
                        return false;
                    }
                    estoqueAtual = rs.getInt("estoque");
                }
            }

            // 3. Calcular diferença
            if (novaQuantidade > quantidadeAntiga) {
                diferenca = novaQuantidade - quantidadeAntiga;
                if (estoqueAtual < diferenca) {
                    System.out.println("❌ Estoque insuficiente para aumentar a quantidade!");
                    conn.rollback();
                    return false;
                }
                estoqueAtual -= diferenca; // tira do estoque
            } else if (novaQuantidade < quantidadeAntiga) {
                diferenca = quantidadeAntiga - novaQuantidade;
                estoqueAtual += diferenca; // devolve ao estoque
            } else {
                // Se for igual, não precisa atualizar nada
                System.out.println("ℹ️ Quantidade não foi alterada.");
                conn.rollback();
                return true;
            }

            // 4. Atualizar o pedido
            try (PreparedStatement ps = conn.prepareStatement(sqlUpdatePedido)) {
                ps.setInt(1, novaQuantidade);
                ps.setInt(2, id); // 🔹 aqui é o ID do pedido!
                ps.executeUpdate();
            }

            // 5. Atualizar o estoque do produto
            try (PreparedStatement ps = conn.prepareStatement(sqlUpdateProduto)) {
                ps.setInt(1, estoqueAtual);
                ps.setInt(2, produtoId);
                ps.executeUpdate();
            }

            conn.commit();
            System.out.println("✅ Pedido e estoque atualizados com sucesso!");
            return true;

        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
            return false;
        }
    }

    public List<PedidoDetalhe> ListarPedidosDetalhe() {
        String sql = "SELECT p.id, p.quantidade, p.data_pedido, " +
                "c.id as cliente_id, c.nome as cliente_nome, " +
                "pr.id as produto_id, pr.nome as produto_nome, pr.preco, pr.estoque as estoque_atual " +
                "FROM pedido p " +
                "JOIN clientes c ON p.cliente_id = c.id " +
                "JOIN produto pr ON p.produto_id = pr.id " +
                "ORDER BY p.id";

        List<PedidoDetalhe> pedidosDetalhe = new ArrayList<>();

        try(Connection conn = Conexao.getConexao();PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery(); ){
            while (rs.next()) {
                PedidoDetalhe d = new PedidoDetalhe();
                d.setId(rs.getInt("id"));
                d.setClienteId(rs.getInt("cliente_id"));
                d.setClienteNome(rs.getString("cliente_nome"));
                d.setProdutoId(rs.getInt("produto_id"));
                d.setProdutoNome(rs.getString("produto_nome"));
                d.setPrecoProduto(rs.getInt("preco"));
                d.setQuantidade(rs.getInt("quantidade"));
                d.setDataPedido(rs.getDate("data_pedido"));
                d.setEstoqueAtual(rs.getInt("estoque_atual"));

                pedidosDetalhe.add(d);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar pedidosDetalhe!"+e.getMessage());
        }
        return pedidosDetalhe;

    }


}
