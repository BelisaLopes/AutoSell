package vista.Veiculos;

import vista.Clientes.JanelaClientes;
import vista.Estatisticas.JanelaEstatistica;
import vista.Oficina.JanelaAdicionarCategoria;
import vista.Oficina.JanelaOficina;
import vista.Transacoes.JanelaTransacoes;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaVeiculos extends JFrame{
    private JButton veiculosButton;
    private JButton oficinaButton;
    private JButton eventosButton;
    private JButton transaçõesButton;
    private JButton estatisticasButton;
    private JButton clientesButton;
    private JButton consultarVeiculosButton;
    private JButton transferirVeiculosButton;
    private JButton definirVeículoComoReparadoButton;
    private JButton definirVeículoPorRepararButton;
    private JPanel painel;

    public JanelaVeiculos(){
        setContentPane(painel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        veiculosButton.addActionListener(this::btnVeiculosButtonActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaButtonActionPerformed);
        eventosButton.addActionListener(this::btnEventosButtonActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesButtonActionPerformed);
        clientesButton.addActionListener(this::btnClientesButtonActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasButtonActionPerformed);

        consultarVeiculosButton.addActionListener(this::btnConsultarVeiculosActionPerformed);
        definirVeículoPorRepararButton.addActionListener(this::btnDefinirVeiculoPorRepararActionPerformed);
        definirVeículoComoReparadoButton.addActionListener(this::btnDefinirVeiculoComoReparadoActionPerformed);
        transferirVeiculosButton.addActionListener(this::btnTransferirVeiculosActionPerformed);
    }

    private void btnDefinirVeiculoComoReparadoActionPerformed(ActionEvent evt) {
        fechar();

        JanelaRepararVeiculo jr = new JanelaRepararVeiculo();
//        jr.setVisible(true);
    }

    private void btnTransferirVeiculosActionPerformed(ActionEvent evt) {
        fechar();

        JanelaTransferirVeiculos jt = new JanelaTransferirVeiculos();
//        jt.setVisible(true);
    }

    private void btnConsultarVeiculosActionPerformed(ActionEvent evt) {
        fechar();

        JanelaConsultarVeiculos jc = new JanelaConsultarVeiculos();
        jc.setVisible(true);
    }

    private void btnDefinirVeiculoPorRepararActionPerformed(ActionEvent evt) {
        fechar();

        JanelaVeiculoPorReparar jv = new JanelaVeiculoPorReparar();
        jv.setVisible(true);
    }

    private void fechar(){
        setVisible(false);
        dispose();
    }

    private void btnVeiculosButtonActionPerformed(ActionEvent evt) {
        fechar();

        JanelaVeiculos j = new JanelaVeiculos();
        j.setVisible(true);
    }

    private void btnOficinaButtonActionPerformed(ActionEvent evt) {
        fechar();

        JanelaOficina j = new JanelaOficina();
        j.setVisible(true);
    }

    private void btnEventosButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnEventosButtonActionPerformed");
    }

    private void btnTransacoesButtonActionPerformed(ActionEvent evt) {
        fechar();

        JanelaTransacoes j = new JanelaTransacoes();
        j.setVisible(true);
    }

    private void btnClientesButtonActionPerformed(ActionEvent evt) {
        fechar();

        JanelaClientes j = new JanelaClientes();
        j.setVisible(true);
    }

    private void btnEstatisticasButtonActionPerformed(ActionEvent evt) {
        fechar();

        JanelaEstatistica j = new JanelaEstatistica();
//        j.setVisible(true);
    }
}
