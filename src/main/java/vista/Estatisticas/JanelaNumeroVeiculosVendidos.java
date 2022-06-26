package vista.Estatisticas;

import modelo.DadosAplicacao;
import vista.Clientes.JanelaClientes;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Transacoes.JanelaTransacoes;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaNumeroVeiculosVendidos extends JFrame {
    private JButton veículosButton;
    private JButton oficinaButton;
    private JButton eventosButton;
    private JButton transaçõesButton;
    private JButton clientesButton;
    private JButton estatísticasButton;
    private JList listVeiculos;
    private JPanel painelPrincipal;
    private JLabel quantidadeLabel;
    private DefaultListModel modeloListaNumeroVeiculos;

    public JanelaNumeroVeiculosVendidos(){
        setContentPane(painelPrincipal);
        pack();
        setVisible(true);

        veículosButton.addActionListener(this::btnVeículosButtonActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaButtonActionPerformed);
        eventosButton.addActionListener(this::btnEventosButtonActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesButtonActionPerformed);
        clientesButton.addActionListener(this::btnClientesButtonActionPerformed);
        estatísticasButton.addActionListener(this::btnEstatísticasButtonActionPerformed);

        modeloListaNumeroVeiculos = new DefaultListModel();

        listVeiculos.setModel(modeloListaNumeroVeiculos);

        DadosAplicacao dados = DadosAplicacao.INSTANCE;

        modeloListaNumeroVeiculos.addElement(dados.iterarHashVeiculosVendidosPorLocal());

        quantidadeLabel.setText(String.valueOf(dados.numeroVeiculosVendidos()));
    }

    private void btnVeículosButtonActionPerformed(ActionEvent evt) {
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

    private void btnEstatísticasButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaEstatistica j = new JanelaEstatistica();
        j.setVisible(true);
    }

    public static void main(String[] args) {
        new JanelaNumeroVeiculosVendidos();
    }
}
