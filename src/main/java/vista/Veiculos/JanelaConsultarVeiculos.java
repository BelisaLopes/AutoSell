package vista.Veiculos;

import modelo.DadosAplicacao;
import modelo.Estabelecimento;
import modelo.Local;
import modelo.Veiculo;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Transacoes.JanelaTransacoes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class JanelaConsultarVeiculos extends JFrame{
    private JButton veiculosButton;
    private JButton estatisticasButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JList<Veiculo> listaVeiculos;
    private JComboBox localComboBox;
    private JTextField marcaTextField;
    private JTextField modeloTextField;
    private JButton apresentarVeículosButton;
    private JTextField matriculaTextField;
    private JLabel nomeDonoAnteriorLabel;
    private JLabel estadoVeiculoLabel;
    private JLabel modeloVeiculoLabel;
    private JPanel painel;
    private JLabel lotacaoLabel;
    private JLabel localVeiculoLabel;
    private JLabel marcaVeiculoLabel;
    private JLabel anoVeiculoLabel;
    private JLabel corVeiculoLabel;
    private JLabel matriculaVeiculoLabel;
    private JLabel portasVeiculoLabel;
    private JLabel kmVeiculoLabel;
    private JLabel combustivelVeiculoLabel;
    private JLabel numeroDonosVeiculoLabel;
    private JButton escolherVeiculoButton;

    private DefaultComboBoxModel modeloComboBoxLocais;
    private DefaultListModel modeloListaVeiculos;

    public JanelaConsultarVeiculos(){
        setContentPane(painel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        veiculosButton.addActionListener(this::btnVeiculosActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaActionPerformed);
        eventosButton.addActionListener(this::btnEventosActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesActionPerformed);
        clientesButton.addActionListener(this::btnClientesActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasActionPerformed);
        modeloListaVeiculos = new DefaultListModel();
        modeloComboBoxLocais = new DefaultComboBoxModel();
        localComboBox.setModel(modeloComboBoxLocais);
        listaVeiculos.setModel(modeloListaVeiculos);
        initComponents();

        apresentarVeículosButton.addActionListener(this::btnApresentarVeiculosActionPerformed);
        escolherVeiculoButton.addActionListener(this::btnEscolherVeiculoActionPerformed);
    }

    private void btnEscolherVeiculoActionPerformed(ActionEvent evt) {
        boolean valido = !listaVeiculos.isSelectionEmpty();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_VEICULO);
            return;
        }

        Veiculo veiculo = listaVeiculos.getSelectedValue();
        mostrarInformacaoVeiculo(veiculo);
    }

    private void mostrarInformacaoVeiculo(Veiculo veiculo) {
        String local = veiculo.getLocal().toString();
        localVeiculoLabel.setText(local); //diagrama para o local
        marcaVeiculoLabel.setText(veiculo.getMarca());
        anoVeiculoLabel.setText(veiculo.getAno() + "");
        matriculaVeiculoLabel.setText(veiculo.getMatricula());
        String combustivel = veiculo.getCombustivel().toString();
        combustivelVeiculoLabel.setText(combustivel); //diagrama para o TipoCombustivel
//        String nomeCliente = veiculo.getDonoAnterior().getNome(); //diagrama para o Cliente
//        nomeDonoAnteriorLabel.setText(nomeCliente);

        String estado = veiculo.getEstadoVeiculo().toString();
        estadoVeiculoLabel.setText(estado); // //diagrama para o EstadoVeiculo
        modeloVeiculoLabel.setText(veiculo.getModelo());
        corVeiculoLabel.setText(veiculo.getCor());
        portasVeiculoLabel.setText(veiculo.getNumeroPortas() + "");
        kmVeiculoLabel.setText(veiculo.getQuilometros() + "");
        numeroDonosVeiculoLabel.setText(veiculo.getNumeroDonos() + "");
    }


    private void btnApresentarVeiculosActionPerformed(ActionEvent evt) {
        Estabelecimento estabelecimento;
        try {
            estabelecimento = (Estabelecimento) localComboBox.getSelectedItem();
        }catch(Exception ex){
            estabelecimento = null;
        }

        String marca = marcaTextField.getText();
        boolean valido = isNomeValido(marca);
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
        List<Veiculo> veiculos = da.getTodosVeiculos(estabelecimento, marca, modelo, matricula);
        valido = veiculos != null;
        if(!valido){
            Erros.mostrarErro(this, Erros.NENHUM_RESULTADO);
            return;
        }

        atualizarListaVeiculos(veiculos);
    }

    private void atualizarListaVeiculos(List<Veiculo> veiculos) {
        modeloListaVeiculos.removeAllElements();
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

    private void initComponents() {
        modeloComboBoxLocais.addElement("TODOS OS LOCAIS");
        List<Estabelecimento> list = DadosAplicacao.INSTANCE.getEstabelecimentos();
        for (Estabelecimento estabelecimento : list) {
            modeloComboBoxLocais.addElement(estabelecimento);
        }
    }

    private void fechar() {
        setVisible(false);
        dispose();
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
