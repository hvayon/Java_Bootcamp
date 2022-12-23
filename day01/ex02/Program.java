package ex02;

public class Program {

    public static void main(String[] args) {

        UsersList list = new UsersArrayList();

        User user = new User("Jorick", 1000);

        list.addUser(user);

        System.out.println(list);

        User user2 = new User("Kuk", 1000);

        list.addUser(user2);

        System.out.println(list);

        User user3 = new User("Lory", 1000);

        list.addUser(user3);

        System.out.println(list);

        User res1 = list.getUserById(1);
        User res2 = list.getUserByIndex(2);
        System.out.println();
        System.out.println(res1);
        System.out.println(res2);

        System.out.println("Number of user = " + list.getUserCount());
    }

}
