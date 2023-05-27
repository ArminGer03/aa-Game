package utility;

import javafx.stage.Stage;
import model.User;

import java.util.ArrayList;

public class DataClass {
    private static Stage stage;
    private static ArrayList<User> users ;
    private static User currentUser;

    static {
        users = Loader.loadUsers();
        if (users == null){
            users = new ArrayList<>();
        }
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        DataClass.stage = stage;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static User getUserByUsername(String username) {
        if (users == null)
            return null;
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }
    public static User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email))
                return user;
        }
        return null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        DataClass.currentUser = currentUser;
    }
}
