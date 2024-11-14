class Lecteur extends Thread {
    private BAL bal;

    public Lecteur(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String lettre = bal.retirer();
                if ("Q".equals(lettre)) {  // Arrêt lorsque la lettre est 'Q'
                    System.out.println("Consommateur : Arrêt reçu, fin du programme.");
                    break;
                }
                Thread.sleep(2000);  // Simule un délai pour le traitement de la lettre
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
