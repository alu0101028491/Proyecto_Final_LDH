package recommendation_system.edu.duke;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class ImageResource {
    static final int WIDTH = 200;
    static final int HEIGHT = 200;
    private Pixel[] myPixels;
    private BufferedImage myImage;
    private String myFileName;
    private String myPath;
    private ImageFrame myDisplay;

    public ImageResource() {
        File file = FileSelector.selectFile(ImageIO.getReaderFileSuffixes());
        if (file == null) {
            throw new ResourceException("ImageResource: No image file choosen");
        }
        this.init(file);
    }

    public ImageResource(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new ResourceException("ImageResource: witdh and height values must be positive [" + width + "x" + height + "]");
        }
        this.init("", this.getBlankImage(width, height));
    }

    public ImageResource(String fileName) {
        this.init(fileName, this.getImageFromFile(fileName));
    }

    public ImageResource(File file) {
        this.init(file);
    }

    public ImageResource(ImageResource other) {
        this.init(String.valueOf(other.myPath) + other.myFileName, other.myImage);
    }

    public int getWidth() {
        return this.myImage.getWidth(this.myDisplay);
    }

    public int getHeight() {
        return this.myImage.getHeight(this.myDisplay);
    }

    public Iterable<Pixel> pixels() {
        if (this.myPixels == null) {
            throw new ResourceException("ImageResource: not ready to iterate over pixels");
        }
        return Arrays.asList(this.myPixels);
    }

    public void draw() {
        this.updateImage();
        this.myDisplay.show(this.myImage);
    }

    public String getFileName() {
        return this.myFileName;
    }

    public void setFileName(String name) {
        if (!name.equals("")) {
            this.myFileName = name;
            this.myDisplay.setTitle(this.myFileName);
        }
    }

    public Pixel getPixel(int x, int y) {
        return this.myPixels[y * this.getWidth() + x];
    }

    public void setPixel(int x, int y, Pixel p) {
        if (x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight()) {
            this.myPixels[y * this.getWidth() + x] = p;
        }
    }

    public String toString() {
        if (this.myImage == null) {
            return "";
        }
        return "IMAGE\nFile name: " + this.myFileName + "\n" + "Width: " + this.getWidth() + "\n" + "Height: " + this.getHeight();
    }

    public void save() {
        if (this.myFileName.equals("")) {
            this.saveAs();
        }
        try {
            this.updateImage();
            File file = new File(String.valueOf(this.myPath) + this.myFileName);
            ImageIO.write((RenderedImage)this.myImage, "jpg", file);
        }
        catch (Exception e) {
            throw new ResourceException("ImageResource: unable to save image to a file ", e);
        }
    }

    public void saveAs() {
        File f = FileSelector.saveFile(ImageIO.getWriterFileSuffixes());
        if (f == null) {
            throw new ResourceException("ImageResource: no file chosen for save.");
        }
        try {
            this.setPath(f.getCanonicalPath());
            this.save();
        }
        catch (Exception e) {
            throw new ResourceException("ImageResource: unable to save image to " + f, e);
        }
    }

    private Pixel[] imageToPixels(Image image) {
        int w = this.getWidth();
        int h = this.getHeight();
        int[] pixels = new int[w * h];
        PixelGrabber pg = new PixelGrabber(image, 0, 0, w, h, pixels, 0, w);
        try {
            pg.grabPixels();
        }
        catch (InterruptedException e) {
            System.err.println("Interrupted waiting for pixels!");
            return null;
        }
        if ((pg.getStatus() & 0x80) != 0) {
            System.err.println("Image fetch aborted or errored");
            return null;
        }
        return this.intsToPixels(pixels, w, h);
    }

    private Pixel[] intsToPixels(int[] pixels, int width, int height) {
        if (pixels == null) {
            throw new ResourceException(String.format("ImageResource: no pixels for %d %d\n", width, height));
        }
        Pixel[] pix = new Pixel[pixels.length];
        for (int i = 0; i < pixels.length; ++i) {
            pix[i] = new Pixel(pixels[i], i % width, i / width);
        }
        return pix;
    }

    private int[] pixelsToInts(Pixel[] pixels) {
        int[] pix = new int[pixels.length];
        for (int i = 0; i < pixels.length; ++i) {
            pix[i] = pixels[i].getValue();
        }
        return pix;
    }

    private void updateImage() {
        int width = this.getWidth();
        int height = this.getHeight();
        this.myImage = new BufferedImage(width, height, 1);
        this.myImage.setRGB(0, 0, width, height, this.pixelsToInts(this.myPixels), 0, width);
    }

    private BufferedImage getBlankImage(int width, int height) {
        return new BufferedImage(width, height, 1);
    }

    private BufferedImage getImageFromFile(String fileName) {
        try {
            File file = new File(fileName);
            BufferedImage image = ImageIO.read(file);
            while (image.getWidth(null) < 0) {
            }
            return image;
        }
        catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    private void setPath(String fileName) {
        int index = fileName.lastIndexOf(File.separator);
        if (index == -1) {
            this.myPath = "";
        } else {
            this.myFileName = fileName.substring(index + 1);
            this.myPath = fileName.substring(0, index + 1);
        }
    }

    private void init(File f) {
        try {
            String path = f.getCanonicalPath();
            this.init(path, this.getImageFromFile(path));
        }
        catch (Exception e) {
            throw new ResourceException("ImageResource: unable to find " + f);
        }
    }

    private void init(String fileName, BufferedImage image) {
        try {
            this.setPath(fileName);
            this.myImage = image;
            this.myDisplay = new ImageFrame(fileName, image);
            this.myPixels = this.imageToPixels(this.myImage);
        }
        catch (Exception e) {
            throw new ResourceException("ImageResource: not an image file " + fileName);
        }
    }
}

