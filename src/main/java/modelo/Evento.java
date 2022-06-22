package modelo;

import java.util.ArrayList;
import java.util.List;

public class Evento extends Local {
    private String nome;
    private Estabelecimento estabelecimento; //
//    private Local local;
    private Data dataInicio;
    private Data dataFim;

    public Evento(Distrito distrito, String nome, Data dataInicio, Data dataFim) {
        super(distrito);
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
//        this.local = local;
//        veiculos = new ArrayList<>();
    }

    public Evento(Distrito distrito, String nome, Data dataInicio, Data dataFim, Estabelecimento estabelecimento){
        this(distrito,nome,dataInicio,dataFim);
        this.estabelecimento = estabelecimento;
    }

    public void adicionarVeiculo(Veiculo veiculo){
//        veiculos.add(veiculo);
    }

    public void removerVeiculo(Veiculo veiculo){
//        veiculos.remove(veiculo);
    }

    public String getNome() {
        return nome;
    }

    public Data getDataInicio() {
        return dataInicio;
    }

    public Data getDataFim() {
        return dataFim;
    }

    public List<Veiculo> getVeiculos() {
//        return veiculos;
        return null;
    }

    @Override
    public void transportarVeiculo(Veiculo veiculo, Local localDestino) {

    }
}
