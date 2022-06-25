package vista.Oficina;

import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaOficina extends JFrame {
    private JButton btnVeiculos;
    private JButton btnOficina;
    private JButton btnEventos;
    private JButton btnClientes;
    private JButton btnEstatisticas;
    private JButton btnTransacoes;
    private JButton btnConsultar;
    private JButton btnRegistarPeca;
    private JButton btnTransferir;
    private JButton btnAtualizarQuantidadeMin;
    private JButton btnRegistarEncomenda;
    private JButton btnRegistarCategoria;
    private JButton btnRemoverCategoria;
    private JPanel panel;
    private JPanel painelPrincipal;

    public JanelaOficina(){
        setContentPane(painelPrincipal);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        btnRegistarCategoria.addActionListener(this::registarCategoria);
        btnVeiculos.addActionListener(this::abrirVeiculos);
    }

    private void abrirVeiculos(ActionEvent actionEvent) {
        fechar();
        JanelaVeiculos.abrir();
    }

    public void registarCategoria(ActionEvent actionEvent){
        //TODO
        System.out.println("Click no registarCategoriaButton");
        JanelaAdicionarCategoria.abrir(this);
    }

    public void fechar(){
        setVisible(false);
    }

    public static void main(String[] args) {
        new JanelaOficina();
    }
}
