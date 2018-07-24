package org.developmentEngine.compiler;

import org.applicationEngine.Events.EventType;
import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.Resources.ObjectResource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Quinn on 6/29/2018.
 */
public class ObjectWriter {

    public void writeDefaultEventHandler(ObjectResource resourceObject) throws IOException {
        //Try to save the project. If the project doesn't have a directory, a prompt will be given.
        DevelopmentEngine.projectManager.saveProject();
        resourceObject.getProperties();
        File javaFile = new File(DevelopmentEngine.projectManager.getProjectDirectory() + "\\$" + resourceObject.getUuid().toString().replace("-", "") + ".java");
        //Check if the file exists.
        if(javaFile.exists()){
            return;
        } else {
            javaFile.createNewFile();
        }

        String classCode = "import org.applicationEngine.objects.Base.Object;\n";
        classCode += "import java.util.ArrayList;\n";
        classCode += "import org.applicationEngine.Events.EventHandler;\n\n";
        classCode +="public class $" + resourceObject.getUuid().toString().replace("-", "") + " implements EventHandler { \n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onCreate(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onCollision(ArrayList<Object> involvedObjects){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onMove(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onDrawBegin(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onDrawEnd(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onUpdate(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onAnimationEnd(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onAnimationStart(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onKeyPressed(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onKeyDown(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onKeyUp(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onMouseUp(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onMouseDown(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onMouseEnter(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onMouseLeave(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onDestroy(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onRoomEnter(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onRoomLeave(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onRoomUpdate(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onTimerStart(){}\n";
        classCode += "\t@Override\n";
        classCode += "\tpublic void onTimerEnd(){}\n";
        classCode += "}";

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(javaFile));
        bufferedWriter.write(classCode);
        bufferedWriter.close();
    }

    public void updateEvent(ObjectResource resource, EventType event, String code) throws IOException {
        if(event != null) {
            if(!new File(DevelopmentEngine.projectManager.getProjectDirectory() + "\\$" + resource.getUuid().toString().replace("-", "") + ".java").exists()){
                this.writeDefaultEventHandler(resource);
            }
            //Get the current code from the eventHandler
            String eventCode = new String(Files.readAllBytes(Paths.get(DevelopmentEngine.projectManager.getProjectDirectory() + "\\$" + resource.getUuid().toString().replace("-", "") + ".java")), StandardCharsets.UTF_8);
            //Get the starting index for the event that we are interested in
            int startIndex = eventCode.indexOf("public void " + event.toString() + "(){") + 15 + event.toString().length();
            //Get the end index of the function that we are interested in
            String strippedCode = eventCode.substring(startIndex);
            int endIndex = startIndex + strippedCode.indexOf("\n\t@Override\n\tpublic void on") - 1;

            //Set the new code to the updated code for the event handler:
            String firstSection = eventCode.substring(0, startIndex);
            String newCode = code;
            String endSection = eventCode.substring(endIndex, eventCode.length());

            String combinedText = firstSection + newCode + endSection;

            //Create a new temporary file to write to.
            File tempFile = new File(DevelopmentEngine.projectManager.getProjectDirectory() + "\\$temp.java");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
            bufferedWriter.write(combinedText);
            bufferedWriter.close();
            //Delete the original file
            new File(DevelopmentEngine.projectManager.getProjectDirectory() + "\\$" + resource.getUuid().toString().replace("-", "") + ".java").delete();
            //Rename the temporary file
            tempFile.renameTo(new File(DevelopmentEngine.projectManager.getProjectDirectory() + "\\$" + resource.getUuid().toString().replace("-", "") + ".java"));
        }
    }

    public String getEvent(ObjectResource resource, EventType event) throws IOException {
        if(event != null) {
            if(!new File(DevelopmentEngine.projectManager.getProjectDirectory() + "\\$" + resource.getUuid().toString().replace("-", "") + ".java").exists()){
                this.writeDefaultEventHandler(resource);
            }

            String eventCode = new String(Files.readAllBytes(Paths.get(DevelopmentEngine.projectManager.getProjectDirectory() + "\\$" + resource.getUuid().toString().replace("-", "") + ".java")), StandardCharsets.UTF_8);
            int startIndex = eventCode.indexOf("public void " + event.toString() + "(){") + 15 + event.toString().length();
            //Get the string and find the next declaration of a public method.
            eventCode = eventCode.substring(startIndex);
            int endIndex = eventCode.indexOf("\n\t@Override\n\tpublic void on") - 1;
            return eventCode.substring(0, endIndex);
        } else {
            return "";
        }
    }
}
