/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package altatiendaonline;

import javax.swing.event.ChangeEvent;
import org.netbeans.validation.api.builtin.stringvalidation.StringValidators;
import org.netbeans.validation.api.ui.ValidationGroup;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class Registro extends javax.swing.JDialog {
    
    
    PantallaPrincipal p;
    public Registro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        p = (PantallaPrincipal) parent;
        ValidationGroup group = validationPanel1.getValidationGroup();
        group.add(jTextFieldNombre, StringValidators.REQUIRE_NON_EMPTY_STRING);
        group.add(jTextFieldNombre_Usuario, StringValidators.REQUIRE_NON_EMPTY_STRING, StringValidators.NO_WHITESPACE);
        group.add(jPasswordFieldPassword, StringValidators.REQUIRE_NON_EMPTY_STRING, StringValidators.NO_WHITESPACE);//?
        group.add(jTextFieldCalle, StringValidators.REQUIRE_NON_EMPTY_STRING);
        group.add(jTextFieldPuerta, StringValidators.REQUIRE_VALID_INTEGER);
        group.add(jTextFieldPiso, StringValidators.REQUIRE_VALID_INTEGER);
        group.add(jTextFieldCodigo_Postal, StringValidators.REQUIRE_VALID_INTEGER, StringValidators.minLength(5), StringValidators.maxLength(5));
        group.add(jTextFieldTelefono, StringValidators.REQUIRE_VALID_INTEGER, StringValidators.minLength(9), StringValidators.maxLength(9));
        group.add(jTextFieldEmail, StringValidators.EMAIL_ADDRESS, StringValidators.NO_WHITESPACE);
        
        validationPanel1.addChangeListener(((ChangeEvent e) -> {
            if (validationPanel1.getProblem() == null) {
                jButtonRegister.setEnabled(true);
            }else{
                jButtonRegister.setEnabled(false);
            }
        }));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel13 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldApellidos = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNombre_Usuario = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldCalle = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldPuerta = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldPiso = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldCodigo_Postal = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldTelefono = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButtonRegister = new javax.swing.JButton();
        validationPanel1 = new org.netbeans.validation.api.ui.swing.ValidationPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel13.setLayout(new java.awt.GridLayout(1, 2, 30, 0));

        jPanel2.setLayout(new java.awt.GridLayout(5, 1, 0, 10));

        jPanel3.setLayout(new java.awt.GridLayout(2, 1));

        jLabel2.setText("Nombre ");
        jLabel2.setName(""); // NOI18N
        jPanel3.add(jLabel2);

        jTextFieldNombre.setName("Nombre"); // NOI18N
        jTextFieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreActionPerformed(evt);
            }
        });
        jPanel3.add(jTextFieldNombre);

        jPanel2.add(jPanel3);

        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        jLabel1.setText("Apellidos ");
        jPanel1.add(jLabel1);

        jTextFieldApellidos.setName("Apellidos"); // NOI18N
        jPanel1.add(jTextFieldApellidos);

        jPanel2.add(jPanel1);

        jPanel4.setLayout(new java.awt.GridLayout(2, 1));

        jLabel3.setText("Nombre usuario");
        jPanel4.add(jLabel3);

        jTextFieldNombre_Usuario.setName("Nombre de usuario"); // NOI18N
        jTextFieldNombre_Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombre_UsuarioActionPerformed(evt);
            }
        });
        jPanel4.add(jTextFieldNombre_Usuario);

        jPanel2.add(jPanel4);

        jPanel5.setLayout(new java.awt.GridLayout(2, 1));

        jLabel4.setText("Contraseña ");
        jPanel5.add(jLabel4);

        jPasswordFieldPassword.setName("Contraseña"); // NOI18N
        jPanel5.add(jPasswordFieldPassword);

        jPanel2.add(jPanel5);

        jPanel6.setLayout(new java.awt.GridLayout(2, 1));

        jLabel5.setText("Calle ");
        jPanel6.add(jLabel5);

        jTextFieldCalle.setName("Calle"); // NOI18N
        jPanel6.add(jTextFieldCalle);

        jPanel2.add(jPanel6);

        jPanel13.add(jPanel2);

        jPanel7.setLayout(new java.awt.GridLayout(5, 1, 0, 10));

        jPanel8.setLayout(new java.awt.GridLayout(2, 1));

        jLabel6.setText("Puerta ");
        jPanel8.add(jLabel6);

        jTextFieldPuerta.setName("Puerta"); // NOI18N
        jPanel8.add(jTextFieldPuerta);

        jPanel7.add(jPanel8);

        jPanel9.setLayout(new java.awt.GridLayout(2, 1));

        jLabel7.setText("Piso ");
        jPanel9.add(jLabel7);

        jTextFieldPiso.setName("Piso"); // NOI18N
        jPanel9.add(jTextFieldPiso);

        jPanel7.add(jPanel9);

        jPanel10.setLayout(new java.awt.GridLayout(2, 1));

        jLabel8.setText("Código Postal");
        jPanel10.add(jLabel8);

        jTextFieldCodigo_Postal.setName("Código_Postal"); // NOI18N
        jPanel10.add(jTextFieldCodigo_Postal);

        jPanel7.add(jPanel10);

        jPanel11.setLayout(new java.awt.GridLayout(2, 1));

        jLabel9.setText("Número teléfono");
        jPanel11.add(jLabel9);

        jTextFieldTelefono.setName("Teléfono"); // NOI18N
        jPanel11.add(jTextFieldTelefono);

        jPanel7.add(jPanel11);

        jPanel12.setLayout(new java.awt.GridLayout(2, 1));

        jLabel10.setText("Correo electrónico");
        jPanel12.add(jLabel10);

        jTextFieldEmail.setName("Email"); // NOI18N
        jPanel12.add(jTextFieldEmail);

        jPanel7.add(jPanel12);

        jPanel13.add(jPanel7);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Completa nuestro formulario:");

        jButtonRegister.setText("Registrarse");
        jButtonRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                            .addComponent(jLabel11)
                            .addComponent(validationPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jButtonRegister)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(validationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonRegister)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreActionPerformed

    private void jButtonRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegisterActionPerformed
        p.anadirUsuario(
                jTextFieldNombre_Usuario.getText(),
                new String(jPasswordFieldPassword.getPassword())
        );
        dispose();
    }//GEN-LAST:event_jButtonRegisterActionPerformed

    private void jTextFieldNombre_UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombre_UsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombre_UsuarioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JTextField jTextFieldApellidos;
    private javax.swing.JTextField jTextFieldCalle;
    private javax.swing.JTextField jTextFieldCodigo_Postal;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldNombre_Usuario;
    private javax.swing.JTextField jTextFieldPiso;
    private javax.swing.JTextField jTextFieldPuerta;
    private javax.swing.JTextField jTextFieldTelefono;
    private org.netbeans.validation.api.ui.swing.ValidationPanel validationPanel1;
    // End of variables declaration//GEN-END:variables
}