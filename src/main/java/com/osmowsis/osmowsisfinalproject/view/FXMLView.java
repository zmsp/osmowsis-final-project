package com.osmowsis.osmowsisfinalproject.view;

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
    DATA_INPUT_MOWERS("/view/datainput/DataInputMowers.fxml");

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
