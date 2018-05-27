package org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor.dragNdrop;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Created by Quinn on 5/25/2018.
 */
public class TransferableComponent implements Transferable {

    private DataFlavor[] flavors = new DataFlavor[]{PanelDataFlavor.panelDataFlavor};
    private JPanel transferComponent;

    public TransferableComponent(JPanel component){
        this.transferComponent = component;
    }

    public JPanel getTransferComponent(){
        return this.transferComponent;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return this.flavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        for(DataFlavor df : flavors){
            if(df.equals(flavor)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        Object data = null;
        if(isDataFlavorSupported(flavor)){
            data = getTransferComponent();
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
        return data;
    }
}
