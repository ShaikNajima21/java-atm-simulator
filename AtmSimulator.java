
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

    private static boolean login(Scanner scanner, int pin) {

        for (int i = 1; i <= 3; i++) {
            int enteredPin = readNumber(scanner, "Enter PIN: ");

            if (enteredPin == pin) {
                System.out.println("Login successful!");
                return true;
            }

            System.out.println("Wrong PIN.");
        }

        return false;
    }

    private static int deposit(int balance, int amount) {

        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
        } else {
            balance += amount;
            System.out.println("Deposit successful.");
            System.out.println("New balance: Rs " + balance);
        }

        return balance;
    }

    private static int withdraw(int balance, int amount) {

        if (amount <= 0 || amount % 100 != 0) {
            System.out.println("Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful.");
            System.out.println("New balance: Rs " + balance);
        }

        return balance;
    }

    private static void addTransaction(
            String[] transactions,
            int[] count,
            String transaction) {

        if (count[0] < 5) {
            transactions[count[0]] = transaction;
            count[0]++;
        } else {
            for (int i = 0; i < 4; i++) {
                transactions[i] = transactions[i + 1];
            }

            transactions[4] = transaction;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int pin = 1234;
        int balance = 5000;
        int totalWithdrawn = 0;

        String[] transactions = new String[5];
        int[] transactionCount = {0};

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
            System.out.println("5. Change PIN");
            System.out.println("6. Mini Statement");

            int choice = readNumber(scanner, "Enter your choice: ");

            switch (choice) {

                case 1:
                    System.out.println("Current balance: Rs " + balance);
                    break;

                case 2:
                    int depositAmount = readNumber(
                            scanner, "Enter deposit amount: ");

                    if (depositAmount <= 0) {
                        System.out.println("Invalid deposit amount.");
                    } else {
                        balance = deposit(balance, depositAmount);

                        addTransaction(
                                transactions,
                                transactionCount,
                                "Deposit Rs " + depositAmount
                        );
                    }
                    break;

                case 3:
                    int withdrawAmount = readNumber(
                            scanner, "Enter withdrawal amount: ");

                    if (withdrawAmount <= 0 || withdrawAmount % 100 != 0) {
                        System.out.println("Invalid withdrawal amount.");
                    } else if (totalWithdrawn + withdrawAmount > 10000) {
                        System.out.println("Daily limit reached.");
                    } else if (withdrawAmount > balance) {
                        System.out.println("Insufficient balance.");
                    } else {
                        balance = withdraw(balance, withdrawAmount);
                        totalWithdrawn += withdrawAmount;

                        addTransaction(
                                transactions,
                                transactionCount,
                                "Withdraw Rs " + withdrawAmount
                        );
                    }
                    break;

                case 4:
                    running = false;
                    System.out.println("Thank you for using ATM!");
                    break;

                case 5:
                    int oldPin = readNumber(scanner, "Old PIN: ");

                    if (oldPin != pin) {
                        System.out.println("Wrong PIN.");
                    } else {
                        int newPin = readNumber(
                                scanner, "New PIN (4 digits): ");

                        if (newPin < 1000 || newPin > 9999) {
                            System.out.println("PIN must have 4 digits.");
                        } else {
                            pin = newPin;
                            System.out.println("PIN changed.");
                        }
                    }
                    break;

                case 6:
                    System.out.println("Last transactions:");

                    if (transactionCount[0] == 0) {
                        System.out.println("No transactions yet.");
                    } else {
                        for (int i = 0; i < transactionCount[0]; i++) {
                            System.out.println(transactions[i]);
                        }
                    }
                    break;

                default:
                    System.out.println(
                            "Please choose 1, 2, 3, 4, 5 or 6.");
            }
        }

        scanner.close();
    }
}
