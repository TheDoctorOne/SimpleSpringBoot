package io.github.thedoctorone.sboot.Service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import io.github.thedoctorone.sboot.Model.Feedback;

public class FeedbackDataHolder {
    private FileOperations<Feedback> operations;
    private List<Feedback> feedbacks;
    private String filename;

    /**
     * @param path - Filepath
     * @param filename - Filename
     */
    public FeedbackDataHolder(String path, String filename) {
        this.filename = filename;
        operations = new FileOperations<Feedback>();
        operations.setFilePath(path);
        try {
            feedbacks = operations.readAsList(filename);
        } catch (ClassNotFoundException | IOException e) {
            Logger.getGlobal().warning("Feedback reading error!");
            e.printStackTrace();
        }
    }

    /**
     * @return the operations
     */
    public FileOperations<Feedback> getOperations() {
        return operations;
    }

    /**
     * @return the data
     */
    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void addFeedback(Feedback feedback) {
        try {
            feedbacks.add(feedback);
            operations.writeAsList(feedbacks, filename);
        } catch (IOException e) {
            Logger.getGlobal().warning("Feedback reading error!");
            e.printStackTrace();
        }
    }


}