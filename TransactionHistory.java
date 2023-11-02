import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
    private List<String> history;

    public TransactionHistory() {
        this.history = new ArrayList<>();
    }

    public void addTransaction(String transaction) {
        history.add(transaction);
    }

    public List<String> getTransactionHistory() {
        return history;
    }
}
