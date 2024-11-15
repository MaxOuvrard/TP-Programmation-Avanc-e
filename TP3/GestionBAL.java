public class GestionBAL {
    public static void main(String[] args) {
        // Initialisation de la boîte aux lettres partagée
        BAL bal = new BAL();

        // Création des threads pour le producteur et le consommateur
        Producteur producteur = new Producteur(bal);
        Lecteur consommateur = new Lecteur(bal);

        // Lancement des threads
        producteur.start();
        consommateur.start();

        // Attente que les threads se terminent
        try {
            producteur.join();
            consommateur.join();
        } catch (InterruptedException e) {
            System.out.println("Le thread principal a été interrompu.");
        }

        System.out.println("Programme terminé.");
    }
}
