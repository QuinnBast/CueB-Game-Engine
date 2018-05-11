package org.userInterface.fileBrowser.Resources;

import org.applicationEngine.objects.Base.SpriteObject;
import org.developmentEngine.resourceManager.resourceTypes.BaseResourceType;
import org.developmentEngine.resourceManager.resourceTypes.Room;

/**
 * Created by Quinn on 5/5/2018.
 */
public class RoomResource extends Resource {
    public RoomResource(String path, Room object) {
        super(path + ".rm", object);
    }
}
