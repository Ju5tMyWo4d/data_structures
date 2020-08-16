public class Main {
    public static void main(String[] args) {
        final BlockingQueue<Integer> bq = new BlockingQueue<>(2);

        new Thread(() -> {
            bq.remove();
        }).start();

        new Thread(() -> {
            bq.add(1);
        }).start();

        new Thread(() -> {
            bq.add(2);
            bq.add(3);
        }).start();
    }
}
