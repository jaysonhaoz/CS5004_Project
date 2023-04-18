/**
 * The Seat class represents a single seat in a theater.
 * It stores information about the seat's name and reservation status.
 * The name of the seat.
 * The name of the person the seat is reserved for, or null if the seat is not reserved.
 */
public class Seat {
    private String name;
    private String reservedFor;

    /**
     * Constructs a Seat object with the given name.
     * @param name the name of the seat.
     */
    public Seat(String name) {
        this.name = name;
        this.reservedFor = null;
    }

    /**
     * Returns the name of the seat.
     * @return  the name of the seat.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the person the seat is reserved for, or null if the seat is not reserved.
     * @return the name of the person the seat is reserved for, or null if not reserved.
     *
     */
    public String getReservedFor() {
        return reservedFor;
    }

    /**
     * Sets the name of the person the seat is reserved for.
     * @param reservedFor reservedFor the name of the person the seat is reserved for.
     */
    public void setReservedFor(String reservedFor) {
<<<<<<< HEAD
        if (this.reservedFor != null) throw new IllegalCallerException("Cannot set already reserved seat!");
=======
>>>>>>> d595e92232fb4165abc36d043a88674a19927a4b
        this.reservedFor = reservedFor;
    }

    /**
     * Checks if the seat is reserved.
     * @return true if the seat is reserved, false otherwise
     */
    public boolean isReserved() {
        return reservedFor != null;
    }

    @Override
    public String toString() {
        return isReserved() ? "X" : "_";
    }
}
