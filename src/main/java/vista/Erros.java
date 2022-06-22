package vista;

import javax.swing.*;
import java.awt.*;

public class Erros {
    public static final int NOME_EVENTO_INVALIDO = 1;
    public static final int DATA_INICIO_INVALIDA = 2;
    public static final int DATA_FIM_INVALIDA = 3;
    public static final int ORDEM_DATAS = 4;
    public static final int EVENTO_DUPLICADO = 5;
    public static final int NENHUM_RESULTADO = 50;

    public static void mostrarErro(Window parent, int numero){
        switch (numero) {
            case NOME_EVENTO_INVALIDO:
                JOptionPane.showMessageDialog(parent, "Nome do evento inválido. O nome deve conter entre 2 e 50 carateres não-brancos");
                break;
            case DATA_INICIO_INVALIDA:
                JOptionPane.showMessageDialog(parent, "Data de início inválida. A data deve ter o formato dd/mm/aaaa");
                break;
            case DATA_FIM_INVALIDA:
                JOptionPane.showMessageDialog(parent, "Data de fim inválida. A data deve ter o formato dd/mm/aaaa");
                break;
            case ORDEM_DATAS:
                JOptionPane.showMessageDialog(parent, "Data de Início tem de ser uma data inferior à Data de Fim");
                break;
            case EVENTO_DUPLICADO:
                JOptionPane.showMessageDialog(parent, "Evento duplicado. Já existe um evento com o mesmo nome, no mesmo local e com as mesmas datas especificadas");
                break;
            case NENHUM_RESULTADO:
                JOptionPane.showMessageDialog(parent, "Não foi encontrado nenhum resultado para a pesquisa efetuada");
                break;
            default:
        }
    }
}
