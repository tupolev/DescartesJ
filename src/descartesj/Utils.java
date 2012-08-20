
package descartesj;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Utils {
 
    public static void setIconImage(String path, JLabel component) {
        ImageIcon image = new ImageIcon(path);
        int scale = 3;

        int width = image.getIconWidth();
        int height = image.getIconHeight();
        BufferedImage buffer = new BufferedImage(scale * width, scale * height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = buffer.createGraphics();
        graphics.scale(scale,scale);
        image.paintIcon(null, graphics, 0, 0);
        graphics.dispose();
        component.setIcon(image);
        //JLabel label = new JLabel(new ImageIcon(buffer)));
    }
    
    public static void setIconDirectToImage(ImageIcon image, JLabel component) {
        int scale = 3;

        int width = image.getIconWidth();
        int height = image.getIconHeight();
        BufferedImage buffer = new BufferedImage(scale * width, scale * height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = buffer.createGraphics();
        graphics.scale(scale,scale);
        image.paintIcon(null, graphics, 0, 0);
        graphics.dispose();
        component.setIcon(image);
        //JLabel label = new JLabel(new ImageIcon(buffer)));
    }
    
}
