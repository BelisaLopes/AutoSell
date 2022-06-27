package vista.Veiculos;

import modelo.Categoria;
import modelo.DadosAplicacao;
import modelo.Estabelecimento;
import modelo.Veiculo;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Sucesso;
import vista.Transacoes.JanelaTransacoes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class JanelaTransferirVeiculos extends JFrame{
    private JButton veiculosButton;
    private JButton estatisticasButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JComboBox locaisComboBox;
    private JComboBox localDestinoComboBox;
    private JButton transferirVeículoButton;
    private JTextField marcaTextField;
    private JTextField modeloTextField;
    private JTextField matriculaTextField;
    private JButton apresentarVeículosButton;
    private JList<Veiculo> listaVeiculos;
    private JButton cancelarButton;
    private JLabel lotacaoDestinoLabel;
    private JPanel painel;
    private JLabel lotacaoOrigemLabel;
    private JButton escolherVeículoButton;


    private DefaultComboBoxModel modeloComboBoxLocais;
    private DefaultComboBoxModel modeloComboBoxLocaisDestino;
    private DefaultListModel modeloListaVeiculos;
    private Veiculo veiculo;
    private Estabelecimento estabelecimento;

    public JanelaTransferirVeiculos(){
        setContentPane(painel);
        pack();
        setLocationRelativeTo(null);

        veiculosButton.addActionListener(this::btnVeiculosActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaActionPerformed);
        eventosButton.addActionListener(this::btnEventosActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesActionPerformed);
        clientesButton.addActionListener(this::btnClientesActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasActionPerformed);
        modeloComboBoxLocais = new DefaultComboBoxModel();
        modeloComboBoxLocaisDestino = new DefaultComboBoxModel();
        modeloListaVeiculos = new DefaultListModel();
        localDestinoComboBox.setModel(modeloComboBoxLocaisDestino);
        locaisComboBox.setModel(modeloComboBoxLocais);
        listaVeiculos.setModel(modeloListaVeiculos);

        initComponents();

        apresentarVeículosButton.addActionListener(this::btnApresentarVeiculosActionPerformed);
        escolherVeículoButton.addActionListener(this::btnEscolherVeiculoActionPerformed);
        transferirVeículoButton.addActionListener(this::btnTransferirVeiculoActionPerformed);
        cancelarButton.addActionListener(this::btnCancelarActionPerformed);

        localDestinoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Estabelecimento estabelecimento = (Estabelecimento) localDestinoComboBox.getSelectedItem();
                int lotacaoAtual = DadosAplicacao.INSTANCE.getNumeroVeiculosNoLocal(estabelecimento);
                lotacaoDestinoLabel.setText(lotacaoAtual + " / "+ estabelecimento.getCapacidadeMaximaVeiculos());
            }
        });
    }

    private void btnCancelarActionPerformed(ActionEvent evt) {
        fechar();

        JanelaVeiculos jv = new JanelaVeiculos();
        jv.setVisible(true);
    }

    private void btnTransferirVeiculoActionPerformed(ActionEvent evt) {
        boolean valido = escolheuVeiculo();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_VEICULO);
            return;
        }

        Estabelecimento origem = (Estabelecimento) veiculo.getLocal();
        Estabelecimento destino = (Estabelecimento) localDestinoComboBox.getSelectedItem();

        valido = isDiferenteEstabelecimento(origem, destino);
        if(!valido){
            Erros.mostrarErro(this, Erros.LOCAL_ORIGEM_IGUAL_LOCAL_DESTINO);
            return;
        }

        DadosAplicacao da = DadosAplicacao.INSTANCE;
        int capacidadeAtual = da.getNumeroVeiculosNoLocal(destino);
        int lotacao = destino.getCapacidadeMaximaVeiculos();

        valido = naoAtingiuLimite(capacidadeAtual, lotacao);
        if(!valido){
            Erros.mostrarErro(this, Erros.LOCAL_DESTINO_LOTACAO);
            return;
        }

        da.transportarVeiculo(veiculo, destino);

        Sucesso.mostrarSucesso(this, Sucesso.VEICULO_TRANSFERIDO);
        fechar();

        JanelaVeiculos jv = new JanelaVeiculos();
        jv.setVisible(true);
    }

    private boolean naoAtingiuLimite(int capacidadeAtual, int lotacao) {
        return capacidadeAtual < lotacao;
    }

    private boolean isDiferenteEstabelecimento(Estabelecimento origem, Estabelecimento destino) {
        return origem != destino;
    }

    private void btnEscolherVeiculoActionPerformed(ActionEvent evt) {
        boolean valido = escolheuVeiculo();
        if(!valido){
            Erros.mostrarErro(this, Erros.SELECIONAR_VEICULO);
            return;
        }

        veiculo = listaVeiculos.getSelectedValue();
        Estabelecimento origem = (Estabelecimento) veiculo.getLocal();
        int capacidade = origem.getCapacidadeMaximaVeiculos();
        int lotacaoAtual = DadosAplicacao.INSTANCE.getNumeroVeiculosNoLocal(origem);
        lotacaoOrigemLabel.setText(lotacaoAtual + "/"+ capacidade);
    }

    private void initComponents() {
        List<Estabelecimento> list = DadosAplicacao.INSTANCE.getEstabelecimentos();
        for (Estabelecimento estabelecimento : list) {
            modeloComboBoxLocais.addElement(estabelecimento);
            modeloComboBoxLocaisDestino.addElement(estabelecimento);
        }
        veiculo = null;
    }

    private boolean escolheuVeiculo(){
        return  !listaVeiculos.isSelectionEmpty();
    }

    private void btnApresentarVeiculosActionPerformed(ActionEvent evt) {
        modeloListaVeiculos.removeAllElements();
        estabelecimento = (Estabelecimento) locaisComboBox.getSelectedItem();

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
        je.setVisible(true);
    }
}
