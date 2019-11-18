package io.github.thedoctorone.sboot.Model;

import java.io.Serializable;

public class Feedback implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name, mail, feedback;

    public Feedback(int id, String name, String mail, String feedback) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.feedback = feedback;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the feedback
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id + " : " + name + " : " + mail + " : " + feedback;
    }
}