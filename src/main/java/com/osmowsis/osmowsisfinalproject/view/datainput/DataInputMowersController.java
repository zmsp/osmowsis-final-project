package com.osmowsis.osmowsisfinalproject.view.datainput;

import com.osmowsis.osmowsisfinalproject.config.StageManager;
import com.osmowsis.osmowsisfinalproject.view.FXMLView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

/**
 * Controller for the view for adding mower info on the data input flow
 *
 * Created on 11/26/2019
 */

@Controller
public class DataInputMowersController
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final StageManager stageManager;

    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Autowired
    public DataInputMowersController(@Lazy final StageManager stageManager)
    {
        this.stageManager = stageManager;
    }

    // PUBLIC METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Handles when a key is pressed and the back button is in focus
     */
    public void handleBackBtnKeyPress(KeyEvent ke)
    {
        if(ke.getCode() == KeyCode.ENTER)
        {
            handleBackBtnClick();
        }
    }

    /**
     * Handles when the back button is pressed
     */
    public void handleBackBtnClick()
    {
        stageManager.switchScene(FXMLView.DATA_INPUT_DIMENSIONS);
    }

    /**
     * Handles when a key is pressed and the add mower button is in focus
     */
    public void handleAddMowerKeyPress(KeyEvent ke)
    {
        if(ke.getCode() == KeyCode.ENTER)
        {
            handleAddMowerBtnClick();
        }
    }

    /**
     * Handles when the add mower button is clicked
     */
    public void handleAddMowerBtnClick()
    {
        // TODO: IMPL MODAL POP UP FOR ADDING MOWER INFO
    }

    /**
     * Handles when a key is pressed and the next button is in focus
     */
    public void handleNextBtnKeyPress(KeyEvent ke)
    {
        if(ke.getCode() == KeyCode.ENTER)
        {
            handleNextBtnClick();
        }
    }

    /**
     * Handles when the next button is clicked
     */
    public void handleNextBtnClick()
    {
        // TODO: PERFORM VALIDATION AND THEN MOVE ON TO THE NEXT PAGE
    }
}
