package io.github.thedoctorone.sboot.Model;

import java.io.Serializable;

public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username, password;

    public Member (String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    public String toString() {
        return "Username : " + username + "\nPassword : " + password;
    }
}