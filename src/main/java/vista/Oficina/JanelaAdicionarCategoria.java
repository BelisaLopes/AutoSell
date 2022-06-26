package vista.Oficina;

import modelo.Categoria;
import modelo.DadosAplicacao;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Sucesso;
import vista.Transacoes.JanelaTransacoes;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JanelaAdicionarCategoria extends JFrame{
    private JButton btnVeiculos;
    private JButton btnOficina;
    private JButton btnEventos;
    private JButton btnTransacoes;
    private JButton btnClientes;
    private JButton btnEstatisticas;
    private JTextField textCategoria;
    private JButton btnAdicionar;
    private JButton btnCancelar;
    private JPanel panel;
    private JPanel painelPrincipal;

    public JanelaAdicionarCategoria(){
        setContentPane(painelPrincipal);
        pack();

        btnAdicionar.addActionListener(this::adicionarCategoria);
        btnCancelar.addActionListener(this::cancelar);

        btnVeiculos.addActionListener(this::abrirVeiculos);
        btnOficina.addActionListener(this::abrirOficina);
        btnEventos.addActionListener(this::abrirEventos);
        btnClientes.addActionListener(this::abrirClientes);
        btnEstatisticas.addActionListener(this::abrirEstatisticas);
        btnTransacoes.addActionListener(this::abrirTransacoes);
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

    private void adicionarCategoria(ActionEvent actionEvent) {
        System.out.println("Click no adicionarCategoria");
        String novaCategoria = textCategoria.getText();
        boolean valido = isNomeValido(novaCategoria);
        if(!valido) {
            Erros.mostrarErro(this, Erros.NOME_INVALIDO);
            return;
        }

        valido = !existeCategoria(novaCategoria);
        if(!valido) {
            Erros.mostrarErro(this, Erros.CATEGORIA_EXISTE);
            return;
        }

        DadosAplicacao.INSTANCE.adicionarCategoria(new Categoria(novaCategoria));
        Sucesso.mostrarSucesso(this, Sucesso.CATEGORIA_REGISTADA);
        fechar();
        new JanelaOficina();
    }

    private boolean existeCategoria(String nome) {
        return DadosAplicacao.INSTANCE.existeCategoria(nome);
    }

    private boolean isNomeValido(String nome) {
        return !(nome.trim().length() < 2) || (nome.trim().length() > 100);
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
