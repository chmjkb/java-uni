import java.util.Map;

public class FastHistogram implements Histogram{

    public class HistogramThread extends Thread{
        Vector inputVector;
        public HistogramThread(Vector inputVector){
            this.inputVector = inputVector;
        }
        @Override
        public void run(){
            for (int i = 0; i < inputVector.getSize(); i++){
                System.out.println(inputVector.getValue(i));
            }
        }
    }
    
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
