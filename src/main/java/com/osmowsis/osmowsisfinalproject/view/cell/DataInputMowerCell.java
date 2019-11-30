package com.osmowsis.osmowsisfinalproject.view.cell;

import com.osmowsis.osmowsisfinalproject.pojo.Mower;
import com.osmowsis.osmowsisfinalproject.view.controller.DataInputMowerCellController;
import javafx.scene.control.ListCell;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * This class represents the actual cell for the mower list view, this
 * class extends ListCell to build on the out of the box functionality for
 * List Cells in JavaFX, mainly this class specifies how the cell will be
 * updated when needed. For additional functionality look to the corresponding
 * controller
 *
 * Created on 11/26/2019
 */

@Component
@Scope("prototype")
public class DataInputMowerCell extends ListCell<Mower>
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private DataInputMowerCellController dataInputMowerCellController;

    // PROTECTED METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void updateItem(Mower item, boolean empty)
    {
        super.updateItem(item, empty);

        if(item != null && !empty)
        {
            if(dataInputMowerCellController == null
                    || dataInputMowerCellController.getMower() != item)
            {
                dataInputMowerCellController = getDataInputMowerCellController();
            }

            dataInputMowerCellController.setCellInfo(item);

            setGraphic(dataInputMowerCellController.getDataInputMowerCell());
        }
        else{
            setGraphic(null);
        }
    }

    // SPRING LOOKUPS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Spring magic for handling prototype scoped tings : )
     *
     * @return - A new instance of the Data Input Mower Cell Controller
     */
    @Lookup
    DataInputMowerCellController getDataInputMowerCellController(){return null;}
}
