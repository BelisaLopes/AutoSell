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
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.util.List;

public class JanelaQuantidadeMinimaPecas extends JFrame{
    private JButton btnVeiculos;
    private JButton btnEstatisticas;
    private JButton btnClientes;
    private JButton btnTransacoes;
    private JButton btnEventos;
    private JButton btnOficina;
    private JTextField textFieldNovaQtd;
    private JButton btnAtualizar;
    private JComboBox comboBoxLocais;
    private JButton btnApresentarPecas;
    private JComboBox comboBoxCategorias;
    private JButton btnCancelar;
    private JPanel painelPrincipal;
    private JList listPecas;
    private JLabel labelQtdMinima;
    private JLabel labelStock;

    private DefaultComboBoxModel modeloComboBoxCategorias;
    private DefaultComboBoxModel modeloComboBoxLocais;
    private DefaultListModel modeloListaPecas;

    public JanelaQuantidadeMinimaPecas(){
        setContentPane(painelPrincipal);
        pack();

        modeloComboBoxCategorias = new DefaultComboBoxModel();
        modeloComboBoxLocais = new DefaultComboBoxModel();
        modeloListaPecas = new DefaultListModel();

        listPecas.setModel(modeloListaPecas);
        comboBoxCategorias.setModel(modeloComboBoxCategorias);
        comboBoxLocais.setModel(modeloComboBoxLocais);

        initComponents();

        btnApresentarPecas.addActionListener(this::apresentarPecas);
        btnCancelar.addActionListener(this::cancelar);
        btnAtualizar.addActionListener(this::atualizarQuantidade);
        comboBoxLocais.addActionListener(this::showStockCmBox);
        listPecas.addListSelectionListener(this::showStockList);

        btnVeiculos.addActionListener(this::abrirVeiculos);
        btnOficina.addActionListener(this::abrirOficina);
        btnEventos.addActionListener(this::abrirEventos);
        btnClientes.addActionListener(this::abrirClientes);
        btnEstatisticas.addActionListener(this::abrirEstatisticas);
        btnTransacoes.addActionListener(this::abrirTransacoes);
    }

    private void showStockList(ListSelectionEvent listSelectionEvent) {
        if(escolheuPeca()) {
            atualizarLabels();
        }
    }

    private void showStockCmBox(ActionEvent actionEvent) {
        if(escolheuPeca()) {
            atualizarLabels();
        }
    }

    private void atualizarLabels() {
        Estabelecimento estabelecimento = (Estabelecimento) comboBoxLocais.getSelectedItem();
        String nomePeca = (String) listPecas.getSelectedValue();
        Peca peca = DadosAplicacao.INSTANCE.getPeca(nomePeca);
        Oficina oficina = estabelecimento.getOficina();
        labelQtdMinima.setText(Integer.toString(oficina.getLimiteMinimoPeca(peca)));
        labelStock.setText(Integer.toString(oficina.getStockPeca(peca)));
    }

