package vista.Oficina;

import modelo.*;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Sucesso;
import vista.Transacoes.JanelaTransacoes;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class JanelaRegistarEncomendaPecas extends JFrame{
    private JButton btnEstatisticas;
    private JButton btnVeiculos;
    private JButton btnClientes;
    private JButton btnTransacoes;
    private JButton btnEventos;
    private JButton btnOficina;
    private JList listPecas;
    private JComboBox comboBoxLocais;
    private JTextField textFieldQtdEncomendada;
    private JButton btnRegistar;
    private JButton btnApresentarPecas;
    private JComboBox comboBoxCategorias;
    private JButton btnCancelar;
    private JPanel painelPrincipal;
    private JLabel labelStock;

    private DefaultComboBoxModel modeloComboBoxCategorias;
    private DefaultComboBoxModel modeloComboBoxLocais;
    private DefaultListModel modeloListaPecas;

    public JanelaRegistarEncomendaPecas(){
        setContentPane(painelPrincipal);
        pack();

        modeloComboBoxCategorias = new DefaultComboBoxModel();
        modeloComboBoxLocais = new DefaultComboBoxModel();
        modeloListaPecas = new DefaultListModel();

        listPecas.setModel(modeloListaPecas);
        comboBoxCategorias.setModel(modeloComboBoxCategorias);
        comboBoxLocais.setModel(modeloComboBoxLocais);

        initComponents();

        btnApresentarPecas.addActionListener(this::apresentarPecas);
        btnCancelar.addActionListener(this::cancelar);
        btnRegistar.addActionListener(this::registarEncomenda);
        comboBoxLocais.addActionListener(this::showStockCmBox);
        listPecas.addListSelectionListener(this::showStockList);

        btnVeiculos.addActionListener(this::abrirVeiculos);
        btnOficina.addActionListener(this::abrirOficina);
        btnEventos.addActionListener(this::abrirEventos);
        btnClientes.addActionListener(this::abrirClientes);
        btnEstatisticas.addActionListener(this::abrirEstatisticas);
        btnTransacoes.addActionListener(this::abrirTransacoes);
    }

    private void showStockList(ListSelectionEvent listSelectionEvent) {
        Estabelecimento estabelecimento = (Estabelecimento) comboBoxLocais.getSelectedItem();
        String nomePeca = (String) listPecas.getSelectedValue();
        Peca peca = DadosAplicacao.INSTANCE.getPeca(nomePeca);
        labelStock.setText(Integer.toString(estabelecimento.getOficina().getStockPeca(peca)));
    }

    private void showStockCmBox(ActionEvent actionEvent) {
        if(escolheuPeca()) {
            Estabelecimento estabelecimento = (Estabelecimento) comboBoxLocais.getSelectedItem();
            String nomePeca = (String) listPecas.getSelectedValue();
            Peca peca = DadosAplicacao.INSTANCE.getPeca(nomePeca);
            labelStock.setText(Integer.toString(estabelecimento.getOficina().getStockPeca(peca)));
        }
    }

    private void registarEncomenda(ActionEvent actionEvent) {
        System.out.println("Click no registarEncomenda");
        boolean valido = escolheuPeca();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_PECA);
            return;
        }

        valido = !isQtdCampoVazio();
        if(!valido){
            Erros.mostrarErro(this, Erros.CAMPO_VAZIO);
            return;
        }

        String textQtdEncomendada = textFieldQtdEncomendada.getText();
        valido = isQuantidadeValida(textQtdEncomendada);
        if(!valido){
            Erros.mostrarErro(this, Erros.QUANTIDADE_INVALIDA);
            return;
        }

        adicionarStock(textQtdEncomendada);
        Sucesso.mostrarSucesso(this, Sucesso.PECA_ENCOMENDADA);
        atualizarLabelStock();
    }

    private void atualizarLabelStock() {
        Estabelecimento estabelecimento = (Estabelecimento) comboBoxLocais.getSelectedItem();

        String nomePeca = (String) listPecas.getSelectedValue();
        Peca peca = DadosAplicacao.INSTANCE.getPeca(nomePeca);

        labelStock.setText(Integer.toString(estabelecimento.getOficina().getStockPeca(peca)));
    }

    private void adicionarStock(String qtdEncomendada) {
        Estabelecimento estabelecimento = (Estabelecimento) comboBoxLocais.getSelectedItem();

        String nomePeca = (String) listPecas.getSelectedValue();
        Peca peca = DadosAplicacao.INSTANCE.getPeca(nomePeca);

        int qtdEncomendadaInt = Integer.parseInt(qtdEncomendada);

        estabelecimento.getOficina().adicionarStockPeca(peca,qtdEncomendadaInt);
    }

    private boolean isQuantidadeValida(String qtdEncomendada) {
        try{
            Integer.parseInt(qtdEncomendada);
        }catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private boolean isQtdCampoVazio() {
        return textFieldQtdEncomendada.getText().length()==0;
    }

    private boolean escolheuPeca() {
        return !listPecas.isSelectionEmpty();
    }

    private void apresentarPecas(ActionEvent actionEvent) {
        System.out.println("Click no apresentarPecas");
        modeloListaPecas.removeAllElements();

        Categoria categoria;
        try{
            categoria = (Categoria) comboBoxCategorias.getSelectedItem();
        }catch (Exception ex){
            categoria = null;
        }

        List<Peca> pecas = DadosAplicacao.INSTANCE.getPecas(categoria);
        if(pecas.isEmpty()){ //categoria sem pecas
            Erros.mostrarErro(this, Erros.CATEGORIA_SEM_PECAS);
            return;
        }

        atualizarListaPecas(pecas);
    }

    private void atualizarListaPecas(List<Peca> pecas) {
        for (Peca peca : pecas) {
            modeloListaPecas.add(modeloListaPecas.getSize(),peca.getNome());
        }
    }

    private void initComponents() {
        modeloListaPecas.removeAllElements();

        DadosAplicacao da = DadosAplicacao.INSTANCE;
        List<Categoria> catalogo = da.getCatalogo();
        modeloComboBoxCategorias.addElement("Todas as Categorias");
        for(Categoria categoria : catalogo){
            modeloComboBoxCategorias.addElement(categoria);
        }
        List<Filial> filiais = da.getFiliais();
        Sede sede = da.getSede();
        modeloComboBoxLocais.addElement(sede);
        for(Filial filial : filiais){
            modeloComboBoxLocais.addElement(filial);
        }

        labelStock.setText("n/a");
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

    private void cancelar(ActionEvent actionEvent) {
        System.out.println("Click no cancelar");
        fechar();
        new JanelaOficina();
    }

    public void fechar(){
        setVisible(false);
        dispose();
    }
}
