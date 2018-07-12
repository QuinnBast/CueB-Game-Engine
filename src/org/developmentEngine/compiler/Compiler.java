package org.developmentEngine.compiler;

import org.developmentEngine.compiler.ObjectInterfaces.ObjectInterface;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Created by Quinn on 6/29/2018.
 */
public class Compiler {

    public void compileClass(String className, String codeToCompile, String filepath){
        File sourceFile = new File(filepath);
        sourceFile.getParentFile().mkdirs();    //Make directory to the file if needed
        try {
            Files.write(sourceFile.toPath(), codeToCompile.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Compile the file that was created
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourceFile.getPath());

        //Load and instantiate the class
        URLClassLoader classLoader = null;
        try {
            classLoader = URLClassLoader.newInstance(new URL[]{sourceFile.toURI().toURL()});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Class<?> instantiatedClass = null;
        try {
            instantiatedClass = Class.forName(className, true, classLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Cast to an interface to use known methods.
        try {
            ObjectInterface compiledClass = (ObjectInterface) instantiatedClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
