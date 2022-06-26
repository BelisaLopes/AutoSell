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
    private JList<Cliente> listClientes;
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
    private Veiculo veiculo = null;

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
        btnRegistarVeiculo.addActionListener(this::registarVeiculo);

        btnVeiculos.addActionListener(this::abrirVeiculos);
        btnOficina.addActionListener(this::abrirOficina);
        btnEventos.addActionListener(this::abrirEventos);
        btnClientes.addActionListener(this::abrirClientes);
        btnEstatisticas.addActionListener(this::abrirEstatisticas);
        btnTransacoes.addActionListener(this::abrirTransacoes);
    }

    private void registarVeiculo(ActionEvent actionEvent) {
        System.out.println("Click no registarVeiculo");
        veiculo = JanelaRegistarVeiculo.mostrarVeiculo(this);

        boolean valido = isVeiculoRegistado();
        if(!valido) {
            Erros.mostrarErro(this, Erros.FALHA_REGISTO_VEICULO);
            return;
        }

        DadosAplicacao dados = DadosAplicacao.INSTANCE;

        dados.adicionarVeiculoPorReparar(veiculo);
        dados.adicionarVeiculoAoLocal(dados.getSede(), veiculo);

        veiculo.setDonoAnterior(listClientes.getSelectedValue());
    }

    private void registarCliente(ActionEvent actionEvent) {
        System.out.println("Click no registarCliente");
        Cliente cliente = JanelaRegistarNovoCliente.mostrarCliente(this);

        boolean valido = isClienteRegistado(cliente);
        if(!valido){
            Erros.mostrarErro(this, Erros.FALHA_REGISTO_CLIENTE);
            return;
        }

        DadosAplicacao dados = DadosAplicacao.INSTANCE;
        dados.adicionarCliente(cliente);
    }

    private boolean isClienteRegistado(Cliente cliente) {
        return !(cliente==null);
    }

    private void registarCompra(ActionEvent actionEvent) {
        System.out.println("Click no registarCompra");
        boolean valido = escolheuCliente();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_CLIENTE);
            return;
        }

        valido = !isAlgumCampoVazio();
        if(!valido){
            Erros.mostrarErro(this, Erros.CAMPO_VAZIO);
            return;
        }

        valido = isDataValida(textFieldData.getText());
        if(!valido){
            Erros.mostrarErro(this, Erros.DATA_INICIO_INVALIDA);
            return;
        }

        valido = isPrecoValido(textFieldPreco.getText());
        if(!valido){
            Erros.mostrarErro(this, Erros.PRECO_INVALIDO);
            return;
        }

        valido = isVeiculoRegistado();
        if(!valido){
            Erros.mostrarErro(this, Erros.REGISTAR_VEICULO);
            return;
        }

        adicionarTransacao();
        Sucesso.mostrarSucesso(this, Sucesso.COMPRA_REGISTADA);
        fechar();
        new JanelaOficina();
    }

    private void adicionarTransacao() {
        Cliente cliente = listClientes.getSelectedValue();
        Data data = Data.parseData(textFieldData.getText());
        Float preco = Float.parseFloat(textFieldPreco.getText());
        Local local = (Local) comboBoxLocais.getSelectedItem();

        Transacao transacao = new Transacao(TipoTransacao.COMPRA, cliente,data,preco,veiculo,local);

        cliente.addTransacao(transacao);
    }

    private boolean isVeiculoRegistado() {
        return !(veiculo==null);
    }

    private boolean isPrecoValido(String preco) {
        try{
            Double.parseDouble(preco);
        }catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private boolean isDataValida(String data){
        Data data_final = Data.parseData(data);
        if(data_final == null){
            return false;
        }
        int dia = data_final.getDia();
        int mes = data_final.getMes();
        int ano = data_final.getAno();
        return (dia > 0 && dia < 32 && mes > 0 && mes < 13 && ano > 0);
    }

    private boolean isAlgumCampoVazio() {
        return textFieldData.getText().isEmpty() && textFieldPreco.getText().isEmpty();
    }

    private boolean escolheuCliente() {
        return !listClientes.isSelectionEmpty();
    }

    private void apresentarClientes(ActionEvent actionEvent) {
        System.out.println("Click no apresentarPecas");
        modeloListaClientes.removeAllElements();

        String nome = textFieldNome.getText();
        String nif = textFieldNif.getText();
        if(nome.isEmpty()){
            nome = null;
        }
        if(nif.isEmpty()){
            nif = null;
        }

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
            modeloListaClientes.add(modeloListaClientes.getSize(),cliente);
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
