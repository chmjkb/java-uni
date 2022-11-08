import java.util.List;
import java.util.Map;
import java.util.Set;

public class Lines implements LinesInterface{

    @Override
    public void addPoints(Set<Point> points) {

    }

    @Override
    public void addSegments(Set<Segment> segments) {

    }

    @Override
    public List<Segment> findConnection(Point start, Point end) {
        return null;
    }

    @Override
    public Map<Point, Set<Segment>> getMapEndpointToSegments() {
        return null;
    }

    @Override
    public Map<Point, Map<Integer, Set<Point>>> getReachableEndpoints() {
        return null;
    }
}
