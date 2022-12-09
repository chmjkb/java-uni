import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerwisAukcyjny implements Aukcja{

    class User{
        String username;
        Powiadomienie contactPoint;
        public User(String user, Powiadomienie contact){
            username = user;
            contactPoint = contact;
        }
        public void notifySub(Aukcja.PrzedmiotAukcji p){
            contactPoint.przebitoTwojÄOfertÄ(p);
        }
    }

    class Offer{
        User user;
        int value;
        public Offer(User u, int o){
            user = u;
            value = o;
        }


    }

    class PrzedmiotAukcji implements Aukcja.PrzedmiotAukcji{
        int id;
        String name;
        int currentOffer;
        int currentPrice;

        public PrzedmiotAukcji(int i, String n){
            id = id;
            name = n;
        }
        @Override
        public int identyfikator() {
            return id;
        }

        @Override
        public String nazwaPrzedmiotu() {
            return name;
        }

        @Override
        public int aktualnaOferta() {
            return findAuctionOnId(identyfikator()).latestBid;
        }

        @Override
        public int aktualnaCena() {
            return findAuctionOnId(identyfikator()).highestBid;
        }
    }

    class LepszyPrzedmiotAukcji{
        Aukcja.PrzedmiotAukcji przedmiotAukcji;
        List<Offer> offers = new ArrayList<>();
        List<User> subs = new ArrayList<>();
        int highestBid;
        int latestBid;
        User currentWinner;

        public LepszyPrzedmiotAukcji(Aukcja.PrzedmiotAukcji p){
            przedmiotAukcji = p;
        }

        public void notifySubs(){
            for (Offer offer : offers){
                if (subs.contains(offer.user)){ // if the user has made an offer and is subscribed
                    if (offer.value < highestBid || offer.value < latestBid){
                        offer.user.notifySub(new PrzedmiotAukcji(przedmiotAukcji.identyfikator(),
                                przedmiotAukcji.nazwaPrzedmiotu()));
                    }
                }
            }
        }

        public boolean checkIfUserBid(User u){
            for (Offer offer : offers){
                if (offer.user.username.equals(u.username)){
                    return true;
                }
            }
            return false;
        }


        public void newOffer(User user, int offer){
            if (offer > highestBid){
                highestBid = offer;
                currentWinner = user;
            }
            for (int i = 0; i < offers.size(); i++){
                if (offers.get(i).user.username.equals(user.username)){
                    offers.set(i, new Offer(user, offer));
                    break;
                }
            }
            latestBid = offer;

            notifySubs();
        }

        public void subscribe(User sub){
            subs.add(sub);
        }

        public void unsubscribe(User sub){
            subs.remove(sub);
        }


    }

    public LepszyPrzedmiotAukcji findAuctionOnId(int id){
        for (LepszyPrzedmiotAukcji auction : allAuctions){
            if (auction.przedmiotAukcji.identyfikator() == id){
                return auction;
            }
        }
        return null;
    }

    List<LepszyPrzedmiotAukcji> allAuctions = new ArrayList<>();




    @Override
    public void dodajUĹźytkownika(String username, Powiadomienie kontakt) {

    }

    @Override
    public void dodajPrzedmiotAukcji(Aukcja.PrzedmiotAukcji przedmiot) {

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
}
