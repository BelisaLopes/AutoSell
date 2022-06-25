package vista.Oficina;

import modelo.Categoria;
import modelo.DadosAplicacao;
import modelo.Evento;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Sucesso;
import vista.Transacoes.JanelaTransacoes;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class JanelaRemoverCategoriaPeca extends JFrame{
    private JButton btnEstatisticas;
    private JButton btnClientes;
    private JButton btnOficina;
    private JButton btnEventos;
    private JButton btnTransacoes;
    private JButton btnVeiculos;
    private JButton btnRemover;
    private JList listCategorias;
    private JButton btnCancelar;
    private JPanel painelPrincipal;

    private DefaultListModel modeloListaCategorias;

    public JanelaRemoverCategoriaPeca(){
        setContentPane(painelPrincipal);
        pack();

        modeloListaCategorias = new DefaultListModel();
        listCategorias.setModel(modeloListaCategorias);
        initComponents();

        btnRemover.addActionListener(this::removerCategoria);
        btnCancelar.addActionListener(this::cancelar);

        btnVeiculos.addActionListener(this::abrirVeiculos);
        btnOficina.addActionListener(this::abrirOficina);
        btnEventos.addActionListener(this::abrirEventos);
        btnClientes.addActionListener(this::abrirClientes);
        btnEstatisticas.addActionListener(this::abrirEstatisticas);
        btnTransacoes.addActionListener(this::abrirTransacoes);
    }

    private void initComponents() {
        modeloListaCategorias.removeAllElements();
        List<Categoria> catalogo = DadosAplicacao.INSTANCE.getCatalogo();
        for (Categoria categoria : catalogo) {
            if(categoria.getPecas().isEmpty()) {
                modeloListaCategorias.add(modeloListaCategorias.getSize(), categoria);
            }
        }
    }

    private void removerCategoria(ActionEvent actionEvent) {
        System.out.println("Click no removerCategoria");
        Categoria categoria = (Categoria) listCategorias.getSelectedValue();
        boolean valido = escolheuCategoria();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_CATEGORIA);
            return;
        }

        DadosAplicacao.INSTANCE.removerCategoria(categoria);
        Sucesso.mostrarSucesso(this, Sucesso.CATEGORIA_REMOVIDA);
        fechar();
        new JanelaOficina();
    }

    private boolean escolheuCategoria() {
        return !listCategorias.isSelectionEmpty();
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
