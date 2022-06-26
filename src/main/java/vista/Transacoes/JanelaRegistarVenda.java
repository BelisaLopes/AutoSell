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
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JanelaRegistarVenda extends JFrame {
    private JButton estatisticasButton;
    private JButton veiculosButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JButton registarNovoClienteButton;
    private JButton registarVeiculoAReceberButton;
    private JTextField TextPreco;
    private JList<Veiculo> listVeiculos;
    private JList<Cliente> listClientes;
    private JComboBox<Estabelecimento> comboBoxFiliais;
    private JTextField textData;
    private JTextField textMarca;
    private JTextField textModelo;
    private JTextField textMatricula;
    private JButton filtrarVeiculosButton;
    private JButton filtrarClientesButton;
    private JCheckBox vendaComRetomaCheckBox;
    private JTextField textNome;
    private JTextField textNIF;
    private JButton confirmarVendaDeVeiculoButton;
    private JButton cancelarButton;
    private JPanel painelPrincipal;
    private JButton escolherVeiculoButton;
    private JButton escolherClienteButton;
    private DefaultComboBoxModel modeloComboBoxFiliais;

    private DefaultListModel modeloListaClientes;

    private DefaultListModel modeloListaVeiculos;

    private Transacao transacao;

    private int valorVeiculoRetoma;

    public JanelaRegistarVenda() {
        setContentPane(painelPrincipal);
        pack();
        setVisible(true);

        veiculosButton.addActionListener(this::btnVeiculosButtonActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaButtonActionPerformed);
        eventosButton.addActionListener(this::btnEventosButtonActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesButtonActionPerformed);
        clientesButton.addActionListener(this::btnClientesButtonActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasButtonActionPerformed);
        registarNovoClienteButton.addActionListener(this::btnRegistarNovoClienteButtonActionPerformed);
        registarVeiculoAReceberButton.addActionListener(this::btnRegistarVeiculoAReceberButtonActionPerformed);
        filtrarVeiculosButton.addActionListener(this::btnFiltrarVeiculosButtonActionPerformed);
        filtrarClientesButton.addActionListener(this::btnFiltrarClientesButtonActionPerformed);
        confirmarVendaDeVeiculoButton.addActionListener(this::btnConfirmarVendaDeVeiculoButtonActionPerformed);
        cancelarButton.addActionListener(this::btnCancelarButtonActionPerformed);
        escolherVeiculoButton.addActionListener(this::btnEscolherVeiculoButtonActionPerformed);
        escolherClienteButton.addActionListener(this::btnEscolherClienteButtonActionPerformed);
        modeloComboBoxFiliais = new DefaultComboBoxModel();
        comboBoxFiliais.setModel(modeloComboBoxFiliais);
        modeloListaClientes = new DefaultListModel();
        listClientes.setModel(modeloListaClientes);
        modeloListaVeiculos = new DefaultListModel();
        listVeiculos.setModel(modeloListaVeiculos);
        initComponents();

    }

    private void initComponents() {
        ArrayList<Filial> listafiliais = DadosAplicacao.INSTANCE.getFiliais();
        for (Filial filial : listafiliais) {
            modeloComboBoxFiliais.addElement(filial);
        }
    }

    private void btnRegistarNovoClienteButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnRegistarNovoClienteButtonActionPerformed");

        Cliente cliente = JanelaRegistarNovoCliente.mostrarCliente(this);
        DadosAplicacao dados = DadosAplicacao.INSTANCE;
        dados.adicionarCliente(cliente);
    }

    private void btnRegistarVeiculoAReceberButtonActionPerformed(ActionEvent evt) {
        boolean isSelecionado = vendaComRetomaCheckBox.isSelected();
        if(isSelecionado) {
            Veiculo veiculo = JanelaRegistarVeiculo.mostrarVeiculo(this);
            DadosAplicacao dados = DadosAplicacao.INSTANCE;
            dados.adicionarVeiculoPorReparar(veiculo);
            dados.adicionarVeiculoAoLocal(dados.getSede(), veiculo);
            valorVeiculoRetoma = veiculo.getValorVeiculo();
        }
        else{
            JOptionPane.showMessageDialog(this, "Não é possível registar um veículo a receber sem a opção de venda com retoma");
        }
    }

    private void btnFiltrarClientesButtonActionPerformed(ActionEvent evt) {
        String nome = textNome.getText();
        boolean valido = isNomeValido(nome);
        if(!valido){
            Erros.mostrarErro(this, Erros.NOME_INVALIDO);
            return;
        }

        String nif = textNIF.getText();
        valido = isNIFValido(nif);
        if(!valido){
            Erros.mostrarErro(this, Erros.NIF_INVALIDO);
            return;
        }

        DadosAplicacao dados = DadosAplicacao.INSTANCE;
        List<Cliente> clientes = dados.getClientes(nome, nif);
        if(clientes == null){
            modeloListaClientes.removeAllElements();
            Erros.mostrarErro(this, Erros.NENHUM_RESULTADO);
            return;
        }
        modeloListaClientes.removeAllElements();
        for (Cliente cliente : clientes) {
            modeloListaClientes.add(modeloListaClientes.getSize(),cliente);
        }
    }

    private boolean isNomeValido(String nome){
        return !(nome.trim().length() < 2 || nome.trim().length() > 100);
    }

    private boolean isNIFValido(String nif){
        return !(nif.trim().length() < 9 || nif.trim().length() > 9 || !nif.matches("(1)?[0-9]{8}"));
    }

    private void btnFiltrarVeiculosButtonActionPerformed(ActionEvent evt) {
        String marca = textMarca.getText();
        boolean valido = isMarcaValida(marca);
        if(!valido){
            Erros.mostrarErro(this, Erros.MARCA_INVALIDA);
            return;
        }

        String modelo = textModelo.getText();
        valido = isModeloValido(modelo);
        if(!valido){
            Erros.mostrarErro(this, Erros.MODELO_INVALIDO);
            return;
        }

        String matricula = textMatricula.getText();
        valido = isMatriculaValida(matricula);
        if(!valido){
            Erros.mostrarErro(this, Erros.MATRICULA_INVALIDA);
            return;
        }

        DadosAplicacao dados = DadosAplicacao.INSTANCE;
        List<Veiculo> veiculos = dados.getVeiculosProntosParaVenda();
        if(veiculos == null){
            modeloListaVeiculos.removeAllElements();
            Erros.mostrarErro(this, Erros.NENHUM_RESULTADO);
            return;
        }
        modeloListaVeiculos.removeAllElements();
        for (Veiculo veiculo : veiculos) {
            modeloListaVeiculos.add(modeloListaVeiculos.getSize(),veiculo);
        }
    }

    private boolean isMarcaValida(String marca){
        return !(marca.trim().length() < 2 || marca.trim().length() > 50);
    }

    private boolean isModeloValido(String modelo){
        return !(modelo.trim().length() < 2 || modelo.trim().length() > 50);
    }

    private boolean isMatriculaValida(String matricula){
        return (matricula.trim().matches("^([A-Z]{2})[-]([0-9]{2})[-]([A-Z]{2})$")||
                matricula.trim().matches("^([0-9]{2})[-]([0-9]{2})[-]([A-Z]{2})$") ||
                matricula.trim().matches("^([A-Z]{2})[-]([0-9]{2})[-]([0-9]{2})$") ||
                matricula.trim().matches("^([0-9]{2})[-]([A-Z]{2})[-]([0-9]{2})$"));
    }

    private void btnConfirmarVendaDeVeiculoButtonActionPerformed(ActionEvent evt) {
        if(listVeiculos.isSelectionEmpty()){
            Erros.mostrarErro(this, Erros.SELECIONAR_VEICULO_VENDER);
            return;
        }
        if(listClientes.isSelectionEmpty()){
            Erros.mostrarErro(this, Erros.SELECIONAR_CLIENTE_COMPRAR_VEICULO);
            return;
        }
        Float precoTransacao = null;
        String data = textData.getText();
        if(data.isEmpty()){
            Erros.mostrarErro(this, Erros.DATA_INICIO_INVALIDA); //MUDAR PARA DATA ATUAL INVALIDA
            return;
        }
        boolean valido = isDataVendaValida(data);
        if(!valido){
            Erros.mostrarErro(this, Erros.DATA_INICIO_INVALIDA); //MUDAR PARA DATA ATUAL INVALIDA
            return;
        }

        Data dataTransacao = Data.parseData(data);

        Veiculo veiculoParaVenda = listVeiculos.getSelectedValue();
        Cliente cliente = listClientes.getSelectedValue();

        Estabelecimento e = (Estabelecimento) modeloComboBoxFiliais.getSelectedItem();

        boolean isSelecionado = vendaComRetomaCheckBox.isSelected();
        if(isSelecionado) {
            precoTransacao = (float) veiculoParaVenda.getValorVeiculo() - valorVeiculoRetoma;
            TextPreco.setText(String.valueOf(precoTransacao));
        }
        else{
            precoTransacao = (float) veiculoParaVenda.getValorVeiculo();
            TextPreco.setText(String.valueOf(precoTransacao));;
        }

        transacao = new Transacao(TipoTransacao.VENDA, cliente, dataTransacao, precoTransacao, veiculoParaVenda, e);

        Sucesso.mostrarSucesso(this, Sucesso.VENDA_VEICULO_EFETUADA);

        cliente.addTransacao(transacao);

        DadosAplicacao dados = DadosAplicacao.INSTANCE;
        dados.adicionarVeiculoVendido(veiculoParaVenda);
        dados.removerVeiculoVendido(veiculoParaVenda);

        fechar();

    }

    private void fechar(){
        setVisible(false);
        dispose();
        JanelaTransacoes j = new JanelaTransacoes();
        j.setVisible(true);
    }

    private boolean isDataVendaValida(String data){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataVenda = LocalDate.parse(data, dateFormat);
        LocalDate dataAtual = LocalDate.now();
        if(dataVenda == null){
            return false;
        }
        return dataVenda.isEqual(dataAtual);
    }

    private void btnCancelarButtonActionPerformed(ActionEvent evt) {
        fechar();
    }

    private void btnEscolherVeiculoButtonActionPerformed(ActionEvent evt) {
        boolean valido = !listVeiculos.isSelectionEmpty();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_VEICULO_VENDER);
            return;
        }
    }

    private void btnEscolherClienteButtonActionPerformed(ActionEvent evt) {
        boolean valido = !listClientes.isSelectionEmpty();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_CLIENTE);
            return;
        }
    }

    private void btnVeiculosButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaVeiculos j = new JanelaVeiculos();
        j.setVisible(true);
    }

    private void btnOficinaButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaOficina j = new JanelaOficina();
        j.setVisible(true);
    }

    private void btnEventosButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaEventos j = new JanelaEventos();
        j.setVisible(true);
    }

    private void btnTransacoesButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaTransacoes j = new JanelaTransacoes();
        j.setVisible(true);
    }

    private void btnClientesButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaClientes j = new JanelaClientes();
        j.setVisible(true);
    }

    private void btnEstatisticasButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaEstatistica j = new JanelaEstatistica();
        j.setVisible(true);
    }
}
