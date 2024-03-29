import java.util.*;

public class Meeting implements MeetingInterface{
    Position2D currentMeetingPoint;
    List<PawnPosition> allPawns = new ArrayList<>();
    @Override
    public void addPawns(List<PawnPosition> positions) {
        /*
        Method responsible for adding pawns to the board
         */
        for (PawnPosition currentPawn : positions) {
            int id = currentPawn.pawnId();
            int x = currentPawn.x();
            int y = currentPawn.y();
            if (isNotOccupied(x, y) && findPawn(id) == null) {
                allPawns.add(new PawnPosition2D(id, x, y));
            }
        }
    }

    @Override
    public void addMeetingPoint(Position meetingPointPosition) {
        /*
        Method responsible for updating the meeting point based on given Position object
         */
        int x = meetingPointPosition.x();
        int y = meetingPointPosition.y();
        currentMeetingPoint = new Position2D(x, y);
    }

    public int calculateDy(PawnPosition pawn, Position meetingPoint){
        return Math.abs(pawn.y() - meetingPoint.y());
    }

    public int calculateDx(PawnPosition pawn, Position meetingPoint){
        return Math.abs(pawn.x() - meetingPoint.x());
    }

    public void makeMove(PawnPosition pawn, String direction, int pawnIndex){
        System.out.println("Making move...");
        int pawnId = pawn.pawnId();
        int currentX = pawn.x();
        int currentY = pawn.y();

        if (direction.equals("left")){
            this.allPawns.set(pawnIndex, new PawnPosition2D(pawnId, currentX - 1, currentY));
        }
        if (direction.equals("right")) {
            this.allPawns.set(pawnIndex, new PawnPosition2D(pawnId, currentX + 1, currentY));
        }
        if (direction.equals("bottom")){
            this.allPawns.set(pawnIndex, new PawnPosition2D(pawnId, currentX, currentY - 1));
        }
        if (direction.equals("top")){
            this.allPawns.set(pawnIndex, new PawnPosition2D(pawnId, currentX, currentY + 1));
        }

    }

    public void fixBoard(int i){
        /*
        Logic behind the pawns movement
         */
        PawnPosition currentPawn = this.allPawns.get(i);
        int dx = calculateDx(currentPawn, this.currentMeetingPoint);
        int dy = calculateDy(currentPawn, this.currentMeetingPoint);

        if (dx > dy){ // Move on the X axis
            if (currentPawn.x() > this.currentMeetingPoint.x()){
                if (isNotOccupied(currentPawn.x() - 1, currentPawn.y())){
                    if (currentPawn.x() > 0) {
                        makeMove(currentPawn, "left", i);
                    }
                }
            }
            else if (currentPawn.x() < this.currentMeetingPoint.x()){
                if (isNotOccupied(currentPawn.x() + 1, currentPawn.y())){
                    makeMove(currentPawn, "right", i);
                }
            }
        }
        if (dx <= dy) {
            if (currentPawn.y() > this.currentMeetingPoint.y()){
                if (isNotOccupied(currentPawn.x(), currentPawn.y() - 1)){
                    if (currentPawn.y() > 0) {
                        makeMove(currentPawn, "bottom", i);
                    }
                }
            }
            else if (currentPawn.y() <  this.currentMeetingPoint.y()){
                if (isNotOccupied(currentPawn.x(), currentPawn.y() + 1)){
                    makeMove(currentPawn, "top", i);
                }
                else {
                    System.out.println(currentPawn.x() + " " + currentPawn.y() + " Occupied");
                }
            }
        }
    }

    @Override
    public void move() {
        /*
        Method responsible for moving the pawns across the board according to provided instructions
         */
        boolean active = true;
        while (active) {
            List<PawnPosition> temp = new ArrayList<>(this.allPawns);
            for (int i = 0; i <  this.allPawns.size(); i++){
                System.out.println("iteration no. " + i);
                System.out.println(this.allPawns.get(i));
                fixBoard(i);
            }
            if (temp.equals(this.allPawns)){
                active = false;
                System.out.println("Didn't make a move!");
            }
            Collections.reverse(this.allPawns);

            /*
            Code below is an alternative version of what I've written before. Not sure but it might be useful
            in case I would want to change the move method

            if (isRoundEven(this.roundNumber) == 1){
                List<PawnPosition> temp = new ArrayList<>(this.allPawns);
                for (int i = 0; i < this.allPawns.size() - 1; i++) {
                    System.out.println("Iteration no. " + i);
                    fixBoard(i);

                }
                if (this.allPawns.equals(temp)) {
                    active = false;
                }
                this.roundNumber++;
            }

            else if (isRoundEven(this.roundNumber) == 0){
                List<PawnPosition> temp_rev = new ArrayList<>(this.allPawns);
                for (int i = this.allPawns.size() - 1; i == 0; i--) {
                    System.out.println("Iteration no. " + i);
                    fixBoard(i);
                }
                if (this.allPawns.equals(temp_rev)) {
                    active = false;
                }
                this.roundNumber++;
            }
             */
        }
    }

