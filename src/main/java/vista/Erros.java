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

    public static final int QUILOMETROS_INVALIDOS = 15;

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
    public static final int SELECIONAR_CLIENTE_COMPRAR_VEICULO = 27;
    public static final int SELECIONAR_EVENTO = 28;
    public static final int SELECIONAR_CLIENTE = 29;
    public static final int CLIENTE_EXISTENTE = 30;

    public static final int CATEGORIA_EXISTE = 31;

    public static final int SELECIONAR_VEICULO = 32;
    public static final int EVENTO_SEM_CARROS = 33;
    public static final int SEM_LOCAL_DESTINO = 34;
    public static final int JA_ATINGIU_LIMITE_OCUPACAO = 35;
    public static final int SEM_EVENTOS_TERMINADOS = 36;
    public static final int SEM_EVENTOS_A_DECORRER_OU_AGENDADOS = 37;
    public static final int SEM_LOCAL_ORIGEM_OU_LOCAL_DESTINO = 38;

    public static final int CATEGORIA_SEM_PECA_NAO_EXISTE = 39;
    public static final int SELECIONAR_CATEGORIA = 40;
    public static final int NAO_EXISTEM_CATEGORIAS = 41;
    public static final int CAMPO_VAZIO = 42;
    public static final int PECA_EXISTE = 43;
    public static final int PRECO_INVALIDO = 44;
    public static final int QUANTIDADE_INVALIDA = 45;
    public static final int DIMENSAO_INVALIDA = 46;
    public static final int QUANTIDADE_SEDE_INFERIOR_FILIAIS = 47;
    public static final int NAO_EXISTEM_PECAS = 48;
    public static final int CATEGORIA_SEM_PECAS = 49;
    public static final int LISTA_VEICULOS_VAZIA = 50;
    public static final int SELECIONAR_PECA = 51;
    public static final int QUANTIDADE_NOVA_IGUAL_ATUAL = 52;
    public static final int QUANTIDADE_SUPERIOR_STOCK = 53;
    public static final int VEICULO_AINDA_EM_REPARACAO = 54;
    public static final int TIPO_QUANTIDADE_PECA = 55;
    public static final int QUANTIDADE_PECA_INVALIDA = 56;
    public static final int SEM_STOCK = 57;
