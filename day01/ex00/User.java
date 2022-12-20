package ex00;

public class User {

    private Integer _identifier;
    private String _name;
    private Integer _balance = 0;

    public User(String name, Integer balance) {
        setName(name);
        setBalance(balance);
        setIdentifier(0);
    }

    public Integer getIdentifier() {
		return _identifier;
	}

    public void setIdentifier(Integer identifier) {
        this._identifier = identifier;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public Integer getBalance() {
        return _balance;
    }

    public void setBalance(Integer balance) {
        if (balance < 0) {
            this._balance = 0;
        } else {
            this._balance = balance;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "_identifier=" + _identifier +
                ", _name='" + _name + '\'' +
                ", _balance=" + _balance +
                '}';
    }
}