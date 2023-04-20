public class ReservationsService {
    public static boolean reserveSeat(Theater theater, int numberOfSeats, String customerName, Boolean wheelchairAccessible) {
        if (numberOfSeats <= 0) throw new IllegalArgumentException("Number of seats to be reserved cannot be negative or 0!");
        if (customerName.length() == 0) throw new IllegalArgumentException("Customer name cannot be empty!");

        if (wheelchairAccessible) {
           for (Row eachRow : theater) {
               if (eachRow.isWheelchairAccessible() && eachRow.reserveRow(numberOfSeats, customerName))
                   return true;
           }
        }
        else {
            for (Row eachRow : theater) {
                if (!eachRow.isWheelchairAccessible()) {
                    if (eachRow.reserveRow(numberOfSeats, customerName))
                        return true;
                }
            }
            for (Row eachRow : theater) {
                if (eachRow.reserveRow(numberOfSeats, customerName))
                    return true;
            }
        }
        return false;
    }

    public void show(Theater theater) {
        for (Row row : theater.getAllRowsInTheater()) {
            System.out.println(row.toString());
        }
    }
}
