package modelo;

import java.util.Objects;

public class Veiculo {
    private String matricula;
    private String marca;
    private String modelo;
    private String cor;

    private int ano;
    private TipoCombustivel combustivel;
    private Cliente donoAnterior;
    private Local local;
    private int quilometros;
    private int numeroDonos;
    private int numeroPortas;

    private String condicaoVeiculo;

    private int valorVeiculo;

    private EstadoVeiculo estadoVeiculo;

    private int pecasUsadasEmReparacoes;

    public Veiculo(String marca, String modelo, int ano, String matricula, String cor, int numeroPortas, TipoCombustivel combustivel, int quilometros, int numeroDonos, String condicaoVeiculo, int valorVeiculo) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.matricula = matricula;
        this.cor = cor;
        this.numeroPortas = numeroPortas;
        this.combustivel = combustivel;
        this.quilometros = quilometros;
        this.numeroDonos = numeroDonos;
        this.condicaoVeiculo = condicaoVeiculo;
        this.valorVeiculo = valorVeiculo;
        pecasUsadasEmReparacoes = 0;
        estadoVeiculo = EstadoVeiculo.POR_REPARAR; //sempre que se cria um veiculo, ele esta por reparar
        this.local = null;

    }

    public Veiculo(String marca, String modelo, int ano, String matricula, String cor, int numeroPortas, TipoCombustivel combustivel, int quilometros, int numeroDonos, String condicaoVeiculo, int valorVeiculo, Local local) {
        this(marca, modelo, ano, matricula, cor, numeroPortas, combustivel, quilometros, numeroDonos, condicaoVeiculo, valorVeiculo);
        this.local = local;
    }

    @Override
    public String toString() {
        return marca + " " + modelo + " - Matricula: "+ matricula;
    }

    public void setEstadoVeiculo(EstadoVeiculo estadoVeiculo) {
        this.estadoVeiculo = estadoVeiculo;
    }

    public void adicionarPecasUsadas(int quantidade){
        pecasUsadasEmReparacoes += quantidade;
    }

    public int getAno() {
        return ano;
    }

    public String getCondicaoVeiculo() {
        return condicaoVeiculo;
    }

    public EstadoVeiculo getEstadoVeiculo() {
        return estadoVeiculo;
    }

    public int getPecasUsadasEmReparacoes() {
        return pecasUsadasEmReparacoes;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setCombustivel(TipoCombustivel combustivel) {
        this.combustivel = combustivel;
    }

    public void setDonoAnterior(Cliente donoAnterior) {
        this.donoAnterior = donoAnterior;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public void setQuilometros(int quilometros) {
        this.quilometros = quilometros;
    }

    public void setNumeroDonos(int numeroDonos) {
        this.numeroDonos = numeroDonos;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getValorVeiculo() {
        return valorVeiculo;
    }

    public String getCor() {
        return cor;
    }

    public TipoCombustivel getCombustivel() {
        return combustivel;
    }

    public Cliente getDonoAnterior() {
        return donoAnterior;
    }

    public int getQuilometros() {
        return quilometros;
    }

    public int getNumeroDonos() {
        return numeroDonos;
    }

    public int getNumeroPortas() {
        return numeroPortas;
    }

    public String getMatricula() {
        return matricula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veiculo)) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(matricula, veiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}
