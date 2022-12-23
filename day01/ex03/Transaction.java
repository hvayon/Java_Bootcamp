package ex03;

import java.util.UUID;

public class Transaction {
	enum    Operation   {
		DEBIT,
		CREDIT
    };

	private final String	_identifier;
	private User			_recipient;
	private User			_sender;
	private Operation		_transferCategory;
	private Integer			_transferAmount;

	public Transaction() {
		this._identifier = UUID.randomUUID().toString();
	}

	public Transaction(User sender, User recipient, Operation transferCategory, Integer transferAmount) {
		this._identifier = UUID.randomUUID().toString();
		setRecipient(recipient);
		setSender(sender);
		setTransferCategory(transferCategory);
		setTransferAmount(transferAmount);
	}

	public void makeTransaction() {
        if (_sender.getBalance() >= this._transferAmount) {
            _sender.setBalance(_transferAmount * -1 + _sender.getBalance());
            _recipient.setBalance(_transferAmount + _recipient.getBalance());
        }
        else
            System.err.println("Can't send money. Not enough balance.");
    }

	public String getIdentifier() {
		return _identifier;
	}

	public User getRecipient() {
		return _recipient;
	}

	public User getSender() {
		return _sender;
	}

	public Operation getTransferCategory() {
		return _transferCategory;
	}

	public Integer getTransferAmount() {
		return _transferAmount;
	}

	public void setRecipient(User recipient) {
		this._recipient = recipient;
	}

	public void setSender(User sender) {
		this._sender = sender;
	}
	
	public void setTransferCategory(Operation transferCategory) {
		this._transferCategory = transferCategory;
	}

	public void setTransferAmount(Integer transferAmount) {
        this._transferAmount = transferAmount;
    }

	@Override
	public String toString() {
		return "Transaction{" +
				"_identifier='" + _identifier + '\'' +
				", _recipient=" + _recipient +
				", _sender=" + _sender +
				", _transferCategory=" + _transferCategory +
				", _transferAmount=" + _transferAmount +
				'}';
	}
}