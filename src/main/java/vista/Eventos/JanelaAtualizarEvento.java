package vista.Eventos;

import modelo.DadosAplicacao;
import modelo.Data;
import modelo.Distrito;
import modelo.Evento;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Oficina.JanelaOficina;
import vista.Transacoes.JanelaTransacoes;
import vista.Veiculos.JanelaVeiculos;

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
    private JList<Evento> listaEventos;
    private JComboBox comboBoxDistritos;
    private JTextField dataInicioTextField;
    private JButton apresentarEventosButton;
    private JTextField dataFimTextField;
    private JButton cancelarButton;
    private JPanel painel;
    private JButton escolherEventoButton;

    private DefaultComboBoxModel modeloComboBoxDistritos;
    private DefaultListModel modeloListaEventos;
    private Evento evento;

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
        modeloListaEventos = new DefaultListModel();
        comboBoxDistritos.setModel(modeloComboBoxDistritos);
        listaEventos.setModel(modeloListaEventos);
        initComponents();

        apresentarEventosButton.addActionListener(this::btnApresentarEventosActionPerformed);
        atualizarEventoButton.addActionListener(this::btnAtualizarEventoActionPerformed);
        escolherEventoButton.addActionListener(this::btnEscolherEventoActionPerformed);
    }

    private void btnEscolherEventoActionPerformed(ActionEvent evt) {
        boolean valido = !listaEventos.isSelectionEmpty();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_EVENTO);
            return;
        }

        evento = listaEventos.getSelectedValue();
        nomeEventoTextField.setText(evento.getNome());
        novaDataInicioTextField.setText(evento.getDataInicio().toString());
        novaDataFimTextField.setText(evento.getDataFim().toString());

    }

    private void btnAtualizarEventoActionPerformed(ActionEvent evt) {
        boolean valido = evento != null;
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_EVENTO);
            return;
        }

        String nome = nomeEventoTextField.getText();
        valido = isNomeValido(nome);
        if(!valido){
            Erros.mostrarErro(this, Erros.NOME_EVENTO_INVALIDO);
            return;
        }

        String dataInicioAtualizar = novaDataInicioTextField.getText();
        valido = isDataValida(dataInicioAtualizar);
        if(!valido){
            Erros.mostrarErro(this, Erros.DATA_INICIO_INVALIDA);
            return;
        }

        Data inicioAtualizar = Data.parseData(dataInicioAtualizar);

        String dataFimAtualizar = novaDataFimTextField.getText();
        valido = isDataValida(dataFimAtualizar);
        if(!valido){
            Erros.mostrarErro(this, Erros.DATA_FIM_INVALIDA);
            return;
        }

        Data fimAtualizar = Data.parseData(dataFimAtualizar);

        valido = isDataFimAfterDataInicio(inicioAtualizar, fimAtualizar);
        if(!valido){
            Erros.mostrarErro(this, Erros.ORDEM_DATAS);
            return;
        }

        var alterado = isEventoAlterado(evento, nome, inicioAtualizar, fimAtualizar);
        if(alterado){
            valido = !isEventoDuplicado(nome, inicioAtualizar, fimAtualizar);
            if(!valido){
                Erros.mostrarErro(this, Erros.EVENTO_DUPLICADO);
                return;
            }
        }

        evento.setNome(nome);
        evento.setDataInicio(inicioAtualizar);
        evento.setDataFim(fimAtualizar);

        fechar();
    }

    private boolean isEventoAlterado(Evento evento, String nome, Data inicio, Data fim){
        return !evento.getNome().equals(nome) || !evento.getDataInicio().equals(inicio) || !evento.getDataFim().equals(fim);
    }

    private boolean isEventoDuplicado(String nome, Data inicio, Data fim) {
        DadosAplicacao da = DadosAplicacao.INSTANCE;
        return da.isEventoDuplicado(nome,inicio,fim);
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
        valido = eventos != null;
        if(!valido){
            modeloListaEventos.removeAllElements();
            Erros.mostrarErro(this, Erros.NENHUM_RESULTADO);
            return;
        }
        modeloListaEventos.removeAllElements();
        for (Evento evento : eventos) {
            modeloListaEventos.add(modeloListaEventos.getSize(),evento);
        }
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

    private boolean isNomeValido(String nome){
        return !(nome.trim().length() < 3) && !(nome.trim().length() > 50);
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

    public static void main(String[] args) {
        new JanelaAtualizarEvento();
    }
}
