package vista.Veiculos;

import modelo.DadosAplicacao;
import modelo.Estabelecimento;
import modelo.EstadoVeiculo;
import modelo.Veiculo;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Sucesso;
import vista.Transacoes.JanelaTransacoes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class JanelaVeiculoPorReparar extends JFrame{
    private JButton veiculosButton;
    private JButton estatisticasButton;
    private JButton clientesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JButton transaçõesButton;
    private JComboBox locaisComboBox;
    private JTextField marcaTextField;
    private JTextField modeloTextField;
    private JTextField matriculaTextField;
    private JList<Veiculo> listaVeiculos;
    private JButton apresentarVeículosButton;
    private JButton definirVeiculoPorRepararButton;
    private JButton cancelarButton;
    private JPanel painel;

    private DefaultComboBoxModel modeloComboBoxLocais;
    private DefaultListModel modeloListaVeiculos;

    private Veiculo veiculo;

    public JanelaVeiculoPorReparar(){
        setContentPane(painel);
        pack();
        setLocationRelativeTo(null);

        veiculosButton.addActionListener(this::btnVeiculosActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaActionPerformed);
        eventosButton.addActionListener(this::btnEventosActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesActionPerformed);
        clientesButton.addActionListener(this::btnClientesActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasActionPerformed);
        cancelarButton.addActionListener(this::btnCancelarActionPerformed);
        modeloListaVeiculos = new DefaultListModel();
        modeloComboBoxLocais = new DefaultComboBoxModel();
        locaisComboBox.setModel(modeloComboBoxLocais);
        listaVeiculos.setModel(modeloListaVeiculos);
        initComponents();

        apresentarVeículosButton.addActionListener(this::btnApresentarVeiculosActionPerformed);
        definirVeiculoPorRepararButton.addActionListener(this::btnDefinirVeiculoPorRepararActionPerformed);

    }

    private void btnApresentarVeiculosActionPerformed(ActionEvent evt) {
        modeloListaVeiculos.removeAllElements();
        Estabelecimento estabelecimento;
        try {
            estabelecimento = (Estabelecimento) locaisComboBox.getSelectedItem();
        }catch(Exception ex){
            estabelecimento = null;
        }

        String marca = marcaTextField.getText();
        boolean valido = isNomeValido(marca);
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
        List<Veiculo> veiculos = da.getVeiculosReparados(estabelecimento, marca, modelo, matricula);
        valido = isResultEmpty(veiculos);
        if(!valido){
            Erros.mostrarErro(this, Erros.NENHUM_RESULTADO);
            return;
        }

        atualizarListaVeiculos(veiculos);
    }

    private boolean isResultEmpty(List<Veiculo> veiculos){
        return veiculos != null;
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

    private boolean isVeiculoSelected(){
        return !listaVeiculos.isSelectionEmpty();
    }

    private void btnDefinirVeiculoPorRepararActionPerformed(ActionEvent evt) {
        boolean valido = isVeiculoSelected();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_VEICULO);
            return;
        }

        veiculo = listaVeiculos.getSelectedValue();
        DadosAplicacao da = DadosAplicacao.INSTANCE;
        da.definirVeiculoPorReparar(veiculo);
        Sucesso.mostrarSucesso(this, Sucesso.ESTADO_VEICULO_ALTERADO);
        fechar();
        JanelaVeiculos jv = new JanelaVeiculos();
        jv.setVisible(true);
    }

    private void initComponents() {
        modeloComboBoxLocais.addElement("TODOS OS LOCAIS");
        List<Estabelecimento> list = DadosAplicacao.INSTANCE.getEstabelecimentos();
        for (Estabelecimento estabelecimento : list) {
            modeloComboBoxLocais.addElement(estabelecimento);
        }
    }

    private void fechar() {
        setVisible(false);
        dispose();
    }

    private void btnCancelarActionPerformed(ActionEvent evt) {
        fechar();

        JanelaVeiculos jv = new JanelaVeiculos();
        jv.setVisible(true);
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
        je.setVisible(true);
    }
}
