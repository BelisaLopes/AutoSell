package modelo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class Oficina {
    private Hashtable<Peca, Integer> stockPecas;
    private Hashtable<Peca, Integer> limiteMinimoStockPecas;


    public Oficina() {
        stockPecas = new Hashtable<>();
        limiteMinimoStockPecas = new Hashtable<>();
    }

    public void registarPeca(Peca peca, int qtdMin) {
        stockPecas.put(peca, qtdMin);
        limiteMinimoStockPecas.put(peca, qtdMin);
    }

    public void atualizarLimiteMinimoPeca(Peca peca, int quantidade) {
        limiteMinimoStockPecas.put(peca, quantidade);
    }

    public void atualizarStockPeca(Peca peca, int quantidadeUsada) {
        int q = stockPecas.get(peca);
        stockPecas.put(peca, q - quantidadeUsada);
    }

    public void adicionarStockPeca(Peca peca, int quantidade) {
        int quantidadeAtual = stockPecas.get(peca);
        stockPecas.put(peca, quantidadeAtual+quantidade);
    }

    public boolean isRuturaStock (Peca peca){
        return getStockPeca(peca) < getLimiteMinimoPeca(peca);
    }

    public int getStockPeca(Peca peca) {
        return stockPecas.get(peca);
    }

    public int getLimiteMinimoPeca(Peca peca) {
        return limiteMinimoStockPecas.get(peca);
    }

    public List<Peca> getPecasCategoria(Categoria categoria){
        Enumeration<Peca> pecas = stockPecas.keys();
        List<Peca> pecasCategoria = new ArrayList<>();
        while(pecas.hasMoreElements()){
            Peca p = pecas.nextElement();
            if(p.getCategoria() == categoria){
                pecasCategoria.add(p);
            }
        }
        return pecasCategoria.size() == 0 ? null : pecasCategoria;
    }

    //colocar nos DadosAplicacao que se reparou um veiculo de certa marca e modelo

}
