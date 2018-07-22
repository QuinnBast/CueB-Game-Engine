package org.developmentEngine.compiler;

import org.developmentEngine.compiler.ObjectInterfaces.ObjectInterface;

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

    public Class<?> compileClass(String className, String filepath) {
        File sourceFile = new File(filepath);

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
            Class<?> testClass = classLoader.loadClass(className);
            return (Class<?>) testClass.newInstance();
            /****************************************************/
            //testClass.getMethod("sayHello").invoke(testClass.newInstance());
            /****************************************************/
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

}
