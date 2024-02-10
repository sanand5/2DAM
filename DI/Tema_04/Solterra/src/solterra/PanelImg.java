package solterra;


import java.awt.Graphics;
import java.io.File;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andre
 */
public class PanelImg extends JPanel implements Serializable{

    private File imgFile;
    
    public PanelImg() {
    }

    public File getPath() {
        return imgFile;
    }

    public void setPath(File path) {
        this.imgFile = path;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        if (imgFile != null && imgFile.exists()) {
            ImageIcon imageicon = new ImageIcon(imgFile.getAbsolutePath());
            g.drawImage(imageicon.getImage(), this.getWidth(), 0, this);
            
        }
    }
    
    
    
}
