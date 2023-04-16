import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ReservationSystem {
    public static void main(String[] args) {
        Theater theater = new Theater("NEU", 15, 10, new ArrayList<>(Arrays.asList(2, 10)));
        ReservationsService reservationsService = new ReservationsService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("What would you like to do?");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("done")) {
                System.out.println("Have a nice day!");
                break;
            } else if (command.startsWith("reserve")) {
                int numSeats = Integer.parseInt(command.split(" ")[1]);
                System.out.println("What's your name?");
                String customerName = scanner.nextLine();
                System.out.println("Do you need wheelchair accessible seats? (yes/no)");
                String wheelchairResponse = scanner.nextLine().trim().toLowerCase();
                boolean wheelchairAccessible = wheelchairResponse.equals("yes");
                reservationsService.reserveSeats(theater, numSeats, customerName, wheelchairAccessible);
            } else if (command.equals("show")) {
                reservationsService.show(theater);
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }

        scanner.close();
    }
}
