package ex02;

public class Program {

    public static void main(String[] args) {

        UsersList list = new UsersArrayList();

        User user = new User("Loh", 1000);

        list.addUser(user);

        System.out.println(list);

        User user2 = new User("LPUM", 1000);

        list.addUser(user2);

        System.out.println(list);

        User user3 = new User("LPUfM", 1000);

        list.addUser(user3);

        System.out.println(list);

        User res = list.getUserById(3);
        System.out.println(res);
    }

}