//    public static final int QUANTIDADE_SUPERIOR_STOCK = 58;
    public static final int QUANTIDADE_STOCK_INSUFICIENTE = 59;
    public static final int LOCAL_ORIGEM_IGUAL_LOCAL_DESTINO = 60;
    public static final int REGISTAR_VEICULO = 61;
    public static final int FALHA_REGISTO_VEICULO = 62;
    public static final int FALHA_REGISTO_CLIENTE = 63;
    public static final int NENHUM_VEICULO_EM_REPARACAO = 64;


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
                JOptionPane.showMessageDialog(parent, "Evento duplicado. Já existe um evento com o mesmo nome e com as mesmas datas especificadas");
                break;

            case NOME_INVALIDO:
                JOptionPane.showMessageDialog(parent, "Nome inválido. O nome deve conter entre 2 e 100 carateres não-brancos");
                break;
            case DATA_NASCIMENTO_INVALIDA:
                JOptionPane.showMessageDialog(parent, "Data de nascimento inválida. A data deve ter o formato dd/mm/aaaa");
                break;
            case MORADA_INVALIDA:
                JOptionPane.showMessageDialog(parent, "Morada inválida. A morada deve conter entre 10 e 256 carateres não-brancos");
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
                JOptionPane.showMessageDialog(parent, "Número de donos inválido. Insira um número positivo");
                break;
            case VALOR_VEICULO_INVALIDO:
                JOptionPane.showMessageDialog(parent, "Valor do veiculo inválido. Insira um número positivo");
                break;
            case QUILOMETROS_INVALIDOS:
                JOptionPane.showMessageDialog(parent, "Número de quilómetros inválido. Insira um número positivo");
                break;
            case PORTAS_INVALIDO:
                JOptionPane.showMessageDialog(parent, "Número de portas inválido. Insira 3 ou 5 portas");
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
            case SELECIONAR_EVENTO:
                JOptionPane.showMessageDialog(parent, "Para continuar é necessário selecionar um evento primeiro");
                break;
            case SELECIONAR_CLIENTE:
                JOptionPane.showMessageDialog(parent, "Para continuar é necessário selecionar um cliente primeiro");
                break;
            case CLIENTE_EXISTENTE:
                JOptionPane.showMessageDialog(parent, "Já existe um cliente com o mesmo nif.");
                break;
            case CATEGORIA_EXISTE:
                JOptionPane.showMessageDialog(parent, "Já existe uma categoria com esse nome. Por favor introduza um nome diferente.");
                break;
            case SELECIONAR_VEICULO:
                JOptionPane.showMessageDialog(parent, "Para continuar é necessário selecionar um veículo primeiro");
                break;
            case EVENTO_SEM_CARROS:
                JOptionPane.showMessageDialog(parent, "O evento selecionado não tem carros registados.");
                break;
            case SEM_LOCAL_DESTINO:
                JOptionPane.showMessageDialog(parent, "Para continuar é necessário selecionar um local destino primeiro.");
                break;
            case JA_ATINGIU_LIMITE_OCUPACAO:
                JOptionPane.showMessageDialog(parent, "O local destino já atingiu o limite de ocupação");
                break;
            case SEM_EVENTOS_TERMINADOS:
                JOptionPane.showMessageDialog(parent, "Não existem eventos terminados à data atual.");
                break;
            case SEM_EVENTOS_A_DECORRER_OU_AGENDADOS:
                JOptionPane.showMessageDialog(parent, "Não existem eventos agendados/a decorrer à data atual.");
                break;
            case SEM_LOCAL_ORIGEM_OU_LOCAL_DESTINO:
                JOptionPane.showMessageDialog(parent, "Para avançar é necessário primeiro escolher um evento origem e um local destino.");
                break;
            case CATEGORIA_SEM_PECA_NAO_EXISTE:
                JOptionPane.showMessageDialog(parent, "Não existem categorias sem peças associadas.");
                break;
            case SELECIONAR_CATEGORIA:
                JOptionPane.showMessageDialog(parent, "Para continuar é necessário selecionar uma categoria primeiro");
                break;
            case NAO_EXISTEM_CATEGORIAS:
                JOptionPane.showMessageDialog(parent, "Não existem categorias registadas. Registe primeiro uma categoria");
                break;
            case CAMPO_VAZIO:
                JOptionPane.showMessageDialog(parent, "O preenchimento de todos os campos é obrigatório.");
                break;
            case PECA_EXISTE:
                JOptionPane.showMessageDialog(parent, "Já existe uma peça com esse nome. Por favor introduza um nome diferente.");
                break;
            case PRECO_INVALIDO:
                JOptionPane.showMessageDialog(parent, "Preço inválido. O preço deve conter apenas números e, no máximo, uma vírugla (representada com '.').");
                break;
            case QUANTIDADE_INVALIDA:
                JOptionPane.showMessageDialog(parent, "Quantidade inválida. A quantidade deve conter apenas números.");
                break;
            case DIMENSAO_INVALIDA:
                JOptionPane.showMessageDialog(parent, "Dimensão inválida. A dimensão deve conter entre 1 e 25 carateres não-brancos.");
                break;
            case QUANTIDADE_SEDE_INFERIOR_FILIAIS:
                JOptionPane.showMessageDialog(parent, "A quantidade mínima para a sede deve ser superior à das filiais.");
                break;
            case NAO_EXISTEM_PECAS:
                JOptionPane.showMessageDialog(parent, "Não existem peças registadas. Registe primeiro uma peça.");
                break;
            case CATEGORIA_SEM_PECAS:
                JOptionPane.showMessageDialog(parent, "A categoria selecionada não tem peças associadas.");
                break;
            case LISTA_VEICULOS_VAZIA:
                JOptionPane.showMessageDialog(parent, "A lista de veículos está vazia.");
                break;
            case SELECIONAR_PECA:
                JOptionPane.showMessageDialog(parent, "Para continuar é necessário selecionar uma peça primeiro.");
                break;
            case QUANTIDADE_NOVA_IGUAL_ATUAL:
                JOptionPane.showMessageDialog(parent, "Quantidade inválida. A quantidade mínima introduzida é igual à quantidade atual.");
                break;
            case QUANTIDADE_SUPERIOR_STOCK:
                JOptionPane.showMessageDialog(parent, "Quantidade inválida. A quantidade introduzida é superior ao stock atual da peça.");
                break;
            case VEICULO_AINDA_EM_REPARACAO:
                JOptionPane.showMessageDialog(parent, "Ainda não terminou a reparação do veículo previamente selecionado. Para continuar, defina-o como reparado");
                break;
            case TIPO_QUANTIDADE_PECA:
                JOptionPane.showMessageDialog(parent, "Valor inválido : o valor da quantidade de peças deve ser um número inteiro");
                break;
            case QUANTIDADE_PECA_INVALIDA:
                JOptionPane.showMessageDialog(parent, "Valor inválido : o valor da quantidade de peças deve ser um número positivo");
                break;
            case SEM_STOCK:
                JOptionPane.showMessageDialog(parent, "Valor inválido : não existe essa quantidade de stock disponível para a peça selecionada");
                break;
            case QUANTIDADE_STOCK_INSUFICIENTE:
                JOptionPane.showMessageDialog(parent, "Quantidade inválida. A quantidade introduzida iria colocar o stock abaixo da quantidade mínima.");
                break;
            case LOCAL_ORIGEM_IGUAL_LOCAL_DESTINO:
                JOptionPane.showMessageDialog(parent, "O local origem escolhido é igual ao local destino. Escolha locais diferentes para transferir.");
                break;
            case REGISTAR_VEICULO:
                JOptionPane.showMessageDialog(parent, "Para continuar é necessário registar um veículo primeiro.");
                break;
            case FALHA_REGISTO_VEICULO:
                JOptionPane.showMessageDialog(parent, "Ocorreu uma falha no registo do veículo.");
                break;
            case FALHA_REGISTO_CLIENTE:
                JOptionPane.showMessageDialog(parent, "Ocorreu uma falha no registo do cliente.");
                break;
            case NENHUM_VEICULO_EM_REPARACAO:
                JOptionPane.showMessageDialog(parent, "Não existe nenhum veículo em reparação. É necessário adicionar peças a um veículo primeiro.");
                break;
            default:
        }
    }
}
