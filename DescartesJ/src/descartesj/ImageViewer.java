/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package descartesj;


import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.image.*;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;

/**
 *
 * @author Lawrence Herlinger
 */
public class ImageViewer extends JPanel {

    private java.awt.Image image;
    private boolean stretched = true;
    private int xCoordinate = 0;
    private int yCoordinate = 0;

//Default no arg constructor
    public ImageViewer() {
    }
//Constructor
    public ImageViewer(Image image) {
        this.image = image;
    }

//Get the image
    public java.awt.Image getImage() {
        return image;
    }
//set the image
    public void setImage(java.awt.Image image) {
        this.image = image;
        this.repaint();
    }
//overriding the paint Component
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            if (isStretched()) {
                g.drawImage(image, xCoordinate, yCoordinate,
                        getSize().width, getSize().height, this);
            } else {
                g.drawImage(image, xCoordinate, yCoordinate, this);
            }
        }
    }

    public boolean isStretched() {
        return stretched;
    }

    public void setStretched(boolean stretched) {
        this.stretched = stretched;
        repaint();
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
