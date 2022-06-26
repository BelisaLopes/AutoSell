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
import java.util.List;

public class JanelaConsultarPecasStock extends JFrame{
    private JButton btnVeiculos;
    private JButton btnEstatisticas;
    private JButton btnOficina;
    private JButton btnClientes;
    private JButton btnTransacoes;
    private JButton btnEventos;
    private JList listPecas;
    private JButton btnApresentarPecas;
    private JComboBox comboBoxLocais;
    private JTextField textFieldMarca;
    private JTextField textFieldModelo;
    private JTextField textFieldDimensao;
    private JTextField textFieldPreco;
    private JComboBox comboBoxCategorias;
    private JLabel labelStock;
    private JLabel labelQtdMinima;
    private JPanel painelPrincipal;

    private DefaultComboBoxModel modeloComboBoxCategorias;
    private DefaultComboBoxModel modeloComboBoxLocais;
    private DefaultListModel modeloListaPecas;

    public JanelaConsultarPecasStock(){
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
        if(escolheuPeca()) {
            updateLabels();
        }
    }

    private void showStockCmBox(ActionEvent actionEvent) {
        if(escolheuPeca()) {
            updateLabels();
        }
    }

    private void updateLabels() {
        Estabelecimento estabelecimento = (Estabelecimento) comboBoxLocais.getSelectedItem();
        String nomePeca = (String) listPecas.getSelectedValue();
        Peca peca = DadosAplicacao.INSTANCE.getPeca(nomePeca);
        Oficina oficina = estabelecimento.getOficina();
        labelStock.setText(Integer.toString(oficina.getStockPeca(peca)));
        labelQtdMinima.setText(Integer.toString(oficina.getLimiteMinimoPeca(peca)));
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

        List<Peca> pecas = getPecas(categoria);
        if(pecas.isEmpty()){
            Erros.mostrarErro(this, Erros.NENHUM_RESULTADO);
            return;
        }

        atualizarListaPecas(pecas);
    }

    private List<Peca> getPecas(Categoria categoria) {
        String marca = textFieldMarca.getText();
        String modelo = textFieldModelo.getText();
        String dimensao = textFieldDimensao.getText();
        String txtPreco = textFieldPreco.getText();
        double preco = -1;
        if(txtPreco.length()!=0) {
            preco = Double.parseDouble(txtPreco);
        }

        return DadosAplicacao.INSTANCE.getPecas(categoria, marca, modelo, dimensao, preco);
    }

    private void atualizarListaPecas(List<Peca> pecas) {
        for (Peca peca : pecas) {
            modeloListaPecas.add(modeloListaPecas.getSize(),peca.getNome());
        }

        textFieldDimensao.setText("");
        textFieldMarca.setText("");
        textFieldModelo.setText("");
        textFieldPreco.setText("");
    }

    private void initComponents() {
        modeloListaPecas.removeAllElements();
        textFieldDimensao.setText("");
        textFieldMarca.setText("");
        textFieldModelo.setText("");
        textFieldPreco.setText("");

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
        labelQtdMinima.setText("n/a");
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

    public void fechar(){
        setVisible(false);
        dispose();
    }
}
