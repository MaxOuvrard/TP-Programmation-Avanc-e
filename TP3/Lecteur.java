public class Lecteur implements Runnable {
    private BAL bal;

    public Lecteur(BAL bal) {
        this.bal = bal;
    }

    public void run() {
        try {
            while (true) {
                String lettre = bal.retire();
                System.out.println("[" + Thread.currentThread().getName() + "] a retiré : " + lettre);
                Thread.sleep(2000); // Attendre 2 secondes avant de consommer une nouvelle lettre
            }
        } catch (InterruptedException e) {
            System.out.println("[" + Thread.currentThread().getName() + "] je m'arrête");
        }
    }
}