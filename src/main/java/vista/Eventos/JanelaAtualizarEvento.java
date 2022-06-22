package vista.Eventos;

import modelo.DadosAplicacao;
import modelo.Distrito;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaAtualizarEvento extends JFrame{
    private JButton estatisticasButton;
    private JButton veiculosButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JTextField nomeEventoTextField;
    private JTextField novaDataInicioTextField;
    private JTextField novaDataFimTextField;
    private JButton atualizarEventoButton;
    private JList listaEventos;
    private JComboBox comboBoxDistritos;
    private JTextField dataInicioTextField;
    private JButton apresentarEventosButton;
    private JTextField dataFimTextField;
    private JButton cancelarButton;
    private JPanel painel;

    private DefaultComboBoxModel modeloComboBoxDistritos;

    public JanelaAtualizarEvento() {
        setContentPane(painel);
        pack();
        setVisible(true);

        veiculosButton.addActionListener(this::btnVeiculosButtonActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaButtonActionPerformed);
        eventosButton.addActionListener(this::btnEventosButtonActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesButtonActionPerformed);
        clientesButton.addActionListener(this::btnClientesButtonActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasButtonActionPerformed);
        cancelarButton.addActionListener(this::btnEventosButtonActionPerformed);
        modeloComboBoxDistritos = new DefaultComboBoxModel();
        comboBoxDistritos.setModel(modeloComboBoxDistritos);
        initComponents();

        apresentarEventosButton.addActionListener(this::btnApresentarEventosButtonActionPerformed);
        atualizarEventoButton.addActionListener(this::btnAtualizarEventoButtonActionPerformed);
    }

    private void btnAtualizarEventoButtonActionPerformed(ActionEvent evt) {

    }

    private void btnApresentarEventosButtonActionPerformed(ActionEvent evt) {
        DadosAplicacao da = DadosAplicacao.INSTANCE;
    }

    private void initComponents() {
        modeloComboBoxDistritos.addElement("TODOS OS DISTRITOS");
        for(Distrito d : Distrito.values()){
            modeloComboBoxDistritos.addElement(d);
        }
    }

    public void fechar(){
        setVisible(false);
        dispose();
        JanelaEventos j = new JanelaEventos();
        j.setVisible(true);
    }

    private void btnEstatisticasButtonActionPerformed(ActionEvent evt) {

    }

    private void btnClientesButtonActionPerformed(ActionEvent evt) {

    }

    private void btnTransacoesButtonActionPerformed(ActionEvent evt) {

    }

    private void btnOficinaButtonActionPerformed(ActionEvent evt) {

    }

    private void btnEventosButtonActionPerformed(ActionEvent evt) {

    }

    private void btnVeiculosButtonActionPerformed(ActionEvent evt) {
    }
}
