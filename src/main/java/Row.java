import java.util.ArrayList;

/**
 * The Row class represents a row of seats in a theater or auditorium.
 * It extends ArrayList to store and manage Seat objects.
 */
public class Row extends ArrayList<Seat> {
    private static final char A = 'A';
    private static final int MAX = 26;
    private int rowNum;
    private boolean isWheelchairAccessible;

    /**
     * Constructs a Row object with the specified number of seats, row number, and wheelchair accessibility.
     * The Row object is initialized with Seat objects in consecutive order.
     *
     * @param seatNum the number of seats in the row
     * @param rowNum  the row number
     * @param isWheelchairAccessible whether the row is wheelchair accessible
     * @throws IllegalArgumentException if the specified number of seats is 0, exceeds MAX value, or the row number is negative or 0
     */
    public Row(int seatNum, int rowNum, boolean isWheelchairAccessible) {
        super(seatNum);
        if (seatNum == 0) throw new IllegalArgumentException("Seats in a row cannot be empty!");
        if (seatNum > MAX) throw new IllegalArgumentException("Number of seat exceeds Max value!");
        if (rowNum <= 0) throw new IllegalArgumentException("row number cannot be negative or 0!");
        for (int i = 0; i < seatNum; i++) {
            Seat newSeat = new Seat(String.valueOf(i + A));
            newSeat.setWheelChairAccessible(isWheelchairAccessible);
            this.add(newSeat);
        }
        this.rowNum = rowNum;
        this.isWheelchairAccessible = isWheelchairAccessible;
    }

    /**
     * Constructs a Row object with the specified initial capacity, row number, wheelchair accessibility, and seats.
     *
     * @param initialCapacity the initial capacity of the list
     * @param rowNum the row number
     * @param isWheelchairAccessible whether the row is wheelchair accessible
     * @param seats the list of Seat objects
     * @throws IllegalArgumentException if the specified initial capacity is negative, the seats list is empty, the number of seats differs from the given seats, or the row number is negative or 0
     */
    public Row(int initialCapacity, int rowNum, boolean isWheelchairAccessible,
        ArrayList<Seat> seats) {
        super(initialCapacity);
        if (seats.isEmpty())
            throw new IllegalArgumentException("Seats in a row cannot be empty!");
        if (this.size() != seats.size())
            throw new IllegalArgumentException(
                "The num of seats of the row differ from the given seats!");
        if (rowNum <= 0) throw new IllegalArgumentException("row number cannot be negative or 0!");
        this.rowNum = rowNum;
        this.isWheelchairAccessible = isWheelchairAccessible;
        this.addAll(seats);
    }

    /**
     * Returns the row number of this Row object.
     *
     * @return the row number
     */
    public int getRowNum() {
        return rowNum;
    }

    /**
     * Returns whether this Row object is wheelchair accessible.
     *
     * @return true if the row is wheelchair accessible, false otherwise
     */
    public boolean isWheelchairAccessible() {
        return isWheelchairAccessible;
    }

    /**
     * Returns the number of available seats in this Row object.
     *
     * @return the number of available seats
     */
    private int seatAvailable() {
        int res = this.size();
        for (Seat seat : this) {
            if (seat.getReservedFor() != null)
                --res;
        }
        return res;
    }

    /**
     * Reserves the specified number of seats in this Row object for the given customer.
     *
     * @param num the number of seats to reserve
     * @param customerName the name of the customer reserving the seats
     * @return true if the reservation is successful, false otherwise
     * @throws IllegalArgumentException if the customer's name is empty
     */
    protected boolean reserveRow(int num, String customerName) {
        if (num > this.seatAvailable()) {
            return false;
        }
        if (customerName.length() == 0)
            throw new IllegalArgumentException("Customer's name cannot be empty!");
        int count = 0;
        for (Seat seat : this) {
            if (count < num) {
                if (seat.getReservedFor() == null){
                    seat.setReservedFor(customerName);
                    ++count;
                }
            }
        }
        return true;
    }

    /**
     * Returns a string representation of this Row object.
     *
     * @return a string representation of the row
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(rowNum).append(this.rowNum < 10 ? "  " : " ");
        for (Seat seat : this) {
            sb.append(seat.toString()).append(" ");
        }
        return sb.toString();
    }
}
