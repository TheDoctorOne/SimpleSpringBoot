package io.github.thedoctorone.sboot.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class FileOperationsImp<E> implements FileOperations<E> {

    private static final long serialVersionUID = 1L;
    private String path;
    
    @Override
    public void setFilePath(@NotEmpty String path) {
        if (path.endsWith("/") || path.endsWith("\\")) {
            path = path.substring(0, path.length() - 2);
        }
        this.path = path;
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }
    }
    
    @Override
    public String getFilePath() {
        return path;
    }
    
    @Override
    public void write(@NotNull Object object, @NotEmpty String filename, @NotEmpty String suffix) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(path + "/" + filename + suffix));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }
    
    @Override
    public void write(@NotNull E object) throws IOException {
        write(object, Calendar.getInstance().getTimeInMillis() + "", object.getClass().getName());
    }
    
    @Override
    public void writeAsList(@NotEmpty List<E> list, @NotEmpty String filename, @NotEmpty String suffix) throws IOException {
        write(list, filename, suffix);
    }
    
    @Override
    public void writeAsList(@NotEmpty List<E> list, String filename) throws IOException {
        write(list, filename, ".list");
    }
    
    @Override
    public void writeAsList(@NotEmpty List<E> list) throws IOException {
        write(list, list.get(0).getClass().getName(), ".list");
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public E read(@NotEmpty String filename, @NotEmpty String suffix) throws IOException, ClassNotFoundException {
        E object = null;
        File file = new File(path + "/" + filename + suffix);
        if(!file.exists()) {
            return null;
        }
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        object = (E) ois.readObject();
        ois.close();
        fis.close();
        return object;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> readAsList(@NotEmpty String filename) throws IOException, ClassNotFoundException {
        List<E> list = new ArrayList<>();
        File file = new File(path + "/" + filename + ".list");
        if(!file.exists()) {
            return list;
        }
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        list = (List<E>) ois.readObject();
        ois.close();
        fis.close();
        return list;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<E> readDirectory() throws ClassNotFoundException, IOException {
        List<E> list = new ArrayList<>();
        File directory = new File(path); 
        if(directory.isDirectory() && directory.exists()) {
            for(File file : directory.listFiles()) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                list.add((E) ois.readObject());
                ois.close();
                fis.close();
            }
        } else {
            directory.mkdir();
        }
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> readDirectory(@NotEmpty String suffix) throws ClassNotFoundException, IOException {
        List<E> list = new ArrayList<>();
        File directory = new File(path); 
        if(directory.isDirectory() && directory.exists()) {
            for(File file : directory.listFiles()) {
                if(file.getName().endsWith(suffix)) {
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    list.add((E) ois.readObject());
                    ois.close();
                    fis.close();
                }
            }
        } else {
            directory.mkdir();
        }
        return list;
    }
}