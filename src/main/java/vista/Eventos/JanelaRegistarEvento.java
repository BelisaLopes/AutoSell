package vista.Eventos;

import modelo.DadosAplicacao;
import modelo.Distrito;
import modelo.Estabelecimento;
import modelo.Filial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class JanelaRegistarEvento extends JFrame{
    private JButton estatisticasButton;
    private JButton veiculosButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JTextField nomeEventoTextField;
    private JTextField dataInicioTextField;
    private JTextField dataFimTextField;
    private JButton registarEventoButton;
    private JComboBox<Distrito> comboBoxDistritos;
    private JComboBox<Estabelecimento> comboBoxFiliais;
    private JCheckBox associarFilialCheckBox;
    private JButton cancelarButton;
    private JPanel painel;

    private DefaultComboBoxModel modeloComboBoxDistritos;
    private DefaultComboBoxModel modeloComboBoxFiliais;

    public JanelaRegistarEvento(){
        setContentPane(painel);
        pack();
        setVisible(true);

        veiculosButton.addActionListener(this::btnVeiculosButtonActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaButtonActionPerformed);
        eventosButton.addActionListener(this::btnEventosButtonActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesButtonActionPerformed);
        clientesButton.addActionListener(this::btnClientesButtonActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasButtonActionPerformed);
        modeloComboBoxDistritos = new DefaultComboBoxModel();
        modeloComboBoxFiliais = new DefaultComboBoxModel();
        comboBoxDistritos.setModel(modeloComboBoxDistritos);
        comboBoxFiliais.setModel(modeloComboBoxFiliais);
        initComponents();

    }

    private void initComponents() {
        for(Distrito d : Distrito.values()){
            modeloComboBoxDistritos.addElement(d);
        }
        ArrayList<Filial> lista = DadosAplicacao.INSTANCE.getFiliais();
        for (Filial filial : lista) {
            modeloComboBoxFiliais.addElement(filial);
        }
    }

    private void btnVeiculosButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnVeiculosButtonActionPerformed");
    }

    private void btnOficinaButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnOficinaButtonActionPerformed");
    }

    private void btnEventosButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnEventosButtonActionPerformed");
        this.setVisible(false);
        dispose();

        JanelaEventos j = new JanelaEventos();
        j.setVisible(true);

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
        new JanelaRegistarEvento();
    }
}
