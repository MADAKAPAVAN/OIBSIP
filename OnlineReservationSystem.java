import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineReservationSystem {
    private static Map<String, String> userCredentials = new HashMap<>();
    private static Map<String, ReservationDetails> reservations = new HashMap<>();
    private static int reservationCounter = 1000;

    public static void main(String[] args) {
        initializeUsers();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the Online Reservation System!");
            System.out.println("1. Login");
            System.out.println("2. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    System.out.println("Thank you for using the Online Reservation System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void initializeUsers() {
        userCredentials.put("user1", "password1");
        userCredentials.put("user2", "password2");
        // Add more user credentials as needed
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            System.out.println("Login successful!");
            showReservationMenu(scanner, username);
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void showReservationMenu(Scanner scanner, String username) {
        while (true) {
            System.out.println("\nReservation Menu:");
            System.out.println("1. Make a Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    makeReservation(scanner, username);
                    break;
                case 2:
                    cancelReservation(scanner, username);
                    break;
                case 3:
                    System.out.println("Logout successful. Returning to the main menu.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void makeReservation(Scanner scanner, String username) {
        reservationCounter++;
        String pnr = "PNR" + reservationCounter;

        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();
        // Assume train name is fetched from a database based on the train number
        String trainName = getTrainName(trainNumber);

        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();

        System.out.print("Enter date of journey: ");
        String dateOfJourney = scanner.nextLine();

        System.out.print("Enter departure place: ");
        String departurePlace = scanner.nextLine();

        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        ReservationDetails reservationDetails = new ReservationDetails(trainNumber, trainName, classType, dateOfJourney, departurePlace, destination);
        reservations.put(pnr, reservationDetails);

        System.out.println("Reservation successful! Your PNR is: " + pnr);
    }

    private static void cancelReservation(Scanner scanner, String username) {
        System.out.print("Enter your PNR number: ");
        String pnrToCancel = scanner.nextLine();

        if (reservations.containsKey(pnrToCancel)) {
            ReservationDetails canceledReservation = reservations.get(pnrToCancel);
            System.out.println("Reservation Details:");
            System.out.println(canceledReservation);

            System.out.print("Press 'OK' to confirm cancellation: ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("OK")) {
                reservations.remove(pnrToCancel);
                System.out.println("Cancellation successful!");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("Invalid PNR number. Please try again.");
        }
    }

    private static String getTrainName(String trainNumber) {
        // Logic to fetch train name from a database based on the train number
        // For demonstration purposes, return a dummy train name
        return "Sample Train";
    }
}

class ReservationDetails {
    private String trainNumber;
    private String trainName;
    private String classType;
    private String dateOfJourney;
    private String departurePlace;
    private String destination;

    public ReservationDetails(String trainNumber, String trainName, String classType, String dateOfJourney, String departurePlace, String destination) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.departurePlace = departurePlace;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Train Number: " + trainNumber +
                "\nTrain Name: " + trainName +
                "\nClass Type: " + classType +
                "\nDate of Journey: " + dateOfJourney +
                "\nDeparture Place: " + departurePlace +
                "\nDestination: " + destination;
    }
}
