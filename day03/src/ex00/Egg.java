package ex00;
public class Egg extends Thread {
    private final int countWords;

    public Egg(int countWords) {
        this.countWords = countWords;
    }

    @Override
    public void run() {
        for (int i = 0; i < countWords; i++) {
            System.out.println("Egg");
        }
    }
}