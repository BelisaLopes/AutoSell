package modelo;

public class Sede extends Estabelecimento {
    public Sede(Distrito distrito, int capacidadeMaximaVeiculos) {
        super(distrito, capacidadeMaximaVeiculos);
    }

    @Override
    public String toString() {
        return "Sede "+ distrito;
    }
}
