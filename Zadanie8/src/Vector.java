/**
 * Obiektowa reprezentacja wektora liczb.
 */
public interface Vector {
    /**
     * Zwraca wartoĹÄ zapisanÄ na pozycji idx. Metoda bezpieczna w uĹźyciu przez
     * wiele wÄtkĂłw jednoczeĹnie. Dozwolone wartoĹci idx od 0 do getSize()-1
     * wĹÄcznie.
     *
     * @param idx pozycja odczytu
     * @return wartoĹÄ zapisana na pozycji idx. Od 0 do bins-1 wĹÄczenie (patrz
     *         metoda setup intefejsu Histogram).
     */
    public int getValue(int idx);

    /**
     * Rozmiar wektora. Determinuje poprawne wartoĹci indeksĂłw (od 0 do getSize()-1
     * wĹÄcznie).
     *
     * @return rozmiar wektora.
     */
    public int getSize();
}