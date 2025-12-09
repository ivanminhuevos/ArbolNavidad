import java.awt.*;

public class ArbolSegmento extends Objeto {
    private static final Color COLOR_HOJA_A = new Color(61, 151, 61);
    private static final Color COLOR_HOJA_B = new Color(67, 106, 67);
    private final boolean par;
    private final boolean initial;

    private final Polygon polySegmento = new Polygon();

    private void recomputePoly() {
        int posX = this.getX();
        int posY = this.getY();

        int posW = this.getW();
        int posH = this.getH();

        polySegmento.reset();

        if(initial) {
            polySegmento.addPoint(posX + (posW / 2), posY);
        } else {
            polySegmento.addPoint(posX + (posW / 2) + (posW / 6), posY);
            polySegmento.addPoint(posX + (posW / 2) - (posW / 6), posY);
        }

        for(int i = 0; i < 16; i++) {
            double iDelta = i / 15.0;

            double sinVal = (Math.sin(iDelta * Math.PI - Math.PI / 2) + 1) / 2 * posW;
            double cosVal = (Math.cos(iDelta * Math.PI - Math.PI / 2) + 1) / 2 * posH;

            polySegmento.addPoint((int) (posX + sinVal), (int) (posY + cosVal + 4));
        }
    }


    public void draw(Graphics g) {
        g.setColor(par ? COLOR_HOJA_A : COLOR_HOJA_B);
        g.fillPolygon(polySegmento);
    }

    public ArbolSegmento(boolean par, boolean initial) {
        this.par = par;
        this.initial = initial;
    }

    @Override
    void onResize() {
        recomputePoly();
    }
}
