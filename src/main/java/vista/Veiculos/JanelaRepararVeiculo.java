package vista.Veiculos;

import modelo.*;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Sucesso;
import vista.Transacoes.JanelaTransacoes;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class JanelaRepararVeiculo extends JFrame{
    private JButton estatisticasButton;
    private JButton veiculosButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JButton registarVeículoComoReparadoButton;
    private JTextField quantidadetextField;
    private JList<Veiculo> listaVeiculos;
    private JButton adicionarPecasButton;
    private JList<String> listaPecasDaReparacao;
    private JComboBox estabelecimentoComboBox;
    private JTextField marcaTextField;
    private JTextField modeloTextField;
    private JList<Peca> listaPecasDisponiveis;
    private JButton apresentarVeículosButton;
    private JButton apresentarPecasButton;
    private JTextField matriculaTextField;
    private JComboBox categoriasComboBox;
    private JButton cancelarButton;
    private JPanel painel;
    private JButton escolherVeiculoButton;
    private JLabel stockLabel;

    private DefaultComboBoxModel modeloComboBoxLocais;
    private DefaultComboBoxModel modeloComboBoxCategorias;
    private DefaultListModel modeloListaVeiculos;
    private DefaultListModel modeloListaPecas;
    private DefaultListModel modeloListaPecasReparacao;
    private Veiculo veiculo;
    private Estabelecimento estabelecimento;

    private Hashtable<Peca, Integer> pecasUsadas;

    public JanelaRepararVeiculo(){
        setContentPane(painel);
        pack();
        setLocationRelativeTo(null);

        veiculosButton.addActionListener(this::btnVeiculosActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaActionPerformed);
        eventosButton.addActionListener(this::btnEventosActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesActionPerformed);
        clientesButton.addActionListener(this::btnClientesActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasActionPerformed);
        cancelarButton.addActionListener(this::btnCancelarActionPerformed);
        modeloListaVeiculos = new DefaultListModel();
        modeloComboBoxLocais = new DefaultComboBoxModel();
        modeloComboBoxCategorias = new DefaultComboBoxModel();
        modeloListaPecas = new DefaultListModel();
        modeloListaPecasReparacao = new DefaultListModel();
        listaPecasDaReparacao.setModel(modeloListaPecasReparacao);
        listaPecasDisponiveis.setModel(modeloListaPecas);
        categoriasComboBox.setModel(modeloComboBoxCategorias);
        estabelecimentoComboBox.setModel(modeloComboBoxLocais);
        listaVeiculos.setModel(modeloListaVeiculos);

        initComponents();


        apresentarVeículosButton.addActionListener(this::btnApresentarVeiculosActionPerformed);
        escolherVeiculoButton.addActionListener(this::btnEscolherVeiculoActionPerformed);
        apresentarPecasButton.addActionListener(this::btnApresentarPecasActionPerformed);
        adicionarPecasButton.addActionListener(this::btnAdicionarPecasActionPerformed);
        registarVeículoComoReparadoButton.addActionListener(this::btnRegistarVeiculoComoReparadoActionPerformed);
        listaPecasDisponiveis.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!listaPecasDisponiveis.isSelectionEmpty()) {
                    Integer stock = estabelecimento.getOficina().getStockPeca(listaPecasDisponiveis.getSelectedValue());
                    stockLabel.setText(stock + " disponível");
                }
            }
        });
    }

    private void btnApresentarVeiculosActionPerformed(ActionEvent evt) {
        boolean valido = pecasUsadas.isEmpty();
        if(!valido){
            Erros.mostrarErro(this, Erros.VEICULO_AINDA_EM_REPARACAO);
            return;
        }

        modeloListaVeiculos.removeAllElements();
        estabelecimento = (Estabelecimento) estabelecimentoComboBox.getSelectedItem();

        String marca = marcaTextField.getText();
        valido = isNomeValido(marca);
        if(!valido){
            Erros.mostrarErro(this, Erros.MARCA_INVALIDA);
            return;
        }

        String modelo = modeloTextField.getText();
        valido = isNomeValido(modelo);
        if(!valido){
            Erros.mostrarErro(this, Erros.MODELO_INVALIDO);
            return;
        }

        String matricula = matriculaTextField.getText();
        valido = isNomeValido(matricula);
        if(!valido){
            Erros.mostrarErro(this, Erros.MATRICULA_INVALIDA);
            return;
        }

        DadosAplicacao da = DadosAplicacao.INSTANCE;
        List<Veiculo> veiculos = da.getVeiculosPorReparar(estabelecimento, marca, modelo, matricula);
        valido = veiculos != null;
        if(!valido){
            Erros.mostrarErro(this, Erros.NENHUM_RESULTADO);
            return;
        }

        atualizarListaVeiculos(veiculos);

    }

    private void atualizarListaVeiculos(List<Veiculo> veiculos) {
        for (Veiculo veiculo : veiculos) {
            modeloListaVeiculos.add(modeloListaVeiculos.getSize(),veiculo);
        }
    }

    private boolean isNomeValido(String nome) {
        if(nome.isEmpty()){
            return true;
        }
        return !(nome.trim().length() < 3) && !(nome.trim().length() > 50);
    }

    private void btnEscolherVeiculoActionPerformed(ActionEvent evt) {
        boolean valido = pecasUsadas.isEmpty();
        if(!valido){
            Erros.mostrarErro(this, Erros.VEICULO_AINDA_EM_REPARACAO);
            return;
        }

        valido = !listaVeiculos.isSelectionEmpty();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_VEICULO);
            return;
        }

        veiculo = listaVeiculos.getSelectedValue();
        List<Categoria> categorias = DadosAplicacao.INSTANCE.getCatalogo();
        atualizarComboBoxCategorias(categorias);
    }

    private void atualizarComboBoxCategorias(List<Categoria> categorias) {
        for (Categoria categoria : categorias) {
            modeloComboBoxCategorias.addElement(categoria);
        }
    }

    private void btnApresentarPecasActionPerformed(ActionEvent evt) {
        boolean valido = veiculo != null;
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_VEICULO);
            return;
        }

        Categoria categoria = (Categoria) categoriasComboBox.getSelectedItem();
        List<Peca> pecas = estabelecimento.getOficina().getPecasCategoria(categoria);
        valido = pecas != null;
        if(!valido){
            Erros.mostrarErro(this, Erros.CATEGORIA_SEM_PECAS);
            return;
        }

        atualizarListaPecas(pecas);
    }

    private void atualizarListaPecas(List<Peca> pecas) {
        modeloListaPecas.removeAllElements();
        for (Peca peca : pecas) {
            modeloListaPecas.add(modeloListaPecas.getSize(), peca);
        }
    }

    private void btnAdicionarPecasActionPerformed(ActionEvent evt) {
        boolean valido = veiculo != null;
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_VEICULO);
            return;
        }

        valido = !listaPecasDisponiveis.isSelectionEmpty();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_PECA);
            return;
        }
        Peca peca = listaPecasDisponiveis.getSelectedValue();
        String quantidadeString = quantidadetextField.getText();
        valido = isQuantidadeValida(quantidadeString);
        if(!valido){
            Erros.mostrarErro(this, Erros.TIPO_QUANTIDADE_PECA);
            return;
        }
        Integer quantidade = Integer.parseInt(quantidadeString);

        valido = isQuantidadePositiva(quantidade);
        if(!valido){
            Erros.mostrarErro(this, Erros.QUANTIDADE_PECA_INVALIDA);
            return;
        }

        Integer stockTotal = estabelecimento.getOficina().getStockPeca(peca);
        Integer stockUsado = pecasUsadas.get(peca);
        if(stockUsado == null){
            stockUsado = 0;
        }

        valido = existeStock(stockTotal, stockUsado, quantidade);
        if(!valido){
            Erros.mostrarErro(this, Erros.SEM_STOCK);
            return;
        }

        atualizarPecasDaReparacao(peca, quantidade);
        atualizarListaPecasUsadas();

        quantidadetextField.setText("");
    }

    private void atualizarPecasDaReparacao(Peca peca, Integer quantidade) {
        Integer q = pecasUsadas.get(peca);
        if(q == null){
            pecasUsadas.put(peca,quantidade);
            return;
        }
        pecasUsadas.put(peca, q + quantidade);
    }

    private void atualizarListaPecasUsadas() {
        modeloListaPecasReparacao.removeAllElements();
        Enumeration<Peca> pecas = pecasUsadas.keys();
        Peca p;
        while (pecas.hasMoreElements()){
            p = pecas.nextElement();
            modeloListaPecasReparacao.add(modeloListaPecasReparacao.getSize(), p + " - " + pecasUsadas.get(p) + " peças");
        }
    }

    private boolean existeStock(Integer stockTotal, Integer stockUsado, Integer quantidade) {
        return stockTotal >= (stockUsado + quantidade);
    }

    private boolean isQuantidadePositiva(Integer quantidade) {
        return quantidade > 0;
    }

    private boolean isQuantidadeValida(String quantidadeString) {
        try{
            Integer.parseInt(quantidadeString);
        }catch(NumberFormatException ex){
            return false;
        }
        return true;
    }

    private void btnRegistarVeiculoComoReparadoActionPerformed(ActionEvent evt) {
        boolean valido = !pecasUsadas.isEmpty();
        if(!valido){
            Erros.mostrarErro(this, Erros.NENHUM_VEICULO_EM_REPARACAO);
            return;
        }

        Integer total = numeroTotalPecasUsadas();

        DadosAplicacao.INSTANCE.definirVeiculoReparado(veiculo, total);

        Oficina oficina = estabelecimento.getOficina();
        Enumeration<Peca> pecas = pecasUsadas.keys();
        Peca p;
        Integer gasto;
        boolean limite = false;
        while (pecas.hasMoreElements()){
            p = pecas.nextElement();
            gasto = pecasUsadas.get(p);
            oficina.atualizarStockPeca(p,gasto);
            if(oficina.isRuturaStock(p)){
                limite = true;
            }
        }

        if(limite){
            Sucesso.mostrarSucesso(this, Sucesso.VEICULO_REPARADO_AVISO_STOCK);
        }else{
            Sucesso.mostrarSucesso(this, Sucesso.VEICULO_REPARADO);
        }

        fechar();
        JanelaVeiculos jv = new JanelaVeiculos();
        jv.setVisible(true);

    }

    private Integer numeroTotalPecasUsadas() {
        Integer total = 0;
        Enumeration<Peca> pecas = pecasUsadas.keys();
        Peca p;
        while (pecas.hasMoreElements()){
            p = pecas.nextElement();
            total += pecasUsadas.get(p);
        }
        return total;
    }


    private void initComponents() {
        pecasUsadas = new Hashtable<>();
        List<Estabelecimento> list = DadosAplicacao.INSTANCE.getEstabelecimentos();
        for (Estabelecimento estabelecimento : list) {
            modeloComboBoxLocais.addElement(estabelecimento);
        }
        veiculo = null;
    }

    private void fechar() {
        setVisible(false);
        dispose();
    }

    private void btnCancelarActionPerformed(ActionEvent evt) {
        fechar();
//        new JanelaVeiculos();
        JanelaVeiculos jv = new JanelaVeiculos();
        jv.setVisible(true);
    }

    private void btnVeiculosActionPerformed(ActionEvent evt) {
        fechar();
        JanelaVeiculos jv = new JanelaVeiculos();
        jv.setVisible(true);
    }

    private void btnOficinaActionPerformed(ActionEvent evt) {
        fechar();
        JanelaOficina jo = new JanelaOficina();
        jo.setVisible(true);
    }

    private void btnEventosActionPerformed(ActionEvent evt) {
        fechar();
        JanelaEventos je = new JanelaEventos();
        je.setVisible(true);
    }

    private void btnTransacoesActionPerformed(ActionEvent evt) {
        fechar();
        JanelaTransacoes jt = new JanelaTransacoes();
        jt.setVisible(true);
    }

    private void btnClientesActionPerformed(ActionEvent evt) {
        fechar();
        JanelaClientes jc = new JanelaClientes();
        jc.setVisible(true);
    }

    private void btnEstatisticasActionPerformed(ActionEvent evt) {
        fechar();
        JanelaEstatistica je = new JanelaEstatistica();
//        je.setVisible(true);
    }
}
