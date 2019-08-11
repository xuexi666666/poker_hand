public class Card implements Comparable<Card> {
    private int number;
    private String suit;

    public Card(String value, String s) {
        number = GameProperty.CONVERT_NUMBER.get(value);
        suit = s;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String value() {
        String result = "";
        if (number >= 2 && number <= 9) {
            result = Integer.toString(number);
        } else if (number >= 10 && number <= 14) {
            result = GameProperty.NUMBER_CONVERT.get(number);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return getNumber() == card.getNumber();
    }

    @Override
    public int compareTo(Card o) {
        return getNumber() > o.getNumber() ? 1 : (getNumber() == o.getNumber() ? 0 : -1);
    }

    @Override
    public String toString() {
        return number+suit;
    }
}
