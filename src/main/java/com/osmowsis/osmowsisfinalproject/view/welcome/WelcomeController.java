package com.osmowsis.osmowsisfinalproject.view.welcome;

import com.osmowsis.osmowsisfinalproject.config.StageManager;
import com.osmowsis.osmowsisfinalproject.view.FXMLView;
import javafx.stage.FileChooser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.File;

/**
 * The controller that controls the welcome.fxml view
 *
 * Created on 11/25/2019
 */

@Slf4j
@Controller
public class WelcomeController
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final StageManager stageManager;

    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Autowired
    public WelcomeController(@Lazy final StageManager stageManager)
    {
        this.stageManager = stageManager;
    }

    // PUBLIC METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Handles when the import file button is clicked
     */
    public void handleImportFileBtnClick()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt", "*.dat", "*.csv"));

        File selectedFile = fileChooser.showOpenDialog(stageManager.getPrimaryStage());

        // TODO: PASS THE FILE TO THE PARSING UTILITY AND USE THAT TO IMPORT THE DATA

        // TODO: SHOW THE VIEW FOR THE MAIN SIMULATION VIEW

        log.info("The import file button was clicked");
    }

    /**
     * Handles when the input data button is clicked
     */
    public void handleInputDataButtonClick()
    {
        stageManager.switchScene(FXMLView.DATA_INPUT_DIMENSIONS);
    }

    /**
     * Handles when the generate data button is clicked
     */
    public void handleGenerateDataButtonClick()
    {
        // TODO: CREATE A SERVICE TO GENERATE RANDOM DATA

        // TODO: GENERATE A RANDOM SCENARIO

        // TODO: SHOW THE THE VIEW FOR THE MAIN SIMULATION

        log.info("The generate random button data button was clicked");
    }
}
