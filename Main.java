import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM(new User("user123", "1234"), new TransactionHistory());
        atm.run();
    }
}
