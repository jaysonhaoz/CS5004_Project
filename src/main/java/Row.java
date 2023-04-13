import java.util.ArrayList;

public class Row extends ArrayList<Seat> {
    private int num;
    private boolean isWheelchairAccessible;

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *                                  is negative
     */
    public Row(int initialCapacity, int num, boolean isWheelchairAccessible) {
        super(initialCapacity);
        this.num = num;
        this.isWheelchairAccessible = isWheelchairAccessible;
        for (int i = 0; i < initialCapacity; i++) {
            Seat seat = new Seat(Character.toString((char) ('A' + i)), null);
            this.add(seat);
        }
    }

    public int getNum() {
        return num;
    }

    public boolean isWheelchairAccessible() {
        return isWheelchairAccessible;
    }
}
