import db.DBConnect;
import models.User;
import models.dtos.UserDto;
import repos.UserRepo;
import services.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try(Connection connection = DBConnect.getConnection()) {
            UserRepo userRepo = new UserRepo(connection);
            UserService userService = new UserService(userRepo);
            userRepo.addUser(new User("myname", "ad@email.com", "we"));
            userRepo.addUser(new User("fasdf", "adsf@email.com", "westrongpass"));
            userRepo.addUser(new User("gasfh", "ahjgj@email.com", "westrongpass"));
            List<User> allUsersWithWeakPassword = userService.findAllUsersWithWeakPassword();
            System.out.println(allUsersWithWeakPassword);

            userRepo.changePassword(1,"newPassword");
            //fullname -> Ivan
            userRepo.updateUser(new UserDto(1, null, null, "new passs!!!!"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}