package org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor.dragNdrop;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

/**
 * Created by Quinn on 5/25/2018.
 */
public class PanelDataFlavor extends DataFlavor {

    //Singleton pattern to have only one panel flavor
    public static final PanelDataFlavor panelDataFlavor = new PanelDataFlavor();

    public PanelDataFlavor(){
        super(DataFlavor.javaJVMLocalObjectMimeType + ";class=\"" + Component[].class.getName() + "\"", null);
    }

}
