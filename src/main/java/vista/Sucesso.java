package vista;

import javax.swing.*;
import java.awt.*;

public class Sucesso {
    public static final int VEICULO_ADICIONADO_EVENTO = 1;

    public static void mostrarSucesso(Window parent, int numero){
        switch (numero) {
            case VEICULO_ADICIONADO_EVENTO:
                JOptionPane.showMessageDialog(parent, "O veículo foi registado ao evento com sucesso");
                break;
            default:
        }
    }
}
