package modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String morada;
    private Data dataNascimento;
    private double NIF;
    private double contacto;
    private List<Transacao> transacoes;

    public Cliente(String nome, String morada, Data dataNascimento, double NIF, double contacto) {
        this.nome = nome;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
        this.NIF = NIF;
        this.contacto = contacto;
        transacoes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double getNIF() {
        return NIF;
    }

    public void setNIF(double NIF) {
        this.NIF = NIF;
    }

    public double getContacto() {
        return contacto;
    }

    public void setContacto(double contacto) {
        this.contacto = contacto;
    }

    public void addTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }
}
