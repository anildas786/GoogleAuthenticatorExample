package com.anil.vertx.authenticator;

import java.util.ArrayList;
import java.util.Optional;

public class UserRepositoryImpl implements  IUsersRepoistory {

    private ArrayList<User> users;

    public UserRepositoryImpl() {
        users = new ArrayList<User>();
    }

    @Override
    public User add(String username, String password, String key) {
        User user = new User(username,password,key);
        users.add(user);
        return user;
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        for (User user:users){
            if (user.getUsername() == userName) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
