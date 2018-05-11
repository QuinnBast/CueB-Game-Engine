package org.userInterface.fileBrowser.Resources;


import org.applicationEngine.objects.Base.SpriteObject;
import org.developmentEngine.resourceManager.resourceTypes.BaseResourceType;
import org.developmentEngine.resourceManager.resourceTypes.Object;

/**
 * Created by Quinn on 5/5/2018.
 */
public class ObjectResource extends Resource {
    public ObjectResource(String path, Object object) {
        super(path + ".obj", object);
    }
}
