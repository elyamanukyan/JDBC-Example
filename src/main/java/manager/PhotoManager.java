package manager;

import database.DBConnectionProvider;
import model.Photo;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PhotoManager {
    private Connection connection;

    public PhotoManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addPhoto(Photo photo) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO photos(user_id,path,alt) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, photo.getUser_id());
        preparedStatement.setString(2, photo.getPath());
        preparedStatement.setString(3, photo.getAlt());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if(resultSet.next()){
            int id = resultSet.getInt(1);
            photo.setId(id);
        }
    }
    public List<Photo> getAllPhotos() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM photos");
        List<Photo> photos = new LinkedList<>();
        while (resultSet.next()){
            Photo photo = new Photo();
            photo.setId(resultSet.getInt("id"));
            photo.setUser_id(resultSet.getString("user_id"));
            photo.setPath(resultSet.getString("path"));
            photo.setAlt(resultSet.getString("alt"));
            photos.add(photo);
        }
        return photos;
    }
    public void updatePhotoById(int id, Photo photo) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE photos SET user_id=?, path=?, alt=? WHERE id=?");
        preparedStatement.setString(1, photo.getUser_id());
        preparedStatement.setString(2, photo.getPath());
        preparedStatement.setString(3, photo.getAlt());
        preparedStatement.setInt(4,id);
        preparedStatement.executeUpdate();
    }

    public void deletePhotoById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM photos WHERE id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }
}
