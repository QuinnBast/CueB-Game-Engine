package org.developmentEngine.compiler;

import org.applicationEngine.Events.EventHandler;
import org.developmentEngine.DevelopmentEngine;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * Created by Quinn on 6/29/2018.
 */
public class Compiler {

    public Compiler(){

    }

    private Class<?> compileClass(String className) {
        File sourceFile = new File(DevelopmentEngine.projectManager.getProjectDirectory() + "\\$" + className + ".java");

        //Compile the file that was created
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        File parent = sourceFile.getParentFile();
        try {
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(parent));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));
        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
        try {
            fileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //compiler.run(null, null, null, sourceFile.getPath());

        //Load and instantiate the class
        URLClassLoader classLoader = null;
        try {
            classLoader = URLClassLoader.newInstance(new URL[]{parent.toURI().toURL()});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //Cast to an interface to use known methods.
        try {
            Class<?> testClass = classLoader.loadClass("$" + className);
            return testClass;
            /****************************************************/
            //testClass.getMethod("sayHello").invoke(testClass.newInstance());
            /****************************************************/
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public EventHandler compileEventHandler(String className){
        try {
            return (EventHandler) this.compileClass(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
