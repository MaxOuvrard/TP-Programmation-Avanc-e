import java.awt.*;
import javax.swing.*;
import java.util.concurrent.Semaphore;

class UnMobile extends JPanel implements Runnable {
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 10, sonTemps = 50, sonCote = 40;

    private boolean inZone2 = false;

    UnMobile(int telleLargeur, int telleHauteur) {
        super();
        saLargeur = telleLargeur;
        saHauteur = telleHauteur;
        setSize(telleLargeur, telleHauteur);
    }

    public void run() {
        while (true) {
            for (sonDebDessin = 0; sonDebDessin < saLargeur - sonCote; sonDebDessin += sonPas) {
                checkZoneTransition();
                repaint();
                sleep();
            }
            for (sonDebDessin = saLargeur - sonCote; sonDebDessin > 0; sonDebDessin -= sonPas) {
                checkZoneTransition();
                repaint();
                sleep();
            }
        }
    }

    private void checkZoneTransition() {
        int zone1End = saLargeur / 3;
        int zone2End = 2 * saLargeur / 3;

        if (sonDebDessin >= zone1End && sonDebDessin < zone2End) {
            if (!inZone2) {
                try {
                    UneFenetre.zone2Semaphore.acquire();
                    inZone2 = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (inZone2) {
                UneFenetre.zone2Semaphore.release();
                inZone2 = false;
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(sonTemps);
        } catch (InterruptedException telleExcp) {
            telleExcp.printStackTrace();
        }
    }

    public void paintComponent(Graphics telCG) {
        super.paintComponent(telCG);
        telCG.fillRect(sonDebDessin, saHauteur / 2, sonCote, sonCote);
    }
}
