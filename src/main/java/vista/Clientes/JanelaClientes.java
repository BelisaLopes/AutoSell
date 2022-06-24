package vista.Clientes;


import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaClientes extends JFrame{
    private JButton veiculosButton;
    private JButton estatisticasButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JButton consultarClientesButton;
    private JButton históricoDeTransaçõesPorButton;
    private JButton atualizarClientesButton;
    private JPanel painelPrincipal;

    public JanelaClientes() {
        setContentPane(painelPrincipal);
        pack();
        setLocationRelativeTo(null); //fazer isto só na janela principal
        setVisible(true);

        veiculosButton.addActionListener(this::btnVeiculosButtonActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaButtonActionPerformed);
        eventosButton.addActionListener(this::btnEventosButtonActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesButtonActionPerformed);
        clientesButton.addActionListener(this::btnClientesButtonActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasButtonActionPerformed);

        consultarClientesButton.addActionListener(this::btnConsultarClientesActionPerformed);
        históricoDeTransaçõesPorButton.addActionListener(this::btnHistoricoDeTransacoesPorActionPerformed);
        atualizarClientesButton.addActionListener(this::btnAtualizarClientesActionPerformed);
    }


    private void btnConsultarClientesActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        dispose();

        JanelaConsultarCliente j = new JanelaConsultarCliente();
        //j.setLocationRelativeTo(this);
        //j.setVisible(true);
    }

    private void btnHistoricoDeTransacoesPorActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        dispose();

        JanelaHistoricoTransacoesPorCliente j = new JanelaHistoricoTransacoesPorCliente();
        //j.setLocationRelativeTo(this);
        //j.setVisible(true);
    }

    private void btnAtualizarClientesActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        dispose();

        JanelaAtualizarCliente j = new JanelaAtualizarCliente();
        j.setLocationRelativeTo(this);
        j.setVisible(true);
    }

    private void btnVeiculosButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaVeiculos j = new JanelaVeiculos();
        j.setVisible(true);
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

    public static void main(String[] args) {
        new JanelaClientes();
    }
}
