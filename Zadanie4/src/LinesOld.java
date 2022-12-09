//import javax.sound.sampled.Line;
//import javax.swing.text.StyledEditorKit;
//import java.util.*;
//
//class Lines implements LinesInterface{
//
//
//    Set<Point> allPoints = new HashSet<>();
//    Set<Segment> allSegments = new HashSet<>();
//
//    static class Point implements LinesInterface.Point{
//        String name;
//        @Override
//        public String getName() {
//            return name;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Point point = (Point) o;
//            return Objects.equals(name, point.name);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(name);
//        }
//    }
//
//    static class Segment implements LinesInterface.Segment{
//        Point point1;
//        Point point2;
//        @Override
//        public LinesInterface.Point getEndpoint1() {
//            return point1;
//        }
//
//        @Override
//        public LinesInterface.Point getEndpoint2() {
//            return point2;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Segment segment = (Segment) o;
//            return Objects.equals(point1, segment.point1) && Objects.equals(point2, segment.point2);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(point1, point2);
//        }
//    }
//
//    static class Graph {
//        public ArrayList<String> answers = new ArrayList<String>();
//        private Map<LinesInterface.Point, List<LinesInterface.Point>> adjVertices = new HashMap<>();
//
//        void addVertex(LinesInterface.Point point) {
//            adjVertices.putIfAbsent(point, new ArrayList<>());
//        }
//
//        void addEdge(Segment segment) {
//            LinesInterface.Point v1 = segment.getEndpoint1();
//            LinesInterface.Point v2 = segment.getEndpoint2();
//            adjVertices.get(v1).add(v2);
//            adjVertices.get(v2).add(v1);
//        }
//
//        List<LinesInterface.Point> getAdjVertices(String label) {
//            Point point = new Point();
//            point.name = label;
//            return adjVertices.get(point);
//        }
//
//        List<String> solve(Point root){
//            Queue<String> queue = new LinkedList<>();
//            String point_name = root.getName();
//            queue.add(point_name);
//
//        }
//        List<String> BFS(Point root, Point end){
//            prev = solve(root);
//        }
//    }
//
//
//    Set<String> breadthFirstTraversal(Graph graph, Point root) {
//        Set<String> visited = new LinkedHashSet<String>();
//        Queue<String> queue = new LinkedList<String>();
//        queue.add(root.getName());
//        visited.add(root.getName());
//        while (!queue.isEmpty()) {
//            String vertex = queue.poll();
//            for (LinesInterface.Point p : graph.getAdjVertices(vertex)) {
//                if (!visited.contains(p.getName())) {
//                    visited.add(p.getName());
//                    queue.add(p.getName());
//                }
//            }
//        }
//        return visited;
//    }
//    @Override
//    public void addPoints(Set<LinesInterface.Point> points) {
//
//    }
//
//    @Override
//    public void addSegments(Set<LinesInterface.Segment> segments) {
//
//    }
//
//    @Override
//    public List<LinesInterface.Segment> findConnection(LinesInterface.Point start, LinesInterface.Point end) {
//        return null;
//    }
//
//    @Override
//    public Map<LinesInterface.Point, Set<LinesInterface.Segment>> getMapEndpointToSegments() {
//        return null;
//    }
//
//    @Override
//    public Map<LinesInterface.Point, Map<Integer, Set<LinesInterface.Point>>> getReachableEndpoints() {
//        return null;
//    }
//
//    public static void main(String[] args) {
//        Lines lines = new Lines();
//        Point PointA = new Point();
//        PointA.name = "A";
//        Point pointB = new Point();
//        pointB.name = "B";
//        Point pointC = new Point();
//        pointC.name = "C";
//        Point pointD = new Point();
//        pointD.name = "D";
//
//        Segment segmentAB = new Segment();
//        segmentAB.point1 = PointA;
//        segmentAB.point2 = pointB;
//        Segment segmentCB = new Segment();
//        segmentCB.point1 = pointC;
//        segmentCB.point2 = pointB;
//        Segment segmentDC = new Segment();
//        segmentDC.point1 = pointD;
//        segmentDC.point2 = pointC;
//
//
//
//        Graph graph1 = new Graph();
//        graph1.addVertex(PointA);
//        graph1.addVertex(pointB);
//        graph1.addVertex(pointC);
//        graph1.addVertex(pointD);
//        graph1.addEdge(segmentAB);
//        graph1.addEdge(segmentCB);
//        graph1.addEdge(segmentDC);
//
//        graph1.getPath(PointA, pointD, PointA.getName(), PointA.getName() + " ");
//        System.out.println(graph1.answers);
//
////        Set<String> points = lines.breadthFirstTraversal(graph1, PointA);
////        for (String point : points){
////            System.out.print(point + ", ");
////        }
//
//
//    }
//
//
//}