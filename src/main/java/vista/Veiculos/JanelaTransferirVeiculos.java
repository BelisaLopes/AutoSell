package vista.Veiculos;

import modelo.DadosAplicacao;
import modelo.Estabelecimento;
import modelo.Veiculo;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Transacoes.JanelaTransacoes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class JanelaTransferirVeiculos extends JFrame{
    private JButton veiculosButton;
    private JButton estatisticasButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JComboBox locaisComboBox;
    private JComboBox localDestinoComboBox;
    private JButton transferirVeículoButton;
    private JTextField marcaTextField;
    private JTextField modeloTextField;
    private JTextField matriculaTextField;
    private JButton apresentarVeículosButton;
    private JList<Veiculo> listaVeiculos;
    private JButton cancelarButton;
    private JLabel lotacaoDestinoLabel;
    private JPanel painel;
    private JLabel lotacaoOrigemLabel;


    private DefaultComboBoxModel modeloComboBoxLocais;
    private DefaultComboBoxModel modeloComboBoxLocaisDestino;
    private DefaultListModel modeloListaVeiculos;
    private Veiculo veiculo;
    private Estabelecimento estabelecimento;

    public JanelaTransferirVeiculos(){
        setContentPane(painel);
        pack();
        setLocationRelativeTo(null);

        veiculosButton.addActionListener(this::btnVeiculosActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaActionPerformed);
        eventosButton.addActionListener(this::btnEventosActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesActionPerformed);
        clientesButton.addActionListener(this::btnClientesActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasActionPerformed);
        modeloComboBoxLocais = new DefaultComboBoxModel();
        modeloComboBoxLocaisDestino = new DefaultComboBoxModel();
        modeloListaVeiculos = new DefaultListModel();
        localDestinoComboBox.setModel(modeloComboBoxLocaisDestino);
        locaisComboBox.setModel(modeloComboBoxLocais);
        listaVeiculos.setModel(modeloListaVeiculos);

        initComponents();

        apresentarVeículosButton.addActionListener(this::btnApresentarVeiculosActionPerformed);
    }

    private void initComponents() {
        List<Estabelecimento> list = DadosAplicacao.INSTANCE.getEstabelecimentos();
        for (Estabelecimento estabelecimento : list) {
            modeloComboBoxLocais.addElement(estabelecimento);
        }
        veiculo = null;
    }

    private void btnApresentarVeiculosActionPerformed(ActionEvent evt) {
        boolean valido = false;
        if(!valido){
            Erros.mostrarErro(this, Erros.VEICULO_AINDA_EM_REPARACAO);
            return;
        }

        modeloListaVeiculos.removeAllElements();
        estabelecimento = (Estabelecimento) locaisComboBox.getSelectedItem();

        String marca = marcaTextField.getText();
        valido = isNomeValido(marca);
        if(!valido){
            Erros.mostrarErro(this, Erros.MARCA_INVALIDA);
            return;
        }

        String modelo = modeloTextField.getText();
        valido = isNomeValido(modelo);
        if(!valido){
            Erros.mostrarErro(this, Erros.MODELO_INVALIDO);
            return;
        }

        String matricula = matriculaTextField.getText();
        valido = isNomeValido(matricula);
        if(!valido){
            Erros.mostrarErro(this, Erros.MATRICULA_INVALIDA);
            return;
        }

        DadosAplicacao da = DadosAplicacao.INSTANCE;
        List<Veiculo> veiculos = da.getVeiculosPorReparar(estabelecimento, marca, modelo, matricula);
        valido = veiculos != null;
        if(!valido){
            Erros.mostrarErro(this, Erros.NENHUM_RESULTADO);
            return;
        }

        atualizarListaVeiculos(veiculos);

    }

    private void atualizarListaVeiculos(List<Veiculo> veiculos) {
        for (Veiculo veiculo : veiculos) {
            modeloListaVeiculos.add(modeloListaVeiculos.getSize(),veiculo);
        }
    }

    private boolean isNomeValido(String nome) {
        if(nome.isEmpty()){
            return true;
        }
        return !(nome.trim().length() < 3) && !(nome.trim().length() > 50);
    }

    private void fechar() {
        setVisible(false);
        dispose();
    }

    private void btnVeiculosActionPerformed(ActionEvent evt) {
        fechar();
        JanelaVeiculos jv = new JanelaVeiculos();
        jv.setVisible(true);
    }

    private void btnOficinaActionPerformed(ActionEvent evt) {
        fechar();
        JanelaOficina jo = new JanelaOficina();
        jo.setVisible(true);
    }

    private void btnEventosActionPerformed(ActionEvent evt) {
        fechar();
        JanelaEventos je = new JanelaEventos();
        je.setVisible(true);
    }

    private void btnTransacoesActionPerformed(ActionEvent evt) {
        fechar();
        JanelaTransacoes jt = new JanelaTransacoes();
        jt.setVisible(true);
    }

    private void btnClientesActionPerformed(ActionEvent evt) {
        fechar();
        JanelaClientes jc = new JanelaClientes();
        jc.setVisible(true);
    }

    private void btnEstatisticasActionPerformed(ActionEvent evt) {
        fechar();
        JanelaEstatistica je = new JanelaEstatistica();
//        je.setVisible(true);
    }
}
