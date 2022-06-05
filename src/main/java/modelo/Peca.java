package modelo;

public class Peca {
    private String nome;
    private String marca;
    private String modelo;
    private int dimensao;
    private int preco;
    private Categoria categoria;

    public Peca(String nome, String marca, String modelo, int dimensao, int preco, Categoria categoria) {
        this.nome = nome;
        this.marca = marca;
        this.modelo = modelo;
        this.dimensao = dimensao;
        this.preco = preco;
        this.categoria = categoria;
        categoria.adicionarPeca(this);
    }

    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getDimensao() {
        return dimensao;
    }

    public int getPreco() {
        return preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Peca";
    }
}
