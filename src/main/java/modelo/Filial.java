package modelo;

public class Filial extends Estabelecimento {
    public Filial(Distrito distrito, int capacidadeMaximaVeiculos) {
        super(distrito, capacidadeMaximaVeiculos);
    }

    @Override
    public void transportarVeiculo(Veiculo veiculo, Local localDestino) {

    }

    @Override
    public String toString() {
        return "Filial "+ distrito;
    }
}
