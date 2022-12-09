import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerwisAukcyjny implements Aukcja{

    public class PrzedmiotAukcji implements Aukcja.PrzedmiotAukcji{
        int identyfikator;
        String nazwaPrzedmiotu;
        int aktualnaOferta;
        int aktualnaCena;

        @Override
        public int identyfikator() {
            return identyfikator;
        }

        @Override
        public String nazwaPrzedmiotu() {
            return nazwaPrzedmiotu;
        }

        @Override
        public int aktualnaOferta() {
            return findAuctionOnId(identyfikator).aktualnaOferta;
        }

        @Override
        public int aktualnaCena() {
            return findAuctionOnId(identyfikator).aktualnaCena;
        }
    }

    public class User{
        String username;
        Powiadomienie contactPoint;
        public User(String user, Powiadomienie contact){
            username = user;
            contactPoint = contact;
        }

        public void update(Aukcja.PrzedmiotAukcji auction){
            contactPoint.przebitoTwojąOfertę(auction);
        }
    }

    public class lepszyPrzedmiotAukcji{
        Aukcja.PrzedmiotAukcji przedmiotAukcji;
        List<User> subs = new ArrayList<>();
        Map<User, Integer> offers = new HashMap<>();
        boolean finished;
        int aktualnaOferta;
        int aktualnaCena;
        User currentWinner;
        User latestBidder;


        public lepszyPrzedmiotAukcji(Aukcja.PrzedmiotAukcji slabszyPrzedmiotAukcji){
            przedmiotAukcji = slabszyPrzedmiotAukcji;
        }

        public void notifySubs(){
            /*
            Notifies all subs about the new offer
             */
            for (User sub : subs) {
                if ((aktualnaCena > offers.get(sub) || aktualnaOferta > offers.get(sub))){
                    Aukcja.PrzedmiotAukcji przedmiotAukcjiPowiadomienie = new PrzedmiotAukcji();
                    sub.update(przedmiotAukcji);
                }
            }
        }

        public void newOffer(User user, Integer offer){
            if (!finished) {
                offers.put(user, offer);
                if (offer > aktualnaCena) {
                    aktualnaCena = offer;
                    currentWinner = user;
                }

                aktualnaOferta = offer;
                latestBidder = user;
                notifySubs();
            }
        }

        public void subscribe(User user){
            subs.add(user);
        }

        public void unsubscribe(User user){
            subs.remove(user);
        }
    }
    List<lepszyPrzedmiotAukcji> allAuctions = new ArrayList<>();
    List<User> allUsers = new ArrayList<>();

    public User findUserOnUsername(String username){
        /*
        Searches for a user in all users list based on given username
         */
        for (User user : allUsers){
            if (user.username.equals(username)){
                return user;
            }
        }
        return null;
    }

    public lepszyPrzedmiotAukcji findAuctionOnId(int identyfikatorPrzedmiotuAukcji){
        /*
        Finds an auction in all auctions list based on given auction id
         */
        for (lepszyPrzedmiotAukcji auction : allAuctions){
            if (auction.przedmiotAukcji.identyfikator() == identyfikatorPrzedmiotuAukcji){
                return auction;
            }
        }
        return null;
    }


    @Override
    public void dodajUżytkownika(String username, Powiadomienie kontakt) {
        /*
        Adds user to the users list
         */
        allUsers.add(new User(username, kontakt));
    }

    @Override
    public void dodajPrzedmiotAukcji(Aukcja.PrzedmiotAukcji przedmiot) {
        /*
        Adds a new auction to the auctions list
         */
        lepszyPrzedmiotAukcji auction = new lepszyPrzedmiotAukcji(przedmiot);
        allAuctions.add(auction);
    }

    @Override
    public void subskrypcjaPowiadomień(String username, int identyfikatorPrzedmiotuAukcji) {
        /*
        User wants to get notified if his bid gets beaten
         */
        User user = findUserOnUsername(username);
        lepszyPrzedmiotAukcji auction = findAuctionOnId(identyfikatorPrzedmiotuAukcji);
        if (user != null && auction != null) {
            auction.subscribe(user);
        }
    }

    @Override
    public void rezygnacjaZPowiadomień(String username, int identyfikatorPrzedmiotuAukcji) {
        /*
        User doesn't want to be notified anymore
         */
        User user = findUserOnUsername(username);
        lepszyPrzedmiotAukcji auction = findAuctionOnId(identyfikatorPrzedmiotuAukcji);
        if (user != null && auction != null) {
            auction.unsubscribe(user);
        }
    }

    @Override
    public void oferta(String username, int identyfikatorPrzedmiotuAukcji, int oferowanaKwota) {
        /*
        Sending a new offer to certain auction by certain user
         */
        lepszyPrzedmiotAukcji auction = findAuctionOnId(identyfikatorPrzedmiotuAukcji);
        User user = findUserOnUsername(username);
        if (user != null && auction != null) {
            auction.newOffer(user, oferowanaKwota);
        }
    }

    @Override
    public void koniecAukcji(int identyfikatorPrzedmiotuAukcji) {
        /*
        Finishes an auction
         */
        lepszyPrzedmiotAukcji auction = findAuctionOnId(identyfikatorPrzedmiotuAukcji);
        auction.finished = true;
    }

    @Override
    public String ktoWygrywa(int identyfikatorPrzedmiotuAukcji) {
        /*
        Returns a current winner for certain auctiom
         */
        lepszyPrzedmiotAukcji auction = findAuctionOnId(identyfikatorPrzedmiotuAukcji);

        if (auction.currentWinner == null){
            return null;
        }
        return auction.currentWinner.username;
    }

    @Override
    public int najwyższaOferta(int identyfikatorPrzedmiotuAukcji) {
        lepszyPrzedmiotAukcji auction = findAuctionOnId(identyfikatorPrzedmiotuAukcji);
        return auction.aktualnaCena;
    }
}
