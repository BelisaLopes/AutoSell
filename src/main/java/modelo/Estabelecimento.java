package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Estabelecimento extends Local{
    private int capacidadeMaximaVeiculos;
    private Oficina oficina;

    public Estabelecimento(Distrito distrito, int capacidadeMaximaVeiculos) {
        super(distrito);
        this.capacidadeMaximaVeiculos = capacidadeMaximaVeiculos;
        oficina = new Oficina();
    }

    public int getCapacidadeMaximaVeiculos() {
        return capacidadeMaximaVeiculos;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void registarPecaNaOficina(Peca peca, int qtdMin) {
        oficina.registarPeca(peca,qtdMin);
    }
}
