package vista.Estatisticas;

import modelo.DadosAplicacao;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Transacoes.JanelaTransacoes;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

public class JanelaModelosMarcasMaiorUsoPecas extends JFrame{
    private JButton btnVeiculos;
    private JButton btnOficina;
    private JButton btnEventos;
    private JButton btnTransacoes;
    private JButton btnClientes;
    private JButton btnEstatisticas;
    private JList<String> listPecasMaiorUso;
    private JRadioButton btnPorMarca;
    private JRadioButton btnTodasMarcas;
    private JLabel labelQtdTotal;
    private JPanel painelPrincipal;
    private JTextField textFieldMarca;
    private JButton btnApresentar;

    private DefaultListModel modeloListaPecasMaiorUso;

    public JanelaModelosMarcasMaiorUsoPecas(){
        setContentPane(painelPrincipal);
        pack();

        modeloListaPecasMaiorUso = new DefaultListModel();
        listPecasMaiorUso.setModel(modeloListaPecasMaiorUso);
        labelQtdTotal.setText("0");

        btnVeiculos.addActionListener(this::abrirVeiculos);
        btnOficina.addActionListener(this::abrirOficina);
        btnEventos.addActionListener(this::abrirEventos);
        btnClientes.addActionListener(this::abrirClientes);
        btnEstatisticas.addActionListener(this::abrirEstatisticas);
        btnTransacoes.addActionListener(this::abrirTransacoes);
        btnTodasMarcas.addActionListener(this::desativarPorMarca);
        btnPorMarca.addActionListener(this::desativarTodasMarcas);
        btnApresentar.addActionListener(this::apresentarPecasMaiorUso);
    }

    private void desativarTodasMarcas(ActionEvent actionEvent) {
        btnTodasMarcas.setSelected(false);
        if(!btnPorMarca.isSelected()){
            btnPorMarca.setSelected(true);
        }
    }

    private void desativarPorMarca(ActionEvent actionEvent) {
        btnPorMarca.setSelected(false);
        if(!btnTodasMarcas.isSelected()){
            btnTodasMarcas.setSelected(true);
        }
    }

    private void apresentarPecasMaiorUso(ActionEvent actionEvent) {
        boolean valido = isOpcaoSelecionada();
        if(!valido){
            Erros.mostrarErro(this, Erros.NAO_SELECIONOU_FILTRO);
            return;
        }

        valido = !isMarcaSelectedENaoValida();
        if(!valido){
            Erros.mostrarErro(this, Erros.MARCA_VAZIA);
            return;
        }

        modeloListaPecasMaiorUso.removeAllElements();
        labelQtdTotal.setText("0");
        DadosAplicacao da = DadosAplicacao.INSTANCE;

        List<String> pecasMaiorUso;
        boolean porMarca = btnPorMarca.isSelected();
        if(!porMarca){
            pecasMaiorUso = da.getPecasUsadasTodasAsMarcas();
        }else {
            pecasMaiorUso = da.getPecasUsadasDaMarca(textFieldMarca.getText());
            valido = !pecasMaiorUso.isEmpty();
            if (!valido) {
                Erros.mostrarErro(this, Erros.NAO_EXISTEM_VEICULOS_REPARADOS_MARCA);
                return;
            }
        }

        //TODO - falta filtrar
        atualizarListaEQuantidade(da, pecasMaiorUso);
    }

    private void atualizarListaEQuantidade(DadosAplicacao da, List<String> pecasMaiorUso) {
        modeloListaPecasMaiorUso.addAll(modeloListaPecasMaiorUso.getSize(), pecasMaiorUso);
        String total = Integer.toString(da.getTotalPecasUsadasNaReparacao());
        labelQtdTotal.setText(total);
    }

    private boolean isMarcaSelectedENaoValida() {
        return btnPorMarca.isSelected() && textFieldMarca.getText().isEmpty();
    }

    private boolean isOpcaoSelecionada() {
        return btnTodasMarcas.isSelected() || btnPorMarca.isSelected();
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

    public void fechar(){
        setVisible(false);
        dispose();
    }
}
