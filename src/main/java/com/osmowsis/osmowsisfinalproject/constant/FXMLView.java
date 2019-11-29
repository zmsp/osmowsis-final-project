package com.osmowsis.osmowsisfinalproject.constant;

import lombok.Getter;

/**
 * Enum that contains all of the names for the different FXML views used through out the GUI
 *
 * Created 11/25/2019
 */
@Getter
public enum FXMLView
{
    // VALUES
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    WELCOME("/view/welcome/Welcome.fxml"),
    DATA_INPUT_DIMENSIONS("/view/datainput/DataInputDimensions.fxml"),
    DATA_INPUT_MOWERS("/view/datainput/DataInputMowers.fxml"),
    DATA_INPUT_MOWER_CELL("/view/datainput/cell/DataInputMowerCell.fxml"),
    SIDEBAR_MOWER_CELL("/view/app/components/sidebar/cell/SidebarMowerCell.fxml"),
    SIDEBAR_GOPHER_CELL("/view/app/components/sidebar/cell/SidebarGopherCell.fxml"),
    LAWN_GRID_CELL("/view/app/components/contentarea/LawnGridCell.fxml"),
    MAIN_APP_CONTAINER("/view/app/AppContainer.fxml");

    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final String fxmlFile;

    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    FXMLView(final String fxmlFile)
    {
        this.fxmlFile = fxmlFile;
    }
}
