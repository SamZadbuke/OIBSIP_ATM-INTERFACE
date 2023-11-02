import java.util.Scanner;

public class ATM {
    private TransactionHistory transactionHistory;
    private User user;
    private Account account;
    private Scanner scanner;

    public ATM(User user, TransactionHistory transactionHistory) {
        this.user = user;
        this.account = new Account(user.getUserId(), user.getUserPin(), 0.0); // Initial balance set to $1000
        this.transactionHistory = transactionHistory;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    performTransactionHistory();
                    break;
                case 2:
                    performWithdrawal();
                    break;
                case 3:
                    performDeposit();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    quit();
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("1. View Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
    }

    private void performTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }

    private void performWithdrawal() {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            transactionHistory.addTransaction("Withdrawal: -$" + amount);
            System.out.println("Withdrawal successful. Remaining balance: $" + account.getBalance());
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    private void performDeposit() {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
            transactionHistory.addTransaction("Deposit: +$" + amount);
            System.out.println("Deposit successful. New balance: $" + account.getBalance());
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private void performTransfer() {
        System.out.print("Enter recipient's user ID: ");
        String recipientId = scanner.next();

        if (isValidRecipient(recipientId)) {
            System.out.print("Enter the amount to transfer: ");
            double amount = scanner.nextDouble();

            if (amount > 0 && amount <= account.getBalance()) {
                if (performActualTransfer(recipientId, amount)) {
                    System.out.println("Transfer successful. Remaining balance: $" + account.getBalance());
                } else {
                    System.out.println("Transfer failed. Please try again.");
                }
            } else {
                System.out.println("Invalid amount or insufficient funds.");
            }
        } else {
            System.out.println("Invalid recipient ID.");
        }
    }

    private boolean isValidRecipient(String recipientId) {
        return !recipientId.isEmpty(); // Placeholder logic for recipient validation
    }

    private boolean performActualTransfer(String recipientId, double amount) {
        if (amount > 0 && amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            transactionHistory.addTransaction("Transfer to " + recipientId + ": -$" + amount);
            return true;
        } else {
            return false; // Transfer failed due to insufficient funds
        }
    }

    private void quit() {
        System.out.println("Thank you for using the ATM. Goodbye!");
        scanner.close();
    }
}
