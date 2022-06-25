package vista.Clientes;


import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Transacoes.JanelaTransacoes;
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
        j.setLocationRelativeTo(this);
        j.setVisible(true);
    }

    private void btnHistoricoDeTransacoesPorActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        dispose();

        JanelaHistoricoTransacoesPorCliente j = new JanelaHistoricoTransacoesPorCliente();
        j.setLocationRelativeTo(this);
        j.setVisible(true);
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
        setVisible(false);
        dispose();
        JanelaOficina j = new JanelaOficina();
        //j.setVisible(true);
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
        //j.setVisible(true);
    }

    public static void main(String[] args) {
        new JanelaClientes();
    }
}
