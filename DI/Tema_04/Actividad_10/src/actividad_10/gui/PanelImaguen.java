package actividad_10.gui;


import java.awt.Graphics;
import java.io.File;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class PanelImaguen extends JPanel implements Serializable{

    private File imgFile;
    
    public PanelImaguen() {
    }

    public File getPath() {
        return imgFile;
    }

    public void setPath(File path) {
        this.imgFile = path;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imgFile != null && imgFile.exists()) {
            ImageIcon imageicon = new ImageIcon(imgFile.getAbsolutePath());
            g.drawImage(imageicon.getImage(), 0,0,getWidth(), getHeight(), this);
            
        }
    }
    
    
    
}
