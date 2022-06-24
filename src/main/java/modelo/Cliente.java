package modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String morada;
    private Data dataNascimento;
    private String NIF;
    private String contacto;
    private List<Transacao> transacoes;

    public Cliente(String nome, String morada, Data dataNascimento, String NIF, String contacto) {
        this.nome = nome;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
        this.NIF = NIF;
        this.contacto = contacto;
        transacoes = new ArrayList<>();
        /*transacoes.add(new Transacao(TipoTransacao.VENDA, this, dataNascimento, 10,
                new Veiculo("BMW", "X5", 2000, "00-XF-00", "azul",
                        5, TipoCombustivel.GASOLEO, 100, 2,
                        "usado", 100)));*/
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

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void addTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public String toString() {
        return nome;
    }
}
