package utility;

import javafx.stage.Stage;
import model.User;

import java.util.ArrayList;

public class DataClass {
    private static Stage stage;
    private static ArrayList<User> users ;
    private static User currentUser;

    static {
        users = DataLoader.loadUsers();
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
}
