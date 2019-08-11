import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Poker {
    private List<Card> cards;
    private int[] numbers;
    private String[] suits;
    public Poker(List<Card> cards){
        this.cards = new ArrayList<>(cards);
        numbers = values();
        suits = suits();
        Arrays.sort(numbers);
        Arrays.sort(suits);
    }

    protected boolean isHighCard(List<Card> cards){
        return false;
    }
    protected int isOnePair(){
        for (int i = 0; i < numbers.length-1; i++) {
            if(numbers[i] == numbers[i+1]){
                return GameProperty.ONE_PAIR;
            }
        }
        return GameProperty.NOT_EXISTS_POKER_TYPE;
    }
    protected boolean isTwoPair(List<Card> cards){
        return false;
    }
    protected boolean isThreeOfAKind(List<Card> cards){
        return false;
    }
    protected boolean isStraight(List<Card> cards){
        return false;
    }
    protected boolean Flush(List<Card> cards){
        return false;
    }
    protected boolean isFullHouse(List<Card> cards){
        return false;
    }
    protected boolean isFourOfAKind(List<Card> cards){
        return false;
    }
    protected boolean isStraightFlush(List<Card> cards){
        return false;
    }

    public List<Card> getCards() {
        return cards;
    }

    private int[] values(){
        return cards.stream().mapToInt(Card::getNumber).toArray();
    }

    private String[] suits() {
        return cards.stream().map(Card::getSuit).toArray(String[]::new);
    }
}
