package org.developmentEngine.projectManagement;

import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.Resources.Resource;
import org.developmentEngine.resourceManager.Resources.SpriteResource;

import java.io.*;

/**
 * Created by Quinn on 7/14/2018.
 */
public class FileManager {

    public void FileManager(){

    }

    public void saveObject(Resource resource, String filePath) throws IOException {
        File file = new File(filePath);
        file.mkdirs();
        FileOutputStream fos = new FileOutputStream(filePath + "\\" + resource.getUuid().toString() + ".qbr");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(resource);
        oos.close();
    }

    public void loadObject(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Resource object = (Resource) ois.readObject();
        ois.close();
        DevelopmentEngine.resourceManager.addResource(object);
    }
}
