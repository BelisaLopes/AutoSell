package vista.Transacoes;

import modelo.Cliente;
import modelo.DadosAplicacao;
import modelo.Veiculo;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaRegistarVenda extends JFrame {
    private JButton estatisticasButton;
    private JButton veiculosButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JButton registarNovoClienteButton;
    private JButton registarVeiculoAReceberButton;
    private JTextField TextFieldPreco;
    private JList listVeiculos;
    private JList listClientes;
    private JComboBox comboBoxFiliais;
    private JTextField textData; //adicionei pois estava a dar erro a dizer que não existia
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
}
