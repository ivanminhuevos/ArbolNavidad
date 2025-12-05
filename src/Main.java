import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class Main {
    private static final Scanner scn = new Scanner(System.in);
    private static void readArgs() {

    }



    public static void main(String[] args) {
        readArgs();

        Frame window = new Frame();

        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();

        window.setSize(800, 600);
        window.setTitle("Arbol de navidad");
        window.setLocation(scrSize.width / 2 - (window.getWidth() / 2), scrSize.height / 2 - (window.getHeight() / 2));
        window.setResizable(false);


        ArbolWindow arb = new ArbolWindow();
        arb.setSize(800 - 8, 600 - 32 - 8);
        arb.setLocation(8, 32);

        arb.init();

        window.add(arb);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                window.dispose();
            }
        });

        window.setLayout(null);
        window.setVisible(true);
    }
}
