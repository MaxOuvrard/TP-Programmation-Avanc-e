public class Main {
    public static void main(String[] args) {
        BAL bal = new BAL();

        Producteur producteur = new Producteur(bal);
        Lecteur lecteur = new Lecteur(bal);

        Thread threadProducteur = new Thread(producteur);
        Thread threadLecteur = new Thread(lecteur);

        threadProducteur.start();
        threadLecteur.start();
    }
}