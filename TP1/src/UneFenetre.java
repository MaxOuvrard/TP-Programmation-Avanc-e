import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class UneFenetre extends JFrame implements ActionListener {
    UnMobile sonMobile;
    Thread laTache;
    JButton boutonStartStop;
    private final int LARG = 1200, HAUT = 700;
    private boolean estEnCours = true;

    public UneFenetre() {
        super("le Mobile");
        Container leContaineur = getContentPane();
        sonMobile = new UnMobile(LARG, HAUT);
        leContaineur.add(sonMobile, BorderLayout.CENTER);

        // Création du bouton
        boutonStartStop = new JButton("Start/Stop");
        boutonStartStop.addActionListener(this);
        leContaineur.add(boutonStartStop, BorderLayout.SOUTH);

        // Démarrer le thread
        laTache = new Thread(sonMobile);
        laTache.start();

        setSize(LARG, HAUT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (estEnCours) {
            laTache.suspend();  // Méthode dépréciée
        } else {
            laTache.resume();  // Méthode dépréciée
        }
        estEnCours = !estEnCours;
    }
}
