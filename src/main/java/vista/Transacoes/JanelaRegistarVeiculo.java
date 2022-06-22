package vista.Transacoes;

import modelo.*;
import vista.Clientes.JanelaClientes;
import vista.Erros;
import vista.Estatisticas.JanelaEstatistica;
import vista.Eventos.JanelaEventos;
import vista.Oficina.JanelaOficina;
import vista.Veiculos.JanelaVeiculos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class JanelaRegistarVeiculo extends JDialog{
    private JButton estatisticasButton;
    private JButton veiculosButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JTextField textAno;
    private JTextField textMarca;
    private JTextField textMatricula;
    private JTextField textCor;
    private JTextField textPortas;
    private JTextField textQuilometros;
    private JTextField textDonos;
    private JTextField textCondicao;
    private JTextField textModelo;
    private JTextField textValor;
    private JPanel painelPrincipal;
    private JButton registarVeiculoButton;
    private JButton cancelarButton;
    private JComboBox<TipoCombustivel> comboBoxCombustivel;

    private DefaultComboBoxModel modeloComboBoxCombustivel;

    private Veiculo veiculo;

    public JanelaRegistarVeiculo(Frame parent, boolean modal) {
        super(parent, modal);
        setContentPane(painelPrincipal);
        pack();

        veiculosButton.addActionListener(this::btnVeiculosButtonActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaButtonActionPerformed);
        eventosButton.addActionListener(this::btnEventosButtonActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesButtonActionPerformed);
        clientesButton.addActionListener(this::btnClientesButtonActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasButtonActionPerformed);
        registarVeiculoButton.addActionListener(this::registarVeiculoButtonActionPerformed);
        cancelarButton.addActionListener(this::cancelarButtonActionPerformed);
        modeloComboBoxCombustivel = new DefaultComboBoxModel();
        comboBoxCombustivel.setModel(modeloComboBoxCombustivel);
        initComponents();
    }

    private void initComponents() {
        for(TipoCombustivel tc : TipoCombustivel.values()){
            modeloComboBoxCombustivel.addElement(tc);
        }
    }

    public static Veiculo mostrarVeiculo(Frame parent) {
        var registoNovoVeiculo = new JanelaRegistarVeiculo(parent, true);
        registoNovoVeiculo.setLocationRelativeTo(parent);
        registoNovoVeiculo.setVisible(true);
        return registoNovoVeiculo.getVeiculo();
    }
    public Veiculo getVeiculo() {
        return veiculo;
    }


    private void btnEventosButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no botão Eventos");
        this.setVisible(false);
        dispose();

        JanelaEventos j = new JanelaEventos();
        j.setVisible(true);
    }

    private void btnTransacoesButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no botão Transações");
        this.setVisible(false);
        dispose();

        JanelaTransacoes j = new JanelaTransacoes();
        j.setVisible(true);
    }

    private void btnClientesButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no botão Clientes");
        this.setVisible(false);
        dispose();

        JanelaClientes j = new JanelaClientes();
        //j.setVisible(true);
    }

    private void btnEstatisticasButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no botão Estatisticas");
        this.setVisible(false);
        dispose();

        JanelaEstatistica j = new JanelaEstatistica();
        //j.setVisible(true);
    }

    private void btnVeiculosButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no botão Veiculos");
        this.setVisible(false);
        dispose();

        JanelaVeiculos j = new JanelaVeiculos();
        //j.setVisible(true);
    }

    private void btnOficinaButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no botão Oficina");
        this.setVisible(false);
        dispose();

        JanelaOficina j = new JanelaOficina();
        //j.setVisible(true);
    }

    private void registarVeiculoButtonActionPerformed(ActionEvent evt) {
        boolean valido = isMarcaValida(textMarca.getText());
        if(!valido){
            Erros.mostrarErro(this, Erros.MARCA_INVALIDA);
            return;
        }

        valido = isModeloValido(textModelo.getText());
        if(!valido){
            Erros.mostrarErro(this, Erros.MODELO_INVALIDO);
            return;
        }

        valido = isAnoValido(textAno.getText());
        if(!valido){
            Erros.mostrarErro(this, Erros.ANO_INVALIDO);
            return;
        }
        int ano = Integer.parseInt(textAno.getText());

        valido = isMatriculaValida(textMatricula.getText());
        if(!valido){
            Erros.mostrarErro(this, Erros.MATRICULA_INVALIDA);
            return;
        }
        valido = isCorValida(textCor.getText());
        if(!valido){
            Erros.mostrarErro(this, Erros.COR_INVALIDA);
            return;
        }
        if(textPortas.getText().isEmpty()){
            Erros.mostrarErro(this, Erros.PORTAS_INVALIDO);
            return;
        }
        int numeroPortas = Integer.parseInt(textPortas.getText());

        valido = isPortasValidas(numeroPortas);
        if(!valido){
            Erros.mostrarErro(this, Erros.PORTAS_INVALIDO);
            return;
        }
        if(textQuilometros.getText().isEmpty()){
            Erros.mostrarErro(this, Erros.NUMERO_DONOS_INVALIDO); //MUDAR PARA O ERRO A ADICIONAR
            return;
        }

        int quilometros = Integer.parseInt(textQuilometros.getText());

        valido = isQuilometrosValido(quilometros);
        if(!valido){
            Erros.mostrarErro(this, Erros.NUMERO_DONOS_INVALIDO); ///mudar
            return;
        }
        if(textDonos.getText().isEmpty()){
            Erros.mostrarErro(this, Erros.NUMERO_DONOS_INVALIDO);
        }

        int numeroDonos = Integer.parseInt(textDonos.getText());
        valido = isDonosValido(numeroDonos);
        if(!valido){
            Erros.mostrarErro(this, Erros.NUMERO_DONOS_INVALIDO);
            return;
        }

        valido = isCondicaoValida(textCondicao.getText());
        if(!valido){
            Erros.mostrarErro(this, Erros.CONDICAO_VEICULO_INVALIDO);
            return;
        }

        if(textValor.getText().isEmpty()) {
            Erros.mostrarErro(this, Erros.VALOR_VEICULO_INVALIDO);
        }

        int valorVeiculo = Integer.parseInt(textValor.getText());
        valido = isValorValido(valorVeiculo);
        if(!valido){
            Erros.mostrarErro(this, Erros.VALOR_VEICULO_INVALIDO);
            return;
        }


        veiculo = new Veiculo(textMarca.getText(), textModelo.getText(), ano, textMatricula.getText(), textCor.getText(), numeroPortas, (TipoCombustivel) comboBoxCombustivel.getSelectedItem(), quilometros, numeroDonos, textCondicao.getText(), valorVeiculo);

        System.out.println("Registar Veiculo");
        fechar();
    }

    private void cancelarButtonActionPerformed(ActionEvent evt) {
        System.out.println("Click no botão Cancelar");
        fechar();
    }

    private void fechar(){
        setVisible(false);
    }

    private boolean isMarcaValida(String marca){
        return !(marca.trim().length() < 2 || marca.trim().length() > 50);
    }

    private boolean isModeloValido(String modelo){
        return !(modelo.trim().length() < 2 || modelo.trim().length() > 50);
    }

    private boolean isAnoValido(String ano){
        return (ano.trim().matches("^(19|20)\\d{2}$"));
    }

    private boolean isMatriculaValida(String matricula){
        return (matricula.trim().matches("^([A-Z]{2})[-]([0-9]{2})[-]([A-Z]{2})$")||
                matricula.trim().matches("^([0-9]{2})[-]([0-9]{2})[-]([A-Z]{2})$") ||
                matricula.trim().matches("^([A-Z]{2})[-]([0-9]{2})[-]([0-9]{2})$") ||
                matricula.trim().matches("^([0-9]{2})[-]([A-Z]{2})[-]([0-9]{2})$"));
    }

    private boolean isCorValida(String cor){
        return !(cor.trim().length() < 2 || cor.trim().length() > 50);
    }

    private boolean isPortasValidas(int portas){
        return (portas == 3 || portas == 5);
    }

    private boolean isQuilometrosValido(int quilometros){
        return (quilometros >= 0);
    }

    private boolean isDonosValido(int donos){
        return (donos >= 0);
    }

    private boolean isCondicaoValida(String condicao){
        return !(condicao.trim().length() < 2 || condicao.trim().length() > 50);
    }

    private boolean isValorValido(int valor){
        return (valor >= 0);
    }

}
