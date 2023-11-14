import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ATM {
    private static Map<String, String> userDatabase = new HashMap<>();
    private static Map<String, Double> accountBalances = new HashMap<>();
    private static String currentUser;

    public static void main(String[] args) {
        initializeUsers();
        showWelcomeScreen();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    displayTransactionsHistory();
                    break;
                case 2:
                    performWithdrawal(scanner);
                    break;
                case 3:
                    performDeposit(scanner);
                    break;
                case 4:
                    performTransfer(scanner);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void initializeUsers() {
        userDatabase.put("user123", "1234");
        userDatabase.put("user456", "5678");

        accountBalances.put("user123", 1000.0);
        accountBalances.put("user456", 1500.0);
    }

    private static void showWelcomeScreen() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (authenticateUser(userId, pin)) {
            currentUser = userId;
            System.out.println("Authentication successful. Welcome, " + userId + "!");
        } else {
            System.out.println("Authentication failed. Exiting...");
            System.exit(0);
        }
    }

    private static boolean authenticateUser(String userId, String pin) {
        return userDatabase.containsKey(userId) && userDatabase.get(userId).equals(pin);
    }

    private static void displayTransactionsHistory() {
        System.out.println("Transaction History for " + currentUser + ":");
        // Implement logic to display transaction history (if available)
    }

    private static void performWithdrawal(Scanner scanner) {
        System.out.print("Enter withdrawal amount: ");
        double withdrawalAmount = scanner.nextDouble();

        if (withdrawalAmount > 0 && withdrawalAmount <= accountBalances.get(currentUser)) {
            accountBalances.put(currentUser, accountBalances.get(currentUser) - withdrawalAmount);
            System.out.println("Withdrawal successful. Remaining balance: $" + accountBalances.get(currentUser));
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    private static void performDeposit(Scanner scanner) {
        System.out.print("Enter deposit amount: ");
        double depositAmount = scanner.nextDouble();

        if (depositAmount > 0) {
            accountBalances.put(currentUser, accountBalances.get(currentUser) + depositAmount);
            System.out.println("Deposit successful. Updated balance: $" + accountBalances.get(currentUser));
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    private static void performTransfer(Scanner scanner) {
        System.out.print("Enter recipient's User ID: ");
        String recipientUserId = scanner.nextLine();

        if (userDatabase.containsKey(recipientUserId) && !recipientUserId.equals(currentUser)) {
            System.out.print("Enter transfer amount: ");
            double transferAmount = scanner.nextDouble();

            if (transferAmount > 0 && transferAmount <= accountBalances.get(currentUser)) {
                accountBalances.put(currentUser, accountBalances.get(currentUser) - transferAmount);
                accountBalances.put(recipientUserId, accountBalances.get(recipientUserId) + transferAmount);
                System.out.println("Transfer successful. Remaining balance: $" + accountBalances.get(currentUser));
            } else {
                System.out.println("Invalid transfer amount or insufficient funds.");
            }
        } else {
            System.out.println("Invalid recipient User ID.");
        }
    }
}