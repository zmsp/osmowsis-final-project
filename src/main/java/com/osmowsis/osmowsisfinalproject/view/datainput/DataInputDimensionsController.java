package com.osmowsis.osmowsisfinalproject.view.datainput;

import com.jfoenix.controls.JFXTextField;
import com.osmowsis.osmowsisfinalproject.config.StageManager;
import com.osmowsis.osmowsisfinalproject.view.FXMLView;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

/**
 * Controller for the Data Input view that is responsible for accepting the lawns X and Y coordinate
 *
 * Created on 11/25/2019
 */

@Slf4j
@Controller
public class DataInputDimensionsController
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final StageManager stageManager;
    private final DataInputValidationService dataInputValidationService;

    @FXML
    private JFXTextField xDimensionField;

    @FXML
    private JFXTextField yDimensionField;

    // CONSTRUCTOR
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Autowired
    public DataInputDimensionsController(@Lazy final StageManager stageManager,
                                         final DataInputValidationService dataInputValidationService)
    {
        this.stageManager = stageManager;
        this.dataInputValidationService = dataInputValidationService;
    }

    // PUBLIC METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Handles a key press while the back button is in focus
     */
    public void handleBackBtnKeyPress(final KeyEvent ke)
    {
        if(ke.getCode() == KeyCode.ENTER)
        {
            handleBackBtnClick();
        }
    }

    /**
     * Handles when the back button is clicked on the dimensions page
     */
    public void handleBackBtnClick()
    {
        stageManager.switchScene(FXMLView.WELCOME);
    }

    /**
     * Handles when a key is pressed and the next button is in focus
     */
    public void handleNextBtnKeyPress(final KeyEvent ke)
    {
        if(ke.getCode() == KeyCode.ENTER)
        {
            handleNextBtnClick();
        }
    }

    /**
     * Handles when the next button is clicked on the dimensions page
     */
    public void handleNextBtnClick()
    {
        if(dataInputValidationService.validateDimensionData(xDimensionField.getText(), yDimensionField.getText()))
        {
            // TODO: UPDATE THE MODEL HERE WITH THE DATA FROM THE INPUT

            stageManager.switchScene(FXMLView.DATA_INPUT_MOWERS);
        }
        else{
            log.error("Something went wrong with your data, display errors and do not switch page");
        }
    }

}
