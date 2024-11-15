public class BAL {
    private String lettre = null;  // La lettre dans la BAL, initialement vide

    // Méthode pour déposer une lettre
    public synchronized void deposer(String lettre) throws InterruptedException {
        while (this.lettre != null) {  // Attente si la BAL contient déjà une lettre
            wait();
        }
        this.lettre = lettre;
        System.out.println("Producteur : Lettre '" + lettre + "' déposée.");
        notifyAll();  // Notifie le consommateur qu'une lettre est disponible
    }

    // Méthode pour retirer une lettre
    public synchronized String retirer() throws InterruptedException {
        while (this.lettre == null) {  // Attente si la BAL est vide
            wait();
        }
        String lettreRetiree = this.lettre;
        this.lettre = null;
        System.out.println("Consommateur : Lettre '" + lettreRetiree + "' retirée.");
        notifyAll();  // Notifie le producteur que la BAL est vide
        return lettreRetiree;
    }
}
