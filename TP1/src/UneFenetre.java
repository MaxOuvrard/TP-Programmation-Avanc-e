import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class UneFenetre extends JFrame implements ActionListener {
    UnMobile sonMobile;
    UnMobile sonMobile2;
    UnMobile sonMobile3;
    UnMobile sonMobile4;
    Thread laTache1;
    Thread laTache2;
    Thread laTache3;
    Thread laTache4;
    // JButton boutonStartStop;
    private final int LARG = 1200, HAUT = 700;
    private final int MOBILE_LARG = 1200, MOBILE_HAUT = 50;
    private boolean estEnCours = true;

    public UneFenetre() {
        super("le Mobile");
        Container leContaineur = getContentPane();
        leContaineur.setLayout(new GridLayout(4,1));

        sonMobile = new UnMobile(MOBILE_LARG, MOBILE_HAUT);
        leContaineur.add(sonMobile);

        sonMobile2 = new UnMobile(MOBILE_LARG, MOBILE_HAUT);
        leContaineur.add(sonMobile2);

        sonMobile3 = new UnMobile(MOBILE_LARG, MOBILE_HAUT);
        leContaineur.add(sonMobile3);

        sonMobile4 = new UnMobile(MOBILE_LARG, MOBILE_HAUT);
        leContaineur.add(sonMobile4);

        // Création du bouton
        // boutonStartStop = new JButton("Start/Stop");
        // boutonStartStop.addActionListener(this);
        // leContaineur.add(boutonStartStop, BorderLayout.SOUTH);

        // Démarrer le thread
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (estEnCours) {
            laTache1.suspend();  // Méthode dépréciée
        } else {
            laTache1.resume();  // Méthode dépréciée
        }
        estEnCours = !estEnCours;
    }
}
