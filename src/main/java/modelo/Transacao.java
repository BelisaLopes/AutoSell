package modelo;

public class Transacao {
    private final TipoTransacao tipoTransacao;
    private Data dataTransacao;
    private float valorTransacao;
    private Cliente cliente;

    private Veiculo veiculo;

    public Transacao(TipoTransacao tipoTransacao, Cliente cliente, Data dataTransacao, float valorTransacao, Veiculo veiculo) {
        this.tipoTransacao = tipoTransacao;
        this.cliente = cliente;
        cliente.addTransacao(this);
        this.dataTransacao = dataTransacao;
        this.valorTransacao = valorTransacao;
        this.veiculo = veiculo;
    }

    @Override
    public String toString() {
        return "Transação: " + tipoTransacao + " -  " + veiculo +" -  Data: " + dataTransacao + " -  Valor: " + valorTransacao + "€";
    }
}
