package org.developmentEngine.projectManagement;

import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.compiler.ObjectWriter;
import org.developmentEngine.resourceManager.ResourceObserver;
import org.developmentEngine.resourceManager.Resources.Resource;
import org.developmentEngine.resourceManager.resourceProperties.PropertyObserver;
import org.userInterface.UserInterface;
import org.userInterface.modals.modals.ObjectEventModal;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Quinn on 7/14/2018.
 */
public class ProjectManager {

    private FileManager fileManager = new FileManager();
    private String currentProjectDirectory = "";
    private String currentProjectName = "";
    private ArrayList<ProjectObserver> projectObservers = new ArrayList<>();
    public static ObjectWriter objectWriter = new ObjectWriter();

    private void onProjectSave(){
        for(ProjectObserver p : projectObservers){
            p.onProjectSave();
        }
    }

    private void onProjectLoad(){
        for(ProjectObserver p : projectObservers){
            p.onProjectLoad();
        }
    }

    private void onNewProject(){
        for(ProjectObserver p : projectObservers){
            p.onNewProject();
        }
    }

    private void addProjectObserver(ProjectObserver p){
        this.projectObservers.add(p);
    }

    public void removeProjectObserver(ProjectObserver p){
        this.projectObservers.remove(p);
    }

    public String getCurrentProjectDirectory() {
        return currentProjectDirectory;
    }

    public void setCurrentProjectDirectory(String currentProjectDirectory) {
        this.currentProjectDirectory = currentProjectDirectory;
    }

    public String getCurrentProjectName() {
        return currentProjectName;
    }

    public void setCurrentProjectName(String currentProjectName) {
        this.currentProjectName = currentProjectName;
    }

    public void newProject() throws IOException {
        //Check to see if a project is currently open.

        //Save the current project.
        if(this.currentProjectDirectory != "") {
            this.saveProject();

            //Remove everything from the development engine.
            DevelopmentEngine.resourceManager.removeAllResources();
            UserInterface.window.getLayers().newFileBrowser();
        }


        String selectedDirectory = UserInterface.modalController.displayDirectoryChooserModal();
        if(selectedDirectory == ""){
            //The user didn't select anything, they just want to cancel out of the dialog.
            return;
        } else {
            currentProjectDirectory = (new File(selectedDirectory).getParent());
            if(new File(selectedDirectory).getName().contains(".qbp")){
                currentProjectName = new File(selectedDirectory).getName();
            } else {
                currentProjectName = new File(selectedDirectory).getName() + ".qbp";
            }
        }

        //Create a new project file which indicates the root directory of the project.
        File file = new File(currentProjectDirectory + "\\" + currentProjectName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Check to see if the project directory exists.
        File projDirectory = new File(this.getProjectDirectory());
        if(!projDirectory.exists()){
            //If the project directory did not exist, create a new directory
            projDirectory.mkdir();
        }

        this.onNewProject();
    }

    public void loadProject() throws IOException, ClassNotFoundException {
        //Save the current project.
        if(this.currentProjectDirectory != "") {
            this.saveProject();
        }

        //Get the user to select a project to load
        String loadedFile = UserInterface.modalController.displayFileChooserModal(new FileNameExtensionFilter("Cue-B Project", "qbp"));
        //Check if the selected file exists.
        File selectedFile = new File(loadedFile);
        if(selectedFile.exists()) {
            //Remove all objects from the project.
            DevelopmentEngine.resourceManager.removeAllResources();
            UserInterface.window.getLayers().newFileBrowser();

            //Get the project folder
            this.currentProjectDirectory = selectedFile.getParent();
            this.currentProjectName = selectedFile.getName();

            //Loop through the project Directory and find all .qbr files and load them into the engine
            File directory = new File(this.getProjectDirectory());
            for (File f : directory.listFiles()) {
                int i = f.getName().lastIndexOf(".");
                if (i > 0) {
                    if (f.getName().substring(i + 1).equals("qbr")) {
                        //If the file in the directory is a serialized object, load it
                        fileManager.loadObject(f.getAbsolutePath());
                    }
                }
            }
            this.onProjectLoad();
        } else {
            //The selected file is not a valid project file.
        }
    }

    public void saveProject() throws IOException {
        if(this.currentProjectDirectory == ""){
            this.newProject();
        }

        //Check to see if the project files exists.
        File file = new File(this.currentProjectDirectory + "\\" + this.currentProjectName);
        if(!file.exists()){
            file.createNewFile();
        }

        //Check to see if the project directory exists.
        File projDirectory = new File(this.getProjectDirectory());
        if(!projDirectory.exists()){
            //If the project directory did not exist, create a new directory
            projDirectory.mkdir();
        }

        for(Resource r : DevelopmentEngine.resourceManager.getAllResources()){
            //Remove all observers from the object's observers.
            ArrayList<PropertyObserver> propertyObservers = new ArrayList<>();
            ArrayList<ResourceObserver> resourceObservers = new ArrayList<>();

            propertyObservers.addAll(r.getProperties().getObservers());
            resourceObservers.addAll(r.getObservers());

            r.getProperties().removeObservers();
            r.removeObservers();

            fileManager.saveObject(r,this.getProjectDirectory());

            r.getProperties().setPropertyObservers(propertyObservers);
            r.setObservers(resourceObservers);
        }

        this.onProjectSave();
    }

    public String getProjectDirectory(){
        return currentProjectDirectory + "\\" + currentProjectName.replace(".qbp", "");
    }
}
