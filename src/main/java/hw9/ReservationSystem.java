package hw9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The ReservationSystem class represents a simple text-based user interface for managing
 * seat reservations in a theater.
 */
public class ReservationSystem {

    /**
     * The main method serves as the entry point of the ReservationSystem application.
     * It initializes a Theater object, handles user input, and provides feedback.
     *
     * @param args command line arguments (not used in this application)
     */
  public static void main(String[] args) {
    Theater theater = new Theater("NEU", 15, 10, new ArrayList<>(Arrays.asList(2, 10)));
    ReservationsService rs = new ReservationsService();
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("What would you like to do?");
      String command = scanner.nextLine().trim().toLowerCase();
      if (command.equals("done")) {
        System.out.println("Have a nice day!");
        break;
      } else if (command.split(" ").length == 2 && command.split(" ")[0].equals("reserve")) {
        String[] cmd = command.split(" ");
        try {
            int numSeats = Integer.parseInt(cmd[1]);
            if (numSeats > theater.get(0).size()) {
                System.out.println("Sorry, we don’t have that many seats together for you.");
            } else {
                System.out.println("What's your name?");
                String customerName = scanner.nextLine();
                System.out.println("Do you need wheelchair accessible seats? (yes/no)");
                String wheelchairResponse = scanner.nextLine().trim().toLowerCase();
                boolean wheelchairAccessible = wheelchairResponse.equals("yes");
                boolean res = rs.reserveSeat(theater, numSeats, customerName, wheelchairAccessible);
                if (res) {
                    System.out.println(
                        "I’ve reserved " + numSeats + " seats for you at the " +
                            theater.getTheaterName() + " in row " + rs.getRowReserved()
                            + ", " + customerName + ".");
                } else {System.out.println("Sorry, we don’t have that many seats together for you.");}
            }
        } catch (NumberFormatException e) {System.out.println("Invalid command. Please try again.");}
      } else if (command.equals("show")) {
        Viewer.showSeats(theater);
      } else {System.out.println("Invalid command. Please try again.");}
    }
    scanner.close();
  }
}
