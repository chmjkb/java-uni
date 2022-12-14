import java.util.Map;

/**
 * Interfejs systemu wyliczajÄcego histogram z
 * dostarczonych w wektorze danych 
 */
public interface Histogram {
    /**
     * Metoda ustala sposĂłb generowania histogramĂłw. Metoda uĹźywana jednokrotnie
     * przed pierwszym wykonaniem setVector.
     *
     * @param threads liczba wÄtkĂłw, ktĂłre naleĹźy uĹźyÄ do wygenerowania jednego histogramu.
     *                DokĹadnie tyle, ani mniej, ani wiÄcej.
     * @param bins    maksymalna liczba koszykĂłw, do ktĂłrych wpadajÄ liczby odczytane z
     *                wektora. NajwiÄksza wartoĹÄ odczytana z wektora bÄdzie rĂłwna
     *                co najwyĹźej bins-1. UWAGA: znajomoĹÄ bins nie jest konieczna do policzenia
     *                histogramu, ale moĹźe uĹatwiÄ to zadanie.
     */
    public void setup(int threads, int bins);

    /**
     * Metoda przekazuje wektor liczb caĹkowitych do przetworzenia na histogram.
     * Metoda nie moĹźe zablokowaÄ pracy wÄtku, ktĂłry jÄ wywoĹa. Ma spowodowaÄ
     * asynchroniczne uruchomienie obliczeĹ i siÄ zakoĹczyÄ.
     *
     * @param vector wektor liczb
     */
    public void setVector(Vector vector);

    /**
     * Pozwala na sprawdzenie, czy histogram jest gotowy do odbioru. Przyjmuje
     * wartoĹÄ false od chwili przekazania wektora z danymi do chwili przygotowania
     * histogramu do odbioru. Przed wykonaniem setVector wartoĹÄ nieustalona.
     *
     * @return true - histogram gotowy, false - histogram w trakcie przetwarzania.
     */
    public boolean isReady();

    /**
     * Zwraca gotowy histogram. Przed wystÄpieniem isReady==true, wartoĹÄ
     * nieustalona. Histogram to mapa wartoĹÄ na liczba powtĂłrzeĹ tej wartoĹci w
     * wektorze. UWAGA: mapa nie moĹźe zawieraÄ kluczy, ktĂłrych liczba
     * wystÄpieĹ wynosi 0.
     *
     * @return histogram danych zapisanych w przekazanym wektorze.
     */
    public Map<Integer, Integer> histogram();
}