package vista.Clientes;

import modelo.*;
import vista.Erros;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class JanelaAtualizarCliente extends JFrame {
    private JButton estatisticasButton;
    private JButton veículosButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton oficinaButton;
    private JButton eventosButton;
    private JList<Cliente> listClientes;
    private JTextField textNomeAtualizado;
    private JTextField textDataNascimentoAtualizada;
    private JTextField textNIFAtualizado;
    private JTextField textContactoAtualizado;
    private JTextField textNome;
    private JTextField textMoradaAtualizada;
    private JButton apresentarClientesButton;
    private JTextField textNIF;
    private JButton escolherClienteButton;
    private JPanel painelPrincipal;
    private JButton atualizarClienteButton;
    private JButton cancelarButton;

    private Cliente cliente;

    private DefaultListModel modeloListaClientes;

    public JanelaAtualizarCliente() {
        setContentPane(painelPrincipal);
        pack();
        setVisible(true);

        veículosButton.addActionListener(this::btnVeiculosActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaActionPerformed);
        eventosButton.addActionListener(this::btnEventosActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesActionPerformed);
        clientesButton.addActionListener(this::btnClientesActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasActionPerformed);
        cancelarButton.addActionListener(this::btnEventosActionPerformed);
        modeloListaClientes = new DefaultListModel();
        listClientes.setModel(modeloListaClientes);

        apresentarClientesButton.addActionListener(this::btnApresentarClientesActionPerformed);
        atualizarClienteButton.addActionListener(this::btnAtualizarClienteActionPerformed);
        escolherClienteButton.addActionListener(this::btnEscolherClienteActionPerformed);

    }

    private void btnEscolherClienteActionPerformed(ActionEvent evt) {
        boolean valido = !listClientes.isSelectionEmpty();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_CLIENTE);
            return;
        }


        cliente = listClientes.getSelectedValue();
        textNomeAtualizado.setText(cliente.getNome());
        textDataNascimentoAtualizada.setText(cliente.getDataNascimento().toString());
        textMoradaAtualizada.setText(cliente.getMorada());
        textNIFAtualizado.setText(cliente.getNIF());
        textContactoAtualizado.setText(cliente.getContacto());
    }

    private void btnAtualizarClienteActionPerformed(ActionEvent evt) {
        String nome = textNomeAtualizado.getText();
        boolean valido = isNomeValido(nome);
        if(!valido){
            Erros.mostrarErro(this, Erros.NOME_INVALIDO);
            return;
        }

        String dataNascimentoAtualizar = textDataNascimentoAtualizada.getText();
        valido = isDataNascimentoValida(dataNascimentoAtualizar);
        if(!valido){
            Erros.mostrarErro(this, Erros.DATA_NASCIMENTO_INVALIDA);
            return;
        }

        Data dataNascimentoAtualizada = Data.parseData(dataNascimentoAtualizar);

        String morada = textMoradaAtualizada.getText();
        valido = isMoradaValida(morada);
        if(!valido){
            Erros.mostrarErro(this, Erros.MORADA_INVALIDA);
            return;
        }

        String nif = textNIFAtualizado.getText();
        valido = isNIFValido(nif);
        if(!valido){
            Erros.mostrarErro(this, Erros.NIF_INVALIDO);
            return;
        }

        String contacto = textContactoAtualizado.getText();
        valido = isContactoValido(contacto);
        if(!valido){
            Erros.mostrarErro(this, Erros.CONTACTO_INVALIDO);
            return;
        }

        var alterado = isClienteAlterado(cliente, nome, dataNascimentoAtualizada, morada, nif, contacto);
        if(alterado){
            valido = !isNifDuplicado(nif);
            if(!valido){
                Erros.mostrarErro(this, Erros.CLIENTE_EXISTENTE);
                return;
            }
        }

        cliente.setNome(nome);
        cliente.setDataNascimento(dataNascimentoAtualizada);
        cliente.setMorada(morada);
        cliente.setNIF(nif);
        cliente.setContacto(contacto);

        fechar();
    }

    private void btnApresentarClientesActionPerformed(ActionEvent evt) {
        String nome = textNome.getText();
        boolean valido = isNomeValido(nome);
        if(!valido){
            Erros.mostrarErro(this, Erros.NOME_INVALIDO);
            return;
        }

        String nif = textNIF.getText();
        valido = isNIFValido(nif);
        if(!valido){
            Erros.mostrarErro(this, Erros.NIF_INVALIDO);
            return;
        }


        DadosAplicacao dados = DadosAplicacao.INSTANCE;
        List<Cliente> clientes = dados.getClientes(nome, nif);
        if(clientes == null){
            modeloListaClientes.removeAllElements();
            Erros.mostrarErro(this, Erros.NENHUM_RESULTADO);
            return;
        }
        modeloListaClientes.removeAllElements();
        for (Cliente cliente : clientes) {
            modeloListaClientes.add(modeloListaClientes.getSize(),cliente);
        }
    }

    private boolean isClienteAlterado(Cliente cliente, String nome, Data dataNascimento, String morada, String nif, String contacto) {
        return !cliente.getNome().equals(nome) || !cliente.getDataNascimento().equals(dataNascimento)
                || !cliente.getMorada().equals(morada) || !cliente.getNIF().equals(nif) || !cliente.getContacto().equals(contacto);
    }

    private boolean isNifDuplicado(String nif) {
        DadosAplicacao da = DadosAplicacao.INSTANCE;
        return da.isNIFDuplicado(nif);
    }

    private boolean isNomeValido(String nome){
        return !(nome.trim().length() < 2 || nome.trim().length() > 100);
    }
    private boolean isMoradaValida(String morada){
        return !(morada.trim().length() < 10 || morada.trim().length() > 256);
    }
    private boolean isNIFValido(String nif){
        return !(nif.trim().length() < 9 || nif.trim().length() > 9 || !nif.matches("(1)?[0-9]{8}"));
    }

    private boolean isDataNascimentoValida(String data){
        Data data_final = Data.parseData(data);
        if(data_final == null){
            return false;
        }
        int dia = data_final.getDia();
        int mes = data_final.getMes();
        int ano = data_final.getAno();

        return (dia > 0 && dia < 32 && mes > 0 && mes < 13 && ano > 1900 && ano < 2022);
    }

    private boolean isContactoValido(String contacto){
        return !(contacto.trim().length() < 9 || contacto.trim().length() > 9 || !contacto.trim().matches("(2|9)?[0-9]{8}"));
    }

    public void fechar(){
        setVisible(false);
        dispose();
        JanelaClientes j = new JanelaClientes();
        j.setVisible(true);
    }

    private void btnVeiculosActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaVeiculos j = new JanelaVeiculos();
        j.setVisible(true);

    }

    private void btnOficinaActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnOficinaButtonActionPerformed");
    }

    private void btnEventosActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnEventosButtonActionPerformed");
        fechar();
    }

    private void btnTransacoesActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnTransacoesButtonActionPerformed");
    }

    private void btnClientesActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnClientesButtonActionPerformed");
    }

    private void btnEstatisticasActionPerformed(ActionEvent evt) {
        System.out.println("Click no btnEstatisticasButtonActionPerformed");
    }


}
