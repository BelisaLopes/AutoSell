package vista.Eventos;

import modelo.DadosAplicacao;
import modelo.Data;
import modelo.Distrito;
import modelo.Evento;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Oficina.JanelaOficina;
import vista.Sucesso;
import vista.Transacoes.JanelaTransacoes;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class JanelaRemoverEventoNaoDecorrido extends JFrame{
    private JButton estatisticasButton;
    private JButton veiculosButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton oficinaButton;
    private JButton eventosButton;
    private JButton removerEventoButton;
    private JList<Evento> listaEventos;
    private JComboBox comboBoxDistritos;
    private JTextField dataIniciotextField;
    private JTextField dataFimTextField;
    private JButton apresentarEventosNaoDecorridosButton;
    private JButton cancelarButton;
    private JPanel painel;

    private DefaultComboBoxModel modeloComboBoxDistritos;
    private DefaultListModel modeloListaEventos;

    public JanelaRemoverEventoNaoDecorrido(){
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
        modeloComboBoxDistritos = new DefaultComboBoxModel();
        modeloListaEventos = new DefaultListModel();
        comboBoxDistritos.setModel(modeloComboBoxDistritos);
        listaEventos.setModel(modeloListaEventos);
        initComponents();

        apresentarEventosNaoDecorridosButton.addActionListener(this::btnApresentarEventosActionPerformed);
        removerEventoButton.addActionListener(this::btnRemoverEventoActionPerformed);
    }

    private void btnRemoverEventoActionPerformed(ActionEvent evt) {
        boolean valido = !listaEventos.isSelectionEmpty();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_EVENTO);
            return;
        }

        Evento evento = listaEventos.getSelectedValue();
        DadosAplicacao da = DadosAplicacao.INSTANCE;
        da.removerEvento(evento);
        Sucesso.mostrarSucesso(this, Sucesso.EVENTO_REMOVIDO);
        modeloListaEventos.removeAllElements();
    }

    private void btnApresentarEventosActionPerformed(ActionEvent evt) {
        String dataInicio = dataIniciotextField.getText();
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
        List<Evento> eventos = da.getEventosNaoDecorridos(distrito, inicio, fim);
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
}
