package modelo;

import java.util.ArrayList;
import java.util.List;

public class Evento extends Local {
    private String nome;
    private Estabelecimento estabelecimento;
    private Data dataInicio;
    private Data dataFim;

    public Evento(Distrito distrito, String nome, Data dataInicio, Data dataFim) {
        super(distrito);
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Evento(Distrito distrito, String nome, Data dataInicio, Data dataFim, Estabelecimento estabelecimento){
        this(distrito,nome,dataInicio,dataFim);
        this.estabelecimento = estabelecimento;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataInicio(Data dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(Data dataFim) {
        this.dataFim = dataFim;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    @Override
    public String toString() {
        return nome;
    }
}
