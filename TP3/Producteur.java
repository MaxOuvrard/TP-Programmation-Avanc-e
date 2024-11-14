import java.util.Scanner;

public class Producteur extends Thread {
    private BAL bal;

    public Producteur(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("Entrez une lettre à déposer (ou 'Q' pour quitter) : ");
                String lettre = scanner.nextLine();
                bal.deposer(lettre);
                if ("Q".equals(lettre)) {  // Arrêt lorsque la lettre est 'Q'
                    break;
                }
                Thread.sleep(1000);  // Simule un délai entre chaque dépôt
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            scanner.close();
        }
    }
}