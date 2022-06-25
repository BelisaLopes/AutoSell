package modelo;

public class Peca {
    private String nome;
    private String marca;
    private String modelo;
    private String dimensao;
    private double preco;
    private Categoria categoria;

    public Peca(String nome, String marca, String modelo, String dimensao, double preco, Categoria categoria) {
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

    public String getDimensao() {
        return dimensao;
    }

    public double getPreco() {
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
