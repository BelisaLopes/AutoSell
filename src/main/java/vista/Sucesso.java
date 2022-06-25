package vista;

import javax.swing.*;
import java.awt.*;

public class Sucesso {
    public static final int VEICULO_ADICIONADO_EVENTO = 1;
    public static final int VENDA_VEICULO_EFETUADA = 2;
    public static final int VEICULO_REGISTADO = 3;
    public static final int CLIENTE_ATUALIZADO = 4;
    public static final int CLIENTE_REGISTADO = 5;

    public static void mostrarSucesso(Window parent, int numero){
        switch (numero) {
            case VEICULO_ADICIONADO_EVENTO:
                JOptionPane.showMessageDialog(parent, "O veículo foi registado ao evento com sucesso");
                break;
            case VENDA_VEICULO_EFETUADA:
                JOptionPane.showMessageDialog(parent, "Venda efetuada com sucesso!");
                break;
            case VEICULO_REGISTADO:
                JOptionPane.showMessageDialog(parent, "Veículo registado com sucesso");
                break;
            case CLIENTE_ATUALIZADO:
                JOptionPane.showMessageDialog(parent, "Cliente atualizado com sucesso");
                break;
            case CLIENTE_REGISTADO:
                JOptionPane.showMessageDialog(parent, "Cliente registado com sucesso");
                break;
            default:
        }
    }
}
