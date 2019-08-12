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
