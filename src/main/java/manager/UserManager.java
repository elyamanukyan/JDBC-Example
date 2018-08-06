package manager;

import database.DBConnectionProvider;
import model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserManager {
    private Connection connection;

    public UserManager(){
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addUser(User user) throws SQLException {
        /*Statement statement = connection.createStatement();
        String query = "INSERT INTO users(email,password,name,surname) VALUES('"
        + user.getEmail() + "','" + user.getPassword() + "','" + user.getName()
        + "','" + user.getSurname()+"')";
        statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS); */
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(email,password,name,surname) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getName());
        preparedStatement.setString(4, user.getSurname());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if(resultSet.next()){
            int id = resultSet.getInt(1);
            user.setId(id);
        }
    }

    public List<User> getAllUsers() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        List<User> users = new LinkedList<>();
        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            users.add(user);
        }
        return users;
    }

    public void updateUserById(int id, User user) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET email=?, password=?, name=?, surname=? WHERE id=?");
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getName());
        preparedStatement.setString(4, user.getSurname());
        preparedStatement.setInt(5,id);
        preparedStatement.executeUpdate();
//        ResultSet resultSet = preparedStatement.getGeneratedKeys();
//        if(resultSet.next()){
//            id = resultSet.getInt(1);
//            user.setId(id);
//        }


    }

    public void deleteUserById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

}
