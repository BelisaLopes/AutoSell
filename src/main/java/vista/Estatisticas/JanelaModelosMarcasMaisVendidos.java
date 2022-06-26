package vista.Estatisticas;

import modelo.DadosAplicacao;
import modelo.Estabelecimento;
import modelo.Filial;
import vista.Clientes.JanelaClientes;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Transacoes.JanelaTransacoes;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class JanelaModelosMarcasMaisVendidos extends JFrame {
    private JButton veículosButton;
    private JButton oficinaButton;
    private JButton eventosButton;
    private JButton transaçõesButton;
    private JButton clientesButton;
    private JButton estatísticasButton;
    private JList listModelosMarcas;
    private JRadioButton porFilialRadioButton;
    private JRadioButton todosOsVeículosRadioButton;
    private JComboBox<Estabelecimento> comboBoxFilial;
    private JPanel painelPrincipal;
    private JButton apresentarListaButton;

    private DefaultListModel modeloListaModelosMarcas;

    private DefaultComboBoxModel modeloComboBoxFiliais;

    public JanelaModelosMarcasMaisVendidos(){
        setContentPane(painelPrincipal);
        pack();
        setVisible(true);

        veículosButton.addActionListener(this::btnVeículosButtonActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaButtonActionPerformed);
        eventosButton.addActionListener(this::btnEventosButtonActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesButtonActionPerformed);
        clientesButton.addActionListener(this::btnClientesButtonActionPerformed);
        estatísticasButton.addActionListener(this::btnEstatísticasButtonActionPerformed);
        apresentarListaButton.addActionListener(this::btnApresentarListaButtonActionPerformed);
        modeloComboBoxFiliais = new DefaultComboBoxModel();
        comboBoxFilial.setModel(modeloComboBoxFiliais);

        modeloListaModelosMarcas = new DefaultListModel();

        listModelosMarcas.setModel(modeloListaModelosMarcas);
        initComponents();
    }

    private void btnApresentarListaButtonActionPerformed(ActionEvent evt) {
        boolean porFilial = porFilialRadioButton.isSelected();
        if(porFilial) {
            DadosAplicacao dados = DadosAplicacao.INSTANCE;
            modeloListaModelosMarcas.addElement(dados.iterarHashMarcasModelosVendidosPorLocal());
        }else{

            boolean todosVeiculos = todosOsVeículosRadioButton.isSelected();

            if(todosVeiculos) {
                DadosAplicacao dados = DadosAplicacao.INSTANCE;
                modeloListaModelosMarcas.addElement(dados.iterarHashMarcasModelosVendidos());
            }
        }

    }

    private void initComponents() {
        ArrayList<Filial> listafiliais = DadosAplicacao.INSTANCE.getFiliais();
        for (Filial filial : listafiliais) {
            modeloComboBoxFiliais.addElement(filial);
        }
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
        new JanelaModelosMarcasMaisVendidos();
    }
}
