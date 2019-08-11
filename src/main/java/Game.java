import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public interface Game {
    Random rand = new Random();
    String[] suitsStyle = {"C","D","S","H"};
    String[] numbers = {"2","3","4","5","6","7","8","9","T","J","Q","K","A"};
    static List<Card> random_init(){
        List<Card> randomCards = new ArrayList<>();
        randomCards.add(new Card(numbers[rand.nextInt(13)], suitsStyle[rand.nextInt(4)]));
        randomCards.add(new Card(numbers[rand.nextInt(13)], suitsStyle[rand.nextInt(4)]));
        randomCards.add(new Card(numbers[rand.nextInt(13)], suitsStyle[rand.nextInt(4)]));
        randomCards.add(new Card(numbers[rand.nextInt(13)], suitsStyle[rand.nextInt(4)]));
        randomCards.add(new Card(numbers[rand.nextInt(13)], suitsStyle[rand.nextInt(4)]));
        return randomCards;
    }

    static List<Card> high_card_init(){
        List<Card> highCards = new ArrayList<>();
        highCards.add(new Card("3", "C"));
        highCards.add(new Card("7", "S"));
        highCards.add(new Card("5", "H"));
        highCards.add(new Card("6", "S"));
        highCards.add(new Card("T", "D"));
        return highCards;
    }

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

    static int[] poker_numbers_bigger_init(int level){
        switch (level){
            case GameProperty.HIGH_CARD:
                return new int[]{3,5,6,7,8};
            case GameProperty.ONE_PAIR:
                return new int[]{3,3,6,7,8};
            case GameProperty.TWO_PAIR:
                return new int[]{3,3,6,6,8};
            case GameProperty.THREE_OF_A_KIND:
                return new int[]{3,3,3,7,8};
            case GameProperty.STRAIGHT:
                return new int[]{5,6,7,8,9};
            case GameProperty.FLUSH:
                return new int[]{3,5,6,7,8};
            case GameProperty.FULL_HOUSE:
                return new int[]{3,3,3,7,7};
            case GameProperty.FOUR_OF_A_KIND:
                return new int[]{3,3,3,3,8};
            case GameProperty.STRAIGHT_FLUSH:
                return new int[]{5,6,7,8,9};
        }
        return null;
    }

    static int[] poker_numbers_smaller_init(int level){
        switch (level){
            case GameProperty.HIGH_CARD:
                return new int[]{2,5,6,7,8};
            case GameProperty.ONE_PAIR:
                return new int[]{3,3,5,7,8};
            case GameProperty.TWO_PAIR:
                return new int[]{3,3,6,6,7};
            case GameProperty.THREE_OF_A_KIND:
                return new int[]{3,3,3,6,8};
            case GameProperty.STRAIGHT:
                return new int[]{4,5,6,7,8};
            case GameProperty.FLUSH:
                return new int[]{2,5,6,7,8};
            case GameProperty.FULL_HOUSE:
                return new int[]{3,3,3,6,6};
            case GameProperty.FOUR_OF_A_KIND:
                return new int[]{3,3,3,3,7};
            case GameProperty.STRAIGHT_FLUSH:
                return new int[]{4,5,6,7,8};
        }
        return null;
    }

    static int getWinnerByOneCard(Poker player1, Poker player2){
        int res = player1.getCards().get(0).compareTo(player2.getCards().get(0));
        return res == 0?GameProperty.TIE:(res == 1? GameProperty.POKER_1_WINNER: GameProperty.POKER_2_WINNER);
    }
}
