package vista.Veiculos;

import modelo.Peca;

import javax.swing.*;
import java.util.Hashtable;

public class JanelaRepararVeiculo {
    private JButton estatisticasButton;
    private JButton veiculosButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JButton registarVeículoComoReparadoButton;
    private JTextField textField1;
    private JList list2;
    private JButton adicionarPeçaSButton;
    private JList list3;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JTextField textField3;
    private JList list1;
    private JButton apresentarVeículosButton;
    private JButton apresentarPeçasButton;
    private JTextField textField5;
    private JComboBox comboBox2;
    private JButton cancelarButton;

    private Hashtable<Peca, Integer> pecasUsadasNaReparacao;
}
