package org.developmentEngine;

import org.developmentEngine.compiler.Compiler;
import org.developmentEngine.projectManagement.ProjectManager;
import org.developmentEngine.resourceManager.ResourceManager;

/**
 * Created by Quinn on 5/4/2018.
 */
public class DevelopmentEngine {

    public static ResourceManager resourceManager = new ResourceManager();
    public static ProjectManager projectManager = new ProjectManager();
    public static Compiler compiler = new Compiler();

    public DevelopmentEngine(){

    }

}
