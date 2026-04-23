package br.com.marcos.vendas.service;

import br.com.marcos.vendas.model.ItemVenda;
import br.com.marcos.vendas.model.Venda;
import br.com.marcos.vendas.repository.ItemVendaRepository;
import br.com.marcos.vendas.repository.VendaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ItemVendaRepository itemVendaRepository;
    private final ItemVendaService itemVendaService;

    public VendaService(VendaRepository vendaRepository,
                        ItemVendaRepository itemVendaRepository,
                        ItemVendaService itemVendaService) {
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository;
        this.itemVendaService = itemVendaService;
    }

    public List<Venda> listar() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> buscarPorId(Long id) {
        return vendaRepository.findById(id);
    }

    public Venda salvar(Venda venda) {

        if (venda.getCliente() == null)
            throw new IllegalArgumentException("Cliente não informado.");

        if (venda.getItemVenda() == null || venda.getItemVenda().isEmpty())
            throw new IllegalArgumentException("A venda deve possuir pelo menos um item.");

        // define data automaticamente
        venda.setDataVenda(LocalDateTime.now());

        // 1. salva venda para gerar ID
        Venda vendaSalva = vendaRepository.save(venda);

        double total = 0;

        // 2. Salva cada item da venda e atualiza o estoque
        for (ItemVenda item : venda.getItemVenda()) {

            // vincula venda ao item
            item.setVenda(vendaSalva);

            // chamar service que cuida do estoque e subtotal
            ItemVenda itemPersistido = itemVendaService.salvar(item);

            total += itemPersistido.getSubtotal();
        }

        // 3. atualiza total da venda
        vendaSalva.setValorTotal(total);
        return vendaRepository.save(vendaSalva);
    }

    public void excluir(Long id) {
        Venda venda = buscarPorId(id).orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        // restaura estoque removendo os itens
        for (ItemVenda item : venda.getItemVenda()) {
            // excluir item -> o service cuida de restaurar estoque
            itemVendaService.excluir(item.getId());
        }

        vendaRepository.delete(venda);
    }

    public Venda atualizar(Venda vendaAtualizada) {
        Venda vendaAntiga =  buscarPorId((long) vendaAtualizada.getId())
                .orElseThrow(() -> new RuntimeException("Venda antiga não encontrada"));

        double total = 0;

        // exclui itens antigos e restaura estoque
        for (ItemVenda item : vendaAntiga.getItemVenda()) {
            itemVendaService.excluir(item.getId());
        }

        // salva itens novos
        for (ItemVenda novoItem : vendaAtualizada.getItemVenda()) {
            novoItem.setVenda(vendaAntiga);

            ItemVenda itemPersistido = itemVendaService.salvar(novoItem);

            total += itemPersistido.getSubtotal();
        }

        vendaAntiga.setValorTotal(total);
        vendaAntiga.setCliente(vendaAtualizada.getCliente());

        return vendaRepository.save(vendaAntiga);
    }
}
