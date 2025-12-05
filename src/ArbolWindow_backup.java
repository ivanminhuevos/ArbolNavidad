import javax.swing.*;
import java.awt.*;

public class ArbolWindow_backup extends JPanel {
    private static final Color COLOR_HOJA_A = new Color(61, 151, 61);
    private static final Color COLOR_HOJA_B = new Color(67, 106, 67);

    private static final Color COLOR_TRONCO = new Color(62, 38, 10);


    // cada paso del arbol es un triangulo

    private void paintTree(Graphics g, Rectangle pos, int ySteps) {
        g.setColor(Color.RED);
        g.drawRect((int) pos.getX(), (int) pos.getY(), (int) pos.getWidth(), (int) pos.getHeight());


        int posX = (int) pos.getX();
        int posY = (int) pos.getY();

        int posW = (int) pos.getWidth();
        int posH = (int) pos.getHeight();


        int troncoW = 32;
        int troncoH = 64;

        double yAccum = posY;

        Rectangle[] tris = new Rectangle[ySteps];


        int sum = 0;
        int acc = 1;
        double[] altoVals = new double[ySteps];
        // calc cuanto de alto hacer
        for(int i = 0; i < ySteps; i++) {
            altoVals[i] = acc;

            sum += acc;
            acc++;
        }

        for(int i = 0; i < ySteps; i++) {
            altoVals[i] /= sum;
        }

        double inviDelta;
        for(int i = 0; i < ySteps; i++) {
            inviDelta = ((double) i + 1) / ((double) (ySteps));
            // hazlo mas fino cuando mas alto esta
            inviDelta = Math.max(inviDelta * 1, 0);

            double invWSub = posW * (1 - inviDelta);


            double altoVal = altoVals[i];
            double HSub = posH * altoVal;

            Rectangle rectAdd = new Rectangle();
            rectAdd.setRect(posX + (invWSub * 0.5), yAccum, posW * inviDelta, HSub);
            tris[i] = rectAdd;

            yAccum += HSub;
        }




        // dibuja el tronco
        g.setColor(COLOR_TRONCO);
        g.fillRect( posX + (posW / 2) - (troncoW / 2), (int) yAccum, troncoW, troncoH);


        Rectangle rectGet;
        for(int i = (tris.length - 1); i >= 0; i--) {
            rectGet = tris[i];

            //paintTreeStep(g, rectGet, (i % 2) == 0, i == 0);
        }
    }


    private Arbol arbol;

    public void init() {
        this.arbol = new Arbol(8);
        arbol.setDimensions(128, 64, 256, 512 - 128);
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

        //Rectangle testRect = new Rectangle(128, 64, 256, 512 - 128);
        //paintTreeStep(g, testRect);

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


        //paintTree(g, testRect, 8);


        //g.drawImage(img, 0, 0, null);
    }
}
