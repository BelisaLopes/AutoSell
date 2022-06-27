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

public class JanelaEstatistica extends JFrame {
    private JButton veiculosButton;
    private JButton estatisticasButton;
    private JButton clientesButton;
    private JButton transaçõesButton;
    private JButton eventosButton;
    private JButton oficinaButton;
    private JButton modelosEMarcasComMaisPecasButton;
    private JButton modelosEMarcasMaisVendidosButton;
    private JButton númeroTotalDeVeículosVendidosButton;
    private JButton melhoresClientesButton;
    private JButton melhoresFiliaisFeirasButton;
    private JPanel painelPrincipal;

    public JanelaEstatistica() {
        setContentPane(painelPrincipal);
        pack();
        abrir();

        veiculosButton.addActionListener(this::btnVeiculosButtonActionPerformed);
        oficinaButton.addActionListener(this::btnOficinaButtonActionPerformed);
        eventosButton.addActionListener(this::btnEventosButtonActionPerformed);
        transaçõesButton.addActionListener(this::btnTransacoesButtonActionPerformed);
        clientesButton.addActionListener(this::btnClientesButtonActionPerformed);
        estatisticasButton.addActionListener(this::btnEstatisticasButtonActionPerformed);

        modelosEMarcasComMaisPecasButton.addActionListener(this::maiorUsoPecas);
        modelosEMarcasMaisVendidosButton.addActionListener(this::btnModelosEMarcasMaisVendidosActionPerformed);
        númeroTotalDeVeículosVendidosButton.addActionListener(this::btnNumeroTotalDeVeiculosVendidosActionPerformed);
        melhoresClientesButton.addActionListener(this::melhoresClientes);
        melhoresFiliaisFeirasButton.addActionListener(this::btnMelhoresFiliaisFeirasActionPerformed);
    }

    private void abrir() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void maiorUsoPecas(ActionEvent actionEvent) {
        System.out.println("Click no maiorUsoPecas");

        JanelaModelosMarcasMaiorUsoPecas j = new JanelaModelosMarcasMaiorUsoPecas();
        abrir(j);
        fechar();
    }

    private void btnModelosEMarcasMaisVendidosActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaModelosMarcasMaisVendidos j = new JanelaModelosMarcasMaisVendidos();
        j.setVisible(true);
    }

    private void btnNumeroTotalDeVeiculosVendidosActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaNumeroVeiculosVendidos j = new JanelaNumeroVeiculosVendidos();
        j.setVisible(true);
    }

    private void melhoresClientes(ActionEvent actionEvent) {
        System.out.println("Click no melhoresClientes");
        boolean valido = existemTransacoes();
        if(!valido){
            Erros.mostrarErro(this, Erros.TRANSACOES_NAO_EXISTEM);
            return;
        }

        JanelaMelhoresClientes j = new JanelaMelhoresClientes();
        abrir(j);
        fechar();
    }

    private boolean existemTransacoes() {
        return DadosAplicacao.INSTANCE.existemTransacoes();
    }

    private void btnMelhoresFiliaisFeirasActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaMelhoresFiliaisFeiras j = new JanelaMelhoresFiliaisFeiras();
        j.setVisible(true);
    }

    private void btnVeiculosButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaVeiculos j = new JanelaVeiculos();
        j.setVisible(true);
    }
    private void btnOficinaButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaOficina j = new JanelaOficina();
        j.setVisible(true);
    }

    private void btnEventosButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaEventos j = new JanelaEventos();
        j.setVisible(true);
    }

    private void btnTransacoesButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaTransacoes j = new JanelaTransacoes();
        j.setVisible(true);
    }

    private void btnClientesButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaClientes j = new JanelaClientes();
        j.setVisible(true);
    }

    private void btnEstatisticasButtonActionPerformed(ActionEvent evt) {
        setVisible(false);
        dispose();
        JanelaEstatistica j = new JanelaEstatistica();
        j.setVisible(true);
    }

    private void abrir(JFrame j) {
        j.setLocationRelativeTo(this);
        j.setVisible(true);
    }

    public void fechar(){
        setVisible(false);
        dispose();
    }

    public static void main(String[] args) {
        new JanelaEstatistica();
    }
}
