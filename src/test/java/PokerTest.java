import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PokerTest {
    private ArrayList<Card> pairCards;
    private Poker onePair;

    private ArrayList<Card> twoPairCards;
    private Poker twoPair;

    private ArrayList<Card> threeOfAKindCards;
    private Poker threeOfAKind;

    private ArrayList<Card> straightCards;
    private Poker straight;

    private ArrayList<Card> flushCards;
    private Poker flush;

    private ArrayList<Card> fullHouseCards;
    private Poker fullHouse;

    private ArrayList<Card> fourOfAKindCards;
    private Poker fourOfAKind;

    private ArrayList<Card> straightFlushCards;
    private Poker straightFlush;

    public void one_pair_init(){
        //given
        pairCards = new ArrayList<>();
        pairCards.add(new Card("3", "C"));
        pairCards.add(new Card("3", "S"));
        pairCards.add(new Card("5", "H"));
        pairCards.add(new Card("6", "S"));
        pairCards.add(new Card("T", "D"));
        onePair = new Poker(pairCards);
    }

    public void two_pair_init(){
        twoPairCards = new ArrayList<>();
        twoPairCards.add(new Card("3", "C"));
        twoPairCards.add(new Card("3", "S"));
        twoPairCards.add(new Card("6", "H"));
        twoPairCards.add(new Card("6", "S"));
        twoPairCards.add(new Card("T", "D"));
        twoPair = new Poker(twoPairCards);
    }

    public void three_of_kind_init(){
        threeOfAKindCards = new ArrayList<Card>();
        threeOfAKindCards.add(new Card("3", "C"));
        threeOfAKindCards.add(new Card("3", "S"));
        threeOfAKindCards.add(new Card("3", "H"));
        threeOfAKindCards.add(new Card("6", "S"));
        threeOfAKindCards.add(new Card("T", "D"));
        threeOfAKind = new Poker(threeOfAKindCards);
    }

    public void straight_init(){
        straightCards = new ArrayList<>();
        straightCards.add(new Card("3", "C"));
        straightCards.add(new Card("4", "S"));
        straightCards.add(new Card("5", "H"));
        straightCards.add(new Card("6", "S"));
        straightCards.add(new Card("7", "D"));
        straight = new Poker(straightCards);
    }

    public void flush_init(){
        flushCards = new ArrayList<>();
        flushCards.add(new Card("2", "C"));
        flushCards.add(new Card("4", "C"));
        flushCards.add(new Card("7", "C"));
        flushCards.add(new Card("8", "C"));
        flushCards.add(new Card("T", "C"));
        flush = new Poker(flushCards);
    }

    public void full_house_init(){
        fullHouseCards = new ArrayList<>();
        fullHouseCards.add(new Card("3", "C"));
        fullHouseCards.add(new Card("3", "S"));
        fullHouseCards.add(new Card("3", "H"));
        fullHouseCards.add(new Card("6", "S"));
        fullHouseCards.add(new Card("6", "D"));
        fullHouse = new Poker(fullHouseCards);
    }

    public void four_OfAKind_init(){
        fourOfAKindCards = new ArrayList<>();
        fourOfAKindCards.add(new Card("3", "C"));
        fourOfAKindCards.add(new Card("3", "S"));
        fourOfAKindCards.add(new Card("3", "H"));
        fourOfAKindCards.add(new Card("3", "S"));
        fourOfAKindCards.add(new Card("6", "D"));
        fourOfAKind = new Poker(fourOfAKindCards);
    }

    public void straight_flush_init() {
        straightFlushCards = new ArrayList<>();
        straightFlushCards.add(new Card("6", "C"));
        straightFlushCards.add(new Card("7", "C"));
        straightFlushCards.add(new Card("8", "C"));
        straightFlushCards.add(new Card("9", "C"));
        straightFlushCards.add(new Card("T", "C"));
        straightFlush = new Poker(straightFlushCards);
    }
    @Test
    public void should_return_who_win_when_give_each_player_one_poker(){
        Card card1 = new Card("3","H");
        Card card2 = new Card("4","H");
        Poker poker1 = new Poker(Arrays.asList(card1));
        Poker poker2 = new Poker(Arrays.asList(card2));
        String res = Game.getWinnerByOneCard(poker1,poker2);
        Assert.assertEquals(GameProperty.POKER_2_WINNER,res);
    }

    @Test
    public void should_return_ONE_PAIR_when_give_a_pair_of_identical_cards(){
        //given
        one_pair_init();
        //when
        int res = onePair.isOnePair();
        //then
        Assert.assertEquals(res,GameProperty.ONE_PAIR);
    }
    @Test
    public void should_return_TWO_PAIR_when_give_tow_pair_of_identical_cards(){
        //given
        two_pair_init();
        //when
        int res = twoPair.isTwoPair();
        //then
        Assert.assertEquals(res,GameProperty.TWO_PAIR);
    }

    @Test
    public void should_return_ThreeOfAKind_when_give_a_three_of_kind_poker(){
        //given
        three_of_kind_init();
        //when
        int res = threeOfAKind.isThreeOfAKind();
        //then
        Assert.assertEquals(res,GameProperty.THREE_OF_A_KIND);
    }

    @Test
    public void should_return_Straight_when_give_a_straight_poker(){
        //given
        straight_init();
        //when
        int res = straight.isStraight();
        //then
        Assert.assertEquals(res,GameProperty.STRAIGHT);
    }

    @Test
    public void should_return_flush_when_give_a_flush_poker(){
        //given
        flush_init();
        //when
        int res = flush.isFlush();
        //then
        Assert.assertEquals(res,GameProperty.FLUSH);
    }

    @Test
    public void should_return_full_house_when_give_a_full_house_poker(){
        //given
        full_house_init();
        //when
        int res = fullHouse.isFullHouse();
        //then
        Assert.assertEquals(res,GameProperty.FULL_HOUSE);
    }

    @Test
    public void should_return_four_OfAKind_when_give_a_four_OfAKind_poker(){
        //given
        four_OfAKind_init();
        //when
        int res = fourOfAKind.isFourOfAKind();
        //then
        Assert.assertEquals(res,GameProperty.FOUR_OF_A_KIND);
    }

    @Test
    public void should_return_Straight_Flush_when_give_a_Straight_Flush_poker(){
        //given
        straight_flush_init();
        //when
        int res = straightFlush.isStraightFlush();
        //then
        Assert.assertEquals(res,GameProperty.STRAIGHT_FLUSH);
    }
}
