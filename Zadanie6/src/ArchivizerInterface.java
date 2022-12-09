
/**
 * Interfejs narzÄdzia do archiwizacji danych
 */
public interface ArchivizerInterface {

    /**
     * Kompresja katalogu o nazwie dir do pliku filename.
     *
     * @param dir      katalog z danymi
     * @param filename plik wynikowy
     * @return rozmiar pliku wynikowego w bajtach
     */
    public int compress(String dir, String filename);

    /**
     * Dekompresja pliku filename do katalogu dir.
     *
     * @param filename plik z archiwum
     * @param dir      katalog, do ktĂłrego naleĹźy zdekompresowaÄ archiwum
     */
    public void decompress(String filename, String dir);
}