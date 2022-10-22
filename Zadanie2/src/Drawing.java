public class Drawing implements SimpleDrawing{
    public static class GeometryImpl implements Geometry{
        int size;
        int initialFirstCord;
        int initialSecondCord;
        @Override
        public int getSize() {
            /*
            Method has to return desired size of board. (user input)
             */
            return this.size;
        }

        @Override
        public int getInitialFirstCoordinate() {
            /*
            Method has to return desired first coordinate of board. (user input)
             */
            return initialFirstCord;
        }

        @Override
        public int getInitialSecondCoordinate() {
            /*
            Method has to return desired size of board. (user input)
             */
            return initialSecondCord;
        }
    }

    public static class SegmentImpl implements Segment{
        int direction;
        int color;
        int length;

        @Override
        public int getDirection() {
            /*
            Returns direction of a segment we want to draw
            1 -> Right
            2 -> Up
            -1 -> Left
            -2 -> Down
             */
            return this.direction;
        }

        @Override
        public int getLength() {
            /*
            Returns amount of pixels we want to color, that is number of elements of the
            array that need to be modified
             */
            return this.length;
        }

        @Override
        public int getColor() {
            /*
            Returns which color we want to color our segment, that is what number we'll put
            in our elements.
             */
            return this.color;
        }
    }
    int size; // size of the board is (size x size)
    int firstCord;  // x-axis
    int secondCord; // y-axis
    int[][] painting;

    @Override
    public void setCanvasGeometry(Geometry input) {
        /*
        Method responsible for allowing to pass Geometry object,
        Gives us ability to create (input.size x input.size) array and set initial coordinates.
         */
        this.size = input.getSize();
        this.firstCord = input.getInitialFirstCoordinate();
        this.secondCord = input.getInitialSecondCoordinate();
        this.painting = new int[input.getSize()][input.getSize()];
    }

    @Override
    public void draw(Segment segment) {
        switch (segment.getDirection()) {
            case 1 -> {
                int right = this.firstCord;
                for (int i = right; i < right + segment.getLength(); i++) {
                    try {
                        this.painting[i][secondCord] = segment.getColor();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                    this.firstCord++;
                }
                this.firstCord--;
            }
            case 2 -> {
                int up = this.secondCord;
                for (int i = up; i < up + segment.getLength(); i++) {
                    try {
                        this.painting[this.firstCord][i] = segment.getColor();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                    this.secondCord++;
                }
                this.secondCord--;
            }
            case -1 -> {
                int left = this.firstCord;
                for (int i = left; i > left - segment.getLength(); i--) {
                    try {
                        this.painting[i][this.secondCord] = segment.getColor();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                    this.firstCord--;
                }
                this.firstCord++;
            }
            case -2 -> {
                int down = this.secondCord;
                for (int i = down; i > down - segment.getLength(); i--) {
                    try {
                        this.painting[this.firstCord][i] = segment.getColor();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                    secondCord--;
                }
                this.secondCord++;
            }
            default -> {
                System.out.println("Wrong input!");
            }
        }
    }

    @Override
    public int[][] getPainting() {
        /*
        Method responsible for returning the current state of our painting.
        Note that if the Geometry is not initialized, the function is supposed to
        return null.
         */
        int[][] newPainting = new int[this.size][this.size];
        for (int i =0; i < this.size; i++){
            for (int j = 0; j < this.size; j++){
                newPainting[this.size-j-1][i] = this.painting[i][j];
            }
        }
        this.painting = newPainting;
        return this.painting;
    }

    @Override
    public void clear() {
        /*
        Method responsible for cleaning our canvas, meaning setting
        all of arrays elements to 0s
         */
        for (int i = 0; i < this.size; i++){
            for (int j = 0; j < this.size; j++){
                this.painting[i][j] = 0;
            }
        }
    }

    public void printPainting(){
        /*
        Method responsible for printing a 2D-Array (painting) in a nice way.
         */
        for (int i = 0; i < this.size; i++){
            for (int j = 0; j < this.size; j++){
                System.out.print("[" + this.painting[i][j] + "]");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        GeometryImpl geometry = new GeometryImpl();
        geometry.size = 5;
        geometry.initialFirstCord = 1;
        geometry.initialSecondCord = 2;

        Drawing board = new Drawing();
        board.setCanvasGeometry(geometry);


    }
}
