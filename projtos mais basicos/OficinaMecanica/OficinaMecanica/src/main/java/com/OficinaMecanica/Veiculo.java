package com.OficinaMecanica;

public class Veiculo {
    private int id;
    private int idCliente;
    private String modelo;
    private String placa;
    private int ano;
    private String status;

    public Veiculo(){}
    public Veiculo(int idCliente, String modelo, String placa, int ano, String status) {
        this.idCliente = idCliente;
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
        this.status = status;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return id+" idCliente: "+idCliente+" Modelo: "+modelo+" placa: "+placa+" ano: "+ano+" status: "+status;
    }



}
