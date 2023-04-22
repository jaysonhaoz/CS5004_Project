package hw9;

/**
 * The ReservationsService class provides methods for reserving seats in a Theater.
 */

public class ReservationsService {

  private Integer rowReserved;

  /**
   * Reserves the specified number of seats in the given Theater object for the provided customer
   * name and according to their wheelchair accessibility requirements.
   *
   * @param theater              the Theater object in which seats will be reserved
   * @param numberOfSeats        the number of seats to reserve
   * @param customerName         the name of the customer reserving the seats
   * @param wheelchairAccessible whether the reserved seats need to be wheelchair accessible
   * @return true if the reservation is successful, false otherwise
   * @throws IllegalArgumentException if the number of seats is negative or 0, or the customer name
   *                                  is empty
   */
  public boolean reserveSeat(Theater theater, int numberOfSeats, String customerName,
      Boolean wheelchairAccessible) {
      if (numberOfSeats <= 0) {
          throw new IllegalArgumentException(
              "Number of seats to be reserved cannot be negative or 0!");
      }

    if (wheelchairAccessible) {
      for (Row eachRow : theater) {
        if (eachRow.isWheelchairAccessible() && eachRow.reserveRow(numberOfSeats, customerName)) {
          rowReserved = eachRow.getRowNum();
          return true;
        }
      }
    } else {
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
   * Returns the row number of the last reserved row in the Theater.
   *
   * @return the row number of the last reserved row, or null if no reservation has been made
   */
  public Integer getRowReserved() {
    return rowReserved;
  }
}
