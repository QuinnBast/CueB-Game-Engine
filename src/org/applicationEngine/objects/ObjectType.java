package org.applicationEngine.objects;

/**
 * Created by Quinn on 5/4/2018.
 */
public enum ObjectType {
    SPRITE ("Sprite"),
    OBJECT ("Object"),
    ROOM ("Room"),
    SCRIPT ("Script");

    private String objectName;

    ObjectType(String name){
        this.objectName = name;
    }

    public String getString(){
        return this.objectName;
    }

}
