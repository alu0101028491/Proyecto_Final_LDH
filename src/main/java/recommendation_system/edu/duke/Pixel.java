package recommendation_system.edu.duke;

public class Pixel {
    static final int MAX_VALUE = 255;
    private int alpha = 255;
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private int myX;
    private int myY;

    Pixel(int i, int x, int y) {
        this.myX = x;
        this.myY = y;
        this.setValue(i);
    }

    Pixel(int r, int g, int b, int a, int x, int y) {
        this.red = r;
        this.green = g;
        this.blue = b;
        this.alpha = a;
        this.myX = x;
        this.myY = y;
    }

    Pixel(int r, int g, int b, int x, int y) {
        this(r, g, b, 255, x, y);
    }

    public Pixel(Pixel other) {
        this(other.getRed(), other.getGreen(), other.getBlue(), other.getAlpha(), other.getX(), other.getY());
    }

    public int getX() {
        return this.myX;
    }

    public int getY() {
        return this.myY;
    }

    public int getRed() {
        return this.red;
    }

    public int getGreen() {
        return this.green;
    }

    public int getBlue() {
        return this.blue;
    }

    public int getAlpha() {
        return this.alpha;
    }

    public void setRed(int r) {
        this.red = this.clamp(r);
    }

    public void setGreen(int g) {
        this.green = this.clamp(g);
    }

    public void setBlue(int b) {
        this.blue = this.clamp(b);
    }

    public void setAlpha(int a) {
        this.alpha = this.clamp(a);
    }

    public String toString() {
        return "Pixel R: " + this.red + " G: " + this.green + " B: " + this.blue;
    }

    int getValue() {
        return this.alpha << 24 | this.red << 16 | this.green << 8 | this.blue;
    }

    void setValue(int pixel) {
        this.alpha = pixel >> 24 & 0xFF;
        this.red = pixel >> 16 & 0xFF;
        this.green = pixel >> 8 & 0xFF;
        this.blue = pixel & 0xFF;
    }

    private int clamp(int value) {
        return Math.max(0, Math.min(value, 255));
    }
}

