
import java.util.Scanner;

public class AtmSimulator {

    private static int readNumber(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please type digits only.");
            }
        }
    }

    public static boolean login(Scanner scanner, int pin) {

        for (int i = 1; i <= 3; i++) {
            int enteredPin = readNumber(scanner, "Enter PIN: ");

            if (enteredPin == pin) {
                System.out.println("Login successful.");
                return true;
            }

            System.out.println("Wrong PIN. Attempts left: " + (3 - i));
        }

        System.out.println("Card blocked. Please contact your bank.");
        return false;
    }

    public static int deposit(int balance, int amount) {

        if (amount <= 0) {
            System.out.println("Amount must be more than 0.");
        } else {
            balance += amount;
            System.out.println("Deposited Rs " + amount
                    + ". New balance: Rs " + balance);
        }

        return balance;
    }

    public static int withdraw(int balance, int amount) {

        if (amount <= 0) {
            System.out.println("Amount must be more than 0.");
        } else if (amount % 100 != 0) {
            System.out.println("Amount must be a multiple of 100.");
        } else if (amount > balance) {
            System.out.println("Not enough balance.");
        } else {
            balance -= amount;
            System.out.println("Please take your cash: Rs " + amount
                    + ". New balance: Rs " + balance);
        }

        return balance;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int pin = 4321;
        int balance = 5000;

        if (!login(scanner, pin)) {
            scanner.close();
            return;
        }

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("1. Balance  2. Deposit  3. Withdraw  4. Exit");

            int choice = readNumber(scanner, "Choose an option: ");

            switch (choice) {

                case 1:
                    System.out.println("Balance: Rs " + balance);
                    break;

                case 2:
                    int depositAmount = readNumber(
                            scanner, "Amount to deposit: ");

                    balance = deposit(balance, depositAmount);
                    break;

                case 3:
                    int withdrawAmount = readNumber(
                            scanner, "Amount to withdraw: ");

                    balance = withdraw(balance, withdrawAmount);
                    break;

                case 4:
                    running = false;
                    System.out.println("Thank you for banking with us.");
                    break;

                default:
                    System.out.println(
                            "Please choose 1, 2, 3 or 4.");
            }
        }

        scanner.close();
    }
}
