package com.anil.vertx.authenticator;

public class User{

    private String username;
    private String password;
    private String key;

    public User(String username,String password, String key){
        username = username;
        password = password;
        key = key;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getKey() {
        return key;
    }

}