    private void atualizarQuantidade(ActionEvent actionEvent) {
        System.out.println("Click no atualizarQuantidade");
        boolean valido = escolheuPeca();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_PECA);
            return;
        }

        valido = !isQtdCampoVazio();
        if(!valido){
            Erros.mostrarErro(this, Erros.CAMPO_VAZIO);
            return;
        }

        String textQtdNovaQtd = textFieldNovaQtd.getText();
        valido = isQuantidadeValida(textQtdNovaQtd);
        if(!valido){
            Erros.mostrarErro(this, Erros.QUANTIDADE_INVALIDA);
            return;
        }

        valido = !isEqualToMinAtual(textQtdNovaQtd);
        if(!valido){
            Erros.mostrarErro(this, Erros.QUANTIDADE_NOVA_IGUAL_ATUAL);
            return;
        }

        valido = !isSuperiorAoStock(textQtdNovaQtd);
        if(!valido){
            Erros.mostrarErro(this, Erros.QUANTIDADE_SUPERIOR_STOCK);
            return;
        }

        valido = !isFilialEQtdSupSede(textQtdNovaQtd);
        if(!valido){
            Erros.mostrarErro(this, Erros.QUANTIDADE_SEDE_INFERIOR_FILIAIS);
            return;
        }

        atualizarQuantidadeMin(textQtdNovaQtd);
        Sucesso.mostrarSucesso(this, Sucesso.QUANTIDADE_ATUALIZADA);
        fechar();
        new JanelaOficina();
    }

    private void atualizarQuantidadeMin(String qtdMinima) {
        Estabelecimento estabelecimento = (Estabelecimento) comboBoxLocais.getSelectedItem();

        String nomePeca = (String) listPecas.getSelectedValue();
        Peca peca = DadosAplicacao.INSTANCE.getPeca(nomePeca);

        int qtdMinimaInt = Integer.parseInt(qtdMinima);

        estabelecimento.getOficina().atualizarLimiteMinimoPeca(peca,qtdMinimaInt);
    }

    private boolean isSuperiorAoStock(String qtdMinima) {
        Estabelecimento estabelecimento = (Estabelecimento) comboBoxLocais.getSelectedItem();

        String nomePeca = (String) listPecas.getSelectedValue();
        Peca peca = DadosAplicacao.INSTANCE.getPeca(nomePeca);

        int qtdMinimaInt = Integer.parseInt(qtdMinima);

        return estabelecimento.getOficina().getStockPeca(peca)<qtdMinimaInt;

    }

    private boolean isFilialEQtdSupSede(String qtdMinima) {
        Estabelecimento estabelecimento = (Estabelecimento) comboBoxLocais.getSelectedItem();

        if(estabelecimento.getDistrito().name()!="LISBOA"){
            String nomePeca = (String) listPecas.getSelectedValue();
            Peca peca = DadosAplicacao.INSTANCE.getPeca(nomePeca);

            int qtdMinimaInt = Integer.parseInt(qtdMinima);
            return qtdMinimaInt > DadosAplicacao.INSTANCE.getSede().getOficina().getLimiteMinimoPeca(peca);
        }

        return false;
    }

    private boolean isEqualToMinAtual(String qtdMinima) {
        Estabelecimento estabelecimento = (Estabelecimento) comboBoxLocais.getSelectedItem();

        String nomePeca = (String) listPecas.getSelectedValue();
        Peca peca = DadosAplicacao.INSTANCE.getPeca(nomePeca);

        int qtdMinimaInt = Integer.parseInt(qtdMinima);

        return estabelecimento.getOficina().getLimiteMinimoPeca(peca)==qtdMinimaInt;
    }

    private boolean isQuantidadeValida(String qtdMinima) {
        try{
            Integer.parseInt(qtdMinima);
        }catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private boolean isQtdCampoVazio() {
        return textFieldNovaQtd.getText().length()==0;
    }

    private boolean escolheuPeca() {
        return !listPecas.isSelectionEmpty();
    }

    private void apresentarPecas(ActionEvent actionEvent) {
        System.out.println("Click no apresentarPecas");
        modeloListaPecas.removeAllElements();

        Categoria categoria;
        try{
            categoria = (Categoria) comboBoxCategorias.getSelectedItem();
        }catch (Exception ex){
            categoria = null;
        }

        List<Peca> pecas = DadosAplicacao.INSTANCE.getPecas(categoria,"","","",-1);
        if(pecas.isEmpty()){ //categoria sem pecas
            Erros.mostrarErro(this, Erros.CATEGORIA_SEM_PECAS);
            return;
        }

        atualizarListaPecas(pecas);
    }

    private void atualizarListaPecas(List<Peca> pecas) {
        for (Peca peca : pecas) {
            modeloListaPecas.add(modeloListaPecas.getSize(),peca.getNome());
        }
    }

    private void initComponents() {
        modeloListaPecas.removeAllElements();

        DadosAplicacao da = DadosAplicacao.INSTANCE;
        List<Categoria> catalogo = da.getCatalogo();
        modeloComboBoxCategorias.addElement("Todas as Categorias");
        for(Categoria categoria : catalogo){
            modeloComboBoxCategorias.addElement(categoria);
        }
        List<Filial> filiais = da.getFiliais();
        Sede sede = da.getSede();
        modeloComboBoxLocais.addElement(sede);
        for(Filial filial : filiais){
            modeloComboBoxLocais.addElement(filial);
        }

        labelQtdMinima.setText("n/a");
        labelStock.setText("n/a");
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

    private void cancelar(ActionEvent actionEvent) {
        System.out.println("Click no cancelar");
        fechar();
        new JanelaOficina();
    }

    public void fechar(){
        setVisible(false);
        dispose();
    }
}
