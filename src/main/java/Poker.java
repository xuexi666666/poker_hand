import org.apache.commons.lang3.StringUtils;

import java.util.*;


public class Poker implements Comparable<Poker> {
    private List<Card> cards;
    private int[] numbers;
    private String[] suits;

    public Poker() {
    }

    public Poker(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        numbers = convertIntArray();
        suits = convertStringArray();
        Arrays.sort(numbers);
        Arrays.sort(suits);
    }

    public int getPokerType(){
        int not_exits_type = GameProperty.NOT_THIS_POKER_TYPE;
        if(isStraightFlush() != not_exits_type){
            return isStraightFlush();
        }else if(isFourOfAKind()!= not_exits_type){
            return isFourOfAKind();
        }else if(isFullHouse()!= not_exits_type){
            return isFullHouse();
        }else if(isStraight() != not_exits_type){
            return isStraight();
        }else if(isThreeOfAKind() != not_exits_type){
            return isThreeOfAKind();
        }else if(isTwoPair() != not_exits_type){
            return isTwoPair();
        }else if(isOnePair() != not_exits_type){
            return isOnePair();
        }else{
            return GameProperty.HIGH_CARD;
        }
    }

    protected int isOnePair() {
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == numbers[i + 1]) {
                return GameProperty.ONE_PAIR;
            }
        }
        return GameProperty.NOT_THIS_POKER_TYPE;
    }

    protected int isTwoPair() {
        int count = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == numbers[i + 1]) {
                count++;
            }
            if (count == 2) {
                return GameProperty.TWO_PAIR;
            }
        }
        return GameProperty.NOT_THIS_POKER_TYPE;
    }

    protected int isThreeOfAKind() {
        for (int i = 0; i < numbers.length - 2; i++) {
            if (numbers[i] == numbers[i + 1] && numbers[i] == numbers[i + 2]) {
                return GameProperty.THREE_OF_A_KIND;
            }
        }
        return GameProperty.NOT_THIS_POKER_TYPE;
    }

    protected int isStraight() {
        long numberSize = Arrays.stream(numbers).distinct().count();
        return (isMaxMinusMinIsFourOrThirteen() && numberSize == 5) ? GameProperty.STRAIGHT : GameProperty.NOT_THIS_POKER_TYPE;
    }

    private boolean isMaxMinusMinIsFourOrThirteen() {
        int res = numbers[4] - numbers[0];
        return res == 4 || res == 13;
    }

    protected int isFlush() {
        return suits[0] == suits[4] ? GameProperty.FLUSH : GameProperty.NOT_THIS_POKER_TYPE;
    }

    protected int isFullHouse() {
        boolean isThreeNumberEqual = (numbers[0] == numbers[2]) || (numbers[2] == numbers[4]);
        boolean isTwoNumberEqual = (numbers[3] == numbers[4] || numbers[0] == numbers[1]);
        return (isThreeNumberEqual && isTwoNumberEqual) ? GameProperty.FULL_HOUSE : GameProperty.NOT_THIS_POKER_TYPE;
    }

    protected int isFourOfAKind() {
        return (numbers[0] == numbers[3] || numbers[1] == numbers[4]) ? GameProperty.FOUR_OF_A_KIND : GameProperty.NOT_THIS_POKER_TYPE;
    }

    protected int isStraightFlush() {
        return (isStraight() != -1 && isFlush() != -1) ? GameProperty.STRAIGHT_FLUSH : GameProperty.NOT_THIS_POKER_TYPE;
    }

    public List<Card> getCards() {
        return cards;
    }

    private int[] convertIntArray() {
        return cards.stream().mapToInt(Card::getNumber).toArray();
    }

    private String[] convertStringArray() {
        return cards.stream().map(Card::getSuit).toArray(String[]::new);
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public String[] getSuits() {
        return suits;
    }

    public void setSuits(String[] suits) {
        this.suits = suits;
    }

    @Override
    public int compareTo(Poker o) {
        int thisPokerType = getPokerType();
        int otherPokerType = o.getPokerType();
        if(thisPokerType > otherPokerType){
            return GameProperty.POKER_1_WINNER;
        }else if(thisPokerType < otherPokerType){
            return GameProperty.POKER_2_WINNER;
        }else{
            String thisNumStr = StringUtils.join(numbers,',');
            String otherNumStr = StringUtils.join(o.getNumbers(),',');
            int res = thisNumStr.compareTo(otherNumStr);
            return res>0?GameProperty.POKER_1_WINNER:(res<0?GameProperty.POKER_2_WINNER:GameProperty.TIE);
        }
    }
}
