import java.util.*;
import java.util.stream.Collectors;


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
    protected int isTwoPair(){
        int count = 0;
        for (int i = 0; i < numbers.length-1; i++) {
            if(numbers[i] == numbers[i+1]){
                count++;
            }
            if (count == 2){
                return GameProperty.TWO_PAIR;
            }
        }
        return GameProperty.NOT_EXISTS_POKER_TYPE;
    }
    protected int isThreeOfAKind(){
        for (int i = 0; i < numbers.length - 2; i++) {
            if (numbers[i] == numbers[i + 1] && numbers[i] == numbers[i + 2]) {
                return GameProperty.THREE_OF_A_KIND;
            }
        }

        return GameProperty.NOT_EXISTS_POKER_TYPE;
    }
    protected int isStraight(){
        boolean MaxMinusMinIsFour = numbers[4]-numbers[0] == 4;
        Set set  = Arrays.stream(numbers).boxed().collect(Collectors.toSet());
        if(MaxMinusMinIsFour && set.size() == 5){
            return GameProperty.STRAIGHT;
        }
        return GameProperty.NOT_EXISTS_POKER_TYPE;
    }
    protected int isFlush(){
        return Arrays.stream(suits).collect(Collectors.toSet()).size()==1?GameProperty.FLUSH:GameProperty.NOT_EXISTS_POKER_TYPE;
    }
    protected int  isFullHouse(){
        boolean isFrontThreeNumberEqual = (numbers[0]==numbers[1])&&(numbers[0]==numbers[2]);
        boolean isLastTwoNumberEqual = numbers[3]==numbers[4];
        return (isFrontThreeNumberEqual&&isLastTwoNumberEqual)?GameProperty.FULL_HOUSE:GameProperty.NOT_EXISTS_POKER_TYPE;
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
