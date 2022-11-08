import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LinesInterface {

    /**
     * Punkt
     */
    public interface Point {
        /**
         * Nazwa odcinka. RĂłĹźne punkty majÄ rĂłĹźne nazwy.
         *
         * @return nazwa odcinka
         */
        public String getName();
    }

    /**
     * Odcinek. Odcinek nie ma kierunku. RĂłwnie dobrze prowadzi od kraĹca 1 do 2 jak
     * i od 2 do 1.
     */
    public interface Segment {
        /**
         * Pierwszy kraniec odcinka
         *
         * @return punkt bÄdÄcy pierwszym kraĹcem odcinka
         */
        public Point getEndpoint1();

        /**
         * Drugi kraniec odcinka
         *
         * @return punkt bÄdÄcy drugim kraĹcem odcinka
         */
        public Point getEndpoint2();
    }

    /**
     * Metoda pozwala na dodanie zbioru punktĂłw.
     *
     * @param points zbiĂłr punktĂłw
     */
    public void addPoints(Set<Point> points);

    /**
     * Metoda pozwala na dodanie zbioru odcinkĂłw ĹÄczÄcych punkty.
     *
     * @param segments zbiĂłr odcinkĂłw
     */
    public void addSegments(Set<Segment> segments);

    /**
     * Metoda wyszukuje poĹÄczenie pomiÄdzy parÄ punktĂłw. KaĹźde poprawne poĹÄczenie
     * podanej pary i bez pÄtli uznane zostanie za poprawne. W przypadku braku
     * poĹÄczenia metoda zwraca listÄ o rozmiarze 0.
     *
     * @param start punkt poczÄtkowy
     * @param end   punkt koĹcowy
     * @return lista kolejnych odcinkĂłw prowadzÄcych od start do end.
     */
    public List<Segment> findConnection(Point start, Point end);

    /**
     * Metoda generuje mapÄ, ktĂłrej kluczem jest punkt. Punkt wskazuje na zbiĂłr
     * odcinkĂłw, ktĂłrych jednym z punktĂłw kraĹcowych jest ten punkt.
     *
     * @return mapa powiÄzaĹ punktĂłw z odcinkami
     */
    public Map<Point, Set<Segment>> getMapEndpointToSegments();

    /**
     * Metoda generuje mapÄ, ktĂłrej kluczem jest punkt. Punkt jest punktem startowym
     * ĹcieĹźek. We wskazywanej przez punkt mapie majÄ znaleĹşÄ siÄ punkty, ktĂłre
     * moĹźna osiÄgnÄÄ poprzez trasÄ zawierajÄcÄ 1, 2, 3 lub 4 odcinki. JeĹli trasa o
     * okreĹlonej dĹugoĹci nie istnieje zbiĂłr punktĂłw niÄ dostÄpny jest zbiorem
     * pustym (zbiĂłr o rozmiarze 0).
     *
     * @return mapa powiÄzaĹ punktĂłw z innymi punktami
     */
    public Map<Point, Map<Integer, Set<Point>>> getReachableEndpoints();

}