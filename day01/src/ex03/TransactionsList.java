package ex03;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);

    void removeTransactionById(String id);
    Transaction[] toArray();
    void printList();
}
