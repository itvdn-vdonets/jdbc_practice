package services;

import models.User;
import repos.UserRepo;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Boolean registerUser(User user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            throw new RuntimeException("Bad data!");
        }
         return userRepo.addUser(user);
    }

    public List<User> findAllUsers() {
        return userRepo.getAllUsers();
    }

    public List<User> findAllUsersWithWeakPassword() {
        /* using streams
        return userRepo.getAllUsers().stream()
                .filter(x -> x.getPassword().length() < 6)
                .collect(Collectors.toList());
        */
        List<User> users = userRepo.getAllUsers();
        List<User> tempList = new ArrayList<>();
        for (User user : users) {
            if(user.getPassword().length()<6){
                tempList.add(user);
            }
        }
        return tempList;
    }



}
