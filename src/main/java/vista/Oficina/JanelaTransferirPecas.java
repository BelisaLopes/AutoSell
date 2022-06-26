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

public class JanelaTransferirPecas extends JFrame{
    private JButton btnEstatisticas;
    private JButton btnVeiculos;
    private JButton btnClientes;
    private JButton btnTransacoes;
    private JButton btnEventos;
    private JButton btnOficina;
    private JTextField textFieldQtdTransferir;
    private JButton btnTransferir;
    private JList listPecas;
    private JComboBox comboBoxLocaisOrigem;
    private JComboBox comboBoxLocaisDestino;
    private JButton btnApresentarPecas;
    private JComboBox comboBoxCategorias;
    private JButton btnCancelar;
    private JLabel labelStock_QtdMin_Origem;
    private JLabel labelStock_QtdMin_Destino;
    private JPanel painelPrincipal;

    private DefaultComboBoxModel modeloComboBoxCategorias;
    private DefaultComboBoxModel modeloComboBoxLocaisOrigem;
    private DefaultComboBoxModel modeloComboBoxLocaisDestino;
    private DefaultListModel modeloListaPecas;

    public JanelaTransferirPecas(){
        setContentPane(painelPrincipal);
        pack();

        modeloComboBoxCategorias = new DefaultComboBoxModel();
        modeloComboBoxLocaisOrigem = new DefaultComboBoxModel();
        modeloComboBoxLocaisDestino = new DefaultComboBoxModel();
        modeloListaPecas = new DefaultListModel();

        listPecas.setModel(modeloListaPecas);
        comboBoxCategorias.setModel(modeloComboBoxCategorias);
        comboBoxLocaisOrigem.setModel(modeloComboBoxLocaisOrigem);
        comboBoxLocaisDestino.setModel(modeloComboBoxLocaisDestino);

        initComponents();

        btnApresentarPecas.addActionListener(this::apresentarPecas);
        btnCancelar.addActionListener(this::cancelar);
        btnTransferir.addActionListener(this::transferirPecas);
        comboBoxLocaisOrigem.addActionListener(this::showStockCmBoxOrigem);
        comboBoxLocaisDestino.addActionListener(this::showStockCmBoxDestino);
        listPecas.addListSelectionListener(this::showStockList);

        btnVeiculos.addActionListener(this::abrirVeiculos);
        btnOficina.addActionListener(this::abrirOficina);
        btnEventos.addActionListener(this::abrirEventos);
        btnClientes.addActionListener(this::abrirClientes);
        btnEstatisticas.addActionListener(this::abrirEstatisticas);
        btnTransacoes.addActionListener(this::abrirTransacoes);
    }

    private void showStockList(ListSelectionEvent listSelectionEvent) {
        if (escolheuPeca()){
            atualizarLabelStockOrigem();
            atualizarLabelStockDestino();
        }
    }

    private void atualizarLabelStockOrigem() {
        Estabelecimento estabelecimento = (Estabelecimento) comboBoxLocaisOrigem.getSelectedItem();
        String nomePeca = (String) listPecas.getSelectedValue();
        Peca peca = DadosAplicacao.INSTANCE.getPeca(nomePeca);
        labelStock_QtdMin_Origem.setText(estabelecimento.getOficina().getStockPeca(peca)+"/"+(estabelecimento.getOficina().getLimiteMinimoPeca(peca)));
    }

    private void atualizarLabelStockDestino() {
        Estabelecimento estabelecimento = (Estabelecimento) comboBoxLocaisDestino.getSelectedItem();
        String nomePeca = (String) listPecas.getSelectedValue();
        Peca peca = DadosAplicacao.INSTANCE.getPeca(nomePeca);
        labelStock_QtdMin_Destino.setText(estabelecimento.getOficina().getStockPeca(peca)+"/"+(estabelecimento.getOficina().getLimiteMinimoPeca(peca)));
    }

    private void showStockCmBoxOrigem(ActionEvent actionEvent) {
        if(escolheuPeca()) {
            atualizarLabelStockOrigem();
        }
    }

    private void showStockCmBoxDestino(ActionEvent actionEvent) {
        if(escolheuPeca()) {
            atualizarLabelStockDestino();
        }
    }

