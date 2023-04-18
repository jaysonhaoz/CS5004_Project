public class Seat {
    private static final char A = 'A';
    private static final char Z = 'Z';
    private String name;
    private String reservedFor;

    public Seat(String name, String reservedFor) {
        if (name.length() != 1 || name.charAt(0) < A || name.charAt(0) > Z)
            throw new IllegalArgumentException("Illegal Seat name!");
        this.name = name;
        this.reservedFor = null;
    }

    public String getName() {
        return name;
    }

    public String getReservedFor() {
        return reservedFor;
    }

    public void setReservedFor(String reservedFor) {
        if (this.reservedFor != null) throw new IllegalCallerException("Cannot set already reserved seat!");
        this.reservedFor = reservedFor;
    }

    public boolean isReserved() {
        return reservedFor != null;
    }

    @Override
    public String toString() {
        return isReserved() ? "X" : "_";
    }
}
