package org.developmentEngine.projectManagement;

import org.developmentEngine.resourceManager.Resources.Resource;

import java.io.*;

/**
 * Created by Quinn on 7/14/2018.
 */
public class FileManager {

    public void FileManager(){

    }

    public void saveObject(Resource resource) throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Quinn\\Desktop\\" + ((Integer)resource.hashCode()).toString() + ".qbr");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(resource);
        oos.close();
    }

    public Resource loadObject(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Resource object = (Resource) ois.readObject();
        ois.close();
        return object;
    }
}
