package vista.Transacoes;

import modelo.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
    private JList listVeiculos;
    private JList listClientes;
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

    private void btnFiltrarVeiculosButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnFiltrarVeiculosButtonActionPerformed");
    }

    private void btnFiltrarClientesButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnFiltrarClientesButtonActionPerformed");
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
