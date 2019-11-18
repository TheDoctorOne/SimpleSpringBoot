package io.github.thedoctorone.sboot.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.internal.util.privilegedactions.GetDeclaredConstructor;
import org.hibernate.validator.internal.util.privilegedactions.GetDeclaredConstructors;

/**
 * Writes & Reads the Object
 * @param <E> is the class you want to write & read
 */
public abstract class FileOperations<E> {
    
    private String path;

    /**
     * Set the file path.
     * @param path - File Path
     */
    public void setFilePath(String path) {
        if(path.endsWith("/") || path.endsWith("\\")) {
            path = path.substring(0, path.length() - 2);
        }
        this.path = path;
    }

    /**
     * @param object
     * @param filename - name of the file
     * @param endfix - endfix of the file, ex: ".dat"
     */
    public void write(@NotNull Object object,@NotEmpty String filename,@NotEmpty String endfix) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(path + "/" + filename + endfix));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    /**
     * Filename will be set to timestamp
     * EndFix will be the class name
     */
    public void write(@NotNull E object) throws IOException {
        write(object, Calendar.getInstance().getTimeInMillis() + "", object.getClass().getName());
    }
    /**
     * @param list of the objects
     * @param filename - name of the file
     * @param endfix - Endfix of the file
     * @throws IOException may occur
     */
    public void writeAsList(@NotEmpty List<E> list,@NotEmpty String filename,@NotEmpty String endfix) throws IOException {
        write(list, filename, endfix);
    }

    /**
     * @param list of the objects
     * @param filename - name of the file
     * @param endfix - Endfix will be ".list"
     * @throws IOException may occur
     */
    public void writeAsList(@NotEmpty List<E> list, String filename) throws IOException {
        write(list, filename, ".list");
    }

    /**
     * @param list of the objects
     * @param filename - is class name
     * @param endfix - Endfix will be ".list"
     * @throws IOException may occur
     */
    public void writeAsList(@NotEmpty List<E> list) throws IOException {
        write(list, list.get(0).getClass().getName() , ".list");
    }

    public E read(@NotEmpty String filename) {
        return null;
    }

    public List<E> readAsList(@NotEmpty String filename) {
        return null;
    }

    public List<E> readAll() {
        return null;
    }
}