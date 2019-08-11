import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PokerTest {
    private ArrayList<Card> pairCards;
    private Poker onePair;

    @Before
    public void init(){
        //given
        pairCards = new ArrayList<>();
        pairCards.add(new Card("3", "C"));
        pairCards.add(new Card("3", "S"));
        pairCards.add(new Card("5", "H"));
        pairCards.add(new Card("6", "S"));
        pairCards.add(new Card("T", "D"));
        onePair = new Poker(pairCards);
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
    public void should_return_ONE_PAIR_win_when_give_a_pair_of_identical_cards(){
        //when
        int res = onePair.isOnePair();
        //then
        Assert.assertEquals(res,GameProperty.ONE_PAIR);
    }
}
