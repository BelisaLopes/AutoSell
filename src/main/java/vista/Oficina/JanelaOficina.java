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
        abrir();

        btnRegistarCategoria.addActionListener(this::registarCategoria);
        btnRemoverCategoria.addActionListener(this::removerCategoria);
        btnRegistarPeca.addActionListener(this::registarPeca);
        btnRegistarEncomenda.addActionListener(this::registarEncomenda);
        btnConsultar.addActionListener(this::consultarStock);
        btnAtualizarQuantidadeMin.addActionListener(this::atualizarQuantidade);
        btnTransferir.addActionListener(this::transferirPecas);

        btnVeiculos.addActionListener(this::abrirVeiculos);
        btnOficina.addActionListener(this::abrirOficina);
        btnEventos.addActionListener(this::abrirEventos);
        btnClientes.addActionListener(this::abrirClientes);
        btnEstatisticas.addActionListener(this::abrirEstatisticas);
        btnTransacoes.addActionListener(this::abrirTransacoes);
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
        System.out.println("Click no abrirOficina");
        fechar();
        new JanelaOficina();
    }

    private void abrirVeiculos(ActionEvent actionEvent) {
        System.out.println("Click no abrirVeiculos");
        fechar();
        new JanelaVeiculos();
    }

    private void transferirPecas(ActionEvent actionEvent) {
        System.out.println("Click no transferirPecas");
        boolean valido = existemPecas();
        if(!valido){
            Erros.mostrarErro(this, Erros.NAO_EXISTEM_PECAS);
            return;
        }

        JanelaTransferirPecas j = new JanelaTransferirPecas();
        abrir(j);
        fechar();
    }

    private void atualizarQuantidade(ActionEvent actionEvent) {
        System.out.println("Click no atualizarQuantidade");
        boolean valido = existemPecas();
        if(!valido){
            Erros.mostrarErro(this, Erros.NAO_EXISTEM_PECAS);
            return;
        }

        JanelaQuantidadeMinimaPecas j = new JanelaQuantidadeMinimaPecas();
        abrir(j);
        fechar();
    }

    private void consultarStock(ActionEvent actionEvent) {
        System.out.println("Click no consultarStock");
        boolean valido = existemPecas();
        if(!valido){
            Erros.mostrarErro(this, Erros.NAO_EXISTEM_PECAS);
            return;
        }

        JanelaConsultarPecasStock j = new JanelaConsultarPecasStock();
        abrir(j);
        fechar();
    }

    private void registarEncomenda(ActionEvent actionEvent) {
        System.out.println("Click no registarEncomenda");
        boolean valido = existemPecas();
        if(!valido){
            Erros.mostrarErro(this, Erros.NAO_EXISTEM_PECAS);
            return;
        }

        JanelaRegistarEncomendaPecas j = new JanelaRegistarEncomendaPecas();
        abrir(j);
        fechar();
    }

    private boolean existemPecas() {
        return DadosAplicacao.INSTANCE.existemPecas();
    }

    public void registarCategoria(ActionEvent actionEvent){
        System.out.println("Click no registarCategoriaButton");
        JanelaAdicionarCategoria j = new JanelaAdicionarCategoria();
        abrir(j);
        fechar();
    }

    private void removerCategoria(ActionEvent actionEvent) {
        System.out.println("Click no removerCategoria");
        boolean valido = existemCategoriasSemPecas();
        if(!valido){
            Erros.mostrarErro(this, Erros.CATEGORIA_SEM_PECA_NAO_EXISTE);
            return;
        }

        JanelaRemoverCategoriaPeca j = new JanelaRemoverCategoriaPeca();
        abrir(j);
        fechar();
    }

    private boolean existemCategoriasSemPecas() {
        return DadosAplicacao.INSTANCE.existemCategoriasSemPecas();
    }

    private void registarPeca(ActionEvent actionEvent) {
        System.out.println("Click no registarPeca");
        boolean valido = existemCategorias();
        if(!valido){
            Erros.mostrarErro(this, Erros.NAO_EXISTEM_CATEGORIAS);
            return;
        }

        JanelaRegistarPeca j = new JanelaRegistarPeca();
        abrir(j);
        fechar();
    }

    private boolean existemCategorias() {
        return DadosAplicacao.INSTANCE.existemCategorias();
    }

    public void fechar(){
        setVisible(false);
        dispose();
    }

    private void abrir(JFrame j) {
        j.setLocationRelativeTo(this);
        j.setVisible(true);
    }


    private void abrir() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
