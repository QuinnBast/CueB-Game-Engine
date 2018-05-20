package org.developmentEngine.resourceManager.resourceProperties;

/**
 * Created by Quinn on 5/19/2018.
 */
public class ScriptProperties extends ResourceProperties {

    private String name = "";
    private String code = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.notifyUpdate();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        this.notifyUpdate();
    }

    public ScriptProperties(){

    }

}
