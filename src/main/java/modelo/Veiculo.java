package modelo;

public class Veiculo {
    private String matricula;
    private String marca;
    private String modelo;
    private String cor;
    private TipoCombustivel combustivel;
    private Cliente donoAnterior;
    private Local local;
    private int kilometragem;
    private int numeroDonos;
    private int numeroPortas;

    public Veiculo() {
    }

    @Override
    public String toString() {
        return "Veiculo{}";
    }
}
