import java.util.*;
import java.util.Vector;

public class FastHistogram implements Histogram{

    // https://www.youtube.com/watch?v=HofOMf8RgjM

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

    List<Thread> currentThreads = new ArrayList<>();
    List<Integer> currentVector = new ArrayList<>();
    Queue<Vector> vectorQueue = new ArrayDeque<>();

    @Override
    public void setup(int threads, int bins) {

    }

//    @Override
//    public void setVector(Vector vector) {
//        /*
//        If all threads are done, we initiate a new vector for processing
//         */
//        if (isReady()){
//            currentVector.clear();
//            for (int i = 0; i < vector.getSize(); i++){
//                currentVector.add(vector.getValue(i));
//            }
//        }
//    }

    @Override
    public boolean isReady() {
        return !currentThreads.stream().allMatch(Thread::isAlive);
    }

    @Override
    public Map<Integer, Integer> histogram() {
        return null;
    }


}
