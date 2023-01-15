package ex03;

public class Threader implements Runnable {
    private final int countThread;

    public Threader(int countThread) {
        this.countThread = countThread;
    }

    @Override
    public void run() {
        String url;

        while ((url = MyFileReader.getUrl()) != null) {
            MyFileReader.retrieveFile(url, countThread);
        }
    }
}
