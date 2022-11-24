package recommendation_system.edu.duke;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;

class ImageFrame
extends JFrame {
    private static final long serialVersionUID = 1L;
    private ImagePanel myPanel;

    public ImageFrame(String fileName, Image image) {
        this.setTitle(fileName);
        this.setDefaultCloseOperation(2);
    }

    void show(Image image) {
        if (this.myPanel == null) {
            this.myPanel = new ImagePanel(image, image.getWidth(this), image.getHeight(this));
            Container c = this.getContentPane();
            c.setBackground(Color.WHITE);
            c.add(this.myPanel);
            this.pack();
        } else {
            this.myPanel.setImage(image, image.getWidth(this), image.getHeight(this));
        }
        this.setVisible(true);
    }

    static class ImagePanel
    extends JPanel {
        private static final long serialVersionUID = 1L;
        private Image myImage;

        public ImagePanel(Image image, int width, int height) {
            this.setBackground(Color.white);
            this.setPreferredSize(new Dimension(width, height));
            this.myImage = image;
        }

        public void setImage(Image image, int width, int height) {
            this.myImage = image;
            this.setPreferredSize(new Dimension(width, height));
            this.repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (this.myImage != null) {
                g.drawImage(this.myImage, 0, 0, Color.white, this);
            }
        }
    }
}

