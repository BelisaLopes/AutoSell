package vista.Eventos;

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
        setLocationRelativeTo(null); //fazer isto só na janela principal
        setVisible(true);

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
    }

    private void btnRegistarVeiculoNoEventoActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        dispose();

        JanelaRegistarVeiculoEvento j = new JanelaRegistarVeiculoEvento();
        j.setLocationRelativeTo(this);
        j.setVisible(true);
    }

    private void btnConsultarEventoActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        dispose();

        JanelaConsultarEventosEVeiculos j = new JanelaConsultarEventosEVeiculos();
        j.setLocationRelativeTo(this);
        j.setVisible(true);
    }

    private void btnAtualizarEventoActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnAtualizarEventoActionPerformed");
        this.setVisible(false);
        dispose();

        JanelaAtualizarEvento j = new JanelaAtualizarEvento();
        j.setLocationRelativeTo(this);
        j.setVisible(true);
    }

    private void btnRegistarEventoActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnRegistarEventoButtonActionPerformed");
        this.setVisible(false);
        dispose();

        JanelaRegistarEvento j = new JanelaRegistarEvento();
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
        new JanelaEventos();
    }
}
