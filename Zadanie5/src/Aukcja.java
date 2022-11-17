/**
 * Interfejs systemu akukcyjnego
 *
 */
public interface Aukcja {

    /**
     * Interfejs reprezentujÄcy przedmiot aukcji.
     */
    public interface PrzedmiotAukcji {
        /**
         * Unikalny, liczbowy identyfikator przedmiotu. KaĹźdy przedmiot aukcji bÄdzie
         * posiadaÄ inny identyfikator.
         *
         * @return identyfikator liczbowy
         */
        public int identyfikator();

        /**
         * Nazwa przedmiotu aukcji
         *
         * @return nazwa przedmiotu
         */
        public String nazwaPrzedmiotu();

        /**
         * Nowa oferta za przedmiot. Dla proszczenia, aby uniknÄÄ uĹamkĂłw, oferta
         * podawana jest w groszach. W przypadku uĹźycia w powiadomieniach metoda
         * przekazuje ofertÄ, ktĂłra przebiĹa kwotÄ zaoferowanÄ przez uĹźytkownika serwisu
         * aukcyjnego. Niekoniecznie aktualna oferta bÄdzie wiÄksza od aktualnej ceny.
         *
         * @return aktualna oferta w groszach
         */
        public int aktualnaOferta();

        /**
         * Aktualna cena za przedmiot. Dla proszczenia, aby uniknÄÄ uĹamkĂłw, cena
         * podawana jest w groszach. W momencie dodawania przedmiotu do aukcji pozwala
         * na przekazanie ceny minimalnej. W przypadku uĹźycia w powiadomieniach metoda
         * przekazuje ofertÄ, ktĂłra aktualnie jest najwyĹźsza.
         *
         * @return aktualna cena w groszach
         */
        public int aktualnaCena();

    }

    /**
     * Powiadomienie uĹźytkownika o przebiciu jego oferty.
     */
    public interface Powiadomienie {
        /**
         * Przekazanie informacji o przebiciu oferty na obserwowany przedmiot aukcji.
         *
         * @param przedmiot obiekt reprezentujÄcy przedmiot aukcji z ustawionÄ aktualnÄ
         *                  ofertÄ.
         */
        public void przebitoTwojÄOfertÄ(PrzedmiotAukcji przedmiot);
    }

    /**
     * Metoda do dodawania uĹźytkowika do systemu aukcyjnego. UĹźytkownicy rozrĂłĹźniani
     * sÄ za pomocÄ ich unikalnego username.
     *
     * @param username unikalne nazwa uĹźytkownika
     * @param kontakt  obiekt za pomocÄ naleĹźy powiadamiaÄ tego uĹźytkownika, gdy
     *                 ktoĹ inny przebije ofertÄ na przedmiot, ktĂłrym uĹźytkownik
     *                 jest zainteresowany.
     */
    public void dodajUĹźytkownika(String username, Powiadomienie kontakt);

    /**
     * Metoda pozwala na dodanie przedmiotu do serwisu aukcyjnego.
     *
     * @param przedmiot dodawany do serwisu przedmiot
     */
    public void dodajPrzedmiotAukcji(PrzedmiotAukcji przedmiot);

    /**
     * UĹźytkownik o podanym username zgĹasza zainteresowanie przedmiotem aktucji o
     * podanym identyfikatorze. Od chwili wykonania tej metody uĹźytkownik jest
     * powiadamiany kaĹźdorazowo, gdy jego oferta zostanie przebita.
     *
     * @param username                      nazwa uĹźytkownika serwisu
     * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
     */
    public void subskrypcjaPowiadomieĹ(String username, int identyfikatorPrzedmiotuAukcji);

    /**
     * Metoda koĹczy obserwacjÄ danego przedmiotu przez uĹźytkownika o podanym
     * username. Rezygnacja z powiadomieĹ oznacza zaprzestanie wysyĹania
     * powiadomieĹ.
     *
     * @param username                      nazwa uĹźytkownika serwisu
     * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
     */
    public void rezygnacjaZPowiadomieĹ(String username, int identyfikatorPrzedmiotuAukcji);

    /**
     * UĹźytkownik przekazuje ofertÄ zakupu przedmiotu za podanÄ kwotÄ. Wszystkie
     * osoby obserwujÄce ten sam przedmiot, a oferujÄce niĹźszÄ kwotÄ powinny zostaÄ
     * automatycznie powiadomione o przebiciu ich oferty.
     *
     * @param username                      nazwa uĹźytkownika serwisu
     * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
     * @param oferowanaKwota                zaoferowana kwota w groszach
     */
    public void oferta(String username, int identyfikatorPrzedmiotuAukcji, int oferowanaKwota);

    /**
     * Za pomocÄ tej metody zamykana jest aukcja. Najlepsza oferta wygrywa. Nowe
     * oferty sÄ ignorowane.
     *
     * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
     */
    public void koniecAukcji(int identyfikatorPrzedmiotuAukcji);

    /**
     * Metoda pozwala poznaÄ nazwÄ uĹźytkownika, ktĂłry zaoferowaĹ nawyĹźszÄ kwotÄ za
     * przedmiot aukcji. JeĹli aukcja zostaĹa zakoĹczona, metoda pozwala poznaÄ dane
     * osoby, ktĂłra aukcjÄ na dany przedmiot wygraĹa.
     *
     * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
     * @return nazwa uĹźytkownika wygrywajÄcego aukcjÄ
     */
    public String ktoWygrywa(int identyfikatorPrzedmiotuAukcji);

    /**
     * Metoda pozwala poznaÄ najlepszÄ ofertÄ za dany przedmiot. Kwota podawna jest
     * w groszach.
     *
     * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
     * @return najwyĹźsza ofera za przedmiot
     */
    public int najwyĹźszaOferta(int identyfikatorPrzedmiotuAukcji);

}