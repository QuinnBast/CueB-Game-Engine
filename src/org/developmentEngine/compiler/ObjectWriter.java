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
        resourceObject.getProperties();
        File javaFile = new File(DevelopmentEngine.projectManager.getProjectDirectory() + "\\$" + resourceObject.getUuid().toString() + ".java");
        //Check if the file exists.
        if(javaFile.exists()){
            return;
        }

        String classCode = "public class $" + resourceObject.getUuid().toString() + " implements ObjectInterface { \n";
        classCode += "\tpublic void onCreate(){}\n";
        classCode += "\tpublic void onCollision(Instance collisionInstance){}\n";
        classCode += "\tpublic void onDraw(){}\n";
        classCode += "\tpublic void onUpdate(){}\n";
        classCode += "\tpublic void onAnimationEnd(){}\n";
        classCode += "\tpublic void onAnimationStart(){}\n";
        classCode += "\tpublic void onKeyPressed(){}\n";
        classCode += "\tpublic void onKeyDown(){}\n";
        classCode += "\tpublic void onKeyUp(){}\n";
        classCode += "\tpublic void onMouseUp(){}\n";
        classCode += "\tpublic void onMouseDown(){}\n";
        classCode += "\tpublic void onMouseEnter(){}\n";
        classCode += "\tpublic void onMouseLeave(){}\n";
        classCode += "\tpublic void onDestroy(){}\n";
        classCode += "}";

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(javaFile));
        bufferedWriter.write(classCode);
        bufferedWriter.close();
    }

    public void updateEvent(ObjectResource resource, EventType event, String code) throws IOException {
        if(event != null) {
            //Get the current code from the eventHandler
            String eventCode = new String(Files.readAllBytes(Paths.get(DevelopmentEngine.projectManager.getProjectDirectory() + "\\$" + resource.getUuid().toString() + ".java")), StandardCharsets.UTF_8);
            //Get the starting index for the event that we are interested in
            int startIndex = eventCode.indexOf("public void " + event.toString() + "(){");
            //Get the end index of the function that we are interested in
            String strippedCode = eventCode.substring(startIndex);
            int endIndex = startIndex + strippedCode.indexOf("public", 2) - 1;

            //Set the new code to the updated code for the event handler:
            String firstSection = eventCode.substring(0, startIndex);
            String newCode;
            if(code != null || code != "") {
                newCode = "public void " + event.toString() + "(){" + code + "}\n";
            } else {
                newCode = "public void " + event.toString() + "(){}\n";
            }
            String endSection = "\t" + eventCode.substring(endIndex + 1, eventCode.length());

            String combinedText = firstSection + newCode + endSection;

            //Create a new temporary file to write to.
            File tempFile = new File(DevelopmentEngine.projectManager.getProjectDirectory() + "\\$temp.java");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
            bufferedWriter.write(combinedText);
            bufferedWriter.close();
            //Delete the original file
            new File(DevelopmentEngine.projectManager.getProjectDirectory() + "\\$" + resource.getUuid().toString() + ".java").delete();
            //Rename the temporary file
            tempFile.renameTo(new File(DevelopmentEngine.projectManager.getProjectDirectory() + "\\$" + resource.getUuid().toString() + ".java"));
        }
    }

    public String getEvent(ObjectResource resource, EventType event) throws IOException {
        if(event != null) {
            String eventCode = new String(Files.readAllBytes(Paths.get(DevelopmentEngine.projectManager.getProjectDirectory() + "\\$" + resource.getUuid().toString() + ".java")), StandardCharsets.UTF_8);
            int startIndex = eventCode.indexOf("public void " + event.toString() + "(){") + 15 + event.toString().length();
            //Get the string and find the next declaration of a public method.
            eventCode = eventCode.substring(startIndex);
            int endIndex = eventCode.indexOf("\n\tpublic void on") - 1;
            return eventCode.substring(0, endIndex);
        } else {
            return "";
        }
    }
}
