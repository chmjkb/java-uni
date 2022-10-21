public class Drawing implements SimpleDrawing {
    int size;

    static class GeometryImpl implements Geometry{
        int size;
        int firstCord;
        int secondCord;
        @Override
        public int getSize() {
            return this.size;
        }
        @Override
        public int getInitialFirstCoordinate() {
            return this.firstCord;
        }
        @Override
        public int getInitialSecondCoordinate() {
            return this.secondCord;
        }
    }

    class SegmentImpl implements Segment{
        private int direction;
        private int length;
        private int color;
        @Override
        public int getDirection() {
            return this.direction;
        }

        @Override
        public int getLength() {
            return this.length;
        }

        @Override
        public int getColor() {
            return this.color;
        }
    }

    // Drawing class implementation
    @Override
    public void setCanvasGeometry(Geometry input) {
        size = input.getSize();
    }

    @Override
    public void draw(Segment segment) {
        segment.getColor();
        segment.getDirection();
        segment.getLength();
    }

    @Override
    public int[][] getPainting() {
        return new int[0][];
    }

    @Override
    public void clear() { }

    public static void main(String[] args) {
        GeometryImpl newBoard = new GeometryImpl();

    }
}