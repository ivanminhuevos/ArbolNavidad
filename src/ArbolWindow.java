import javax.swing.*;
import java.awt.*;

public class ArbolWindow extends JPanel {
    private Arbol arbol;

    public void init(int segmentos, boolean estrella, int ornamentos, boolean tronco) {
        this.arbol = new Arbol(segmentos, estrella, ornamentos, tronco);
        arbol.setPosScale(128, 64, 256, 512 - 128);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // llama el paint de JPanel

        Rectangle size = g.getClipBounds();
        int x = (int) size.getX();
        int y = (int) size.getY();
        int w = (int) size.getWidth();
        int h = (int) size.getHeight();

        g.setColor(new Color(180, 220, 255));
        g.fillRect(x, y, w, h);

        arbol.draw(g);
    }
}
