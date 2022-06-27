package vista.Eventos;

import vista.Clientes.JanelaClientes;
import vista.Estatisticas.JanelaEstatistica;
import vista.Oficina.JanelaOficina;
import vista.Transacoes.JanelaTransacoes;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaEventos extends JFrame{
    private JButton veiculosButton;
    private JButton oficinaButton;
    private JButton clientesButton;
    private JButton estatisticasButton;
    private JButton eventosButton;
    private JButton transaçõesButton;
    private JButton registarEventoButton;
    private JButton atualizarEventoButton;
    private JButton registarVeiculoNumEventoButton;
    private JButton consultarEventoButton;
    private JButton removerEventoNãoDecorridoButton;
    private JButton transportarVeículosEntreEventosButton;
    private JPanel painelPrincipal;


    public JanelaEventos() {
        setContentPane(painelPrincipal);
        pack();
        abrir();
//        setLocationRelativeTo(null); //fazer isto só na janela principal
//        setVisible(true);

        veiculosButton.addActionListener(this::btnVeiculosButtonActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaButtonActionPerformed);
        eventosButton.addActionListener(this::btnEventosButtonActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesButtonActionPerformed);
        clientesButton.addActionListener(this::btnClientesButtonActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasButtonActionPerformed);

        registarEventoButton.addActionListener(this::btnRegistarEventoActionPerformed);
        atualizarEventoButton.addActionListener(this::btnAtualizarEventoActionPerformed);
        consultarEventoButton.addActionListener(this::btnConsultarEventoActionPerformed);
        registarVeiculoNumEventoButton.addActionListener(this::btnRegistarVeiculoNoEventoActionPerformed);
        transportarVeículosEntreEventosButton.addActionListener(this::btnTransportarVeiculosEntreEventosActionPerformed);
        removerEventoNãoDecorridoButton.addActionListener(this::btnRemoverEventoActionPerformed);
    }

    private void btnRemoverEventoActionPerformed(ActionEvent evt) {
        fechar();

        JanelaRemoverEventoNaoDecorrido jr = new JanelaRemoverEventoNaoDecorrido();
        jr.setVisible(true);
    }

    private void abrir() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void btnTransportarVeiculosEntreEventosActionPerformed(ActionEvent evt) {
        fechar();
        JanelaTransportarVeiculos jt = new JanelaTransportarVeiculos();
        jt.setVisible(true);
    }

    private void fechar() {
        setVisible(false);
        dispose();
    }

    private void btnRegistarVeiculoNoEventoActionPerformed(ActionEvent evt) {
        fechar();

        JanelaRegistarVeiculoEvento j = new JanelaRegistarVeiculoEvento();
        j.setVisible(true);
    }

    private void btnConsultarEventoActionPerformed(ActionEvent evt) {
        fechar();

        JanelaConsultarEventosEVeiculos j = new JanelaConsultarEventosEVeiculos();
        j.setVisible(true);
    }

    private void btnAtualizarEventoActionPerformed(ActionEvent evt) {
        fechar();

        JanelaAtualizarEvento j = new JanelaAtualizarEvento();
        j.setVisible(true);
    }

    private void btnRegistarEventoActionPerformed(ActionEvent evt) {
        fechar();

        JanelaRegistarEvento j = new JanelaRegistarEvento();
        j.setVisible(true);
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
        j.setVisible(true);
    }

    public static void main(String[] args) {
        new JanelaEventos();
    }
}
