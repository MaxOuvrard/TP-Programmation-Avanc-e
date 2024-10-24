import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable
{
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 10, sonTemps=50, sonCote=40;
    
    UnMobile(int telleLargeur, int telleHauteur)
    {
	super();
	saLargeur = telleLargeur;
	saHauteur = telleHauteur;
	setSize(telleLargeur, telleHauteur);
    }

    public void run() {
		while (true) { // Boucle infinie pour les déplacements continus
			// Déplacement de gauche à droite
			for (sonDebDessin = 0; sonDebDessin < saLargeur - sonCote; sonDebDessin += sonPas) {
				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}
	
			// Déplacement de droite à gauche
			for (sonDebDessin = saLargeur - sonCote; sonDebDessin > 0; sonDebDessin -= sonPas) {
				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}
		}
	}
	

    public void paintComponent(Graphics telCG)
    {
	super.paintComponent(telCG);
	telCG.fillRect(sonDebDessin, saHauteur/2, sonCote, sonCote);
    }
}