package vista.Eventos;

import modelo.*;
import vista.Erros;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class JanelaConsultarEventosEVeiculos extends JFrame{
    private JButton estatisticasButton;
    private JButton veiculosButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JComboBox comboBoxDistritos;
    private JTextField dataInicioTextField;
    private JTextField dataFimTextField;
    private JButton apresentarEventosButton;
    private JList<Evento> listaEventos;
    private JList<Veiculo> listaVeiculos;
    private JLabel localLabel;
    private JLabel dataInicioLabel;
    private JLabel dataFimLabel;
    private JLabel numeroVeiculosLabel;
    private JButton escolherEventoButton;
    private JPanel painel;

    private DefaultComboBoxModel modeloComboBoxDistritos;
    private DefaultListModel modeloListaEventos;
    private DefaultListModel modeloListaVeiculos;
    private Evento evento;

    public JanelaConsultarEventosEVeiculos(){
        setContentPane(painel);
        pack();
        setVisible(true);

        veiculosButton.addActionListener(this::btnVeiculosActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaActionPerformed);
        eventosButton.addActionListener(this::btnEventosActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesActionPerformed);
        clientesButton.addActionListener(this::btnClientesActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasActionPerformed);
        modeloComboBoxDistritos = new DefaultComboBoxModel();
        modeloListaEventos = new DefaultListModel();
        modeloListaVeiculos = new DefaultListModel();
        comboBoxDistritos.setModel(modeloComboBoxDistritos);
        listaEventos.setModel(modeloListaEventos);
        listaVeiculos.setModel(modeloListaVeiculos);
        initComponents();

        apresentarEventosButton.addActionListener(this::btnApresentarEventosActionPerformed);
        escolherEventoButton.addActionListener(this::btnEscolherEventoActionPerformed);
    }

    private void btnEscolherEventoActionPerformed(ActionEvent evt) {
        boolean valido = !listaEventos.isSelectionEmpty();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_EVENTO);
            return;
        }

        evento = listaEventos.getSelectedValue();
        if(evento.getEstabelecimento() == null){
            localLabel.setText("n/a");
        }else{
            localLabel.setText(evento.getEstabelecimento().toString());
        }

        dataInicioLabel.setText(evento.getDataInicio().toString());
        dataFimLabel.setText(evento.getDataFim().toString());

        DadosAplicacao da = DadosAplicacao.INSTANCE;
        List<Veiculo> veiculos = da.getVeiculosLocal(evento);

        numeroVeiculosLabel.setText(String.valueOf(veiculos.size()));

        modeloListaVeiculos.removeAllElements();
        for (Veiculo veiculo : veiculos) {
            modeloListaVeiculos.add(modeloListaVeiculos.getSize(), veiculo);
        }
    }

    private void btnApresentarEventosActionPerformed(ActionEvent evt) {
        modeloListaVeiculos.removeAllElements();
        dataFimLabel.setText("");
        dataInicioLabel.setText("");
        numeroVeiculosLabel.setText("");
        localLabel.setText("");

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
        if(eventos == null){
            modeloListaEventos.removeAllElements();
            Erros.mostrarErro(this, Erros.NENHUM_RESULTADO);
            return;
        }
        modeloListaEventos.removeAllElements();
        for (Evento evento : eventos) {
            modeloListaEventos.add(modeloListaEventos.getSize(),evento);
        }
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

    private void btnEstatisticasActionPerformed(ActionEvent evt) {

    }

    private void btnClientesActionPerformed(ActionEvent evt) {

    }

    private void btnTransacoesActionPerformed(ActionEvent evt) {

    }

    private void btnEventosActionPerformed(ActionEvent evt) {
        fechar();
    }

    private void fechar() {
        setVisible(false);
        dispose();
        JanelaEventos j = new JanelaEventos();
        j.setVisible(true);
    }

    private void btnOficinaActionPerformed(ActionEvent evt) {

    }

    private void btnVeiculosActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaVeiculos j = new JanelaVeiculos();
        j.setVisible(true);
    }

    private void initComponents() {
        modeloComboBoxDistritos.addElement("TODOS OS DISTRITOS");
        for(Distrito d : Distrito.values()){
            modeloComboBoxDistritos.addElement(d);
        }
    }
}
