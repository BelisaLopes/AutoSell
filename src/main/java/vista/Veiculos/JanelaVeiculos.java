package vista.Veiculos;

import vista.Oficina.JanelaAdicionarCategoria;

import javax.swing.*;

public class JanelaVeiculos extends JFrame{
    private JButton veiculosButton;
    private JButton oficinaButton;
    private JButton eventosButton;
    private JButton transaçõesButton;
    private JButton estatisticasButton;
    private JButton clientesButton;
    private JButton consultarVeiculosButton;
    private JButton transferirVeiculosButton;
    private JButton definirVeículoComoReparadoButton;
    private JButton definirVeículoPorRepararButton;
    private JPanel painel;

    public JanelaVeiculos(){
        setContentPane(painel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void abrir() {
        System.out.println("abrir Veículos");
        new JanelaVeiculos();
    }
}
