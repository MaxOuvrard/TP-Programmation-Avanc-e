import java.awt.*;
import javax.swing.*;
import java.util.concurrent.Semaphore;

class UneFenetre extends JFrame {
    UnMobile sonMobile;
    UnMobile sonMobile2;
    UnMobile sonMobile3;
    UnMobile sonMobile4;
    Thread laTache1;
    Thread laTache2;
    Thread laTache3;
    Thread laTache4;

    private final int LARG = 1200, HAUT = 700;
    private final int MOBILE_LARG = 1200, MOBILE_HAUT = 50;

    public static final Semaphore zone2Semaphore = new Semaphore(1);

    public UneFenetre() {
        super("le Mobile");
        Container leContaineur = getContentPane();
        leContaineur.setLayout(new GridLayout(4, 1));

        sonMobile = new UnMobile(MOBILE_LARG, MOBILE_HAUT);
        sonMobile2 = new UnMobile(MOBILE_LARG, MOBILE_HAUT);
        sonMobile3 = new UnMobile(MOBILE_LARG, MOBILE_HAUT);
        sonMobile4 = new UnMobile(MOBILE_LARG, MOBILE_HAUT);

        leContaineur.add(sonMobile);
        leContaineur.add(sonMobile2);
        leContaineur.add(sonMobile3);
        leContaineur.add(sonMobile4);

        laTache1 = new Thread(sonMobile);
        laTache2 = new Thread(sonMobile2);
        laTache3 = new Thread(sonMobile3);
        laTache4 = new Thread(sonMobile4);

        laTache1.start();
        laTache2.start();
        laTache3.start();
        laTache4.start();

        setSize(LARG, HAUT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
