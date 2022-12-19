package ex00;
import java.util.UUID;

public class Transaction {
	enum    Operation   {
        debit,
        credit
    };

	private final UUID	_identifier;
	private User		_recipient;
	private User		_sender;
	private Operation	_transferCategory;
	private Integer		_transferAmount;

	public Transaction(User recipient, User sender, Integer transferAmount) {
		this._recipient = recipient;
		this._sender = sender;
		_identifier = UUID.randomUUID();

		if (transferAmount < 0) {
            _transferCategory = Operation.credit;
        } else {
            _transferCategory = Operation.debit;
        }
		this._transferAmount = transferAmount;

		if (sender.getBalance() < _transferAmount && _transferCategory == Operation.debit ||
		recipient.getBalance() < -1 * _transferAmount && _transferCategory == Operation.credit) {
			System.err.println("Can't send money. Not enough balance.");
		} else {
			makeTransaction();
		}

	}

    public void makeTransaction() {
        if (sender.getBalance() >= this._transferAmount) {
            sender.setBalance(_transferAmount * -1 + sender.getBalance());
            recipient.setBalance(_transferAmount + recipient.getBalance());
        }
        else
            System.err.println("Can't send money. Not enough balance.");
    }

	getBalance() /*дописать геттеры, проверить подсказки */
	
}