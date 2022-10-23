
public class Drawing implements SimpleDrawing{
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
        if (this.size > 0 ) {
            this.painting = new int[input.getSize()][input.getSize()];
        }
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
                System.out.println("Wrong direction!");
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

        /* Ten fragment kodu odwraca tablice o 90 stopni, przeciwnie do ruchu wskazowek zegara,
        zeby bylo zgodne z tym co
        * jest na obrazku, wydaje mi sie ze to nie jest konieczne wiec komentuje.*/
//        int[][] newPainting = new int[this.size][this.size];
//        for (int i =0; i < this.size; i++){
//            for (int j = 0; j < this.size; j++){
//                newPainting[this.size-j-1][i] = this.painting[i][j];
//            }
//        }
//        this.painting = newPainting;
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
}