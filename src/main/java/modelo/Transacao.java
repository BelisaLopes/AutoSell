package modelo;

public class Transacao {
    private final TipoTransacao tipoTransacao;
    private Data dataTransacao;
    private float valorTransacao;
    private Cliente cliente;

    Local local;

    private Veiculo veiculo;

    public Transacao(TipoTransacao tipoTransacao, Cliente cliente, Data dataTransacao, float valorTransacao, Veiculo veiculo, Local local) {
        this.tipoTransacao = tipoTransacao;
        this.cliente = cliente;
        cliente.addTransacao(this);
        this.dataTransacao = dataTransacao;
        this.valorTransacao = valorTransacao;
        this.veiculo = veiculo;
        this.local = local;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Transação: " + tipoTransacao + " -  " + local + " -  " + veiculo +" -  Data: " + dataTransacao + " -  Valor: " + valorTransacao + "€";
    }
}
