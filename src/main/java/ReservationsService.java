/**
 *
 */
public class ReservationsService {
    Integer rowReserved;

    /**
     * @param theater
     * @param numberOfSeats
     * @param customerName
     * @param wheelchairAccessible
     * @return
     */
    public boolean reserveSeat(Theater theater, int numberOfSeats, String customerName, Boolean wheelchairAccessible) {
        if (numberOfSeats <= 0) throw new IllegalArgumentException("Number of seats to be reserved cannot be negative or 0!");
        if (customerName.length() == 0) throw new IllegalArgumentException("Customer name cannot be empty!");

        if (wheelchairAccessible) {
           for (Row eachRow : theater) {
               if (eachRow.isWheelchairAccessible() && eachRow.reserveRow(numberOfSeats, customerName)) {
                   rowReserved = eachRow.getRowNum();
                   return true;
               }
           }
        }
        else {
            for (Row eachRow : theater) {
                if (!eachRow.isWheelchairAccessible()) {
                    if (eachRow.reserveRow(numberOfSeats, customerName)) {
                        this.rowReserved = eachRow.getRowNum();
                        return true;
                    }
                }
            }
            for (Row eachRow : theater) {
                if (eachRow.reserveRow(numberOfSeats, customerName)) {
                    this.rowReserved = eachRow.getRowNum();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return
     */
    public Integer getRowReserved() {
        return rowReserved;
    }
}
