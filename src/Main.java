import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static final Scanner scn = new Scanner(System.in);
    private static int nextIntRange(String msg, int min, int max) {
        int valOut;

        do {
            System.out.print(msg);
            valOut = scn.nextInt();
        } while(valOut < min || valOut > max);

        return valOut;
    }


    private static final HashMap<String, Boolean> PALABRAS_A_BOOLEAN = new HashMap<String, Boolean>();
    private static void initPalabrasBoolean() {
        PALABRAS_A_BOOLEAN.put("si", true);
        PALABRAS_A_BOOLEAN.put("s", true);
        PALABRAS_A_BOOLEAN.put("true", true);
        PALABRAS_A_BOOLEAN.put("verdadero", true);
        PALABRAS_A_BOOLEAN.put("porfa", true);
        PALABRAS_A_BOOLEAN.put("porfavor", true);
        PALABRAS_A_BOOLEAN.put("1", true);

        PALABRAS_A_BOOLEAN.put("no", false);
        PALABRAS_A_BOOLEAN.put("n", false);
        PALABRAS_A_BOOLEAN.put("false", false);
        PALABRAS_A_BOOLEAN.put("falso", false);
        PALABRAS_A_BOOLEAN.put("no quiero", false);
        PALABRAS_A_BOOLEAN.put("0", true);
    }

    private static boolean nextNiceBoolean() {
        String strRead = scn.next().toLowerCase();

        return PALABRAS_A_BOOLEAN.getOrDefault(strRead, false);
    }


    private static ArbolWindow readArgsAndBuildTree(JFrame window) {
        int segmentos = nextIntRange("Cuantos ramas? (4-16) >", 4, 16);
        int ornamentos = nextIntRange("Cuantos ornamentos? (0-64) >", 0, 64);

        System.out.print("Quieres estrella? >");
        boolean estrella = nextNiceBoolean();

        System.out.print("Quieres tronco? >");
        boolean tronco = nextNiceBoolean();

        ArbolWindow arb = new ArbolWindow();
        arb.setSize(800 - 8, 600 - 32 - 8);
        arb.setLocation(0, 0);
        arb.setOpaque(true);

        arb.init(segmentos, estrella, ornamentos, tronco);

        window.add(arb);

        return arb;
    }

    private static double curTime = 0.0;
    public static double getCurTime() {
        return curTime;
    }


    public static void main(String[] args) {
        initPalabrasBoolean();


        JFrame window = new JFrame();

        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();

        window.setSize(800, 600);
        window.setTitle("Arbol de navidad");
        window.setLocation(scrSize.width / 2 - (window.getWidth() / 2), scrSize.height / 2 - (window.getHeight() / 2));
        window.setResizable(false);


        ArbolWindow arb = readArgsAndBuildTree(window);

        Timer update = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                curTime += 20.0 / 1000.0;

                arb.repaint();
            }
        });

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                window.dispose();
                update.stop();
            }
        });


        window.setLayout(null);
        window.setVisible(true);

        update.setRepeats(true);
        update.start();
    }
}
