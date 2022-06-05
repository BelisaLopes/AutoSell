package modelo;

import java.util.ArrayList;
import java.util.List;

public class Estabelecimento extends Local{
    private int capacidadeMaximaVeiculos;
    private Oficina oficina;

    public Estabelecimento(Distrito distrito, int capacidadeMaximaVeiculos) {
        super(distrito);
        this.capacidadeMaximaVeiculos = capacidadeMaximaVeiculos;
        veiculos = new ArrayList<>();
        oficina = new Oficina();
    }

    @Override
    public void transportarVeiculo(Veiculo veiculo, Local localDestino) {
//        if(veiculos.size() < capacidadeMaximaVeiculos){
//            veiculos.add(veiculo);
//            localDestino.transportarVeiculo(veiculo);
//        }
    }

    public int getCapacidadeMaximaVeiculos() {
        return capacidadeMaximaVeiculos;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public Distrito getDistrito() {
        return distrito;
    }
}
