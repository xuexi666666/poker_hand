import java.util.ArrayList;
import java.util.List;

public interface Game {
    static List<Card> one_pair_init(){
        List<Card> pairCards = new ArrayList<>();
        pairCards.add(new Card("3", "C"));
        pairCards.add(new Card("3", "S"));
        pairCards.add(new Card("5", "H"));
        pairCards.add(new Card("6", "S"));
        pairCards.add(new Card("T", "D"));
        return pairCards;
    }

    static List<Card> two_pair_init(){
        List<Card> twoPairCards = new ArrayList<>();
        twoPairCards.add(new Card("3", "C"));
        twoPairCards.add(new Card("3", "S"));
        twoPairCards.add(new Card("6", "H"));
        twoPairCards.add(new Card("6", "S"));
        twoPairCards.add(new Card("T", "D"));
        return twoPairCards;
    }

    static List<Card> three_of_kind_init(){
        List<Card> threeOfAKindCards = new ArrayList<>();
        threeOfAKindCards.add(new Card("3", "C"));
        threeOfAKindCards.add(new Card("3", "S"));
        threeOfAKindCards.add(new Card("3", "H"));
        threeOfAKindCards.add(new Card("6", "S"));
        threeOfAKindCards.add(new Card("T", "D"));
        return threeOfAKindCards;
    }

    static List<Card> straight_init(){
        List<Card> straightCards = new ArrayList<>();
        straightCards.add(new Card("3", "C"));
        straightCards.add(new Card("4", "S"));
        straightCards.add(new Card("5", "H"));
        straightCards.add(new Card("6", "S"));
        straightCards.add(new Card("7", "D"));
        return straightCards;
    }

    static List<Card> flush_init(){
        List<Card> flushCards = new ArrayList<>();
        flushCards.add(new Card("2", "C"));
        flushCards.add(new Card("4", "C"));
        flushCards.add(new Card("7", "C"));
        flushCards.add(new Card("8", "C"));
        flushCards.add(new Card("T", "C"));
        return flushCards;
    }

    static List<Card> full_house_init(){
        List<Card> fullHouseCards = new ArrayList<>();
        fullHouseCards.add(new Card("3", "C"));
        fullHouseCards.add(new Card("3", "S"));
        fullHouseCards.add(new Card("3", "H"));
        fullHouseCards.add(new Card("6", "S"));
        fullHouseCards.add(new Card("6", "D"));
        return fullHouseCards;
    }

    static List<Card> four_OfAKind_init(){
        List<Card> fourOfAKindCards = new ArrayList<>();
        fourOfAKindCards.add(new Card("3", "C"));
        fourOfAKindCards.add(new Card("3", "S"));
        fourOfAKindCards.add(new Card("3", "H"));
        fourOfAKindCards.add(new Card("3", "S"));
        fourOfAKindCards.add(new Card("6", "D"));
        return fourOfAKindCards;
    }

    static List<Card> straight_flush_init() {
        List<Card> straightFlushCards = new ArrayList<>();
        straightFlushCards.add(new Card("6", "C"));
        straightFlushCards.add(new Card("7", "C"));
        straightFlushCards.add(new Card("8", "C"));
        straightFlushCards.add(new Card("9", "C"));
        straightFlushCards.add(new Card("T", "C"));
        return straightFlushCards;
    }

    static String getWinnerByOneCard(Poker player1, Poker player2){
        int res = player1.getCards().get(0).compareTo(player2.getCards().get(0));
        return res == 0?GameProperty.TIE:(res == 1? GameProperty.POKER_1_WINNER: GameProperty.POKER_2_WINNER);
    }
}
