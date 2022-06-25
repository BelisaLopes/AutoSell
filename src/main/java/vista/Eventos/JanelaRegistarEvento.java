package vista.Eventos;

import modelo.*;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Oficina.JanelaOficina;
import vista.Sucesso;
import vista.Transacoes.JanelaTransacoes;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class JanelaRegistarEvento extends JDialog{
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

//    private Evento evento;

    public JanelaRegistarEvento(){
//        super(parent);
        setContentPane(painel);
        pack();
        setLocationRelativeTo(null);
//        abrir();
//        setVisible(true);

        veiculosButton.addActionListener(this::btnVeiculosActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaActionPerformed);
        eventosButton.addActionListener(this::btnEventosActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesActionPerformed);
        clientesButton.addActionListener(this::btnClientesActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasActionPerformed);
        cancelarButton.addActionListener(this::btnCancelarActionPerformed);
        modeloComboBoxDistritos = new DefaultComboBoxModel();
        modeloComboBoxFiliais = new DefaultComboBoxModel();
        comboBoxDistritos.setModel(modeloComboBoxDistritos);
        comboBoxFiliais.setModel(modeloComboBoxFiliais);
        initComponents();

        registarEventoButton.addActionListener(this::btnAdicionarEventoActionPerformed);

    }

    private void abrir() {
//        setContentPane(painel);
        setLocationRelativeTo(null);
//        pack();
        setVisible(true);
    }


//    public static Evento mostrarCriacaoEvento(Frame parent){
//        System.out.println("mostrarCriacaoEvento");
//        var registar = new JanelaRegistarEvento(parent, true);
//        registar.setLocationRelativeTo(parent);
//        registar.setVisible(true);
//
//    }

    private void btnAdicionarEventoActionPerformed(ActionEvent evt) {
        String nome = nomeEventoTextField.getText();
        boolean valido = isNomeValido(nome);
        if(!valido){
            Erros.mostrarErro(this, Erros.NOME_EVENTO_INVALIDO);
            return;
        }

        String dataInicio = dataInicioTextField.getText();
        valido = isDataValida(dataInicio);
        if(!valido){
            Erros.mostrarErro(this, Erros.DATA_INICIO_INVALIDA);
            return;
        }

        Data inicio = Data.parseData(dataInicio);

        String dataFim = dataFimTextField.getText();
        valido = isDataValida(dataFim);
        if(!valido){
            Erros.mostrarErro(this, Erros.DATA_FIM_INVALIDA);
            return;
        }

        Data fim = Data.parseData(dataFim);
        valido = Data.isFirstDateAfterSecondDate(fim,inicio);
        if(!valido){
            Erros.mostrarErro(this, Erros.ORDEM_DATAS);
            return;
        }

        valido = !isEventoDuplicado(nome,inicio, fim);
        if(!valido){
            Erros.mostrarErro(this, Erros.EVENTO_DUPLICADO);
            return;
        }

        Distrito distrito = (Distrito) modeloComboBoxDistritos.getSelectedItem();
        Evento novoEvento = new Evento(distrito, nome, inicio, fim);
        boolean isSelecionado = associarFilialCheckBox.isSelected();
        if(isSelecionado){
            Estabelecimento e = (Estabelecimento) modeloComboBoxFiliais.getSelectedItem();
            distrito = e.getDistrito();
            novoEvento.setDistrito(distrito);
        }

        DadosAplicacao da = DadosAplicacao.INSTANCE;
        da.adicionarEvento(novoEvento);
        Sucesso.mostrarSucesso(this, Sucesso.EVENTO_REGISTADO);
        limpar();
    }

    private void limpar() {
        nomeEventoTextField.setText("");
        dataInicioTextField.setText("");
        dataFimTextField.setText("");
        associarFilialCheckBox.setSelected(false);
    }

    private void fechar() {
        setVisible(false);
        dispose();
    }

    private boolean isEventoDuplicado(String nome, Data inicio, Data fim) {
        DadosAplicacao da = DadosAplicacao.INSTANCE;
        return da.isEventoDuplicado(nome,inicio, fim);
    }

    private boolean isDataValida(String data){
        Data data_final = Data.parseData(data);
        if(data_final == null){
            return false;
        }
        int dia = data_final.getDia();
        int mes = data_final.getMes();
        int ano = data_final.getAno();
        return (dia > 0 && dia < 32 && mes > 0 && mes < 13 && ano > 0);
    }

    private boolean isNomeValido(String nome){
        return !(nome.trim().length() < 3) && !(nome.trim().length() > 50);
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


    private void btnCancelarActionPerformed(ActionEvent evt) {
        fechar();
        JanelaEventos je = new JanelaEventos();
        je.setVisible(true);
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

    public static void main(String[] args) {
        new JanelaRegistarEvento();
    }
}
