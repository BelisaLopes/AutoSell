package modelo;

import java.util.ArrayList;
import java.util.List;

public class Evento extends Local {
    private String nome;
//    private Local local;
    private Data dataInicio;
    private Data dataFim;

    public Evento(Distrito distrito, String nome) {
        super(distrito);
        this.nome = nome;
//        this.local = local;
//        veiculos = new ArrayList<>();
    }

    public void adicionarVeiculo(Veiculo veiculo){
//        veiculos.add(veiculo);
    }

    public void removerVeiculo(Veiculo veiculo){
//        veiculos.remove(veiculo);
    }



    public List<Veiculo> getVeiculos() {
//        return veiculos;
        return null;
    }

    @Override
    public void transportarVeiculo(Veiculo veiculo, Local localDestino) {

    }
}
