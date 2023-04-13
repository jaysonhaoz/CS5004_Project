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
}
