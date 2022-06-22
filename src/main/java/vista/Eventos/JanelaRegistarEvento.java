package vista.Eventos;

import modelo.*;
import vista.Erros;

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
//        setVisible(true);

        veiculosButton.addActionListener(this::btnVeiculosButtonActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaButtonActionPerformed);
        eventosButton.addActionListener(this::btnEventosButtonActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesButtonActionPerformed);
        clientesButton.addActionListener(this::btnClientesButtonActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasButtonActionPerformed);
        cancelarButton.addActionListener(this::btnEventosButtonActionPerformed);
        modeloComboBoxDistritos = new DefaultComboBoxModel();
        modeloComboBoxFiliais = new DefaultComboBoxModel();
        comboBoxDistritos.setModel(modeloComboBoxDistritos);
        comboBoxFiliais.setModel(modeloComboBoxFiliais);
        initComponents();

        registarEventoButton.addActionListener(this::btnAdicionarEventoActionPerformed);

    }

//    public static Evento mostrarCriacaoEvento(Frame parent){
//        System.out.println("mostrarCriacaoEvento");
//        var registar = new JanelaRegistarEvento(parent, true);
//        registar.setLocationRelativeTo(parent);
//        registar.setVisible(true);
//
//    }

    private void btnAdicionarEventoActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnRegistarEventoActionPerformed");
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
        valido = isDataFimAfterDataInicio(inicio,fim);
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
        boolean isSelecionado = associarFilialCheckBox.isSelected(); //falta esta parte no diag. sequencia
        if(isSelecionado){
            Estabelecimento e = (Estabelecimento) modeloComboBoxFiliais.getSelectedItem();
            distrito = e.getDistrito();
            novoEvento = new Evento(distrito, nome, inicio, fim, e);
        }

        DadosAplicacao da = DadosAplicacao.INSTANCE;
        da.adicionarEvento(novoEvento);
        fechar();
    }

    private void fechar() {
        setVisible(false);

//        JanelaEventos j = new JanelaEventos();
//        j.setVisible(true);
        dispose();
    }

    private boolean isEventoDuplicado(String nome, Data inicio, Data fim) {
        DadosAplicacao da = DadosAplicacao.INSTANCE;
        return da.isEventoDuplicado(nome,inicio, fim);
    }

    private boolean isDataFimAfterDataInicio(Data inicio, Data fim) {
        if(fim.getAno() > inicio.getAno()){
            return true;
        }
        if(fim.getMes() > inicio.getMes()){
            return true;
        }

        return fim.getDia() >= inicio.getDia();
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

    private void btnVeiculosButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnVeiculosButtonActionPerformed");
    }

    private void btnOficinaButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnOficinaButtonActionPerformed");
    }

    private void btnEventosButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnEventosButtonActionPerformed");
//        this.setVisible(false);
//        dispose();
        fechar();

//        JanelaEventos j = new JanelaEventos();
//        j.setVisible(true);

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
