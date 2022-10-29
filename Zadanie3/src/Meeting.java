import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Meeting implements MeetingInterface{
    int roundNumber = 1;
    Position2D currentMeetingPoint;
    List<PawnPosition2D> allPawns = new ArrayList<>();  // Helps with keeping track of pawnIds

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

    @Override
    public void move() {
        /*
        TODO
         */
    }
    @Override
    public Set<PawnPosition> getAllPawns() {
        /*
        TODO
         */
        return null;
    }

    public String getPawnListIndex(int pawnId) {
        for (int i = 0; i < this.allPawns.size(); i++) {
            PawnPosition2D currentPawn = this.allPawns.get(i);
            if (currentPawn.pawnId() == pawnId) {
                return Integer.toString(i);
            }
        }
        return "No pawn with " + pawnId + "id.";
    }

    public boolean isOccupied(int x, int y){
        for (PawnPosition2D currentPawn: allPawns){
            if (currentPawn.x() == x && currentPawn.y() == y){
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<PawnPosition> getNeighbours(int pawnId) {
        Set<PawnPosition> neighbours = new HashSet<>();
        String pawnListIndex = getPawnListIndex(pawnId);
        PawnPosition2D pawnOfInterest = allPawns.get(Integer.parseInt(pawnListIndex));
        int pawn_x = pawnOfInterest.x();
        int pawn_y = pawnOfInterest.y();

        if (isOccupied(pawn_x + 1, pawn_y)){
            neighbours.add(new PawnPosition2D());
        }
    }

    public static void main(String[] args) {
        Meeting myMeeting = new Meeting();
    }
}
