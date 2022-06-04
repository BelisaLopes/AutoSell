package modelo;

import java.util.ArrayList;
import java.util.List;

public class Evento{
    private Local local;
    private List<Veiculo> veiculos;
    private Data dataInicio;
    private Data dataFim;

    public Evento(Local local) {
        this.local = local;
        veiculos = new ArrayList<>();
    }

    public void adicionarVeiculo(Veiculo veiculo){
        veiculos.add(veiculo);
    }

    public void removerVeiculo(Veiculo veiculo){
        veiculos.remove(veiculo);
    }

    public Local getLocal() {
        return local;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
}
