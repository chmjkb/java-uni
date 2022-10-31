import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Meeting implements MeetingInterface{
    int roundNumber = 1;
    Position2D currentMeetingPoint;
    List<PawnPosition> allPawns = new ArrayList<>();  // Helps with keeping track of pawnIds

    @Override
    public void addPawns(List<PawnPosition> positions) {
        /*
        Method responsible for adding pawns to the board
         */
        for (PawnPosition currentPawn : positions) {
            int id = currentPawn.pawnId();
            int x = currentPawn.x();
            int y = currentPawn.y();
            allPawns.add(new PawnPosition2D(id, x ,y));
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

    public int isRoundEven(int round){
        /*

         */
        if (round % 2 == 0){
            return 1;
        }
        return 0;
    }

    public int calculateDy(PawnPosition pawn, Position meetingPoint){
        return Math.abs(pawn.y() - meetingPoint.y());
    }

    public int calculateDx(PawnPosition pawn, Position meetingPoint){
        return Math.abs(pawn.x() - meetingPoint.x());
    }

    public void makeMove(PawnPosition pawn, String direction, int pawnIndex){
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

    }

    @Override
    public void move() {
        /*
        TODO
         */
        switch (isRoundEven(this.roundNumber)){
            case 1:
                for (int i = 0; i < this.allPawns.size(); i++){
                    PawnPosition currentPawn = this.allPawns.get(i);
                    int dx = calculateDx(currentPawn, this.currentMeetingPoint);
                    int dy = calculateDy(currentPawn, this.currentMeetingPoint);

                    if (dx > dy){ // Move on the X axis
                        if (currentPawn.x() > this.currentMeetingPoint.x()){
                            if (!isOccupied(currentPawn.x() - 1, currentPawn.y())){
                                makeMove(currentPawn, "left", i);
                            }
                        }
                        if (currentPawn.x() < this.currentMeetingPoint.x()){
                            if (!isOccupied(currentPawn.x() + 1, currentPawn.y())){
                                makeMove(currentPawn, "right", i);
                            }
                        }
                    }
                    if (dx <= dy){
                        if (currentPawn.y() > this.currentMeetingPoint.y()){
                            if (!isOccupied(currentPawn.x(), currentPawn.y() - 1)){
                                makeMove(currentPawn, "bottom", i);
                            }
                        }
                        if (currentPawn.y() <  this.currentMeetingPoint.y()){
                            if (!isOccupied(currentPawn.x(), currentPawn.y() + 1)){
                                makeMove(currentPawn, "top", i);
                            }
                        }
                    }
                }
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

    public boolean isOccupied(int x, int y){
        /*
        Checks whether a place in the board of certain coordinates is occupied by some pawn
         */
        for (PawnPosition currentPawn: allPawns){
            if (currentPawn.x() == x && currentPawn.y() == y){
                return true;
            }
        }
        return false;
    }

    public int findPawn(int pawnId){
        /*
        Finds a pawn of certain pawnId and return its index from AllPawns list
         */
        int i = 0;
        for (PawnPosition currentPawn : allPawns){
            if (currentPawn.pawnId() == pawnId){
                return i;
            }
            i += 1;
        }
        return i;
    }

    @Override
    public Set<PawnPosition> getNeighbours(int pawnId) {
        Set<PawnPosition> neighbours = new HashSet<>();
        int pawnIndex = findPawn(pawnId);
        PawnPosition pawnOfInterest = this.allPawns.get(pawnIndex);
        int pawnX = pawnOfInterest.x();
        int pawnY = pawnOfInterest.y();

        /*
        please don't look at the code below thx
         */
        if (getPawnOfCertainPos(pawnX -1, pawnY -1) != null){
            neighbours.add(getPawnOfCertainPos(pawnX -1, pawnY -1));
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
        if (getPawnOfCertainPos(pawnX - 1, pawnY - 1) != null){
            neighbours.add(getPawnOfCertainPos(pawnX - 1, pawnY - 1));
        }
        if (getPawnOfCertainPos(pawnX - 1, pawnY) != null){
            neighbours.add(getPawnOfCertainPos(pawnX - 1, pawnY));
        }
        if (getPawnOfCertainPos(pawnX - 1, pawnY + 1) != null){
            neighbours.add(getPawnOfCertainPos(pawnX - 1, pawnY + 1));
        }
        return neighbours;
    }

    public static void main(String[] args) {
        List<PawnPosition> newPawnsGame = new ArrayList<>();
        newPawnsGame.add(new PawnPosition2D(1, 0, 0));
        newPawnsGame.add(new PawnPosition2D(2, 1, 0));
        Meeting myMeeting = new Meeting();
        myMeeting.addPawns(newPawnsGame);

        myMeeting.makeMove(myMeeting.allPawns.get(1), "bottom", 1);
        System.out.println(myMeeting.getAllPawns());
    }
}
