import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PokerTest {
    private List<Card> highCards;
    private Poker highCard;

    private List<Card> pairCards;
    private Poker onePair;

    private List<Card> twoPairCards;
    private Poker twoPair;

    private List<Card> threeOfAKindCards;
    private Poker threeOfAKind;

    private List<Card> straightCards;
    private Poker straight;

    private List<Card> flushCards;
    private Poker flush;

    private List<Card> fullHouseCards;
    private Poker fullHouse;

    private List<Card> fourOfAKindCards;
    private Poker fourOfAKind;

    private List<Card> straightFlushCards;
    private Poker straightFlush;

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
    public void should_return_HIGH_CARD_when_give_a_high_card_poker(){
        //given
        highCards = Game.high_card_init();
        highCard = new Poker(highCards);
        //when
        int res = highCard.getPokerType();
        //then
        Assert.assertEquals(res,GameProperty.HIGH_CARD);
    }

    @Test
    public void should_throw_null_exception_when_give_error_params_init_cards(){
        //given+when+then
        try {
            Card card = new Card("10", "S");
        }catch (NullPointerException ex){
            Assert.assertEquals("传入参数不正确",ex.getMessage());
        }
    }

    @Test
    public void should_return_ONE_PAIR_when_give_a_pair_of_identical_cards(){
        //given
        pairCards = Game.one_pair_init();
        onePair = new Poker(pairCards);
        //when
        int res = onePair.isOnePair();
        //then
        Assert.assertEquals(res,GameProperty.ONE_PAIR);
    }
    @Test
    public void should_return_TWO_PAIR_when_give_tow_pair_of_identical_cards(){
        //given
        twoPairCards = Game.two_pair_init();
        twoPair = new Poker(twoPairCards);
        //when
        int res = twoPair.isTwoPair();
        //then
        Assert.assertEquals(res,GameProperty.TWO_PAIR);
    }

    @Test
    public void should_return_ThreeOfAKind_when_give_a_three_of_kind_poker(){
        //given
        threeOfAKindCards = Game.three_of_kind_init();
        threeOfAKind = new Poker(threeOfAKindCards);
        //when
        int res = threeOfAKind.isThreeOfAKind();
        //then
        Assert.assertEquals(res,GameProperty.THREE_OF_A_KIND);
    }

    @Test
    public void should_return_Straight_when_give_a_straight_poker(){
        //given
        straightCards = Game.straight_init();
        straight = new Poker(straightCards);
        //when
        int res = straight.isStraight();
        //then
        Assert.assertEquals(res,GameProperty.STRAIGHT);
    }

    @Test
    public void should_return_flush_when_give_a_flush_poker(){
        //given
        flushCards = Game.flush_init();
        flush = new Poker(flushCards);
        //when
        int res = flush.isFlush();
        //then
        Assert.assertEquals(res,GameProperty.FLUSH);
    }

    @Test
    public void should_return_full_house_when_give_a_full_house_poker(){
        //given
        fullHouseCards = Game.full_house_init();
        fullHouse = new Poker(fullHouseCards);
        //when
        int res = fullHouse.isFullHouse();
        //then
        Assert.assertEquals(res,GameProperty.FULL_HOUSE);
    }

    @Test
    public void should_return_four_OfAKind_when_give_a_four_OfAKind_poker(){
        //given
        fourOfAKindCards = Game.four_OfAKind_init();
        fourOfAKind = new Poker(fourOfAKindCards);
        //when
        int res = fourOfAKind.isFourOfAKind();
        //then
        Assert.assertEquals(res,GameProperty.FOUR_OF_A_KIND);
    }

    @Test
    public void should_return_Straight_Flush_when_give_a_Straight_Flush_poker(){
        //given
        straightFlushCards = Game.straight_flush_init();
        straightFlush = new Poker(straightFlushCards);
        //when
        int res = straightFlush.isStraightFlush();
        //then
        Assert.assertEquals(res,GameProperty.STRAIGHT_FLUSH);
    }

    @Test
    public void should_return__when_give_a_Straight_Flush_poker(){
        //given
        straightFlushCards = Game.straight_flush_init();
        straightFlush = new Poker(straightFlushCards);
        //when
        int res = straightFlush.isStraightFlush();
        //then
        Assert.assertEquals(res,GameProperty.STRAIGHT_FLUSH);
    }
    @Test
    public void should_return_not_this_poker_type_when_give_a_error_poker(){
        //given
        straightFlushCards = Game.straight_flush_init();
        straightFlush = new Poker(straightFlushCards);
        //when
        int res = straightFlush.isFullHouse();
        //then
        Assert.assertEquals(res,GameProperty.NOT_THIS_POKER_TYPE);
    }
}
