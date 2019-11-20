package io.github.thedoctorone.sboot.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import io.github.thedoctorone.sboot.Model.Member;

public class UserService {
    private List<Member> userList;
    private FileOperations<Member> fileOperations;
    private String filename;
    private String path;

    public UserService(String path, String filename) {
        this.path = path;
        this.filename = filename;
        fileOperations = new FileOperations<Member>();
        fileOperations.setFilePath(this.path);
        if (!readUsers()) {
            this.userList = new ArrayList<Member>();
            if(!writeUsers(this.userList)) {
                Logger.getLogger("UserService").warning("CAN'T INTERRACT WITH PATH!");
                System.exit(0);
            }
        }

    }
    /**
     * Reads the user file
     * @return true if reading the user file successfull.
     *  <br> 
     * False: if user file not exists
     *  if does not has the permission to read the file
     *  if ClassNotFoundException occurs
     */
    private boolean readUsers() {
        try {
            File file = new File(filename);
            if(!file.exists())
                return false;
            userList = fileOperations.readAsList(filename);
            return true;
        } catch (ClassNotFoundException | IOException e) {
            return false;
        }
    }
    /**
     * Writes the user list to file 
     * @param write is the list of the users will be writed.
     * @return true if operation success.
     * @return false if operation fails.
     */
    public boolean writeUsers(List<Member> write) {
        try {
            fileOperations.writeAsList(write, filename);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks the usernames.
     * @param username is the username that will be checked.
     * @return true if username exists
     * @return false if username not exists
     */
    public boolean doWeHave(String username) {
        for(Member m : userList) {
            if(m.getUsername().equals(username))
                return true;
        }
        return false;
    }


    /**
     * Checking the user information.
     * @param username is the username that will be checked.
     * @param password is the password that will be checked.
     * @return true if the informations right.
     * @return false if the information not right.
     */
    public boolean loginCheck(String username, String password) {
        String hashedPass = password.hashCode() + "";
        Member toCheck = new Member(username, hashedPass);
        if(userList.contains(toCheck))
            return true;
        return false;
    }

    /**
     * Registering the user
     * @param username is the username 
     * @param password is the password
     * @return true if register success.
     * @return false otherwise.
     */
    public boolean register(String username, String password) {
        if(!doWeHave(username)) {
            Member register = new Member(username, password.hashCode() + "");
            userList.add(register);
            if(!writeUsers(userList)) {
                Logger.getLogger("UserService").warning("CAN'T INTERACT WITH USERFILE!");
            }
            return true;
        }
        return false;
    }

    /**
     * @return the userList
     */
    public List<Member> getUserList() {
        return userList;
    }
}