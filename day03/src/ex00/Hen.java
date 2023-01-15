package ex00;
public class Hen extends Thread {
    private final int countWords;

    public Hen(int countWords) {
        this.countWords = countWords;
    }

    @Override
    public void run() {
        for (int i = 0; i < countWords; i++) {
            System.out.println("Hen");
        }
    }
}