package modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private String nome;
    private List<Peca> pecas;
    
    public Categoria(String nome) {
        this.nome = nome;
        this.pecas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Peca> getPecas() {
        return pecas;
    }

    public void adicionarPeca(Peca peca) {
        pecas.add(peca);
    }

    @Override
    public String toString() {
        return nome;
    }
}
