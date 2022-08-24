package repos;

import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    private final Connection connection;

    public UserRepo(Connection connection) {
        this.connection = connection;
    }

    public Boolean addUser(User user) {
        String sql = "INSERT INTO Users(full_name, email, password) VALUES (?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Users";
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    User tmpUser = new User();
                    tmpUser.setId(resultSet.getInt(1));
                    tmpUser.setFullName(resultSet.getString(2));
                    tmpUser.setEmail(resultSet.getString(3));
                    tmpUser.setPassword(resultSet.getString(4));
                    userList.add(tmpUser);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public User getUser(Integer id) {
        //TODO: implement getting user from db
        return null;
    }

    public Boolean changePassword(Integer id, String newPass) {
        //TODO: implement changing password from db
        return false;
    }


}
