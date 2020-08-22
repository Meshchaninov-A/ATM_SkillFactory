package runner;


public class BankApplicationWebService extends Thread {

    public void run() {
        while (true) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Поток запущен");

        }
    }
}
