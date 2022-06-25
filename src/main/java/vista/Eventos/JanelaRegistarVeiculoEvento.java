package vista.Eventos;

import modelo.*;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Oficina.JanelaOficina;
import vista.Transacoes.JanelaTransacoes;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class JanelaRegistarVeiculoEvento extends JFrame{
    private JButton estatisticasButton;
    private JButton veiculosButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JButton registarVeiculoNoEventoButton;
    private JList<Evento> listaEventos;
    private JList<Veiculo> listaVeiculos;
    private JComboBox comboBoxDistritos;
    private JTextField dataInicioTextField;
    private JTextField dataFimTextField;
    private JButton apresentarEventosButton;
    private JTextField marcaTextField;
    private JTextField modeloTextField;
    private JTextField matriculaTextField;
    private JButton apresentarVeículosButton;
    private JButton cancelarButton;
    private JPanel painel;
    private JScrollPane eventosList;
    private JScrollPane veiculosList;
    private JButton escolherEventoButton;

    private DefaultComboBoxModel modeloComboBoxDistritos;
    private DefaultListModel modeloListaEventos;
    private DefaultListModel modeloListaVeiculos;
    private Evento evento;

    public JanelaRegistarVeiculoEvento() {
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
        modeloListaEventos = new DefaultListModel();
        modeloListaVeiculos = new DefaultListModel();
        comboBoxDistritos.setModel(modeloComboBoxDistritos);
        listaEventos.setModel(modeloListaEventos);
        listaVeiculos.setModel(modeloListaVeiculos);
        initComponents();

        apresentarEventosButton.addActionListener(this::btnApresentarEventosActionPerformed);
        escolherEventoButton.addActionListener(this::btnEscolherEventoActionPerformed);
        apresentarVeículosButton.addActionListener(this::btnApresentarVeiculosActionPerformed);
        registarVeiculoNoEventoButton.addActionListener(this::btnRegistarVeiculoEventoActionPerformed);
    }

    private void btnRegistarVeiculoEventoActionPerformed(ActionEvent evt) {

    }

    private void btnApresentarVeiculosActionPerformed(ActionEvent evt) {
        boolean valido = evento != null;
        if(!valido){
            Erros.mostrarErro(this,Erros.SELECIONAR_EVENTO);
            return;
        }

        modeloListaVeiculos.removeAllElements();
        String marca = marcaTextField.getText();
        valido = isNomeValido(marca);
        if(!valido){
            Erros.mostrarErro(this,Erros.MARCA_INVALIDA);
            return;
        }

        String modelo = modeloTextField.getText();
        valido = isNomeValido(modelo);
        if(!valido){
            Erros.mostrarErro(this,Erros.MODELO_INVALIDO);
            return;
        }

        String matricula = matriculaTextField.getText();
        valido = isMatriculaValida(matricula);
        if(!valido){
            Erros.mostrarErro(this,Erros.MATRICULA_INVALIDA);
            return;
        }

        DadosAplicacao da = DadosAplicacao.INSTANCE;
        List<Veiculo> veiculos = da.getVeiculos(evento, marca, modelo, matricula);

        valido = veiculos != null;
        if(!valido){
            Erros.mostrarErro(this,Erros.NENHUM_RESULTADO);
            return;
        }

        modeloListaVeiculos.removeAllElements();
        for (Veiculo veiculo : veiculos) {
            modeloListaVeiculos.add(modeloListaVeiculos.getSize(), veiculo);
        }
    }

    private boolean isMatriculaValida(String matricula) {
        return (matricula.trim().matches("^([A-Z]{2})[-]([0-9]{2})[-]([A-Z]{2})$")||
                matricula.trim().matches("^([0-9]{2})[-]([0-9]{2})[-]([A-Z]{2})$") ||
                matricula.trim().matches("^([A-Z]{2})[-]([0-9]{2})[-]([0-9]{2})$") ||
                matricula.trim().matches("^([0-9]{2})[-]([A-Z]{2})[-]([0-9]{2})$"));
    }

    private boolean isNomeValido(String nome) {
        if(nome.isEmpty()){
            return true;
        }
        return !(nome.trim().length() < 3) && !(nome.trim().length() > 50);
    }

    private void btnEscolherEventoActionPerformed(ActionEvent evt) {
        boolean valido = !listaEventos.isSelectionEmpty();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_EVENTO);
            return;
        }

        evento = listaEventos.getSelectedValue();
    }

    private void btnApresentarEventosActionPerformed(ActionEvent evt) {
        modeloListaVeiculos.removeAllElements();

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
        return false;
    }

    private void btnVeiculosActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaVeiculos j = new JanelaVeiculos();
        j.setVisible(true);
    }

    private void btnOficinaActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnOficinaButtonActionPerformed");
        setVisible(false);
        dispose();
        JanelaOficina j = new JanelaOficina();
//        j.setVisible(true);
    }

    private void btnEventosActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnEventosButtonActionPerformed");
        fechar();
    }

    private void fechar() {
        setVisible(false);
        dispose();

    }

    private void btnTransacoesActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnTransacoesButtonActionPerformed");
        setVisible(false);
        dispose();
        JanelaTransacoes j = new JanelaTransacoes();
        j.setVisible(true);
    }

    private void btnClientesActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnClientesButtonActionPerformed");
        setVisible(false);
        dispose();
        JanelaClientes j = new JanelaClientes();
        j.setVisible(true);
    }

    private void btnEstatisticasActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnEstatisticasButtonActionPerformed");
        setVisible(false);
        dispose();
        JanelaEstatistica j = new JanelaEstatistica();
//        j.setVisible(true);
    }

    private void initComponents() {
        modeloComboBoxDistritos.addElement("TODOS OS DISTRITOS");
        for(Distrito d : Distrito.values()){
            modeloComboBoxDistritos.addElement(d);
        }
    }
}
