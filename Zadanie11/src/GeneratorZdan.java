/**
 * Interfejs systemu budujÄcego proste zdania ze sĹĂłw zapisanych w plikowej
 * bazie danych.
 *
 */
public interface GeneratorZdan {
    /**
     * Metoda przekazuje poĹoĹźenie pliku z bazÄ danych SQLite.
     *
     * @param filename plik z bazÄ danych
     */
    public void plikBazyDanych(String filename);

    /**
     * Metoda odtwarza zdanie na podstawie danych zapisanych w bazie danych.
     *
     * @param zdanieID klucz zdania
     * @return Zdanie wygenerowane na podstawie wpisĂłw w bazie.
     */
    public String zbudujZdanie(int zdanieID);
}