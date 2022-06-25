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

public class JanelaTransportarVeiculos extends JFrame{
    private JButton estatisticasButton;
    private JButton veiculosButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JButton transportarVeículoButton;
    private JList<Veiculo> listaVeiculosEvento;
    private JComboBox<Evento> eventoOrigemComboBox;
    private JComboBox<Local> eventoDestinoComboBox;
    private JTextField marcaTextField;
    private JTextField modeloTextField;
    private JTextField matriculaTextField;
    private JButton apresentarVeiculosButton;
    private JRadioButton transportarParaOutroEventoRadioButton;
    private JRadioButton transportarParaASedeRadioButton;
    private JButton cancelarButton;
    private JPanel painel;
    private JButton confirmarEventosButton;

    private DefaultComboBoxModel modeloListaEventosOrigem;
    private DefaultComboBoxModel modeloListaEventosDestino;
    private DefaultListModel modeloListaVeiculos;
    private Evento eventoOrigem;
    private Local eventoDestino;

    public JanelaTransportarVeiculos(){
        setContentPane(painel);
        pack();
        setVisible(true);

        veiculosButton.addActionListener(this::btnVeiculosActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaActionPerformed);
        eventosButton.addActionListener(this::btnEventosActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesActionPerformed);
        clientesButton.addActionListener(this::btnClientesActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasActionPerformed);
        cancelarButton.addActionListener(this::btnCancelarActionPerformed);
        modeloListaEventosOrigem = new DefaultComboBoxModel<>();
        modeloListaVeiculos = new DefaultListModel();
        modeloListaEventosDestino = new DefaultComboBoxModel();
        listaVeiculosEvento.setModel(modeloListaVeiculos);
        eventoOrigemComboBox.setModel(modeloListaEventosOrigem);
        eventoDestinoComboBox.setModel(modeloListaEventosDestino);
        initComponents();

        confirmarEventosButton.addActionListener(this::btnConfirmarEventosActionPerformed);
        apresentarVeiculosButton.addActionListener(this::btnApresentarVeiculosActionPerformed);
    }

    private void btnConfirmarEventosActionPerformed(ActionEvent evt) {
        boolean valido = modeloListaEventosOrigem.getSize() != 0;
        if(!valido){
            Erros.mostrarErro(this, Erros.SEM_EVENTOS_TERMINADOS);
            return;
        }

        eventoOrigem = (Evento) modeloListaEventosOrigem.getSelectedItem();
        DadosAplicacao da = DadosAplicacao.INSTANCE;
        valido = da.getNumeroVeiculosNoLocal(eventoOrigem) > 0;
        if(!valido){
            Erros.mostrarErro(this, Erros.EVENTO_SEM_CARROS);
            return;
        }

        valido = transportarParaOutroEventoRadioButton.isSelected() || transportarParaASedeRadioButton.isSelected();
        if(!valido){
            Erros.mostrarErro(this, Erros.SEM_LOCAL_DESTINO);
            return;
        }

        boolean local_destino = transportarParaOutroEventoRadioButton.isSelected();
        if(local_destino){
            valido = modeloListaEventosDestino.getSize() != 0;
            if(!valido){
                Erros.mostrarErro(this, Erros.SEM_EVENTOS_A_DECORRER_OU_AGENDADOS);
                return;
            }
            eventoDestino = (Evento) modeloListaEventosDestino.getSelectedItem();
        }

        local_destino = transportarParaASedeRadioButton.isSelected();

        Sede sede = da.getSede();
        if(local_destino){
            valido = da.getLugaresLivres(sede) > 0;
            if(!valido){
                Erros.mostrarErro(this, Erros.JA_ATINGIU_LIMITE_OCUPACAO);
                return;
            }
            eventoDestino = sede;
        }
    }

    private void btnApresentarVeiculosActionPerformed(ActionEvent evt) {
        boolean valido = eventoOrigem != null && eventoDestino != null;
        if(!valido){
            Erros.mostrarErro(this,Erros.SEM_LOCAL_ORIGEM_OU_LOCAL_DESTINO);
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

//        DadosAplicacao da = DadosAplicacao.INSTANCE;
//        List<Veiculo> veiculos = da.getVeiculos(evento, marca, modelo, matricula);
//
//        valido = veiculos != null;
//        if(!valido){
//            Erros.mostrarErro(this,Erros.NENHUM_RESULTADO);
//            return;
//        }
//
//        modeloListaVeiculos.removeAllElements();
//        for (Veiculo veiculo : veiculos) {
//            modeloListaVeiculos.add(modeloListaVeiculos.getSize(), veiculo);
//        }
    }

    private boolean isMatriculaValida(String matricula) {
        if(matricula.isEmpty()){
            return true;
        }
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


    private void initComponents() {
        DadosAplicacao da = DadosAplicacao.INSTANCE;
        List<Evento> eventos = da.getEventosTerminados();
        for (Evento e : eventos) {
            modeloListaEventosOrigem.addElement(e);
        }

        eventos = da.getEventosNaoTerminados();
        for (Evento e : eventos) {
            modeloListaEventosDestino.addElement(e);
        }

        ButtonGroup g = new ButtonGroup();
        g.add(transportarParaASedeRadioButton);
        g.add(transportarParaOutroEventoRadioButton);
    }

    private void btnCancelarActionPerformed(ActionEvent evt) {
        fechar();
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
        JanelaEventos j = new JanelaEventos();
        j.setVisible(true);

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
}
