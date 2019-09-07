package com.anil.vertx.authenticator;
import java.util.Optional;

public interface IUsersRepoistory {

    User add( String username, String password, String key);

    Optional<User> findByUserName ( String userName);
}
