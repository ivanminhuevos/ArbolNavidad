import java.awt.*;

public class Arbol extends Objeto {
    private static final Color COLOR_TRONCO = new Color(62, 38, 10);

    private ArbolSegmento[] segments;
    private int segmentCount;
    private int ornamentos = 0;
    private boolean segmentsInit = false;

    private ArbolEstrella estrella;

    private void initSegments() {
        int posX = this.getX();
        int posY = this.getY();

        int posW = this.getW();
        int posH = this.getH();


        int sum = 0;
        int acc = 1;
        double[] altoVals = new double[segmentCount];

        // calc cuanto de alto hacer
        for(int i = 0; i < segmentCount; i++) {
            altoVals[i] = acc;

            sum += acc;
            acc++;
        }

        for(int i = 0; i < segmentCount; i++) {
            altoVals[i] /= sum;
        }

        double yAccum = posY;
        double inviDelta;
        for(int i = 0; i < segmentCount; i++) {
            inviDelta = ((double) i + 1) / ((double) (segmentCount));
            // hazlo mas fino cuando mas alto esta
            inviDelta = Math.max(inviDelta * 1, 0);

            double invWSub = posW * (1 - inviDelta);

            double altoVal = altoVals[i];
            double HSub = posH * altoVal;

            ArbolSegmento seg = new ArbolSegmento((i % 2 == 0), i == 0, (int) Math.ceil(ornamentos * altoVal));
            seg.setPosScale((int) (posX + (invWSub * 0.5)), (int) yAccum, (int) (posW * inviDelta), (int) HSub + 1);

            segments[(segmentCount - 1) - i] = seg;

            yAccum += HSub;
        }


        segmentsInit = true;
    }

    @Override
    void onResize() {
        int posX = this.getX();
        int posY = this.getY();

        int posW = this.getW();
        int posH = this.getH();

        if(this.estrella != null) {
            estrella.setPosScale(posX + (posW / 2), posY - 12, 16, 16);
        }
        initSegments();
    }

    public void draw(Graphics g) {
        if(!segmentsInit) { // no hemos inicializado los segmentos 💔💔💔
            System.out.println("Inicializando segmentos en runtime, esto no deberia pasar!");
            initSegments();
        }

        int posX = this.getX();
        int posY = this.getY();

        int posW = this.getW();
        int posH = this.getH();

        int troncoW = 48;
        int troncoH = 64;

        // dibuja el tronco
        g.setColor(COLOR_TRONCO);
        g.fillRect( posX + (posW / 2) - (troncoW / 2), posH + troncoH, troncoW, troncoH);

        ArbolSegmento seg;
        for(int i = 0; i < segments.length; i++) {
            seg = segments[i];

            seg.draw(g);
        }

        //g.setColor(Color.RED);
        //g.drawRect(posX, posY, posW, posH);
        //g.fillRect(posX + (posW / 2), posY, 1, posH);

        if(this.estrella != null) {
            estrella.draw(g);
        }
    }

    public Arbol(int segmentCount) {
        this.segmentCount = segmentCount;
        this.segments = new ArbolSegmento[segmentCount];


        this.estrella = new ArbolEstrella();
    }

    public Arbol(int segmentCount, boolean doEstrella) {
        this.segmentCount = segmentCount;
        this.segments = new ArbolSegmento[segmentCount];


        if(doEstrella) {
            this.estrella = new ArbolEstrella();
        }
    }

    public Arbol(int segmentCount, boolean doEstrella, int ornamentos) {
        this.segmentCount = segmentCount;
        this.segments = new ArbolSegmento[segmentCount];

        this.ornamentos = ornamentos;

        if(doEstrella) {
            this.estrella = new ArbolEstrella();
        }
    }
}
