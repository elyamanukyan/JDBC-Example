import manager.PhotoManager;
import manager.UserManager;
import model.Photo;
import model.User;

import java.sql.SQLException;
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


        PhotoManager photoManager = new PhotoManager();
        try {
            photoManager.addPhoto(new Photo("3","sdsdfds/fdd/dfvdf","ManoukianPhoto"));
            System.out.println();

            Photo photo = new Photo("5","sdsdfds/fdd/dfvdf/222","ManoukianPhoto2");
            photoManager.addPhoto(photo);
            System.out.println(photo);
            System.out.println();

            List<Photo> allPhotos = photoManager.getAllPhotos();
            for(Photo thePhoto : allPhotos) System.out.println(thePhoto);
            photoManager.deletePhotoById(4);
            System.out.println();

            photoManager.updatePhotoById(2, new Photo("19","aaaaaaa/bbbbbbbbbb","aaaaaManoukian"));
            System.out.println();

            allPhotos = photoManager.getAllPhotos();
            for(Photo thePhoto : allPhotos) System.out.println(thePhoto);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
