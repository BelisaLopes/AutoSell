package vista.Transacoes;

import vista.Eventos.JanelaEventos;
import vista.Eventos.JanelaRegistarEvento;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaTransacoes extends JFrame {
    private JButton veiculosButton;
    private JButton estatisticasButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JButton registarCompraButton;
    private JButton registarVendaButton;
    private JButton consultarHistóricoDeTransaçõesButton;
    private JPanel painelPrincipal;

    public JanelaTransacoes() {
        setContentPane(painelPrincipal);
        pack();
        setVisible(true);

        veiculosButton.addActionListener(this::btnVeiculosButtonActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaButtonActionPerformed);
        eventosButton.addActionListener(this::btnEventosButtonActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesButtonActionPerformed);
        clientesButton.addActionListener(this::btnClientesButtonActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasButtonActionPerformed);
        registarCompraButton.addActionListener(this::btnRegistarCompraButtonActionPerformed);
        registarVendaButton.addActionListener(this::btnRegistarVendaButtonActionPerformed);
        consultarHistóricoDeTransaçõesButton.addActionListener(this::btnConsultarHistóricoDeTransaçõesButtonActionPerformed);

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

    private void btnRegistarCompraButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnRegistarCompraButtonActionPerformed");
    }

    private void btnRegistarVendaButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnRegistarVendaButtonActionPerformed");

        this.setVisible(false);
        dispose();

        JanelaRegistarVenda j = new JanelaRegistarVenda();
        j.setVisible(true);
    }

    private void btnConsultarHistóricoDeTransaçõesButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnConsultarHistóricoDeTransaçõesButtonActionPerformed");
    }

    public static void main(String[] args) {
        new JanelaTransacoes();
    }
}
