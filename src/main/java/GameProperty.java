import java.util.HashMap;
import java.util.Map;

interface GameProperty {
    Map<Integer, String> NUMBER_CONVERT = new HashMap<Integer, String>() {{
        put(10, "T");
        put(11, "J");
        put(12, "Q");
        put(13, "K");
        put(14, "A");
    }};
    Map<String, Integer> CONVERT_NUMBER = new HashMap<String, Integer>() {
        {
            put("2", 2);
            put("3", 3);
            put("4", 4);
            put("5", 5);
            put("6", 6);
            put("7", 7);
            put("8", 8);
            put("9", 9);
            put("T", 10);
            put("J", 11);
            put("Q", 12);
            put("K", 13);
            put("A", 14);
        }
    };
    String SUIT_STYLE = "CDHS";
    String POKER_1_WINNER = "poker1 winner";
    String POKER_2_WINNER = "poker2 winner";
    String TIE = "tie";
    int NOT_THIS_POKER_TYPE = -1;
    int HIGH_CARD = 0;
    int ONE_PAIR = 1;
    int TWO_PAIR = 2;
    int THREE_OF_A_KIND = 3;
    int STRAIGHT = 4;
    int FLUSH = 5;
    int FULL_HOUSE = 6;
    int FOUR_OF_A_KIND = 7;
    int STRAIGHT_FLUSH = 8;
}
