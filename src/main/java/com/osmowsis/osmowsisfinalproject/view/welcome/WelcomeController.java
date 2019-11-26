package com.osmowsis.osmowsisfinalproject.view.welcome;

import com.osmowsis.osmowsisfinalproject.config.StageManager;
import com.osmowsis.osmowsisfinalproject.view.FXMLView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

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
        log.info("The import file button was clicked");
    }

    /**
     * Handles when the input data button is clicked
     */
    public void handleInputDataButtonClick()
    {
        log.info("The input data button was clicked");

        stageManager.switchScene(FXMLView.DATA_INPUT_DIMENSIONS);
    }

    /**
     * Handles when the generate data button is clicked
     */
    public void handleGenerateDataButtonClick()
    {
        log.info("The generate random button data button was clicked");
    }
}
