import java.util.List;
import java.util.Set;

/**
 * Intefejs dla programu wyznaczajÄcego poĹoĹźenie pionkĂłw.
 */
public interface MeetingInterface {
    /**
     * Metoda dodaje listÄ poĹoĹźeĹ, w ktĂłrych poczÄtkowo znajdujÄ siÄ pionki.
     * PoniewaĹź kolejnoĹÄ pionkĂłw decyduje o kolejnoĹci ruchu uĹźyta jest lista.
     *
     * @param positions poĹoĹźenia pionkĂłw
     */
    public void addPawns(List<PawnPosition> positions);

    /**
     * Metoda ustawia punkt zborny - do tego punktu starajÄ siÄ dotrzeÄ pionki
     *
     * @param meetingPointPosition poĹoĹźenie punktu zbornego
     */
    public void addMeetingPoint(Position meetingPointPosition);

    /**
     * Ruch pionkĂłw. Metoda koĹczy siÄ, gdy Ĺźaden z pionkĂłw nie moĹźe przysunÄÄ siÄ
     * bliĹźej punktu zbornego, bo blokowany jest przez inne pionki.
     */
    public void move();

    /**
     * Metoda zwraca koĹcowe poĹoĹźenia wszystkich pionkĂłw. PoĹoĹźenie pionka o danym
     * numerze identyfikacyjnym zazwyczaj ulegnie zmianie, jego numer musi jednak
     * pozostaÄ niezmieniony. KaĹźdy pionek musi znajdowaÄ siÄ na innej pozycji.
     *
     * @return koĹcowe poĹoĹźenia pionkĂłw
     */
    public Set<PawnPosition> getAllPawns();

    /**
     * Metoda zwraca poĹoĹźenia pionkĂłw, ktĂłre sÄsiadujÄ z pionkiem o podanym pawnId.
     *
     * @param pawnId numer pionka
     * @return sÄsiedzi pionka pawnId
     */
    public Set<PawnPosition> getNeighbours(int pawnId);
}