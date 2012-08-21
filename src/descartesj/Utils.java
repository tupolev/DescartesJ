
package descartesj;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Utils {
 
    public static void setIconImage(String path, JLabel component) {
        ImageIcon image = new ImageIcon(path);
//        int scale = 1;
//
//        int width = image.getIconWidth();
//        int height = image.getIconHeight();
//        //BufferedImage buffer = new BufferedImage(scale * width, scale * height, BufferedImage.TYPE_INT_ARGB);
//        BufferedImage buffer = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D graphics = buffer.createGraphics();
//        graphics.scale(1/10,1/10);
//        image.paintIcon(null, graphics, 0, 0);
//        graphics.dispose();
//        component.setIcon(image);
//        //JLabel label = new JLabel(new ImageIcon(buffer)));
        setIconScaledToImage(image, component);
        
    }
    
    public static void setIconDirectToImage(ImageIcon image, JLabel component) {
//        int scale = 1;
//
//        int width = image.getIconWidth();
//        int height = image.getIconHeight();
//        //BufferedImage buffer = new BufferedImage(scale * width, scale * height, BufferedImage.TYPE_INT_ARGB);
//        BufferedImage buffer = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D graphics = buffer.createGraphics();
//        graphics.scale(1/10,1/10);
//        image.paintIcon(null, graphics, 0, 0);
//        graphics.dispose();
//        component.setIcon(image);
        //JLabel label = new JLabel(new ImageIcon(buffer)));
        setIconScaledToImage(image, component);
    }
    
    
    public static void setIconScaledToImage(ImageIcon image, JLabel component) {
        //ImageIcon fot = new ImageIcon(path_ala_imagen);
        
        Icon icono = new ImageIcon(image.getImage().getScaledInstance(component.getWidth(), component.getHeight(), Image.SCALE_DEFAULT));
        component.setIcon(icono);
        MainWindow.getWindows()[0].repaint();

    
    }
    
}
