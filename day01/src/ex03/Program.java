package ex03;

public class Program {
    public static void main(String[] args) {

        TransactionsList list = new TransactionsLinkedList();

        User first = new User("Andy", 100);
        System.out.println(first);

        User second = new User("Kate", 1234);
        System.out.println(second);

        Transaction transaction1 = new Transaction(
                first,
                second,
                Transaction.Operation.DEBIT,
                1000
        );
        Transaction transaction2 = new Transaction(
                second,
                first,
                Transaction.Operation.CREDIT,
                1000
        );

        list.addTransaction(transaction1);
        System.out.println("List:");
        list.printList();

        list.addTransaction(transaction2);
        System.out.println("List:");
        list.printList();

        System.out.println("Removing transaction 1");
        list.removeTransactionById(transaction1.getIdentifier());
        System.out.println("List after removal:");
        list.printList();

    }
}
