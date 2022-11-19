import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class SerwisAukcyjny2 implements Aukcja{

    Map<PrzedmiotAukcji, Map<User, Integer>> currentAuctions = new HashMap<>();
    Map<PrzedmiotAukcji, Map<User, Integer>> finishedAuctions = new HashMap<>();
    ArrayList<User> listOfUsers = new ArrayList<>();

//    public class Auction{
//        Map<String, Integer> participantsAndOffers;
//        ArrayList<User> watchers;
//
//        public void getNotifications(User user){
//            watchers.add(user);
//        }
//
//        public void stopNotifications(User user){
//            watchers.remove(user);
//        }
//
//        public void notifyUser(User user){
//            user.contactPoint.przebitoTwojÄOfertÄ();
//        }
//    }

    public class User{
        String username;
        Powiadomienie contactPoint;
        ArrayList<PrzedmiotAukcji> userAuctions;

        public User(String name, Powiadomienie contact){
            username = name;
            contactPoint = contact;
        }

        public void sendNotification(PrzedmiotAukcji auction){
            contactPoint.przebitoTwojÄOfertÄ(auction);
        }

        public void enableNotifications(PrzedmiotAukcji auction){
            userAuctions.add(auction);
        }

        public void disableNotifications(PrzedmiotAukcji auction){
            userAuctions.remove(auction);
        }


    }

    public User findUser(String username){
        /*
        Finds user with certain username in users list, returns User
         */
        for (User user: listOfUsers){
            if (user.username.equals(username)){
                return user;
            }
        }
        return null;
    }

    public PrzedmiotAukcji findAuctionOnId(int identyfikatorPrzedmiotuAukcji){
        /*
        Finds auction with certain auction id, returns Auction
         */
        for (var entry: currentAuctions.entrySet()){
            if (entry.getKey().identyfikator() == identyfikatorPrzedmiotuAukcji){
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public void dodajUĹźytkownika(String username, Powiadomienie kontakt) {
        /*
        Adds user to a lists of users in the auction service
         */
        listOfUsers.add(new User(username, kontakt));
    }

    @Override
    public void dodajPrzedmiotAukcji(PrzedmiotAukcji przedmiot) {
        /*
        Adds a new auction
         */
        currentAuctions.putIfAbsent(przedmiot, new HashMap<>());
    }

    @Override
    public void subskrypcjaPowiadomieĹ(String username, int identyfikatorPrzedmiotuAukcji) {
        /*
        Subscribes a user to a certain auction
         */
        User user = findUser(username);
        PrzedmiotAukcji auction = findAuctionOnId(identyfikatorPrzedmiotuAukcji);
        user.enableNotifications(auction);
    }

    @Override
    public void rezygnacjaZPowiadomieĹ(String username, int identyfikatorPrzedmiotuAukcji) {
        /*
        Deletes user from subscribers list of a certain auction
         */
        User user = findUser(username);
        PrzedmiotAukcji auction = findAuctionOnId(identyfikatorPrzedmiotuAukcji);
        user.disableNotifications(auction);
    }

    public void findSubscribedUsers(int identyfikatorPrzedmiotuAukcji){
        /*
        Returns a list of users subscribed to a certain auction
         */
    }

    @Override
    public void oferta(String username, int identyfikatorPrzedmiotuAukcji, int oferowanaKwota) {
        /*
        Sets up a new offer for a given auction
         */
        PrzedmiotAukcji auction = findAuctionOnId(identyfikatorPrzedmiotuAukcji);
        User user = findUser(username);

        currentAuctions.get(auction).put(user, oferowanaKwota);
    }

    @Override
    public void koniecAukcji(int identyfikatorPrzedmiotuAukcji) {
        /*
        Removes an auction from currentauctions list
         */
        PrzedmiotAukcji auction = findAuctionOnId(identyfikatorPrzedmiotuAukcji);
        Map<User, Integer> usersAndOffers = currentAuctions.get(auction);
        finishedAuctions.put(auction, usersAndOffers);
        currentAuctions.remove(auction);
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
