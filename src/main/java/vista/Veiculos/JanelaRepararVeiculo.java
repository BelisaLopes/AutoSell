package vista.Veiculos;

import modelo.DadosAplicacao;
import modelo.Estabelecimento;
import modelo.Peca;
import modelo.Veiculo;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Transacoes.JanelaTransacoes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Hashtable;
import java.util.List;

public class JanelaRepararVeiculo extends JFrame{
    private JButton estatisticasButton;
    private JButton veiculosButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JButton registarVeículoComoReparadoButton;
    private JTextField textField1;
    private JList<Veiculo> listaVeiculos;
    private JButton adicionarPecasButton;
    private JList<Peca> listaPecasDaReparacao;
    private JComboBox estabelecimentoComboBox;
    private JTextField marcaTextField;
    private JTextField modeloTextField;
    private JList<Peca> listaPecasDisponiveis;
    private JButton apresentarVeículosButton;
    private JButton apresentarPecasButton;
    private JTextField matriculaTextField;
    private JComboBox categoriasComboBox;
    private JButton cancelarButton;
    private JPanel painel;
    private JButton escolherVeiculoButton;

    private DefaultComboBoxModel modeloComboBoxLocais;
    private DefaultListModel modeloListaVeiculos;
    private Hashtable<Peca, Integer> pecasUsadasNaReparacao;
    private Veiculo veiculo;

    private Hashtable<Peca, Integer> pecasUsadas;

    public JanelaRepararVeiculo(){
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
        estabelecimentoComboBox.setModel(modeloComboBoxLocais);
        listaVeiculos.setModel(modeloListaVeiculos);
        pecasUsadas = new Hashtable<>();
        initComponents();


        apresentarVeículosButton.addActionListener(this::btnApresentarVeiculosActionPerformed);
        escolherVeiculoButton.addActionListener(this::btnEscolherVeiculoActionPerformed);
        apresentarPecasButton.addActionListener(this::btnApresentarPecasActionPerformed);
        adicionarPecasButton.addActionListener(this::btnAdicionarPecasActionPerformed);
        registarVeículoComoReparadoButton.addActionListener(this::btnRegistarVeiculoComoReparadoActionPerformed);
        cancelarButton.addActionListener(this::btnCancelarActionPerformed);
    }

    private void btnApresentarVeiculosActionPerformed(ActionEvent evt) {
        modeloListaVeiculos.removeAllElements();
        Estabelecimento estabelecimento = (Estabelecimento) estabelecimentoComboBox.getSelectedItem();

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

    private void btnEscolherVeiculoActionPerformed(ActionEvent evt) {
        boolean valido = !listaVeiculos.isSelectionEmpty();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_VEICULO);
            return;
        }

        veiculo = listaVeiculos.getSelectedValue();
    }

    private void btnApresentarPecasActionPerformed(ActionEvent evt) {

    }

    private void btnAdicionarPecasActionPerformed(ActionEvent evt) {

    }

    private void btnRegistarVeiculoComoReparadoActionPerformed(ActionEvent evt) {

    }

    private void limparPecas() {
        pecasUsadas = new Hashtable<>();
    }


    private void initComponents() {
        List<Estabelecimento> list = DadosAplicacao.INSTANCE.getEstabelecimentos();
        for (Estabelecimento estabelecimento : list) {
            modeloComboBoxLocais.addElement(estabelecimento);
        }
        veiculo = null;
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
//        je.setVisible(true);
    }
}
