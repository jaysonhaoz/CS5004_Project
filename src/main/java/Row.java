import java.util.ArrayList;

/**
 *
 */
public class Row extends ArrayList<Seat> {
    private static final char A = 'A';
    private static final int MAX = 26;
    private int rowNum;
    private boolean isWheelchairAccessible;

    /**
     * @param seatNum
     * @param rowNum
     * @param isWheelchairAccessible
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
        if (rowNum <= 0) throw new IllegalArgumentException("row number cannot be negative or 0!");
        this.rowNum = rowNum;
        this.isWheelchairAccessible = isWheelchairAccessible;
        this.addAll(seats);
    }

    /**
     * @return
     */
    public int getRowNum() {
        return rowNum;
    }

    /**
     * @return
     */
    public boolean isWheelchairAccessible() {
        return isWheelchairAccessible;
    }

    /**
     * @return
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
     * @param num
     * @param customerName
     * @return
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
     * @return
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
