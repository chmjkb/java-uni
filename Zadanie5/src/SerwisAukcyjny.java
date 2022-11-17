import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class SerwisAukcyjny implements Aukcja{
    Map<PrzedmiotAukcji, Map<String, Integer>> listaAukcji = new HashMap<>();

    @Override
    public void dodajUĹźytkownika(String username, Powiadomienie kontakt) {

    }

    @Override
    public void dodajPrzedmiotAukcji(PrzedmiotAukcji przedmiot) {
    }

    @Override
    public void subskrypcjaPowiadomieĹ(String username, int identyfikatorPrzedmiotuAukcji) {

    }

    @Override
    public void rezygnacjaZPowiadomieĹ(String username, int identyfikatorPrzedmiotuAukcji) {

    }

    @Override
    public void oferta(String username, int identyfikatorPrzedmiotuAukcji, int oferowanaKwota) {

    }

    @Override
    public void koniecAukcji(int identyfikatorPrzedmiotuAukcji) {

    }

    @Override
    public String ktoWygrywa(int identyfikatorPrzedmiotuAukcji) {
        return null;
    }

    @Override
    public int najwyĹźszaOferta(int identyfikatorPrzedmiotuAukcji) {
        return 0;
    }

    public static void main(String[] args) {
        Aukcja aukcja = new SerwisAukcyjny();

    }
}
