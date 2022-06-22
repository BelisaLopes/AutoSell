package vista.Eventos;

import modelo.DadosAplicacao;
import modelo.Data;
import modelo.Distrito;
import modelo.Evento;
import vista.Erros;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

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
    private JButton escolherEventoButton;

    private DefaultComboBoxModel modeloComboBoxDistritos;

    public JanelaAtualizarEvento() {
        setContentPane(painel);
        pack();
        setVisible(true);

        veiculosButton.addActionListener(this::btnVeiculosActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaActionPerformed);
        eventosButton.addActionListener(this::btnEventosActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesActionPerformed);
        clientesButton.addActionListener(this::btnClientesActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasActionPerformed);
        cancelarButton.addActionListener(this::btnEventosActionPerformed);
        modeloComboBoxDistritos = new DefaultComboBoxModel();
        comboBoxDistritos.setModel(modeloComboBoxDistritos);
        initComponents();

        apresentarEventosButton.addActionListener(this::btnApresentarEventosActionPerformed);
        atualizarEventoButton.addActionListener(this::btnAtualizarEventoActionPerformed);
        escolherEventoButton.addActionListener(this::btnEscolherEventoActionPerformed);
    }

    private void btnEscolherEventoActionPerformed(ActionEvent evt) {

    }

    private void btnAtualizarEventoActionPerformed(ActionEvent evt) {

    }

    private void btnApresentarEventosActionPerformed(ActionEvent evt) {
        String dataInicio = dataInicioTextField.getText();
        boolean valido = isDataValida(dataInicio);
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
        Distrito distrito;
        try{
            distrito = (Distrito) comboBoxDistritos.getSelectedItem();
        }catch (Exception ex){
            distrito = null;
        }

        DadosAplicacao da = DadosAplicacao.INSTANCE;
        List<Evento> eventos = da.getEventos(distrito, inicio,fim);

    }

    private boolean isDataValida(String data) {
        if(data.isEmpty()){
            return true;
        }
        Data data_final = Data.parseData(data);
        if(data_final == null){
            return false;
        }
        int dia = data_final.getDia();
        int mes = data_final.getMes();
        int ano = data_final.getAno();
        return (dia > 0 && dia < 32 && mes > 0 && mes < 13 && ano > 0);
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

    private void btnVeiculosActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnVeiculosButtonActionPerformed");
    }

    private void btnOficinaActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnOficinaButtonActionPerformed");
    }

    private void btnEventosActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnEventosButtonActionPerformed");
        fechar();
    }

    private void btnTransacoesActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnTransacoesButtonActionPerformed");
    }

    private void btnClientesActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnClientesButtonActionPerformed");
    }

    private void btnEstatisticasActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnEstatisticasButtonActionPerformed");
    }

}
