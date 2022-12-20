package ex02;

public class UsersArrayList implements UsersList {
    private int currentLength = 0;
    private User[] listOfUser = new User[10];

    @Override
    public void addUser(User user) {
            if (currentLength < listOfUser.length) {
                listOfUser[currentLength] = user;
                currentLength++;
            } else {
                User[] newList = new User[(int) (listOfUser.length * 1.5)];
                for (int i = 0; i < listOfUser.length; i++) {
                    newList[i] = listOfUser[i];
                }
                newList[listOfUser.length] = user;
                listOfUser = newList;
                currentLength++;
            }
    }

    @Override
    public User getUserById(Integer id) {
        for(int i = 0; i < currentLength; i++) {
            if (i == id) {
              return listOfUser[i];
            }
        }
        throw new UserNotFoundException("User with id " + id + " not found");
    }

    @Override
    public User getUserByIndex(Integer index) {
        return null;
    }

    @Override
    public Integer getUserCount() {
        return null;
    }
}
