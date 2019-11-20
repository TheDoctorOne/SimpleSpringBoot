package io.github.thedoctorone.sboot.Service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Writes & Reads the Object
 * 
 * @param <E> is the class you want to write & read
 */
public interface FileOperations<E> extends Serializable {

    /**
     * Set the file path.
     * 
     * @param path - File Path
     */
    public void setFilePath(@NotEmpty String path);

    /**
     * Returns path
     * 
     * @return - path
     */
    public String getFilePath();

    /**
     * @param object
     * @param filename - name of the file
     * @param suffix   - suffix of the file, ex: ".dat"
     */
    public void write(@NotNull Object object, @NotEmpty String filename, @NotEmpty String suffix) throws IOException;

    /**
     * Filename will be set to timestamp suffix will be the class name
     */
    public void write(@NotNull E object) throws IOException;

    /**
     * @param list     of the objects - can not be empty
     * @param filename - name of the file
     * @param suffix   - suffix of the file
     * @throws IOException may occur
     */
    public void writeAsList(@NotEmpty List<E> list, @NotEmpty String filename, @NotEmpty String suffix) throws IOException;

    /**
     * @param list     of the objects - can not be empty
     * @param filename - name of the file
     * suffix   - suffix will be ".list"
     * @throws IOException may occur
     */
    public void writeAsList(@NotEmpty List<E> list, String filename) throws IOException;

    /**
     * @param list     of the objects - can not be empty
     * @param filename - is class name
     * suffix   - suffix will be ".list"
     * @throws IOException may occur
     */
    public void writeAsList(@NotEmpty List<E> list) throws IOException;

    /**
     * Reads the file according to filename
     * 
     * @param filename - name of the file
     * @param suffix - suffix of the file ex: ".dat"
     * @return the object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public E read(@NotEmpty String filename, @NotEmpty String suffix) throws IOException, ClassNotFoundException;

    public List<E> readAsList(@NotEmpty String filename) throws IOException, ClassNotFoundException;

    /**
     * Reads the directory according to path, use it wisely!
     * 
     * @return - list of objects
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<E> readDirectory() throws ClassNotFoundException, IOException;

    /**
     * Reads the files at the directory according to their suffix 
     * @param suffix
     * @return - list of objects
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public List<E> readDirectory(@NotEmpty String suffix) throws ClassNotFoundException, IOException;
}