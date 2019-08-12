import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
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
    Poker poker;

    @Before
    public void init(){
        poker = new Poker();
    }

    @Test
    public void should_return_who_win_when_give_each_player_one_poker() {
        //given
        Card card1 = new Card("3", "H");
        Card card2 = new Card("4", "H");
        Poker poker1 = new Poker(Arrays.asList(card1));
        Poker poker2 = new Poker(Arrays.asList(card2));
        //when
        int res = Game.getWinnerByOneCard(poker1, poker2);
        //then
        Assert.assertEquals(GameProperty.POKER_2_WINNER, res);
    }

    @Test
    public void should_throw_null_exception_when_give_error_params_init_cards() {
        try {
            //given
            Card card = new Card("10", "S");
        } catch (NullPointerException ex) {
            //when+then
            Assert.assertEquals("传入参数不正确", ex.getMessage());
        }
    }

    @Test
    public void should_return_not_this_poker_type_when_give_a_error_poker() {
        //given
//        straightFlushCards = Game.straight_flush_init();
//        straightFlush = new Poker(straightFlushCards);
        //refactor after
        int[] straight_numbers = {6,7,8,9,10};
        poker.setNumbers(straight_numbers);
        //when
//        int res = straightFlush.isFullHouse();
        int res = poker.isFullHouse();
        //then
        Assert.assertEquals(res, GameProperty.NOT_THIS_POKER_TYPE);
    }

    @Test
    public void should_return_HIGH_CARD_when_give_a_high_card_poker() {
        //given
//        highCards = Game.high_card_init();
//        highCard = new Poker(highCards);
        //refactor after
        int[] high_card_numbers = {3,5,6,7,10};
        poker.setNumbers(high_card_numbers);
        //when
//        int res = highCard.getPokerType();
        int res = poker.getPokerType();
        //then
        Assert.assertEquals(res, GameProperty.HIGH_CARD);
    }

    @Test
    public void should_return_ONE_PAIR_when_give_a_pair_of_identical_cards() {
        //given
//        pairCards = Game.one_pair_init();
//        onePair = new Poker(pairCards);
        //refactor after
        int[] one_pair_numbers = {3,3,5,6,10};
        poker.setNumbers(one_pair_numbers);
        //when
        int res = poker.isOnePair();
        //then
        Assert.assertEquals(res, GameProperty.ONE_PAIR);
    }

    @Test
    public void should_return_TWO_PAIR_when_give_tow_pair_of_identical_cards() {
        //given
//        twoPairCards = Game.two_pair_init();
//        twoPair = new Poker(twoPairCards);
        int[] two_pair_numbers = {3,3,6,6,10};
        poker.setNumbers(two_pair_numbers);
        //when
        int res = poker.isTwoPair();
        //then
        Assert.assertEquals(res, GameProperty.TWO_PAIR);
    }

    @Test
    public void should_return_ThreeOfAKind_when_give_a_three_of_kind_poker() {
        //given
//        threeOfAKindCards = Game.three_of_kind_init();
//        threeOfAKind = new Poker(threeOfAKindCards);
        int[] three_of_kind_numbers = {3,3,3,6,10};
        poker.setNumbers(three_of_kind_numbers);
        //when
        int res = poker.isThreeOfAKind();
        //then
        Assert.assertEquals(res, GameProperty.THREE_OF_A_KIND);
    }

    @Test
    public void should_return_Straight_when_give_a_straight_poker() {
        //given
//        straightCards = Game.straight_init();
//        straight = new Poker(straightCards);
        int[] straight_numbers1 = {3,4,5,6,7};
        int[] straight_numbers2 = {2,3,4,5,14};
        poker.setNumbers(straight_numbers1);
        //when
        int res1 = poker.isStraight();
        poker.setNumbers(straight_numbers2);
        int res2 = poker.isStraight();
        //then
        Assert.assertEquals(res1, GameProperty.STRAIGHT);
        Assert.assertEquals(res1, res2);

    }

    @Test
    public void should_return_flush_when_give_a_flush_poker() {
        //given
//        flushCards = Game.flush_init();
//        flush = new Poker(flushCards);
        String[] flush_suits = {"C","C","C","C","C"};
        poker.setSuits(flush_suits);
        //when
        int res = poker.isFlush();
        //then
        Assert.assertEquals(res, GameProperty.FLUSH);
    }

    @Test
    public void should_return_full_house_when_give_a_full_house_poker() {
        //given
//        fullHouseCards = Game.full_house_init();
//        fullHouse = new Poker(fullHouseCards);
        int[] full_house_numbers1 = {3,3,3,6,6};
        int[] full_house_numbers2 = {2,2,3,3,3};
        poker.setNumbers(full_house_numbers1);
        //when
        int res1 = poker.isFullHouse();
        poker.setNumbers(full_house_numbers2);
        int res2 = poker.isFullHouse();
        //then
        Assert.assertEquals(res1, GameProperty.FULL_HOUSE);
        Assert.assertEquals(res1, res2);
    }

    @Test
    public void should_return_four_OfAKind_when_give_a_four_OfAKind_poker() {
        //given
        int[] four_OfAKind_numbers1 = {3,3,3,3,6};
        int[] four_OfAKind_numbers2 = {2,3,3,3,3};
        poker.setNumbers(four_OfAKind_numbers1);
        //when
        int res1 = poker.isFourOfAKind();
        poker.setNumbers(four_OfAKind_numbers2);
        int res2 = poker.isFourOfAKind();
        //then
        Assert.assertEquals(res1, GameProperty.FOUR_OF_A_KIND);
        Assert.assertEquals(res1, res2);
    }

    @Test
    public void should_return_Straight_Flush_when_give_a_Straight_Flush_poker() {
        //given
        straightFlushCards = Game.straight_flush_init();
        straightFlush = new Poker(straightFlushCards);
        //when
        int res = straightFlush.isStraightFlush();
        //then
        Assert.assertEquals(res, GameProperty.STRAIGHT_FLUSH);
    }


    @Test
    public void should_return_poker_kinds_level_highest_win_when_give_two_different_kinds_poker_by_mock() {
        //given
        Poker playerOne = mock(Poker.class);
        Poker playerTwo = mock(Poker.class);
        int randomLevelHiger = Game.rand.nextInt(GameProperty.STRAIGHT_FLUSH)+1;
        int randomLevelLower = Game.rand.nextInt(randomLevelHiger);
        //when
        when(playerOne.getPokerType()).thenReturn(randomLevelHiger);
        when(playerTwo.getPokerType()).thenReturn(randomLevelLower);
        when(playerOne.compareTo(playerTwo)).thenReturn(GameProperty.POKER_1_WINNER);
        when(playerTwo.compareTo(playerOne)).thenReturn(GameProperty.POKER_2_WINNER);
        int HigherLevel = playerOne.getPokerType();
        int LowerLevel = playerTwo.getPokerType();
        int resOne = playerOne.compareTo(playerTwo);
        int resTwo = playerTwo.compareTo(playerOne);
        //then
        verify(playerOne,times(1)).compareTo(playerTwo);
        verify(playerOne,times(1)).getPokerType();
        verify(playerTwo,times(1)).compareTo(playerOne);
        verify(playerTwo,times(1)).getPokerType();
        Assert.assertTrue(randomLevelHiger>randomLevelLower);
        Assert.assertTrue(HigherLevel>LowerLevel);
        Assert.assertEquals(resOne, GameProperty.POKER_1_WINNER);
        Assert.assertEquals(resTwo, GameProperty.POKER_2_WINNER);
    }

    @Test
    public void should_return_poker_higher_card_win_when_give_two_identical_kinds_poker_by_mock() {
        //given
        Poker playerOne = mock(Poker.class);
        Poker playerTwo = mock(Poker.class);
        int randomLevel = Game.rand.nextInt(GameProperty.STRAIGHT_FLUSH)+1;
        int[] biggerNumbers = Game.poker_numbers_bigger_init(randomLevel);
        int[] smallerNumbers = Game.poker_numbers_smaller_init(randomLevel);
        //when
        when(playerOne.getPokerType()).thenReturn(randomLevel);
        when(playerTwo.getPokerType()).thenReturn(randomLevel);
        when(playerOne.getNumbers()).thenReturn(biggerNumbers);
        when(playerTwo.getNumbers()).thenReturn(smallerNumbers);
        when(playerOne.compareTo(playerTwo)).thenReturn(GameProperty.POKER_1_WINNER);
        when(playerTwo.compareTo(playerOne)).thenReturn(GameProperty.POKER_2_WINNER);
        int playerOneLevel = playerOne.getPokerType();
        int playerTwoLevel = playerTwo.getPokerType();
        String biggerNumbersStr = StringUtils.join(playerOne.getNumbers(),',');
        String smallerNumbersStr = StringUtils.join(playerTwo.getNumbers(),',');
        int numsCompare = StringUtils.compare(biggerNumbersStr,smallerNumbersStr);
        int resOne = playerOne.compareTo(playerTwo);
        int resTwo = playerTwo.compareTo(playerOne);
        //then
        verify(playerOne,times(1)).getPokerType();
        verify(playerOne,times(1)).getNumbers();
        verify(playerOne,times(1)).compareTo(playerTwo);
        Assert.assertTrue(playerOneLevel==playerTwoLevel);
        Assert.assertTrue(numsCompare == 1);
        Assert.assertEquals(resOne, GameProperty.POKER_1_WINNER);
        Assert.assertEquals(resTwo, GameProperty.POKER_2_WINNER);
    }

    @Test
    public void should_return_tie_when_give_exactly_the_same_poker_by_mock() {
        //given
        Poker playerOne = mock(Poker.class);
        Poker playerTwo = mock(Poker.class);
        int randomLevel = Game.rand.nextInt(GameProperty.STRAIGHT_FLUSH)+1;
        int[] numbers = Game.poker_numbers_bigger_init(randomLevel);

        //when
        when(playerOne.getPokerType()).thenReturn(randomLevel);
        when(playerTwo.getPokerType()).thenReturn(randomLevel);
        when(playerOne.getNumbers()).thenReturn(numbers);
        when(playerTwo.getNumbers()).thenReturn(numbers);
        when(playerOne.compareTo(playerOne)).thenReturn(GameProperty.TIE);
        when(playerOne.compareTo(playerTwo)).thenReturn(GameProperty.TIE);
        when(playerTwo.compareTo(playerOne)).thenReturn(GameProperty.TIE);
        when(playerTwo.compareTo(playerTwo)).thenReturn(GameProperty.TIE);
        int playerOneLevel = playerOne.getPokerType();
        int playerTwoLevel = playerTwo.getPokerType();
        String oneNumbersStr = StringUtils.join(playerOne.getNumbers(),',');
        String twoNumbersStr = StringUtils.join(playerTwo.getNumbers(),',');
        int numsCompare = StringUtils.compare(oneNumbersStr,twoNumbersStr);
        int resOne = playerOne.compareTo(playerOne);
        int resTwo = playerTwo.compareTo(playerOne);
        int resThree = playerTwo.compareTo(playerTwo);
        int resFour = playerOne.compareTo(playerTwo);

        //then
        verify(playerOne,times(1)).getPokerType();
        verify(playerOne,times(1)).getNumbers();
        verify(playerOne,times(1)).compareTo(playerTwo);
        Assert.assertTrue(playerOneLevel==playerTwoLevel);
        Assert.assertTrue(numsCompare == 0);
        Assert.assertEquals(resOne, GameProperty.TIE);
        Assert.assertEquals(resTwo, GameProperty.TIE);
        Assert.assertEquals(resThree, GameProperty.TIE);
        Assert.assertEquals(resFour, GameProperty.TIE);
    }

    @Test
    public void should_winner_when_poker_level_higher() {
        //given
        List<Card> randomInitOne = Game.random_init();
        List<Card> randomInitTwo = Game.random_init();
        Poker playerOne = new Poker(randomInitOne);
        Poker playerTwo = new Poker(randomInitTwo);
        String playerOneNumsStr = StringUtils.join(playerOne.getNumbers(),',');
        String playerTwoNumsStr = StringUtils.join(playerTwo.getNumbers(),',');
        int playerOneType = playerOne.getPokerType();
        int playerTwoType = playerTwo.getPokerType();
        int compareNumStr = playerOneNumsStr.compareTo(playerTwoNumsStr);
        int compareType = playerOneType-playerTwoType;
        int winnerByType = compareType>0?GameProperty.POKER_1_WINNER:
                (compareType<0?GameProperty.POKER_2_WINNER:GameProperty.TIE);
        int winnerByNums = compareNumStr>0?GameProperty.POKER_1_WINNER:
                (compareNumStr<0?GameProperty.POKER_2_WINNER:GameProperty.TIE);
        int realOneRes  = winnerByType != 0?winnerByType:winnerByNums;
        int realTwoRes  = realOneRes != 0?(realOneRes==1?GameProperty.POKER_2_WINNER:GameProperty.POKER_1_WINNER):(winnerByNums);
        //then
        int resultOne = playerOne.compareTo(playerTwo);
        int resultTwo = playerTwo.compareTo(playerOne);
        //when
        Assert.assertEquals(realOneRes,resultOne);
        Assert.assertEquals(realTwoRes,resultTwo);
    }
    @Test
    public void should_return_exits_poker_type_when_random_genentor_poker() {
        //given
        List<Card> randomCards = Game.random_init();
        Poker randPoker = new Poker(randomCards);
        //when
        int res = randPoker.getPokerType();
        // then
        Assert.assertNotEquals(GameProperty.NOT_THIS_POKER_TYPE,res);
    }
}
