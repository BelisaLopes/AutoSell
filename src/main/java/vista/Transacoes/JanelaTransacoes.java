package vista.Transacoes;

import vista.Clientes.JanelaClientes;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Veiculos.JanelaVeiculos;

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
        abrir();

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

    private void abrir() {
        setLocationRelativeTo(null);
        setVisible(true);
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

    private void btnRegistarCompraButtonActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        dispose();
        JanelaRegistarCompra j = new JanelaRegistarCompra();
        //j.setVisible(true);
    }

    private void btnRegistarVendaButtonActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        dispose();
        JanelaRegistarVenda j = new JanelaRegistarVenda();
        j.setVisible(true);
    }

    private void btnConsultarHistóricoDeTransaçõesButtonActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        dispose();
        JanelaHistoricoTransacoes j = new JanelaHistoricoTransacoes();
        //j.setVisible(true);
    }

    public static void main(String[] args) {
        new JanelaTransacoes();
    }
}
