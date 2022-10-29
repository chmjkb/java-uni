import java.util.List;
import java.util.Set;

public class Meeting implements MeetingInterface{

    int boardSize = 10;
    int[][] board = new int[boardSize][boardSize];
    int[] currentMeetingPoint;  // X,Y
    @Override
    public void addPawns(List<PawnPosition> positions) {

    }

    @Override
    public void addMeetingPoint(Position meetingPointPosition) {
        currentMeetingPoint[0] = Position.x();
        currentMeetingPoint[1] = Position.y();
    }

    @Override
    public void move() {

    }

    @Override
    public Set<PawnPosition> getAllPawns() {
        return null;
    }

    public boolean topOccupied(int i, int j){
        if (board[i+1][j] == 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Set<PawnPosition> getNeighbours(int pawnId) {

        for (int i = 0; i < boardSize; i++){
            for (int j = 0; i < boardSize; j++){
                if (board[i][j] == pawnId){
                    if (topOccupied(i, j)){

                    }
                }
            }
        }
    }
}
