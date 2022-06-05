package modelo;

public class Veiculo {
    private String matricula;
    private String marca;
    private String modelo;
    private String cor;
    private TipoCombustivel combustivel;
    private Cliente donoAnterior;
    private Distrito distrito;
    private int kilometragem;
    private int numeroDonos;
    private int numeroPortas;

    public Veiculo() {
    }

    @Override
    public String toString() {
        return "Veiculo{}";
    }

    public void transportarVeiculo(Local localDestino){

//        setLocal(localDestino);
//        if(localDestino != this.local){
//            removerVeiculo(veiculo);
//        }
//        else{
//            adicionarVeiculo(veiculo);
//        }
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

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public void setKilometragem(int kilometragem) {
        this.kilometragem = kilometragem;
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

    public String getCor() {
        return cor;
    }

    public TipoCombustivel getCombustivel() {
        return combustivel;
    }

    public Cliente getDonoAnterior() {
        return donoAnterior;
    }

    public int getKilometragem() {
        return kilometragem;
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
}
