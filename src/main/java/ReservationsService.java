public class ReservationsService {
    public void reserveSeats(Theater theater, int numberOfSeats, String customerName, boolean wheelchairAccessible) {
        Row bestRow = null;

        for (Row row : theater.getRows()) {
            if (row.isWheelchairAccessible() != wheelchairAccessible) {
                continue;
            }

            int availableSeatsInRow = 0;
            for (Seat seat : row) {
                if (seat.isReserved()) {
                    availableSeatsInRow++;
                } else {
                    availableSeatsInRow = 0;
                }

                if (availableSeatsInRow == numberOfSeats) {
                    bestRow = row;
                    break;
                }
            }

            if (bestRow != null) {
                break;
            }
        }

        if (bestRow == null) {
            System.out.println("Sorry, we don't have that many seats together for you.");
            return;
        }

        int reservedSeats = 0;
        for (Seat seat : bestRow) {
            if (seat.isReserved() && reservedSeats < numberOfSeats) {
                seat.setReservedFor(customerName);
                reservedSeats++;
            }
        }

        System.out.printf("I've reserved %d seats for you at the %s in row %d, %s.%n", numberOfSeats, theater.getName(), bestRow.getNum(), customerName);
    }

    public void show(Theater theater) {
        for (Row row : theater.getRows()) {
            System.out.println(row.toString());
        }
    }
}
