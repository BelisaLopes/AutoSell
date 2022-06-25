package vista.Transacoes;

import modelo.*;
import vista.Erros;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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

    private void btnVeiculosButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnVeiculosButtonActionPerformed");
    }

    private void btnOficinaButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnOficinaButtonActionPerformed");
    }

    private void btnEventosButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnEventosButtonActionPerformed");
    }

    private void btnTransacoesButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnTransacoesButtonActionPerformed");
    }

    private void btnClientesButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnClientesButtonActionPerformed");
    }

    private void btnEstatisticasButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnEstatisticasButtonActionPerformed");
    }

    private void btnRegistarNovoClienteButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnRegistarNovoClienteButtonActionPerformed");

        Cliente cliente = JanelaRegistarNovoCliente.mostrarCliente(this);
        DadosAplicacao dados = DadosAplicacao.INSTANCE;
        dados.adicionarCliente(cliente);
    }

    private void btnRegistarVeiculoAReceberButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnRegistarVeiculoAReceberButtonActionPerformed");

        Veiculo veiculo = JanelaRegistarVeiculo.mostrarVeiculo(this);
        DadosAplicacao dados = DadosAplicacao.INSTANCE;
        dados.adicionarVeiculoPorReparar(veiculo);
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
        System.out.println("Click no btnConfirmarVendaDeVeiculoButtonActionPerformed");
    }

    private void btnCancelarButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnCancelarButtonActionPerformed");
    }

    private void btnEscolherVeiculoButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnEscolherVeiculoButtonActionPerformed");
    }

    private void btnEscolherClienteButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnEscolherClienteButtonActionPerformed");
    }
}
