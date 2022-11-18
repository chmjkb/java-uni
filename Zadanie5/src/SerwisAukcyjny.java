import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class SerwisAukcyjny implements Aukcja{
    Map<PrzedmiotAukcji, Map<String, Integer>> currentAuctions = new HashMap<>();
    Map<PrzedmiotAukcji, Map<String, Integer>> finishedAuctions = new HashMap<>();

    public PrzedmiotAukcji getAuctionBasedOnId(int id){
        for (PrzedmiotAukcji key: currentAuctions.keySet()){
            if (key.identyfikator() == id){
                return key;
            }
        }
        return null;
    }

    @Override
    public void dodajUĹźytkownika(String username, Powiadomienie kontakt) {

    }

    @Override
    public void dodajPrzedmiotAukcji(PrzedmiotAukcji przedmiot) {
        currentAuctions.putIfAbsent(przedmiot, new HashMap<String, Integer>());
    }

    @Override
    public void subskrypcjaPowiadomieĹ(String username, int identyfikatorPrzedmiotuAukcji) {

    }

    @Override
    public void rezygnacjaZPowiadomieĹ(String username, int identyfikatorPrzedmiotuAukcji) {

    }

    @Override
    public void oferta(String username, int identyfikatorPrzedmiotuAukcji, int oferowanaKwota) {
        PrzedmiotAukcji auction = getAuctionBasedOnId(identyfikatorPrzedmiotuAukcji);
        Map<String, Integer> currentOffers = currentAuctions.get(auction);
        currentOffers.replace(username, oferowanaKwota);
        currentAuctions.replace(auction, currentOffers);
    }

    @Override
    public void koniecAukcji(int identyfikatorPrzedmiotuAukcji) {
        PrzedmiotAukcji auctionTofinish = getAuctionBasedOnId(identyfikatorPrzedmiotuAukcji);
        finishedAuctions.put(auctionTofinish, currentAuctions.get(auctionTofinish));
        currentAuctions.remove(auctionTofinish);
    }

    public Map<String, Integer> getHighestBidAndParticipant(int identyfikatorPrzedmiotuAukcji){
        PrzedmiotAukcji auctionOfInterest = getAuctionBasedOnId(identyfikatorPrzedmiotuAukcji);
        Map<String, Integer> bidsAndParticipants = currentAuctions.get(auctionOfInterest);

        Map<String, Integer> highestBidAndParticipant = new HashMap<>();

        Integer highestBid = 0;
        for (var entry : bidsAndParticipants.entrySet()) {
            if (entry.getValue() > highestBid){
                highestBid = entry.getValue();
                highestBidAndParticipant.clear();
                highestBidAndParticipant.put(entry.getKey(), highestBid);
            }
        }
        return highestBidAndParticipant;
    }

    @Override
    public String ktoWygrywa(int identyfikatorPrzedmiotuAukcji) {
        Map<String, Integer> highestBidAndParticipant = getHighestBidAndParticipant(identyfikatorPrzedmiotuAukcji);
        Optional<String> firstKey = highestBidAndParticipant.keySet().stream().findFirst();
        String key = null;
        if (firstKey.isPresent()) {
            key = firstKey.get();
        }
        return key;
    }

    @Override
    public int najwyĹźszaOferta(int identyfikatorPrzedmiotuAukcji) {
        Map<String, Integer> highestBidAndParticipant = getHighestBidAndParticipant(identyfikatorPrzedmiotuAukcji);
        Optional<String> firstKey = highestBidAndParticipant.keySet().stream().findFirst();
        String key = null;
        if (firstKey.isPresent()) {
            key = firstKey.get();
        }
        return highestBidAndParticipant.get(key); // TODO
        
    }

    public static void main(String[] args) {
        Aukcja aukcja = new SerwisAukcyjny();
    }
}
