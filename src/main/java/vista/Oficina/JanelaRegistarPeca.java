package vista.Oficina;

import modelo.*;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Sucesso;
import vista.Transacoes.JanelaTransacoes;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class JanelaRegistarPeca extends JFrame{
    private JButton btnVeiculos;
    private JButton btnEstatisticas;
    private JButton btnClientes;
    private JButton btnTransacoes;
    private JButton btnEventos;
    private JButton btnOficina;
    private JButton btnRegistar;
    private JTextField textSede;
    private JTextField textFiliais;
    private JTextField textNome;
    private JTextField textPreco;
    private JTextField textModelo;
    private JTextField textDimensao;
    private JTextField textMarca;
    private JComboBox comboBoxCategoria;
    private JButton btnCancelar;
    private JPanel painelPrincipal;

    private DefaultComboBoxModel modeloComboBoxCategorias;

    public JanelaRegistarPeca(){
        setContentPane(painelPrincipal);
        pack();

        modeloComboBoxCategorias = new DefaultComboBoxModel();
        comboBoxCategoria.setModel(modeloComboBoxCategorias);
        initComponents();

        btnRegistar.addActionListener(this::registarPeca);
        btnCancelar.addActionListener(this::cancelar);

        btnVeiculos.addActionListener(this::abrirVeiculos);
        btnOficina.addActionListener(this::abrirOficina);
        btnEventos.addActionListener(this::abrirEventos);
        btnClientes.addActionListener(this::abrirClientes);
        btnEstatisticas.addActionListener(this::abrirEstatisticas);
        btnTransacoes.addActionListener(this::abrirTransacoes);
    }

    private void initComponents() {
        List<Categoria> catalogo = DadosAplicacao.INSTANCE.getCatalogo();
        for(Categoria categoria : catalogo){
            modeloComboBoxCategorias.addElement(categoria);
        }
    }

    private void abrirTransacoes(ActionEvent actionEvent) {
        System.out.println("Click no abrirTransacoes");
        fechar();
        new JanelaTransacoes();
    }

    private void abrirEstatisticas(ActionEvent actionEvent) {
        System.out.println("Click no abrirEstatisticas");
        fechar();
        new JanelaEstatistica();
    }

    private void abrirClientes(ActionEvent actionEvent) {
        System.out.println("Click no abrirClientes");
        fechar();
        new JanelaClientes();
    }

    private void abrirEventos(ActionEvent actionEvent) {
        System.out.println("Click no abrirEventos");
        fechar();
        new JanelaEventos();
    }

    private void abrirOficina(ActionEvent actionEvent) {
        System.out.println("Click no abrirOficina");
        fechar();
        new JanelaOficina();
    }

    private void abrirVeiculos(ActionEvent actionEvent) {
        System.out.println("Click no abrirVeiculos");
        fechar();
        new JanelaVeiculos();
    }

    private void registarPeca(ActionEvent actionEvent) {
        boolean valido = !isAlgumCampoVazio();
        if(!valido){
            Erros.mostrarErro(this, Erros.CAMPO_VAZIO);
            return;
        }

        String nome = textNome.getText();
        valido = isNomeValido(nome);
        if(!valido) {
            Erros.mostrarErro(this, Erros.NOME_INVALIDO);
            return;
        }

        valido = !existePecaComNome(nome);
        if(!valido) {
            Erros.mostrarErro(this, Erros.PECA_EXISTE);
            return;
        }

        String preco = textPreco.getText();
        valido = isPrecoValido(preco);
        if(!valido) {
            Erros.mostrarErro(this, Erros.PRECO_INVALIDO);
            return;
        }

        String marca = textMarca.getText();
        valido = isMarcaValida(marca);
        if(!valido) {
            Erros.mostrarErro(this, Erros.MARCA_INVALIDA);
            return;
        }

        String modelo = textModelo.getText();
        valido = isModeloValido(modelo);
        if(!valido) {
            Erros.mostrarErro(this, Erros.MODELO_INVALIDO);
            return;
        }

        String dimensao = textDimensao.getText();
        valido = isDimensaoValida(dimensao);
        if(!valido) {
            Erros.mostrarErro(this, Erros.DIMENSAO_INVALIDA);
            return;
        }

        String qtdSede = textSede.getText();
        String qtdFiliais = textFiliais.getText();
        valido = areQuantidadesMinimasValidas(qtdSede, qtdFiliais);
        if(!valido) {
            Erros.mostrarErro(this, Erros.QUANTIDADE_INVALIDA);
            return;
        }

        valido = isQtdMinimaFilialInferior(qtdSede, qtdFiliais);
        if(!valido) {
            Erros.mostrarErro(this, Erros.QUANTIDADE_SEDE_INFERIOR_FILIAIS);
            return;
        }

        Categoria categoria = (Categoria) comboBoxCategoria.getSelectedItem();
        double precoDouble = Double.parseDouble(preco);
        int qtdSedeInt = Integer.parseInt(qtdSede);
        int qtdFiliaisInt = Integer.parseInt(qtdFiliais);

        Peca novaPeca = new Peca(nome, marca, modelo, dimensao, precoDouble, categoria);

        DadosAplicacao.INSTANCE.adicionarPeca(novaPeca, qtdSedeInt, qtdFiliaisInt);
        Sucesso.mostrarSucesso(this, Sucesso.PECA_REGISTADA);
        fechar();
        new JanelaOficina();
    }

    private boolean isQtdMinimaFilialInferior(String qtdMinSede, String qtdMinFiliais) {
        Integer valorQtdSede;
        Integer valorQtdFilial;
        try{
            valorQtdSede = Integer.parseInt(qtdMinSede);
            valorQtdFilial = Integer.parseInt(qtdMinFiliais);
        }catch (NumberFormatException ex) {
            return false;
        }
        return valorQtdSede.intValue()>valorQtdFilial.intValue();
    }

    private boolean areQuantidadesMinimasValidas(String qtdMinSede, String qtdMinFiliais) {
        try{
            Integer.parseInt(qtdMinSede);
            Integer.parseInt(qtdMinFiliais);
        }catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private boolean isDimensaoValida(String dimensao) {
        return !(dimensao.trim().length() < 2) || (dimensao.trim().length() > 50);
    }

    private boolean isModeloValido(String modelo) {
        return !(modelo.trim().length() < 2) || (modelo.trim().length() > 50);
    }

    private boolean isMarcaValida(String marca) {
        return !(marca.trim().length() < 2) || (marca.trim().length() > 50);
    }

    private boolean isPrecoValido(String preco) {
        try{
            Double.parseDouble(preco);
        }catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private boolean existePecaComNome(String nome) {
        return DadosAplicacao.INSTANCE.existePeca(nome);
    }

    private boolean isNomeValido(String nome) {
        return !(nome.trim().length() < 2) || (nome.trim().length() > 100);
    }

    private boolean isAlgumCampoVazio() {
        return textNome.getText().length()==0 || textMarca.getText().length()==0 || textModelo.getText().length()==0 || textPreco.getText().length()==0 ||
                textDimensao.getText().length()==0 || textSede.getText().length()==0 || textFiliais.getText().length()==0;
    }

    private void cancelar(ActionEvent actionEvent){
        System.out.println("Click no cancelar");
        fechar();
        new JanelaOficina();
    }

    private void fechar(){
        setVisible(false);
        dispose();
    }
}
