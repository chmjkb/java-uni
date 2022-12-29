
public interface NetConnection {
    /**
     * Metoda otwiera poĹÄczenie do serwera dostÄpnego protokoĹem TCP/IP pod adresem
     * host i numerem portu TCP port.
     *
     * @param host adres IP lub nazwa komputera
     * @param port numer portu, na ktĂłrym serwer oczekuje na poczÄczenie
     */
    public void connect(String host, int port);
}