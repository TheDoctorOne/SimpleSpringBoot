package io.github.thedoctorone.sboot.Model;

import java.io.Serializable;

public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username, password;

    public Member (String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Member) {
            Member temp = (Member) obj;
            if(toString().equals(temp.toString())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "Username : " + username + "\nPassword : " + password;
    }
}