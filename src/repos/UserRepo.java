package repos;

import models.User;
import models.dtos.UserDto;

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
        String sql = "UPDATE users SET password = ? WHERE id = ?";
        try (PreparedStatement pr = connection.prepareStatement(sql)) {
            pr.setString(1, newPass);
            pr.setInt(2, id);
            int i = pr.executeUpdate();
            if (i==1) return true;
        } catch (SQLException ex) {
            System.out.println("SQL error !");
        }
        return false;
    }


    public User getUserById(Integer id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User tmpUser = new User();
        try (PreparedStatement pr = connection.prepareStatement(sql)) {
            pr.setInt(1, id);
            try (ResultSet resultSet = pr.executeQuery()) {
                while (resultSet.next()) {
                    tmpUser.setId(resultSet.getInt(1));
                    tmpUser.setFullName(resultSet.getString(2));
                    tmpUser.setEmail(resultSet.getString(3));
                    tmpUser.setPassword(resultSet.getString(4));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return tmpUser;
    }


    public Boolean updateUser(UserDto userDto) {
        User user = getUserById(userDto.getId());

        if (userDto.getFullName() != null) {
            user.setFullName(userDto.getFullName());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }
        if (userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }

        String sql = "UPDATE users SET full_name = ?, email = ?, password = ? WHERE id = ?";
        try (PreparedStatement pr = connection.prepareStatement(sql)) {
            pr.setString(1, user.getFullName());
            pr.setString(2, user.getEmail());
            pr.setString(3, user.getPassword());
            pr.setInt(4, user.getId());
            int i = pr.executeUpdate();
            if (i==1) return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return false;
    }



}
