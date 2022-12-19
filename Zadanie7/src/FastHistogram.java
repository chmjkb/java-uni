import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FastHistogram implements Histogram {
    private int threads;
    private int bins;
    private Vector vector;
    private Map<Integer, Integer> histogram = new ConcurrentHashMap<>();
    private ExecutorService executor;
    private boolean ready;



    @Override
    public void setup(int threads, int bins) {
        this.bins = bins;
        this.threads = threads;
    }

    @Override
    public void setVector(Vector vector) {
        ready = false;
        this.vector = vector;
        this.executor = Executors.newFixedThreadPool(threads);
        histogram.clear();
        int chunkSize = vector.getSize() / threads;
        for (int i = 0; i < threads; i++) {
            int start = i * chunkSize;
            int end = (i == threads - 1) ? vector.getSize() : (i + 1) * chunkSize;
            executor.execute(new HistogramRunnable(vector, start, end, histogram));
        }

        ExecutorService readyExecutor = Executors.newSingleThreadExecutor();
        readyExecutor.execute(() -> {
            try {
                executor.awaitTermination(1, TimeUnit.HOURS);
                ready = true;
            } catch (InterruptedException e) {
                // handle exception
            }
        });
        readyExecutor.shutdown();
        executor.shutdown();
    }

    @Override
    public boolean isReady() {
        return ready;
    }

    @Override
    public Map<Integer, Integer> histogram() {
        return histogram;
    }
}

class HistogramRunnable implements Runnable {
    private final Vector vector;
    private final int start;
    private final int end;
    private final Map<Integer, Integer> histogram;

    public HistogramRunnable(Vector vector, int start, int end, Map<Integer, Integer> histogram) {
        this.vector = vector;
        this.start = start;
        this.end = end;
        this.histogram = histogram;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            int value = vector.getValue(i);
            histogram.compute(value, (k, v) -> v == null ? 1 : v + 1);
        }
    }
}
