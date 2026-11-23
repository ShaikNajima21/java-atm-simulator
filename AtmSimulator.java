
import java.util.Scanner;

public class AtmSimulator {

    private static int readNumber(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static boolean login(Scanner scanner, int pin) {

        for (int i = 1; i <= 3; i++) {
            int enteredPin = readNumber(scanner, "Enter PIN: ");

            if (enteredPin == pin) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Wrong PIN.");
            }
        }

        return false;
    }

    public static int deposit(int balance, int amount) {

        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
        } else {
            balance = balance + amount;
            System.out.println("Deposit successful.");
            System.out.println("New balance: Rs " + balance);
        }

        return balance;
    }

    public static int withdraw(int balance, int amount) {

        if (amount <= 0 || amount % 100 != 0) {
            System.out.println("Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance = balance - amount;
            System.out.println("Withdrawal successful.");
            System.out.println("New balance: Rs " + balance);
        }

        return balance;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int pin = 1234;
        int balance = 5000;

        if (!login(scanner, pin)) {
            System.out.println("Card blocked.");
            scanner.close();
            return;
        }

        boolean running = true;

        while (running) {

            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");

            int choice = readNumber(scanner, "Enter your choice: ");

            switch (choice) {

                case 1:
                    System.out.println("Current balance: Rs " + balance);
                    break;

                case 2:
                    int depositAmount = readNumber(
                        scanner, "Enter deposit amount: "
                    );

                    balance = deposit(balance, depositAmount);
                    break;

                case 3:
                    int withdrawAmount = readNumber(
                        scanner, "Enter withdrawal amount: "
                    );

                    balance = withdraw(balance, withdrawAmount);
                    break;

                case 4:
                    running = false;
                    System.out.println("Thank you for using ATM!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