    private void transferirPecas(ActionEvent actionEvent) {
        System.out.println("Click no transferirPecas");
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

        valido = !isLocalOrigemIgualDestino();
        if(!valido){
            Erros.mostrarErro(this, Erros.LOCAL_ORIGEM_IGUAL_LOCAL_DESTINO);
            return;
        }

        String textQtdTransferir = textFieldQtdTransferir.getText();
        valido = isQuantidadeValida(textQtdTransferir);
        if(!valido){
            Erros.mostrarErro(this, Erros.QUANTIDADE_INVALIDA);
            return;
        }

        valido = !isQtdSupStock(textQtdTransferir);
        if(!valido){
            Erros.mostrarErro(this, Erros.QUANTIDADE_SUPERIOR_STOCK);
            return;
        }

        valido = !colocaStockAbaixoQtdMin(textQtdTransferir);
        if(!valido){
            Erros.mostrarErro(this, Erros.QUANTIDADE_STOCK_INSUFICIENTE);
            return;
        }

        transferirPecas(textQtdTransferir);
        Sucesso.mostrarSucesso(this, Sucesso.PECA_TRANSFERIDA);
        atualizarLabelStockOrigem();
        atualizarLabelStockDestino();
    }

    private void transferirPecas(String qtdTransferida) {
        Estabelecimento estabelecimentoOrigem = (Estabelecimento) comboBoxLocaisOrigem.getSelectedItem();
        Estabelecimento estabelecimentoDestino = (Estabelecimento) comboBoxLocaisDestino.getSelectedItem();

        String nomePeca = (String) listPecas.getSelectedValue();
        Peca peca = DadosAplicacao.INSTANCE.getPeca(nomePeca);

        int qtdTransferir = Integer.parseInt(qtdTransferida);

        estabelecimentoOrigem.getOficina().adicionarStockPeca(peca,-qtdTransferir);
        estabelecimentoDestino.getOficina().adicionarStockPeca(peca,qtdTransferir);
    }

    private boolean isLocalOrigemIgualDestino() {
        Estabelecimento estabelecimentoOrigem = (Estabelecimento) comboBoxLocaisOrigem.getSelectedItem();
        Estabelecimento estabelecimentoDestino = (Estabelecimento) comboBoxLocaisDestino.getSelectedItem();

        return estabelecimentoDestino.equals(estabelecimentoOrigem);
    }

    private boolean colocaStockAbaixoQtdMin(String qtdTransferida) {
        Estabelecimento estabelecimento = (Estabelecimento) comboBoxLocaisOrigem.getSelectedItem();

        String nomePeca = (String) listPecas.getSelectedValue();
        Peca peca = DadosAplicacao.INSTANCE.getPeca(nomePeca);

        int qtdTransferir = Integer.parseInt(qtdTransferida);
        Oficina oficina = estabelecimento.getOficina();
        return oficina.getStockPeca(peca)-qtdTransferir < oficina.getLimiteMinimoPeca(peca);
    }

    private boolean isQtdSupStock(String qtdTransferida) {
        Estabelecimento estabelecimento = (Estabelecimento) comboBoxLocaisOrigem.getSelectedItem();

        String nomePeca = (String) listPecas.getSelectedValue();
        Peca peca = DadosAplicacao.INSTANCE.getPeca(nomePeca);

        int qtdTransferir = Integer.parseInt(qtdTransferida);

        return estabelecimento.getOficina().getStockPeca(peca) < qtdTransferir;
    }


    private boolean isQuantidadeValida(String qtdTransferida) {
        try{
            Integer.parseInt(qtdTransferida);
        }catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private boolean isQtdCampoVazio() {
        return textFieldQtdTransferir.getText().length()==0;
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
        modeloComboBoxLocaisOrigem.addElement(sede);
        modeloComboBoxLocaisDestino.addElement(sede);
        for(Filial filial : filiais){
            modeloComboBoxLocaisOrigem.addElement(filial);
            modeloComboBoxLocaisDestino.addElement(filial);
        }

        labelStock_QtdMin_Origem.setText("n/a");
        labelStock_QtdMin_Destino.setText("n/a");
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