    @Override
    public Set<PawnPosition> getAllPawns() {
        /*
        Returns all pawns represented as a set
         */
        return new HashSet<>(this.allPawns);
    }


    public PawnPosition getPawnOfCertainPos(int x, int y){
        /*
        Returns a pawn sitting at a given position on the board if one exists
         */
        for (PawnPosition currentPawn: this.allPawns){
            if (currentPawn.x() == x && currentPawn.y() == y){
                return currentPawn;
            }
        }
        return null;
    }

    public boolean isNotOccupied(int x, int y){
        /*
        Checks whether a place in the board of certain coordinates is occupied by some pawn
         */
        for (PawnPosition currentPawn: allPawns){
            if ((currentPawn.x() == x && currentPawn.y() == y)){
                return false;
            }
        }
        return true;
    }

    public Integer findPawn(int pawnId){
        /*
        Finds a pawn of certain pawnId and return its index from AllPawns list
         */
        for (int i = 0; i < this.allPawns.size(); i++) {
            PawnPosition currentPawn = this.allPawns.get(i);
            if (currentPawn.pawnId() == pawnId){
                return i;
            }
        }
        return null;
    }

    public void printPawns(){
        /*
        Prints pawns in a readable way, helps with testing
         */
        for (int i = 0; i < 30; i++){
            for (int j = 0; j < 30; j++){

                if (isNotOccupied(i, j)){
                    System.out.print("[  ]");
                    continue;
                } else if (i == this.currentMeetingPoint.x() && j == this.currentMeetingPoint.y()){
                    System.out.print("[MP]");
                    continue;
                }
                System.out.print("[" + i + j + "]");
            }
            System.out.print("\n");
        }
    }

    @Override
    public Set<PawnPosition> getNeighbours(int pawnId) {
        /*
        Checks 8 spots near the pawn with given pawnId and returns its neighbours
         */
        Set<PawnPosition> neighbours = new HashSet<>();
        int pawnIndex = findPawn(pawnId);
        PawnPosition pawnOfInterest = this.allPawns.get(pawnIndex);
        int pawnX = pawnOfInterest.x();
        int pawnY = pawnOfInterest.y();

        if (getPawnOfCertainPos(pawnX -1, pawnY -1) != null){
            neighbours.add(getPawnOfCertainPos(pawnX - 1, pawnY - 1));
        }
        if (getPawnOfCertainPos(pawnX + 1, pawnY) != null){
            neighbours.add(getPawnOfCertainPos(pawnX + 1, pawnY));
        }
        if (getPawnOfCertainPos(pawnX + 1, pawnY + 1) != null){
            neighbours.add(getPawnOfCertainPos(pawnX + 1, pawnY + 1));
        }
        if (getPawnOfCertainPos(pawnX, pawnY -1) != null){
            neighbours.add(getPawnOfCertainPos(pawnX, pawnY - 1));
        }
        if (getPawnOfCertainPos(pawnX, pawnY + 1) != null){
            neighbours.add(getPawnOfCertainPos(pawnX, pawnY + 1));
        }
        if (getPawnOfCertainPos(pawnX + 1, pawnY - 1) != null){
            neighbours.add(getPawnOfCertainPos(pawnX + 1, pawnY - 1));
        }
        if (getPawnOfCertainPos(pawnX - 1, pawnY) != null){
            neighbours.add(getPawnOfCertainPos(pawnX - 1, pawnY));
        }
        if (getPawnOfCertainPos(pawnX - 1, pawnY + 1) != null){
            neighbours.add(getPawnOfCertainPos(pawnX - 1, pawnY + 1));
        }
        return neighbours;
    }
}
