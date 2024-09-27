import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile sonMobile;
    private final int LARG=1200, HAUT=700;
    
    public UneFenetre() {
        super("le Mobile");
        Container leContaineur = getContentPane();
        sonMobile = new UnMobile(LARG, HAUT);
        leContaineur.add(sonMobile);
        Thread laTache = new Thread(sonMobile);
        laTache.start();
        setVisible(true);
    }
}
