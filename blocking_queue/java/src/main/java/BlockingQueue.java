public class BlockingQueue<T> {
    private final T[] data;
    private int startIndex = 0;
    private int endIndex = 0;

    public BlockingQueue(final int size) {
        data = (T[])new Object[size];
    }

    synchronized public void add(T element) {
        while(!isSpaceLeft()) {
            try {
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        data[endIndex] = element;
        endIndex = nextIndex(endIndex);
        notifyAll();
    }

    synchronized public T remove() {
        while(!hasNext()) {
            try {
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        T tmp = data[startIndex];
        data[startIndex] = null;
        startIndex = nextIndex(startIndex);
        notifyAll();
        return tmp;
    }

    private boolean hasNext() {
        return data[startIndex] != null;
    }

    private boolean isSpaceLeft() {
        return data[endIndex] == null;
    }

    private int nextIndex(final int index) {
        return (index + 1) % data.length;
    }
}
