import java.awt.*;

public class ArbolSegmento extends Objeto {
    private static final Color COLOR_HOJA_A = new Color(61, 151, 61);
    private static final Color COLOR_HOJA_B = new Color(67, 106, 67);
    private final boolean par;
    private final boolean initial;

    private final Polygon polySegmento = new Polygon();

    private int numOrnamentos;
    private Ornamento[] ornamentos;

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

    private boolean pointInPoly(int x, int y) {
        return polySegmento.contains(x, y);
    }

    private double dist2D(int xA, int yA, int xB, int yB) {
        return Math.sqrt((yB - yA) * (yB - yA) + (xB - xA) * (xB - xA));
    }

    private boolean canPlaceThere(int x, int y, int currOrnamentos, int attCounter) {
        if(!pointInPoly(x, y)) {
            return false;
        }

        if(attCounter > 51200) {
            return true;
        }

        // asegura una distancia minima a todos los otros ornamentos
        double dist;
        Ornamento other;
        for(int i = 0; i < currOrnamentos; i++) {
            other = ornamentos[i];

            dist = dist2D(x, y, other.getX(), other.getY());
            if(dist < 25.0) {
                return false;
            }
        }

        return true;
    }

    // debe ser llamada despues de recomputePoly()
    private void recomputeOrnamentos() {
        int posX = this.getX();
        int posY = this.getY();

        int posW = this.getW();
        int posH = this.getH();


        for(int i = 0; i < numOrnamentos; i++) {
            int cX, cY;

            int attCounter = 0;
            do {
                attCounter++;

                cX = posX + (int) (Math.random() * posW);
                cY = posY + (int) (Math.random() * posH);
            } while(!canPlaceThere(cX, cY, i, attCounter));

            Ornamento ornAdd = new Ornamento();
            ornAdd.setPosScale(cX, cY, 16, 16);

            ornamentos[i] = ornAdd;
        }
    }

    public void draw(Graphics g) {
        g.setColor(par ? COLOR_HOJA_A : COLOR_HOJA_B);
        g.fillPolygon(polySegmento);

        for(int i = 0; i < numOrnamentos; i++) {
            ornamentos[i].draw(g);
        }
    }

    public ArbolSegmento(boolean par, boolean initial, int numOrnamentos) {
        this.par = par;
        this.initial = initial;

        this.ornamentos = new Ornamento[numOrnamentos];
        this.numOrnamentos = numOrnamentos;
    }

    @Override
    void onResize() {
        recomputePoly();
        recomputeOrnamentos();
    }
}
