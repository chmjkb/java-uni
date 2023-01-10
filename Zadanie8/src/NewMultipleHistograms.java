import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

class NewMultipleHistograms implements Histogram {
    private Consumer<HistogramResult> consumer;
    private ExecutorService histogramExecutor;
    private ExecutorService consumerExecutor;

    class HistogramRunnable implements Runnable {
        /*
        class that helps with calculating the histogram
         */
        private final int id;
        private final Vector vector;
        private final Map<Integer, Integer> histogram = new HashMap<>();
        private Consumer<HistogramResult> consumer;

        public HistogramRunnable(int id, Vector vector, Consumer<HistogramResult> cons) {
            this.id = id;
            this.vector = vector;
            this.consumer = cons;
        }

        @Override
        public void run() {
            // calculating the histogram
            for (int i = 0; i < vector.getSize(); i++) {
                histogram.compute(vector.getValue(i), (k, v) -> v == null ? 1 : v + 1);
            }
            // accepting with different thread
            consumerExecutor.execute(() -> this.consumer.accept(new HistogramResult(this.id, histogram)));
        }
    }

    @Override
    public void setup(int bins, Consumer<HistogramResult> histogramConsumer) {
        // basically a constructor
        this.consumer = histogramConsumer;
        this.histogramExecutor = Executors.newCachedThreadPool();
        this.consumerExecutor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void addVector(int vectorID, Vector vector) {
        // sending the HistogramRunnable task to the executor
        HistogramRunnable task = new HistogramRunnable(vectorID, vector, this.consumer);
        this.histogramExecutor.execute(task);
    }
}
