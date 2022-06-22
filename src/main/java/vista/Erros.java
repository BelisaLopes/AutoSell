package vista;

import javax.swing.*;
import java.awt.*;

public class Erros {
    public static final int NOME_EVENTO_INVALIDO = 1;
    public static final int DATA_INICIO_INVALIDA = 2;
    public static final int DATA_FIM_INVALIDA = 3;
    public static final int ORDEM_DATAS = 4;
    public static final int EVENTO_DUPLICADO = 5;

    public static final int NOME_INVALIDO = 6;

    public static final int DATA_NASCIMENTO_INVALIDA = 7;

    public static final int MORADA_INVALIDA = 8;

    public static final int NIF_INVALIDO = 9;

    public static final int CONTACTO_INVALIDO = 10;

    public static final int MARCA_INVALIDA = 11;

    public static final int ANO_INVALIDO = 12;

    public static final int MATRICULA_INVALIDA = 13;

    public static final int PALAVRA_INVALIDA = 14;

    public static final int VALOR_NEGATIVO_INVALIDO = 15;

    public static final int PORTAS_INVALIDO = 16;
    public static final int NENHUM_RESULTADO = 17;

    public static final int MODELO_INVALIDO = 18;
    public static final int COR_INVALIDA = 19;
    public static final int CONDICAO_VEICULO_INVALIDO = 20;
    public static final int NUMERO_DONOS_INVALIDO = 21;
    public static final int VALOR_VEICULO_INVALIDO = 22;
    public static final int SELECAO_CLIENTE_INVALIDA = 23;
    public static final int DATA_VENDA_INVALIDA = 24;
    public static final int SELECIONAR_VEICULO_A_RECEBER = 25;
    public static final int SELECIONAR_VEICULO_VENDER = 26;
    private static final int SELECIONAR_CLIENTE_COMPRAR_VEICULO = 27;


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

            case NOME_INVALIDO:
                JOptionPane.showMessageDialog(parent, "Nome inválido. O nome deve conter entre 2 e 100 carateres não-brancos");
                break;
            case DATA_NASCIMENTO_INVALIDA:
                JOptionPane.showMessageDialog(parent, "Data de nascimento inválida. A data deve ter o formato dd/mm/aaaa");
                break;
            case MORADA_INVALIDA:
                JOptionPane.showMessageDialog(parent, "Morada inválida. A morada deve conter entre 2 e 256 carateres não-brancos");
                break;
            case NIF_INVALIDO:
                JOptionPane.showMessageDialog(parent, "NIF inválido. O NIF deve ter 9 dígitos e começar pelo número 1");
                break;
            case CONTACTO_INVALIDO:
                JOptionPane.showMessageDialog(parent, "Contacto inválido. O contacto deve conter 9 dígitos e começar pelo número 9 ou 2");
                break;
            case MARCA_INVALIDA:
                JOptionPane.showMessageDialog(parent, "Marca inválida. A marca deve conter entre 2 e 50 carateres não-brancos");
                break;
            case MODELO_INVALIDO:
                JOptionPane.showMessageDialog(parent, "Modelo inválido. O modelo deve conter entre 2 e 50 carateres não-brancos");
                break;
            case ANO_INVALIDO:
                JOptionPane.showMessageDialog(parent, "Ano inválido. O ano deve estar compreendido entre 1900 e ano corrente");
                break;
            case MATRICULA_INVALIDA:
                JOptionPane.showMessageDialog(parent, "Matrícula Inválida: Deve seguir um dos seguintes formatos: AA-00-AA, 00-00-AA, AA-00-00, 00-AA-00");
                break;
            case COR_INVALIDA:
                JOptionPane.showMessageDialog(parent, "Cor inválida. A cor deve conter entre 2 e 50 carateres não-brancos");
                break;
            case CONDICAO_VEICULO_INVALIDO:
                JOptionPane.showMessageDialog(parent, "Condição do veículo inválida. A condição do veículo deve conter entre 2 e 50 carateres não-brancos");
                break;
            case NUMERO_DONOS_INVALIDO:
                JOptionPane.showMessageDialog(parent, "Número inválido. Insira um número positivo");
                break;
            case VALOR_VEICULO_INVALIDO:
                JOptionPane.showMessageDialog(parent, "Número inválido. Insira um número positivo");
                break;
            case PORTAS_INVALIDO:
                JOptionPane.showMessageDialog(parent, "Número inválido. Insira 3 ou 5 portas");
                break;
            case NENHUM_RESULTADO:
                JOptionPane.showMessageDialog(parent, "Não foi encontrado nenhum resultado para a pesquisa efetuada");
                break;
            case SELECAO_CLIENTE_INVALIDA:
                JOptionPane.showMessageDialog(parent, "Apenas pode selecionar um cliente");
                break;
            case DATA_VENDA_INVALIDA:
                JOptionPane.showMessageDialog(parent, "Data inválida, por favor insira a data atual");
                break;
            case SELECIONAR_VEICULO_A_RECEBER:
                JOptionPane.showMessageDialog(parent, "Por favor selecione a opção “Registar veículo a receber");
                break;
            case SELECIONAR_VEICULO_VENDER:
                JOptionPane.showMessageDialog(parent, "Para concluir a venda, selecione o veículo que pretende vender");
                break;
            case SELECIONAR_CLIENTE_COMPRAR_VEICULO:
                JOptionPane.showMessageDialog(parent, "Para concluir a venda, selecione o cliente que pretende comprar o veículo");
                break;
            default:
        }
    }
}
