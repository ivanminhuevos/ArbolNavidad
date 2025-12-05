import java.awt.*;

public class ArbolEstrella extends Objeto {
    private static final Color COLOR_ESTRELLA = new Color(255, 244, 132);
    private static Polygon polyEstrella = new Polygon();

    private void computePoly() {
        int posX = this.getX();
        int posY = this.getY();

        int posW = this.getW();
        int posH = this.getH();

        polyEstrella.reset();
        for(int i = 0; i < 10; i++) {
            double iDelta = i / 10.0;

            double sinVal = Math.sin(iDelta * Math.PI * 2 + Math.PI) * posW;
            double cosVal = Math.cos(iDelta * Math.PI * 2 + Math.PI) * posH;

            if((i % 2) == 0) {
                sinVal *= 2;
                cosVal *= 2;
            }


            polyEstrella.addPoint((int) (posX + sinVal), (int) (posY + cosVal));
        }
    }


    public void draw(Graphics g) {
        g.setColor(COLOR_ESTRELLA);
        g.fillPolygon(polyEstrella);
    }


    public ArbolEstrella() {
    }


    @Override
    void onResize() {
        computePoly();
    }
}
