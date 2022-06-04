package modelo;

public class Filial extends Empresa{
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
