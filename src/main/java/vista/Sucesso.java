package vista;

import javax.swing.*;
import java.awt.*;

public class Sucesso {
    public static final int VEICULO_ADICIONADO_EVENTO = 1;
    public static final int VENDA_VEICULO_EFETUADA = 2;
    public static final int VEICULO_REGISTADO = 3;
    public static final int CLIENTE_ATUALIZADO = 4;
    public static final int CLIENTE_REGISTADO = 5;
    public static final int EVENTO_REGISTADO = 6;
    public static final int EVENTO_ATUALIZADO = 7;
    public static final int CATEGORIA_REGISTADA = 8;
    public static final int CATEGORIA_REMOVIDA = 9;
    public static final int PECA_REGISTADA = 10;
    public static final int PECA_ENCOMENDADA = 11;
    public static final int EVENTO_REMOVIDO = 12;
    public static final int ESTADO_VEICULO_ALTERADO = 13;

    public static void mostrarSucesso(Window parent, int numero){
        switch (numero) {
            case VEICULO_ADICIONADO_EVENTO:
                JOptionPane.showMessageDialog(parent, "O veículo foi registado ao evento com sucesso!");
                break;
            case VENDA_VEICULO_EFETUADA:
                JOptionPane.showMessageDialog(parent, "Venda efetuada com sucesso!");
                break;
            case VEICULO_REGISTADO:
                JOptionPane.showMessageDialog(parent, "Veículo registado com sucesso!");
                break;
            case CLIENTE_ATUALIZADO:
                JOptionPane.showMessageDialog(parent, "Cliente atualizado com sucesso!");
                break;
            case CLIENTE_REGISTADO:
                JOptionPane.showMessageDialog(parent, "Cliente registado com sucesso!");
                break;
            case EVENTO_REGISTADO:
                JOptionPane.showMessageDialog(parent, "O evento foi criado com sucesso!");
                break;
            case EVENTO_ATUALIZADO:
                JOptionPane.showMessageDialog(parent, "O evento foi atualizado com sucesso!");
                break;
            case CATEGORIA_REGISTADA:
                JOptionPane.showMessageDialog(parent, "A categoria foi criada com sucesso!");
                break;
            case CATEGORIA_REMOVIDA:
                JOptionPane.showMessageDialog(parent, "A categoria foi removida com sucesso!");
                break;
            case PECA_REGISTADA:
                JOptionPane.showMessageDialog(parent, "A peça foi registada com sucesso!");
                break;
            case PECA_ENCOMENDADA:
                JOptionPane.showMessageDialog(parent, "A encomenda foi registada com sucesso!");
            case EVENTO_REMOVIDO:
                JOptionPane.showMessageDialog(parent, "O evento foi removido com sucesso!");
                break;
            case ESTADO_VEICULO_ALTERADO:
                JOptionPane.showMessageDialog(parent, "O estado do veículo foi alterado com sucesso!");
                break;
            default:
        }
    }
}
