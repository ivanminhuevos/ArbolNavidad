import java.awt.*;

// clase objeto base, abstracta para qe podamos hacer @Override
public abstract class Objeto {
    private int x = 0;
    public int getX() {
        return this.x;
    }

    private int y = 0;
    public int getY() {
        return this.y;
    }

    private int w = 32;
    public int getW() {
        return this.w;
    }

    private int h = 32;
    public int getH() {
        return this.h;
    }

    public void setPosScale(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        onResize();
    }

    public void setPosScale(Rectangle rect) {
        this.x = (int) rect.getX();
        this.y = (int) rect.getY();
        this.w = (int) rect.getWidth();
        this.h = (int) rect.getHeight();

        onResize();
    }

    public Rectangle getDimensions() {
        return new Rectangle(this.x, this.y, this.w, this.h);
    }

    // para ser overrideada cuando se cambie el tamaño
    abstract void onResize();
}
