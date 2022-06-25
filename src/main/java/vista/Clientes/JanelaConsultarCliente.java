package vista.Clientes;

import modelo.Cliente;
import modelo.DadosAplicacao;
import modelo.Data;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Transacoes.JanelaTransacoes;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class JanelaConsultarCliente extends JFrame {
    private JButton veículosButton;
    private JButton estatísticasButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton oficinaButton;
    private JButton eventosButton;
    private JTextField textNome;
    private JList<Cliente> listClientes;
    private JTextField textNIF;
    private JButton apresentarClientesButton;
    private JButton escolherClienteButton;
    private JLabel NomeLabel;
    private JLabel DataNascimentoLabel;
    private JLabel MoradaLabel;
    private JLabel NIFLabel;
    private JLabel ContactoLabel;
    private JPanel painelPrincipal;

    private Cliente cliente;

    private DefaultListModel modeloListaClientes;

    public JanelaConsultarCliente() {
        setContentPane(painelPrincipal);
        pack();
        setVisible(true);

        veículosButton.addActionListener(this::btnVeiculosButtonActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaButtonActionPerformed);
        eventosButton.addActionListener(this::btnEventosButtonActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesButtonActionPerformed);
        clientesButton.addActionListener(this::btnClientesButtonActionPerformed);
        estatísticasButton.addActionListener(this::btnEstatisticasButtonActionPerformed);
        modeloListaClientes = new DefaultListModel();
        listClientes.setModel(modeloListaClientes);

        apresentarClientesButton.addActionListener(this::btnApresentarClientesActionPerformed);
        escolherClienteButton.addActionListener(this::btnEscolherClienteActionPerformed);
    }

    private void btnEscolherClienteActionPerformed(ActionEvent evt) {
        boolean valido = !listClientes.isSelectionEmpty();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_CLIENTE);
        }

        cliente = listClientes.getSelectedValue();
        NomeLabel.setText(cliente.getNome());
        DataNascimentoLabel.setText(cliente.getDataNascimento().toString());
        MoradaLabel.setText(cliente.getMorada());
        NIFLabel.setText(cliente.getNIF());
        ContactoLabel.setText(cliente.getContacto());
    }

    private void btnApresentarClientesActionPerformed(ActionEvent evt) {
        String nome = textNome.getText();
        boolean valido = isNomeValido(nome);
        if(!valido){
            Erros.mostrarErro(this, Erros.NOME_INVALIDO);
            return;
        }

        String nif = textNIF.getText();
        valido = isNIFValido(nif);
        if(!valido){
            Erros.mostrarErro(this, Erros.NIF_INVALIDO);
            return;
        }


        DadosAplicacao dados = DadosAplicacao.INSTANCE;
        List<Cliente> clientes = dados.getClientes(nome, nif);
        if(clientes == null){
            modeloListaClientes.removeAllElements();
            Erros.mostrarErro(this, Erros.NENHUM_RESULTADO);
            return;
        }
        modeloListaClientes.removeAllElements();
        for (Cliente cliente : clientes) {
            modeloListaClientes.add(modeloListaClientes.getSize(),cliente);
        }
    }

    private boolean isNomeValido(String nome){
        return !(nome.trim().length() < 2 || nome.trim().length() > 100);
    }

    private boolean isNIFValido(String nif){
        return !(nif.trim().length() < 9 || nif.trim().length() > 9 || !nif.matches("(1)?[0-9]{8}"));
    }



    public void fechar(){
        setVisible(false);
        dispose();
        JanelaClientes j = new JanelaClientes();
        j.setVisible(true);
    }

    private void btnVeiculosButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaVeiculos j = new JanelaVeiculos();
        j.setVisible(true);

    }

    private void btnOficinaButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaOficina j = new JanelaOficina();
        j.setVisible(true);
    }

    private void btnEventosButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaEventos j = new JanelaEventos();
        j.setVisible(true);
    }

    private void btnTransacoesButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaTransacoes j = new JanelaTransacoes();
        j.setVisible(true);
    }

    private void btnClientesButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaClientes j = new JanelaClientes();
        j.setVisible(true);
    }

    private void btnEstatisticasButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaEstatistica j = new JanelaEstatistica();
        j.setVisible(true);
    }



}
