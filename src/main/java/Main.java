import manager.UserManager;
import model.User;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args){
        UserManager userManager = new UserManager();
        try {
            userManager.addUser(new User("elya@gmail.com","elya","Elya","Manoukian"));
            System.out.println();

            User user = new User("elya4@gmail.com","elya4","Elya4","Manoukian4");
            userManager.addUser(user);
            System.out.println(user);
            System.out.println();

            List<User> allUsers = userManager.getAllUsers();
            for(User theUser : allUsers) System.out.println(theUser);
            userManager.deleteUserById(1);
            System.out.println();

            userManager.updateUserById(2, new User("mariam@gmail.com","Mariam","Mariam","Manoukian"));
            System.out.println();

            allUsers = userManager.getAllUsers();
            for(User theUser : allUsers) System.out.println(theUser);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
