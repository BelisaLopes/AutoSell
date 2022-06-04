package modelo;

import java.util.ArrayList;
import java.util.List;

public class Empresa extends Local{
    private int capacidadeMaximaVeiculos;
    private List<Veiculo> veiculos;

    public Empresa(Distrito distrito, int capacidadeMaximaVeiculos) {
        super(distrito);
        this.capacidadeMaximaVeiculos = capacidadeMaximaVeiculos;
        veiculos = new ArrayList<>();
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
