package ex00;

public class Program {
	public static void main(String[] args) {
		User first = new User("Andy", 100);
		System.out.println(first);
		first.setBalance(-143);
		System.out.println(first);

		User second = new User("Kate", 1234);
		System.out.println(second);

		Transaction transaction = new Transaction(
				first,
				second,
				Transaction.Operation.DEBIT,
				1000
		);

		transaction.makeTransaction();
		System.out.println(transaction);

		Transaction transactionCredit = new Transaction(
				second,
				first,
				Transaction.Operation.CREDIT,
				1000
		);
		transactionCredit.makeTransaction();
		System.out.println(transactionCredit);
	}
}