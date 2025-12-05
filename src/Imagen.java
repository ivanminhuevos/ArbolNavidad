import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Imagen extends Objeto {
    private final Image img;


    public void draw(Graphics g) {
        int x = this.getX();
        int y = this.getY();

        int w = this.getW();
        int h = this.getH();

        g.drawImage(img, x, y, w, h, null);
    }


    public Imagen(String ruta) {
        URL path = Main.class.getResource(ruta);
        File file;
        try {
            file = new File(path.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try {
            this.img = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Imagen no encontrada!");
            throw new RuntimeException(e);
        }
    }

    @Override
    void onResize() {}
}
