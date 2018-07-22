package org.developmentEngine.projectManagement;

/**
 * Created by Quinn on 7/21/2018.
 */
public interface ProjectObserver {

    void onProjectSave();
    void onNewProject();
    void onProjectLoad();

}
