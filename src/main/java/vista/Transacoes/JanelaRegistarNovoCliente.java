package vista.Transacoes;

import modelo.Cliente;
import modelo.Data;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JanelaRegistarNovoCliente extends JDialog {
    private JButton estatisticasButton;
    private JButton veiculosButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton oficinaButton;
    private JButton eventosButton;
    private JTextField textNome;
    private JTextField textDataNascimento;
    private JTextField textMorada;
    private JTextField textNIF;
    private JTextField textContacto;
    private JButton registarClienteButton;
    private JButton cancelarButton;
    private JPanel painelPrincipal;

    private Cliente cliente;

    public JanelaRegistarNovoCliente(Frame parent, boolean modal) {
        super(parent, modal);
        setContentPane(painelPrincipal);
        pack();

        veiculosButton.addActionListener(this::btnVeiculosButtonActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaButtonActionPerformed);
        eventosButton.addActionListener(this::btnEventosButtonActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesButtonActionPerformed);
        clientesButton.addActionListener(this::btnClientesButtonActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasButtonActionPerformed);
        registarClienteButton.addActionListener(this::registarClienteButtonActionPerformed);
        cancelarButton.addActionListener(this::cancelarButtonActionPerformed);
    }

    public static Cliente mostrarCliente(Frame parent) {
        var registoNovoCliente = new JanelaRegistarNovoCliente(parent, true);
        registoNovoCliente.setLocationRelativeTo(parent);
        registoNovoCliente.setVisible(true);
        return registoNovoCliente.getCliente();
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void btnEventosButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no botão Eventos");
        this.setVisible(false);
        dispose();

        JanelaEventos j = new JanelaEventos();
        j.setVisible(true);
    }

    private void btnTransacoesButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no botão Transações");
        this.setVisible(false);
        dispose();

        JanelaTransacoes j = new JanelaTransacoes();
        j.setVisible(true);
    }

    private void btnClientesButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no botão Clientes");
        this.setVisible(false);
        dispose();

        JanelaClientes j = new JanelaClientes();
        //j.setVisible(true);
    }

    private void btnEstatisticasButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no botão Estatisticas");
        this.setVisible(false);
        dispose();

        JanelaEstatistica j = new JanelaEstatistica();
        //j.setVisible(true);
    }

    private void btnVeiculosButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no botão Veiculos");
        this.setVisible(false);
        dispose();

        JanelaVeiculos j = new JanelaVeiculos();
        //j.setVisible(true);
    }

    private void btnOficinaButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no botão Oficina");
        this.setVisible(false);
        dispose();

        JanelaOficina j = new JanelaOficina();
        //j.setVisible(true);
    }


    private void registarClienteButtonActionPerformed(ActionEvent evt) {
        boolean valido = isNomeValido(textNome.getText());
        if(!valido){
            Erros.mostrarErro(this, Erros.NOME_INVALIDO);
            return;
        }
        valido = isDataNascimentoValida(textDataNascimento.getText());
        if(!valido){
            Erros.mostrarErro(this, Erros.DATA_NASCIMENTO_INVALIDA);
            return;
        }
        Data data = Data.parseData(textDataNascimento.getText());

        valido = isMoradaValida(textMorada.getText());
        if(!valido){
            Erros.mostrarErro(this, Erros.MORADA_INVALIDA);
            return;
        }

        valido = isNIFValido(textNIF.getText());
        if(!valido){
            Erros.mostrarErro(this, Erros.NIF_INVALIDO);
            return;
        }

        valido = isContactoValido(textContacto.getText());
        if(!valido){
            Erros.mostrarErro(this, Erros.CONTACTO_INVALIDO);
            return;
        }

        cliente = new Cliente(textNome.getText(), textMorada.getText(), data, textNIF.getText(), textContacto.getText());
        JOptionPane.showMessageDialog(this, "Cliente registado com sucesso!");
        fechar();
    }

    private void cancelarButtonActionPerformed(ActionEvent evt) {
        System.out.println("Cancelar");
        fechar();
    }

    private void fechar(){
        setVisible(false);
    }

    private boolean isNomeValido(String nome){
        return !(nome.trim().length() < 2 || nome.trim().length() > 100);
    }
    private boolean isMoradaValida(String morada){
        return !(morada.trim().length() < 10 || morada.trim().length() > 256);
    }
    private boolean isNIFValido(String nif){
        return !(nif.trim().length() < 9 || nif.trim().length() > 9 || !nif.matches("(1)?[0-9]{8}"));
    }

    private boolean isDataNascimentoValida(String data){
        Data data_final = Data.parseData(data);
        if(data_final == null){
            return false;
        }
        int dia = data_final.getDia();
        int mes = data_final.getMes();
        int ano = data_final.getAno();

        return (dia > 0 && dia < 32 && mes > 0 && mes < 13 && ano > 1900 && ano < 2022);
    }

    private boolean isContactoValido(String contacto){
        return !(contacto.trim().length() < 9 || contacto.trim().length() > 9 || !contacto.trim().matches("(2|9)?[0-9]{8}"));
    }

    /*private boolean existeCriancaComNome(String nome){ //tem de ir aos DadosAplicacao
        DadosAplicacao da = DadosAplicacao.INSTANCE;
        return da.existeCriancaComNome(nome);
    }*/

}
