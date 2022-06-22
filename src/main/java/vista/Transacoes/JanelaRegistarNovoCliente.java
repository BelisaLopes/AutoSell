package vista.Transacoes;

import modelo.Cliente;
import modelo.DadosAplicacao;
import modelo.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JanelaRegistarNovoCliente extends JFrame {
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

    public JanelaRegistarNovoCliente(){
        //todo
        setContentPane(painelPrincipal); //vai ter um componente visual, entao cria-se no form
        pack();
        setVisible(true);
        registarClienteButton.addActionListener(this::registarClienteButtonActionPerformed);
        cancelarButton.addActionListener(this::cancelarButtonActionPerformed);
    }

    private void registarClienteButtonActionPerformed(ActionEvent evt) {

        System.out.println("Registar Cliente");
    }

    private void cancelarButtonActionPerformed(ActionEvent evt) {
        System.out.println("Cancelar");
        fechar();
    }

    private void fechar(){
        dispose();
    }

    private boolean isNomeValido(String nome){
        return !(nome.trim().length() < 2 || nome.trim().length() > 100);
    }
    private boolean isMoradaValida(String morada){
        return !(morada.trim().length() < 10 || morada.trim().length() > 256);
    }
    private boolean isNIFValido(String nif){
        return !(nif.trim().length() < 9 || nif.trim().length() > 9);
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
        return !(contacto.trim().length() < 9 || contacto.trim().length() > 9);
    }

    /*private boolean existeCriancaComNome(String nome){ //tem de ir aos DadosAplicacao
        DadosAplicacao da = DadosAplicacao.INSTANCE;
        return da.existeCriancaComNome(nome);
    }*/

}
