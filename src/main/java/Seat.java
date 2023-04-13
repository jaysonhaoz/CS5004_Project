public class Seat {
    private String name;
    private String reservedFor;

    public Seat(String name, String reservedFor) {
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
