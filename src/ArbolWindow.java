import javax.swing.*;
import java.awt.*;

public class ArbolWindow extends JPanel {
    private Arbol arbol;
    public void init() {
        this.arbol = new Arbol(8);
        arbol.setPosScale(128, 64, 256, 512 - 128);
    }

    public void init(int segmentos, boolean estrella) {
        this.arbol = new Arbol(segmentos, estrella);
        arbol.setPosScale(128, 64, 256, 512 - 128);
    }

    public void init(int segmentos, boolean estrella, int ornamentos) {
        this.arbol = new Arbol(segmentos, estrella, ornamentos);
        arbol.setPosScale(128, 64, 256, 512 - 128);
    }


    public void repaintOrnamentos() {
        this.arbol.repaintOrnamentos();
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

        /*
        URL path = Main.class.getResource("alvaro.jpg");
        File file;
        try {
            file = new File(path.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
         */

        /*
        Image img;
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Imagen no encontrada!");
            throw new RuntimeException(e);
        }
        */

        //g.drawImage(img, 0, 0, null);
    }
}
