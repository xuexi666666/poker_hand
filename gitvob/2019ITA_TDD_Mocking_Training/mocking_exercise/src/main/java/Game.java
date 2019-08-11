public class Game {
    public static String getWinnerByOneCard(Poker player1, Poker player2){
        int res = player1.getCards().get(0).compareTo(player2.getCards().get(0));
        return res == 0?GameProperty.TIE:(res == 1? GameProperty.POKER_1_WINNER: GameProperty.POKER_2_WINNER);
    }
}
