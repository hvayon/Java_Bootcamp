package ex01;

public final class UserIdsGenerator {

    private static final UserIdsGenerator INSTANCE = new UserIdsGenerator();

    private static int id = 0;
    private UserIdsGenerator() { }

    public static UserIdsGenerator getInstance() {
        return INSTANCE;
    }

    public int generateId() {
        id++;
        return id;
    }
}