import java.awt.*;

public class Ornamento extends Objeto {
    private final Color COLOR_A = new Color(255, 0, 0);
    private final Color COLOR_B = new Color(0, 255, 0);

    private final Color COLOR_SOPORTE = new Color(195, 188, 151);

    private final Color COLOR_HIGHLIGHT_A = new Color(255, 255, 255, 128);
    private final Color COLOR_HIGHLIGHT_B = new Color(255, 255, 255, 196);

    private float offsetCurtime = 0;

    public Ornamento() {
        this.offsetCurtime = (float) Math.random();
    }

    public void draw(Graphics g) {
        int posX = this.getX();
        int posY = this.getY();

        int posW = this.getW();
        int posH = this.getH();

        Color c = Color.getHSBColor((float) Main.getCurTime() + this.offsetCurtime, 0.9f, 0.8f);

        g.setColor(c);
        g.fillOval(posX - posW / 2, posY, posW, posH);

        g.setColor(COLOR_HIGHLIGHT_A);
        g.fillOval(posX - 3, posY + 1, (int) (posW / 1.5), (int) (posH / 1.5));

        g.setColor(COLOR_HIGHLIGHT_B);
        g.fillOval(posX, posY + 3, (int) (posW / 2.5), (int) (posH / 2.5));

        g.setColor(COLOR_SOPORTE);
        g.fillRect(posX - (posW / 6), posY - 1, posW / 3, 2);
    }

    void onResize() {

    }
}
