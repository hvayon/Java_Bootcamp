package ex01;

public class User {

    private Integer identifier;
    private String name;
    private Integer balance;

    public User(String name, Integer balance) {
        this.identifier = UserIdsGenerator.getInstance().generateId();
        setName(name);
        setBalance(balance);
    }

    public Integer getIdentifier() {
		return identifier;
	}

    public void setIdentifier() {
        this.identifier = identifier;
    }

    public String getName(void) {
        return name;
    }

    public setName(String name) {
        this.name = name;
    }

    public Integer getBalance(void) {
        return balance;
    }

    public setBalance(Integer balance) {
        if (balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }
}