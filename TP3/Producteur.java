public class Producteur implements Runnable {
    private BAL bal;

    public Producteur(BAL bal) {
        this.bal = bal;
    }

    public void run() {
        try {
            int count = 0;
            while (true) {
                String lettre = "Lettre " + count++;
                bal.depose(lettre);
                System.out.println("[" + Thread.currentThread().getName() + "] a déposé : " + lettre);
                Thread.sleep(1000); 
            }
        } catch (InterruptedException e) {
            System.out.println("[" + Thread.currentThread().getName() + "] je m'arrête");
        }
    }
}