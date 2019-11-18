package io.github.thedoctorone.sboot.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Writes & Reads the Object
 * 
 * @param <E> is the class you want to write & read
 */
public abstract class FileOperations<E> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String path;

    /**
     * Set the file path.
     * 
     * @param path - File Path
     */
    public void setFilePath(@NotEmpty String path) {
        if (path.endsWith("/") || path.endsWith("\\")) {
            path = path.substring(0, path.length() - 2);
        }
        this.path = path;
    }

    /**
     * Returns path
     * 
     * @return - path
     */
    public String getFilePath() {
        return path;
    }

    /**
     * @param object
     * @param filename - name of the file
     * @param suffix   - suffix of the file, ex: ".dat"
     */
    public void write(@NotNull Object object, @NotEmpty String filename, @NotEmpty String suffix) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(path + "/" + filename + suffix));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    /**
     * Filename will be set to timestamp suffix will be the class name
     */
    public void write(@NotNull E object) throws IOException {
        write(object, Calendar.getInstance().getTimeInMillis() + "", object.getClass().getName());
    }

    /**
     * @param list     of the objects - can not be empty
     * @param filename - name of the file
     * @param suffix   - suffix of the file
     * @throws IOException may occur
     */
    public void writeAsList(@NotEmpty List<E> list, @NotEmpty String filename, @NotEmpty String suffix)
            throws IOException {
        write(list, filename, suffix);
    }

    /**
     * @param list     of the objects - can not be empty
     * @param filename - name of the file
     * @param suffix   - suffix will be ".list"
     * @throws IOException may occur
     */
    public void writeAsList(@NotEmpty List<E> list, String filename) throws IOException {
        write(list, filename, ".list");
    }

    /**
     * @param list     of the objects - can not be empty
     * @param filename - is class name
     * @param suffix   - suffix will be ".list"
     * @throws IOException may occur
     */
    public void writeAsList(@NotEmpty List<E> list) throws IOException {
        write(list, list.get(0).getClass().getName(), ".list");
    }

    /**
     * Reads the file according to filename
     * 
     * @param filename - name of the file
     * @return the object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public E read(@NotEmpty String filename) throws IOException, ClassNotFoundException {
        E object = null;
        FileInputStream fis = new FileInputStream(new File(path + "/" + filename));
        ObjectInputStream ois = new ObjectInputStream(fis);
        object = (E) ois.readObject();
        ois.close();
        fis.close();
        return object;
    }

    @SuppressWarnings("unchecked")
    public List<E> readAsList(@NotEmpty String filename) throws IOException, ClassNotFoundException {
        List<E> list = new ArrayList<>();
        FileInputStream fis = new FileInputStream(new File(path + "/" + filename));
        ObjectInputStream ois = new ObjectInputStream(fis);
        list = (List<E>) ois.readObject();
        ois.close();
        fis.close();
        return list;
    }

    /**
     * Reads the directory according to path, use it wisely!
     * 
     * @return - list of objects
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public List<E> readDirectory() throws ClassNotFoundException, IOException {
        List<E> list = new ArrayList<>();
        File directory = new File(path); 
        if(directory.isDirectory()) {
            for(File file : directory.listFiles()) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                list.add((E) ois.readObject());
                ois.close();
                fis.close();
            }
        }
        return list;
    }

    /**
     * Reads the files at the directory according to their suffix 
     * @param suffix
     * @return - list of objects
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public List<E> readDirectory(@NotEmpty String suffix) throws ClassNotFoundException, IOException {
        List<E> list = new ArrayList<>();
        File directory = new File(path); 
        if(directory.isDirectory()) {
            for(File file : directory.listFiles()) {
                if(file.getName().endsWith(suffix)) {
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    list.add((E) ois.readObject());
                    ois.close();
                    fis.close();
                }
            }
        }
        return list;
    }
}