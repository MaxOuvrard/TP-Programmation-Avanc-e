public class BAL {
    private String lettre;
    private boolean disponible = false;

    public synchronized void depose(String lettre) throws InterruptedException {
        while (disponible) {
            wait();
        }
        this.lettre = lettre;
        disponible = true;
        notifyAll();
    }

    public synchronized String retire() throws InterruptedException {
        while (!disponible) {
            wait();
        }
        disponible = false;
        notifyAll();
        return lettre;
    }
}