import java.util.ArrayList;

public class Row extends ArrayList<Seat> {

    private int rowNum;
    private boolean isWheelchairAccessible;

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity is negative
     */
    public Row(int initialCapacity, int rowNum, boolean isWheelchairAccessible,
        ArrayList<Seat> seats) {
        super(initialCapacity);
        if (seats.isEmpty())
            throw new IllegalArgumentException("Seats in a row cannot be empty!");
        if (this.size() != seats.size())
            throw new IllegalArgumentException(
                "The num of seats of the row differ from the given seats!");
        this.rowNum = rowNum;
        this.isWheelchairAccessible = isWheelchairAccessible;
        for (int i = 0; i < initialCapacity; i++) {
            Seat seat = new Seat(Character.toString((char) ('A' + i)));
            this.add(seat);
        }
    }

    public int getRowNum() {
        return rowNum;
    }

    public boolean isWheelchairAccessible() {
        return isWheelchairAccessible;
    }

    private int seatAvailable() {
        int res = this.size();
        for (Seat seat : this) {
            if (seat.getReservedFor() != null)
                --res;
        }
        return res;
    }

    protected void reserveRow(int num, String customerName) {
        if (num > this.seatAvailable()) {
            System.out.println("Sorry, we don’t have that many seats together for you.");
            return;
        }
        if (customerName.length() == 0)
            throw new IllegalArgumentException("Customer's name cannot be empty!");
        for (Seat seat : this) {
            if (seat.getReservedFor() == null)
                seat.setReservedFor(customerName);
        }
    }

    @Override
    public String toString() {
        String prefix = (isWheelchairAccessible ? "=" : "_") + " ";
        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append(rowNum).append(" ");
        for (Seat seat : this) {
            sb.append(seat.toString()).append(" ");
        }
        return sb.toString();
    }
}
