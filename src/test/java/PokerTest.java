import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PokerTest {
    private ArrayList<Card> pairCards;
    private Poker onePair;

    private ArrayList<Card> twoPairCards;
    private Poker twoPair;

    private ArrayList<Card> threeOfAKindCards;
    private Poker threeOfAKind;

    private ArrayList<Card> straightCards;
    private Poker straight;

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

}
