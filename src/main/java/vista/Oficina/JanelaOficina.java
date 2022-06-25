package vista.Oficina;

import modelo.DadosAplicacao;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Transacoes.JanelaTransacoes;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaOficina extends JFrame {
    private JButton btnVeiculos;
    private JButton btnOficina;
    private JButton btnEventos;
    private JButton btnClientes;
    private JButton btnEstatisticas;
    private JButton btnTransacoes;
    private JButton btnConsultar;
    private JButton btnRegistarPeca;
    private JButton btnTransferir;
    private JButton btnAtualizarQuantidadeMin;
    private JButton btnRegistarEncomenda;
    private JButton btnRegistarCategoria;
    private JButton btnRemoverCategoria;
    private JPanel panel;
    private JPanel painelPrincipal;

    public JanelaOficina(){
        setContentPane(painelPrincipal);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        btnRegistarCategoria.addActionListener(this::registarCategoria);
        btnRemoverCategoria.addActionListener(this::removerCategoria);

        btnVeiculos.addActionListener(this::abrirVeiculos);
        btnOficina.addActionListener(this::abrirOficina);
        btnEventos.addActionListener(this::abrirEventos);
        btnClientes.addActionListener(this::abrirClientes);
        btnEstatisticas.addActionListener(this::abrirEstatisticas);
        btnTransacoes.addActionListener(this::abrirTransacoes);
    }

    public static void main(String[] args) {
        new JanelaOficina();
    }

    private void abrirTransacoes(ActionEvent actionEvent) {
        System.out.println("Click no abrirTransacoes");
        fechar();
        new JanelaTransacoes();
    }

    private void abrirEstatisticas(ActionEvent actionEvent) {
        System.out.println("Click no abrirEstatisticas");
        fechar();
        new JanelaEstatistica();
    }

    private void abrirClientes(ActionEvent actionEvent) {
        System.out.println("Click no abrirClientes");
        fechar();
        new JanelaClientes();
    }

    private void abrirEventos(ActionEvent actionEvent) {
        System.out.println("Click no abrirEventos");
        fechar();
        new JanelaEventos();
    }

    private void abrirOficina(ActionEvent actionEvent) {
        // TODO - manter??
        System.out.println("Click no abrirOficina");
        fechar();
        new JanelaOficina();
    }

    private void abrirVeiculos(ActionEvent actionEvent) {
        System.out.println("Click no abrirVeiculos");
        fechar();
        new JanelaVeiculos();
    }

    public void registarCategoria(ActionEvent actionEvent){
        System.out.println("Click no registarCategoriaButton");
        fechar();
        JanelaAdicionarCategoria j = new JanelaAdicionarCategoria();
        j.setLocationRelativeTo(this);
        j.setVisible(true);
    }

    private void removerCategoria(ActionEvent actionEvent) {
        System.out.println("Click no removerCategoria");
        boolean valido = existemCategoriasSemPecas();
        if(!valido){
            Erros.mostrarErro(this, Erros.CATEGORIA_SEM_PECA_NAO_EXISTE);
            return;
        }
        fechar();
        JanelaRemoverCategoriaPeca j = new JanelaRemoverCategoriaPeca();
        j.setLocationRelativeTo(this);
        j.setVisible(true);
    }

    private boolean existemCategoriasSemPecas() {
        return DadosAplicacao.INSTANCE.existemCategoriasSemPecas();
    }

    public void fechar(){
        setVisible(false);
        dispose();
    }
}
