import java.util.List;

// https://www.baeldung.com/java-record-keyword

/**
 * PoĹoĹźenie w dwĂłwymiarowej przestrzeni z numerem pionka.
 */
public record PawnPosition2D(int pawnId, int x, int y) implements PawnPosition {

/**
 * MaĹy przykĹad dziaĹania rekordĂłw w Java
 *
 * @param args - lista argumentĂłw programu - nieuĹźywana
 */
public static void main(String[] args) {
        Position pos = new PawnPosition2D(1, 3, 3);
        System.out.println(pos);

        List<Position> positions = List.of(new PawnPosition2D(2, 1, 1), new PawnPosition2D(3, 3, 3));

        System.out.println(positions);
        }
        }