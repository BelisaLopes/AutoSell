package vista.Transacoes;

import modelo.*;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Sucesso;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.util.List;

public class JanelaRegistarCompra extends JFrame{
    private JButton btnVeiculos;
    private JButton btnOficina;
    private JButton btnEventos;
    private JButton btnTransacoes;
    private JButton btnClientes;
    private JButton btnEstatisticas;
    private JButton btnRegistarVeiculo;
    private JButton btnRegistarCompra;
    private JButton btnRegistarCliente;
    private JList listClientes;
    private JTextField textFieldData;
    private JComboBox comboBoxLocais;
    private JTextField textFieldNome;
    private JTextField textFieldNif;
    private JButton btnApresentarClientes;
    private JTextField textFieldPreco;
    private JButton btnCancelar;
    private JPanel painelPrincipal;

    private DefaultComboBoxModel modeloComboBoxLocais;
    private DefaultListModel modeloListaClientes;

    public JanelaRegistarCompra(){
        setContentPane(painelPrincipal);
        pack();

        modeloComboBoxLocais = new DefaultComboBoxModel();
        modeloListaClientes = new DefaultListModel();

        listClientes.setModel(modeloListaClientes);
        comboBoxLocais.setModel(modeloComboBoxLocais);

        initComponents();

        btnApresentarClientes.addActionListener(this::apresentarClientes);
        btnCancelar.addActionListener(this::cancelar);
        btnRegistarCompra.addActionListener(this::registarCompra);
        btnRegistarCliente.addActionListener(this::registarCliente);

        btnVeiculos.addActionListener(this::abrirVeiculos);
        btnOficina.addActionListener(this::abrirOficina);
        btnEventos.addActionListener(this::abrirEventos);
        btnClientes.addActionListener(this::abrirClientes);
        btnEstatisticas.addActionListener(this::abrirEstatisticas);
        btnTransacoes.addActionListener(this::abrirTransacoes);
    }

    private void registarCliente(ActionEvent actionEvent) {

    }

    private void registarCompra(ActionEvent actionEvent) {
        System.out.println("Click no registarCompra");
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

    private void apresentarClientes(ActionEvent actionEvent) {
        System.out.println("Click no apresentarPecas");
        modeloListaClientes.removeAllElements();

        String nome = textFieldNome.getText();
        String nif = textFieldNif.getText();

        List<Cliente> clientes = DadosAplicacao.INSTANCE.getClientes(nome,nif);
        boolean valido = !isClientesVazio(clientes);
        if(!valido){
            Erros.mostrarErro(this, Erros.NENHUM_RESULTADO);
            return;
        }

        atualizarListaClientes(clientes);
    }

    private boolean isClientesVazio(List<Cliente> clientes) {
        return clientes == null;
    }

    private void atualizarListaClientes(List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            modeloListaClientes.add(modeloListaClientes.getSize(),cliente.getNome());
        }
    }

    private void initComponents() {
        modeloListaClientes.removeAllElements();

        textFieldData.setText("");
        textFieldNif.setText("");
        textFieldNome.setText("");
        textFieldPreco.setText("");

        DadosAplicacao da = DadosAplicacao.INSTANCE;
        List<Filial> filiais = da.getFiliais();
        Sede sede = da.getSede();
        modeloComboBoxLocais.addElement(sede);
        for(Filial filial : filiais){
            modeloComboBoxLocais.addElement(filial);
        }
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
