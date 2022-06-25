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
    private JLabel eventoOrigemLabel;
    private JLabel localDestinoLabel;

    private DefaultComboBoxModel modeloListaEventosOrigem;
    private DefaultComboBoxModel modeloListaEventosDestino;
    private DefaultListModel modeloListaVeiculos;
    private Evento eventoOrigem;
    private Local eventoDestino;
    private Veiculo veiculo;

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
        transportarVeículoButton.addActionListener(this::btnTransportarVeiculoActionPerformed);
    }

    private void btnTransportarVeiculoActionPerformed(ActionEvent evt) {
        boolean valido = !listaVeiculosEvento.isSelectionEmpty();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_VEICULO);
            return;
        }
        veiculo = listaVeiculosEvento.getSelectedValue();
        valido = veiculo != null;
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_VEICULO);
            return;
        }

        DadosAplicacao da = DadosAplicacao.INSTANCE;
        da.transportarVeiculo(veiculo,eventoDestino);
        Sucesso.mostrarSucesso(this, Sucesso.VEICULO_ADICIONADO_EVENTO);
        modeloListaVeiculos.removeAllElements();

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

        valido = isRadioButtonSelected();
        if(!valido){
            Erros.mostrarErro(this, Erros.SEM_LOCAL_DESTINO);
            return;
        }

//        boolean local_destino = transportarParaOutroEventoRadioButton.isSelected();
        if(transportarParaOutroEventoRadioButton.isSelected()){
            valido = modeloListaEventosDestino.getSize() != 0;
            if(!valido){
                Erros.mostrarErro(this, Erros.SEM_EVENTOS_A_DECORRER_OU_AGENDADOS);
                return;
            }
            eventoDestino = (Evento) modeloListaEventosDestino.getSelectedItem();
            localDestinoLabel.setText(((Evento) eventoDestino).getNome());
        }else{
            Sede sede = da.getSede();
            valido = da.getLugaresLivres(sede) > 0;
            if(!valido){
                Erros.mostrarErro(this, Erros.JA_ATINGIU_LIMITE_OCUPACAO);
                return;
            }
            eventoDestino = sede;
            localDestinoLabel.setText("Sede");
            eventoOrigemLabel.setText(eventoOrigem.getNome());
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

        DadosAplicacao da = DadosAplicacao.INSTANCE;
        List<Veiculo> veiculos = da.getVeiculosParaTransportar(eventoOrigem, eventoDestino, marca, modelo, matricula);

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

    private boolean isRadioButtonSelected(){
        return transportarParaOutroEventoRadioButton.isSelected() || transportarParaASedeRadioButton.isSelected();
    }

    private void initComponents() {
        DadosAplicacao da = DadosAplicacao.INSTANCE;
        List<Evento> eventosT = da.getEventosTerminados();
        for (Evento e : eventosT) {
            modeloListaEventosOrigem.addElement(e);
        }

        List<Evento> eventosNT = da.getEventosNaoTerminados();
        for (Evento e : eventosNT) {
            modeloListaEventosDestino.addElement(e);
        }

        ButtonGroup g = new ButtonGroup();
        g.add(transportarParaASedeRadioButton);
        g.add(transportarParaOutroEventoRadioButton);
    }

    private void btnCancelarActionPerformed(ActionEvent evt) {
        fechar();
        JanelaEventos je = new JanelaEventos();
        je.setVisible(true);
    }

    public void fechar(){
        setVisible(false);
        dispose();
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
