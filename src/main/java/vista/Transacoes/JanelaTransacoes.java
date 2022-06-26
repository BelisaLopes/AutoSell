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
    private JButton btnRegistarCompra;
    private JButton registarVendaButton;
    private JButton btnConsultarHistorico;
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

        btnConsultarHistorico.addActionListener(this::consultarHistorico);
        btnRegistarCompra.addActionListener(this::registarCompra);

        registarVendaButton.addActionListener(this::btnRegistarVendaButtonActionPerformed);

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

    private void btnRegistarVendaButtonActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        dispose();
        JanelaRegistarVenda j = new JanelaRegistarVenda();
        j.setVisible(true);
    }

    private void consultarHistorico(ActionEvent actionEvent) {
        JanelaHistoricoTransacoes j = new JanelaHistoricoTransacoes();
        abrir(j);
        fechar();
    }

    private void registarCompra(ActionEvent actionEvent) {
        JanelaRegistarCompra j = new JanelaRegistarCompra();
        abrir(j);
        fechar();
    }

    private void fechar() {
        this.setVisible(false);
        dispose();
    }


    private void abrir(JFrame j) {
        j.setLocationRelativeTo(this);
        j.setVisible(true);
    }

    public static void main(String[] args) {
        new JanelaTransacoes();
    }
}
