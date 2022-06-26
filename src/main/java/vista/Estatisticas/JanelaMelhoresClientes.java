package vista.Estatisticas;

import modelo.Cliente;
import modelo.DadosAplicacao;
import vista.Clientes.JanelaClientes;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Transacoes.JanelaTransacoes;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

public class JanelaMelhoresClientes extends JFrame{
    private JButton btnVeiculos;
    private JButton btnOficina;
    private JButton btnEventos;
    private JButton btnTransacoes;
    private JButton btnClientes;
    private JButton btnEstatisticas;
    private JList<String> listMelhoresClientes;
    private JPanel painelPrincipal;

    private DefaultListModel modeloListaMelhoresClientes;

    public JanelaMelhoresClientes(){
        setContentPane(painelPrincipal);
        pack();

        modeloListaMelhoresClientes = new DefaultListModel();
        listMelhoresClientes.setModel(modeloListaMelhoresClientes);

        apresentarMelhoresClientes();

        btnVeiculos.addActionListener(this::abrirVeiculos);
        btnOficina.addActionListener(this::abrirOficina);
        btnEventos.addActionListener(this::abrirEventos);
        btnClientes.addActionListener(this::abrirClientes);
        btnEstatisticas.addActionListener(this::abrirEstatisticas);
        btnTransacoes.addActionListener(this::abrirTransacoes);
    }

    private void apresentarMelhoresClientes() {
        DadosAplicacao da = DadosAplicacao.INSTANCE;
        List<Cliente> clientes = da.getClientes(null,null);
        Map<Integer, Cliente> clientesOrdenados = new TreeMap<Integer, Cliente>(Collections.reverseOrder());

        for (Cliente cliente: clientes) {
            clientesOrdenados.put(cliente.getTransacoes().size(),cliente);
        }

        Set<Integer> keys = clientesOrdenados.keySet();
        Iterator i = keys.iterator();
        while (i.hasNext()) {
            Integer nTransacoes = (Integer) i.next();
            Cliente cliente = (Cliente) clientesOrdenados.get(nTransacoes);
            modeloListaMelhoresClientes.add(modeloListaMelhoresClientes.getSize(),cliente.getNome()+" - "+nTransacoes+" transações");
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

    public void fechar(){
        setVisible(false);
        dispose();
    }
}
