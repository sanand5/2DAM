package examen_andreusanz.logc;

import examen_andreusanz.dto.user;

import java.util.ArrayList;
import java.util.List;

public class UserLogic {

    private static List<user> userList = new ArrayList<>();
    
    public static void addUser(user user) {
        userList.add(user);
    }
    
    public static boolean findUser(String username, String password) {
        for (user user : userList) {
            if (user.getUser().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
