package vista.Estatisticas;

import vista.Clientes.JanelaClientes;
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

        modelosEMarcasComMaisPecasButton.addActionListener(this::btnModelosEMarcasComMaisPecasActionPerformed);
        modelosEMarcasMaisVendidosButton.addActionListener(this::btnModelosEMarcasMaisVendidosActionPerformed);
        númeroTotalDeVeículosVendidosButton.addActionListener(this::btnNumeroTotalDeVeiculosVendidosActionPerformed);
        melhoresClientesButton.addActionListener(this::btnMelhoresClientesActionPerformed);
        melhoresFiliaisFeirasButton.addActionListener(this::btnMelhoresFiliaisFeirasActionPerformed);
    }

    private void abrir() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void btnModelosEMarcasComMaisPecasActionPerformed(ActionEvent evt) {
        System.out.println("Modelos e marcas com mais peças");
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

    private void btnMelhoresClientesActionPerformed(ActionEvent evt) {
        System.out.println("Melhores clientes");
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

    public static void main(String[] args) {
        new JanelaEstatistica();
    }
}
