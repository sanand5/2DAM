/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package actividad_10.gui;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class SobreNosotros extends javax.swing.JDialog {

    /**
     * Creates new form SobreNosotros
     */
    public SobreNosotros(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        ImageIcon imagen = new ImageIcon("./src/actividad_10/img/LogoLateral.png");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(jLabelImg.getWidth(), jLabelImg.getHeight(), Image.SCALE_DEFAULT ));
        jLabelImg.setIcon(icono);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jLabelImg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Solterra Enegia");

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea.setEditable(false);
        jTextArea.setColumns(20);
        jTextArea.setLineWrap(true);
        jTextArea.setRows(5);
        jTextArea.setText("En Solterra Energía, nuestra historia comienza con una profunda pasión por la energía sostenible y un legado transmitido de generación en generación. De la mano de padre e hijo, hemos estado llevando a cabo proyectos energéticos para familiares y amigos durante años. Esta dedicación nos llevó a fundar Solterra Energía, convencidos de que todos debemos contribuir a un futuro más limpio y sostenible.");
        jTextArea.setToolTipText("");
        jTextArea.setWrapStyleWord(true);
        jTextArea.setAutoscrolls(false);
        jScrollPane1.setViewportView(jTextArea);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 341, 154));
        jPanel2.add(jLabelImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 341, 125));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelImg;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;
    // End of variables declaration//GEN-END:variables
}