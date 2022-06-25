package vista.Oficina;

import modelo.Categoria;
import modelo.DadosAplicacao;
import vista.Erros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JanelaAdicionarCategoria extends JDialog {
    private JButton btnVeiculos;
    private JButton btnOficina;
    private JButton btnEventos;
    private JButton btnTransacoes;
    private JButton btnClientes;
    private JButton btnEstatisticas;
    private JTextField novaCategoriaText;
    private JButton btnAdicionar;
    private JButton btnCancelar;
    private JPanel panel;
    private JPanel painelPrincipal;

    private Categoria categoria;

    public JanelaAdicionarCategoria(Frame parent, boolean modal) {
        super(parent, modal);
        setContentPane(painelPrincipal);
        parent.setVisible(false);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);

        btnAdicionar.addActionListener(this::adicionarCategoria);
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
    }

    private void adicionarCategoria(ActionEvent actionEvent) {
        System.out.println("Adicionar Categoria");
        String novaCategoria = novaCategoriaText.getText();
        boolean valido = isCategoriaValida(novaCategoria);
        if(!valido) {
            Erros.mostrarErro(this, Erros.NOME_INVALIDO);
            return;
        }

        valido = !existeCategoria(novaCategoria);
        if(!valido) {
            Erros.mostrarErro(this, Erros.CATEGORIA_EXISTE);
            return;
        }

        DadosAplicacao.INSTANCE.adicionarCategoria(novaCategoria);
        fechar();
    }

    private boolean existeCategoria(String nomeCategoria) {
        return DadosAplicacao.INSTANCE.existeCategoria(nomeCategoria);
    }

    private boolean isCategoriaValida(String nomeCategoria) {
        return !(nomeCategoria.trim().length() < 2) || (nomeCategoria.trim().length() > 100);
    }

    private void btnCancelarActionPerformed(ActionEvent evt){
        //todo
        System.out.println("Click no cancelarCriacaoCrianca");
        fechar();
    }

    private void fechar(){
        //todo
        setVisible(false);
        super.getParent().setVisible(true);
    }
}
