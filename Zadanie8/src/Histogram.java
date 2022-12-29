import java.util.Map;
import java.util.function.Consumer;

/**
 * Interfejs systemu wyliczajÄcego histogram z dostarczonych w wektorze danych
 */
public interface Histogram {

    public record HistogramResult(int vectorID, Map<Integer, Integer> histogram) {
    }

    /**
     * Metoda ustala sposĂłb generowania histogramĂłw. Metoda uĹźywana jednokrotnie
     * przed pierwszym wykonaniem setVector.
     *
     * @param bins              maksymalna liczba koszykĂłw, do ktĂłrych wpadajÄ
     *                          liczby odczytane z wektora. NajwiÄksza wartoĹÄ
     *                          odczytana z wektora bÄdzie rĂłwna co najwyĹźej bins-1.
     *                          UWAGA: znajomoĹÄ bins nie jest konieczna do
     *                          policzenia histogramu, ale moĹźe uĹatwiÄ to zadanie.
     * @param histogramConsumer Obiekt, do ktĂłrego naleĹźy przekazywaÄ wyliczone
     *                          histogramy. UWAGA: metodÄ accept nie moĹźe wykonaÄ
     *                          ten sam wÄtek, ktĂłry generowaĹ histogram!!!
     */
    public void setup(int bins, Consumer<HistogramResult> histogramConsumer);

    /**
     * Metoda przekazuje wektor liczb caĹkowitych do przetworzenia na histogram.
     * Metoda nie moĹźe zablokowaÄ pracy wÄtku, ktĂłry jÄ wywoĹa. Ma spowodowaÄ
     * asynchroniczne uruchomienie obliczeĹ i siÄ zakoĹczyÄ. Wraz z wektorem
     * przekazywany jest unikalny numer vectorID - pozwala on na dopasowanie
     * histogramu do wektora z danymi wejĹciowymi.
     *
     * @param vectorID unikalny identyfikator wektora
     * @param vector   wektor liczb
     */
    public void addVector(int vectorID, Vector vector);
}