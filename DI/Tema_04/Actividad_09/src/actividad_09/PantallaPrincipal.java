/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package actividad_09;

import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author andre
 */
public class PantallaPrincipal extends javax.swing.JFrame {


    PantallaSecundaria secondScreen;
    public PantallaPrincipal() {
        initComponents();
        secondScreen = new PantallaSecundaria(this, false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonRojo = new javax.swing.JButton();
        jLabelTitulo = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenuImaguen = new javax.swing.JMenu();
        jMenuItemImaguen_01 = new javax.swing.JMenuItem();
        jMenuItemImaguen_02 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonRojo.setBackground(new java.awt.Color(0, 255, 0));
        jButtonRojo.setText("Boton Rojo");
        jButtonRojo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRojoActionPerformed(evt);
            }
        });

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabelTitulo.setText("SIIIIMUUULAAAACROOOOO");

        jMenuImaguen.setText("Imaguen");

        jMenuItemImaguen_01.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItemImaguen_01.setText("Primera Imaguen");
        jMenuItemImaguen_01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemImaguen_01ActionPerformed(evt);
            }
        });
        jMenuImaguen.add(jMenuItemImaguen_01);

        jMenuItemImaguen_02.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItemImaguen_02.setText("Segunda Imaguen");
        jMenuItemImaguen_02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemImaguen_02ActionPerformed(evt);
            }
        });
        jMenuImaguen.add(jMenuItemImaguen_02);

        jMenuBar2.add(jMenuImaguen);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jButtonRojo, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelTitulo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jButtonRojo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRojoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRojoActionPerformed
        JOptionPane.showMessageDialog(this, "Todo bien?", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButtonRojoActionPerformed

    private void jMenuItemImaguen_01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemImaguen_01ActionPerformed
       secondScreen.setimg(new File("./src/actividad_09/img/GOKU.jpg"));
       secondScreen.setVisible(true);
    }//GEN-LAST:event_jMenuItemImaguen_01ActionPerformed

    private void jMenuItemImaguen_02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemImaguen_02ActionPerformed
        // TODO add your handling code here:
        secondScreen.setimg(new File("./src/actividad_09/img/VEGETA.jpg"));
        secondScreen.setVisible(true);
    }//GEN-LAST:event_jMenuItemImaguen_02ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRojo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu jMenuImaguen;
    private javax.swing.JMenuItem jMenuItemImaguen_01;
    private javax.swing.JMenuItem jMenuItemImaguen_02;
    // End of variables declaration//GEN-END:variables
}
