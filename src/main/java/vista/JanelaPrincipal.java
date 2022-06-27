package vista;

import vista.Clientes.JanelaClientes;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Transacoes.JanelaTransacoes;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaPrincipal extends JFrame {
    private JButton veiculosButton;
    private JButton oficinaButton;
    private JButton eventosButton;
    private JButton transaçõesButton;
    private JButton estatisticasButton;
    private JButton clientesButton;
    private JPanel painelPrincipal;

    public JanelaPrincipal(){
        setContentPane(painelPrincipal);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        veiculosButton.addActionListener(this::btnVeiculosButtonActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaButtonActionPerformed);
        eventosButton.addActionListener(this::btnEventosButtonActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesButtonActionPerformed);
        clientesButton.addActionListener(this::btnClientesButtonActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasButtonActionPerformed);

    }

    private void btnVeiculosButtonActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        dispose();

        JanelaVeiculos jv = new JanelaVeiculos();
        jv.setVisible(true);
    }

    private void btnOficinaButtonActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        dispose();

        JanelaOficina j = new JanelaOficina();
        j.setVisible(true);
    }

    private void btnEventosButtonActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        dispose();

        JanelaEventos j = new JanelaEventos();
        j.setVisible(true);
    }

    private void btnTransacoesButtonActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        dispose();

        JanelaTransacoes j = new JanelaTransacoes();
        j.setVisible(true);
    }

    private void btnClientesButtonActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        dispose();

        JanelaClientes j = new JanelaClientes();
        j.setVisible(true);
    }

    private void btnEstatisticasButtonActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        dispose();

        JanelaEstatistica j = new JanelaEstatistica();
        j.setVisible(true);
    }

    public static void main(String[] args) {
        new JanelaPrincipal();
    }
}
