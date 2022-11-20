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

        public void update(PrzedmiotAukcji auction){
            contactPoint.przebitoTwojÄOfertÄ(auction);
        }
    }

    class PrzedmiotAukcji implements Aukcja.PrzedmiotAukcji{
        int identyfikator;
        int cenaPoczatkowa;
        String nazwaPrzedmiotu;
        int aktualnaOferta;
        boolean finished = false;
        int aktualnaCena;
        User currentWinner;
        User latestBidder;
        ArrayList<User> subs = new ArrayList<>();
        Map<User, Integer> offers = new HashMap<>();

        public boolean isThereAnyOffer(){
            return !offers.isEmpty();
        }

        public void notifySubs(){
            /*
            Notifies all subs about the new offer
             */
            for (User sub : subs) {
                if (offers.get(sub) != null && (offers.get(sub) < aktualnaCena &&
                        offers.get(sub) < aktualnaOferta) && sub != latestBidder ) {
                    sub.update(this);
                }
            }
        }

        public void newOffer(User user, Integer offer){
            /*
            Adds a new offer
             */
            if (!finished) {
                if (!isThereAnyOffer()) {
                    cenaPoczatkowa = aktualnaCena;
                }

                if (offer > aktualnaCena) {
                    aktualnaCena = offer;
                    currentWinner = user;
                }
                if (cenaPoczatkowa <= offer) {
                    latestBidder = user;
                    aktualnaOferta = offer;
                    offers.put(user, offer);
                }
                notifySubs();
            }

        }

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
            return aktualnaOferta;
        }

        @Override
        public int aktualnaCena() {
            return aktualnaCena;
        }

        public void subscribe(User user){
            subs.add(user);
        }

        public void unsubscribe(User user){
            subs.remove(user);
        }
    }

    List<PrzedmiotAukcji> allAuctions = new ArrayList<>();
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

    public PrzedmiotAukcji findAuctionOnId(int identyfikatorPrzedmiotuAukcji){
        /*
        Finds an auction in all auctions list based on given auction id
         */
        for (PrzedmiotAukcji auction : allAuctions){
            if (auction.identyfikator() == identyfikatorPrzedmiotuAukcji){
                return auction;
            }
        }
        return null;
    }

    @Override
    public void dodajUĹźytkownika(String username, Powiadomienie kontakt) {
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
        PrzedmiotAukcji auction = (PrzedmiotAukcji) przedmiot;
        allAuctions.add(auction);
    }

    @Override
    public void subskrypcjaPowiadomieĹ(String username, int identyfikatorPrzedmiotuAukcji) {
        User user = findUserOnUsername(username);
        PrzedmiotAukcji auction = findAuctionOnId(identyfikatorPrzedmiotuAukcji);
        if (user != null && auction != null) {
            auction.subscribe(user);
        }
    }

    @Override
    public void rezygnacjaZPowiadomieĹ(String username, int identyfikatorPrzedmiotuAukcji) {
        User user = findUserOnUsername(username);
        PrzedmiotAukcji auction = findAuctionOnId(identyfikatorPrzedmiotuAukcji);
        if (user != null && auction != null) {
            auction.unsubscribe(user);
        }
    }

    @Override
    public void oferta(String username, int identyfikatorPrzedmiotuAukcji, int oferowanaKwota) {
        PrzedmiotAukcji auction = findAuctionOnId(identyfikatorPrzedmiotuAukcji);
        User user = findUserOnUsername(username);
        if (user != null && auction != null) {
            auction.newOffer(user, oferowanaKwota);
        }
    }

    @Override
    public void koniecAukcji(int identyfikatorPrzedmiotuAukcji) {
        PrzedmiotAukcji auction = findAuctionOnId(identyfikatorPrzedmiotuAukcji);
        auction.finished = true;
    }

    @Override
    public String ktoWygrywa(int identyfikatorPrzedmiotuAukcji) {
        PrzedmiotAukcji auction = findAuctionOnId(identyfikatorPrzedmiotuAukcji);
        return auction.currentWinner.username;
    }

    @Override
    public int najwyĹźszaOferta(int identyfikatorPrzedmiotuAukcji) {
        PrzedmiotAukcji auction = findAuctionOnId(identyfikatorPrzedmiotuAukcji);
        return auction.aktualnaCena();
    }
}
