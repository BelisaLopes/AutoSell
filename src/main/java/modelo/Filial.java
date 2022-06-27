package modelo;

public class Filial extends Estabelecimento {
    public Filial(Distrito distrito, int capacidadeMaximaVeiculos) {
        super(distrito, capacidadeMaximaVeiculos);
    }

    @Override
    public String toString() {
        return "Filial "+ distrito;
    }
}
