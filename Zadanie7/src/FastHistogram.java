import java.util.Map;

public class FastHistogram implements Histogram{
    @Override
    public void setup(int threads, int bins) {

    }

    @Override
    public void setVector(Vector vector) {

    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public Map<Integer, Integer> histogram() {
        return null;
    }
}
