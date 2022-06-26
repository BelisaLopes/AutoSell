package vista.Transacoes;

import modelo.*;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class JanelaHistoricoTransacoes extends JFrame{
    private JButton btnEstatisticas;
    private JButton btnVeiculos;
    private JButton btnEventos;
    private JButton btnClientes;
    private JButton btnTransacoes;
    private JButton btnOficina;
    private JComboBox comboBoxLocais;
    private JTextField textFieldData;
    private JButton btnApresentarTransacoes;
    private JList<String> listTransacoes;
    private JPanel painelPrincipal;

    private DefaultComboBoxModel modeloComboBoxLocais;
    private DefaultListModel modeloListaTransacoes;

    public JanelaHistoricoTransacoes(){
        setContentPane(painelPrincipal);
        pack();

        modeloComboBoxLocais = new DefaultComboBoxModel();
        modeloListaTransacoes = new DefaultListModel();

        listTransacoes.setModel(modeloListaTransacoes);
        comboBoxLocais.setModel(modeloComboBoxLocais);

        initComponents();

        btnApresentarTransacoes.addActionListener(this::apresentarTransacoes);

        btnVeiculos.addActionListener(this::abrirVeiculos);
        btnOficina.addActionListener(this::abrirOficina);
        btnEventos.addActionListener(this::abrirEventos);
        btnClientes.addActionListener(this::abrirClientes);
        btnEstatisticas.addActionListener(this::abrirEstatisticas);
        btnTransacoes.addActionListener(this::abrirTransacoes);
    }

    private void apresentarTransacoes(ActionEvent actionEvent) {
        System.out.println("Click no apresentarTransacoes");
        modeloListaTransacoes.removeAllElements();
        String dataString = textFieldData.getText();
        Data data;
        if(dataString.isEmpty()){
            data = null;
        }else{
            data = Data.parseData(dataString);
        }

        Local local;
        try{
            local = (Local) comboBoxLocais.getSelectedItem();
        }catch (Exception ex){
            local = null;
        }

        List<Transacao> transacoes = DadosAplicacao.INSTANCE.getTransacoes(local, data);
        boolean valido = !transacoes.isEmpty();
        if(!valido){
            Erros.mostrarErro(this, Erros.NENHUM_RESULTADO);
            return;
        }

        atualizarListaTransacoes(transacoes);
    }

    private void atualizarListaTransacoes(List<Transacao> transacoes) {
        for (Transacao transacao : transacoes) {
            modeloListaTransacoes.add(modeloListaTransacoes.getSize(),transacao.toString());
        }
    }

    private void initComponents() {
        modeloListaTransacoes.removeAllElements();
        textFieldData.setText("");

        DadosAplicacao da = DadosAplicacao.INSTANCE;
        List<Filial> filiais = da.getFiliais();

        modeloComboBoxLocais.addElement("Todos os Locais");
        Sede sede = da.getSede();
        modeloComboBoxLocais.addElement(sede);
        for(Filial filial : filiais){
            modeloComboBoxLocais.addElement(filial);
        }
    }

    private void abrirTransacoes(ActionEvent actionEvent) {
        System.out.println("Click no abrirTransacoes");
        fechar();
        new JanelaTransacoes();
    }

    private void abrirEstatisticas(ActionEvent actionEvent) {
        System.out.println("Click no abrirEstatisticas");
        fechar();
        new JanelaEstatistica();
    }

    private void abrirClientes(ActionEvent actionEvent) {
        System.out.println("Click no abrirClientes");
        fechar();
        new JanelaClientes();
    }

    private void abrirEventos(ActionEvent actionEvent) {
        System.out.println("Click no abrirEventos");
        fechar();
        new JanelaEventos();
    }

    private void abrirOficina(ActionEvent actionEvent) {
        System.out.println("Click no abrirOficina");
        fechar();
        new JanelaOficina();
    }

    private void abrirVeiculos(ActionEvent actionEvent) {
        System.out.println("Click no abrirVeiculos");
        fechar();
        new JanelaVeiculos();
    }

    public void fechar(){
        setVisible(false);
        dispose();
    }
}
