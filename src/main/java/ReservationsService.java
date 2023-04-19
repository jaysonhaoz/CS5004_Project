public class ReservationsService {
    public void reserveSeats(Theater theater, int numberOfSeats, String customerName, boolean wheelchairAccessible) {
        Row bestRow = null;
        int startIndex = -1;

        for (Row row : theater.getAllRowsInTheater()) {
            if (row.isWheelchairAccessible() != wheelchairAccessible) {
                continue;
            }

            int availableSeatsInRow = 0;
            for (int i = 0; i < row.size(); i++) {
                Seat seat = row.get(i);
                if (!seat.isReserved()) {
                    availableSeatsInRow++;
                } else {
                    availableSeatsInRow = 0;
                }

                if (availableSeatsInRow == numberOfSeats) {
                    bestRow = row;
                    startIndex = i - numberOfSeats + 1;
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

        for (int i = startIndex; i < startIndex + numberOfSeats; i++) {
            bestRow.get(i).setReservedFor(customerName);
        }

        System.out.printf("I've reserved %d seats for you at the %s in row %d, %s.%n", numberOfSeats, theater.getTheaterName(), bestRow.getRowNum(), customerName);
    }

    public void show(Theater theater) {
        for (Row row : theater.getAllRowsInTheater()) {
            System.out.println(row.toString());
        }
    }
}
