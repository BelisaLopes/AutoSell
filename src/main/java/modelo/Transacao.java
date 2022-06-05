package modelo;

public class Transacao {
    private final TipoTransacao tipoTransacao;
    private Data dataTransacao;
    private float valorTransacao;
    private Cliente cliente;

    public Transacao(TipoTransacao tipoTransacao, Cliente cliente, Data dataTransacao, float valorTransacao) {
        this.tipoTransacao = tipoTransacao;
        this.cliente = cliente;
        cliente.addTransacao(this);
        this.dataTransacao = dataTransacao;
        this.valorTransacao = valorTransacao;
    }

    @Override
    public String toString() {
        return "Transacao";
    }
}
