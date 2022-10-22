import java.util.Arrays;

public class Drawing implements SimpleDrawing {
    int size;
    int[][] painting;
    int fistCord;
    int secondCord;
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

    static class SegmentImpl implements Segment{
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
    public void setCanvasGeometry(Geometry input)
    {
        this.painting = new int[input.getSize()][input.getSize()];
    }

    @Override
    public void draw(Segment segment) {
        switch (segment.getDirection()){
            case 1:
                // 1 means right, so we need to increment the first coord, and leave the second as it is;
                int targetFirstCord = this.fistCord + segment.getLength();
                for (int currentIndex = this.fistCord; currentIndex < targetFirstCord; currentIndex++){
                    this.painting[currentIndex][secondCord] = segment.getColor();
                }

        }
    }

    @Override
    public int[][] getPainting() {
        return this.painting;
    }

    @Override
    public void clear() {
        Arrays.stream(this.painting).forEach(a -> Arrays.fill(a, 0));
    }

    public static void main(String[] args) {
        // Configuring board related variables
        GeometryImpl newBoard = new GeometryImpl();
        newBoard.size = 3;
        newBoard.firstCord = 0;
        newBoard.secondCord = 0;

        // Configuring segment object
        SegmentImpl newSegment = new SegmentImpl();
        newSegment.color = 1;
        newSegment.length = 3;
        newSegment.direction = 1;

        Drawing drawing = new Drawing();
        drawing.setCanvasGeometry(newBoard);
        drawing.draw(newSegment);
        for (int i = 0; i < newBoard.getSize(); i++){
            System.out.println("i row " + i);
            for (int j = 0; j < newBoard.getSize(); j++){
                System.out.println(drawing.painting[j][i]);
            }
        }

    }
}