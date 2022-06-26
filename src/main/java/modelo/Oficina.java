package modelo;

import java.util.Hashtable;

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

    public void atualizarStockPeca(Peca peca, int quantidade) {
        int q = stockPecas.get(peca);
        stockPecas.put(peca, q - quantidade);
    }

    public void adicionarStockPeca(Peca peca, int quantidade) {
        int quantidadeAtual = stockPecas.get(peca);
        stockPecas.put(peca, quantidadeAtual+quantidade);
    }

    public int getStockPeca(Peca peca) {
        return stockPecas.get(peca);
    }

    public int getLimiteMinimoPeca(Peca peca) {
        return limiteMinimoStockPecas.get(peca);
    }

    //colocar nos DadosAplicacao que se reparou um veiculo de certa marca e modelo

}
