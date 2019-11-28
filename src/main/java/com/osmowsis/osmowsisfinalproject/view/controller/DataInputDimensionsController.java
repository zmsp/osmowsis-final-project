package com.osmowsis.osmowsisfinalproject.view.controller;

import com.jfoenix.controls.JFXTextField;
import com.osmowsis.osmowsisfinalproject.config.StageManager;
import com.osmowsis.osmowsisfinalproject.constant.Direction;
import com.osmowsis.osmowsisfinalproject.model.SimulationDataModel;
import com.osmowsis.osmowsisfinalproject.constant.FXMLView;
import com.osmowsis.osmowsisfinalproject.constant.DataInputError;
import com.osmowsis.osmowsisfinalproject.service.DataInputValidationService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Controller for the Data Input view that is responsible for accepting the lawns X and Y dimensions
 *
 * Created on 11/25/2019
 */

@Controller
public class DataInputDimensionsController implements Initializable
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final StageManager stageManager;
    private final DataInputValidationService dataInputValidationService;
    private final SimulationDataModel simulationDataModel;

    @FXML
    private JFXTextField xDimensionField;

    @FXML
    private JFXTextField yDimensionField;

    @FXML
    private HBox errorContainer;

    @FXML
    private Label xDimensionErrorLabel;

    @FXML
    private Label yDimensionErrorLabel;

    // CONSTRUCTOR
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Autowired
    public DataInputDimensionsController(@Lazy final StageManager stageManager,
                                         final DataInputValidationService dataInputValidationService,
                                         final SimulationDataModel simulationDataModel)
    {
        this.stageManager = stageManager;
        this.dataInputValidationService = dataInputValidationService;
        this.simulationDataModel = simulationDataModel;
    }

    // INIT METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // BIND THE MANAGED PROPERTIES WITH THE VISIBLE PROPERTIES FOR THE ERROR LABELS, THIS ALLOWS CONTAINER TO FLEX
        xDimensionErrorLabel.managedProperty().bindBidirectional(xDimensionErrorLabel.visibleProperty());
        yDimensionErrorLabel.managedProperty().bindBidirectional(yDimensionErrorLabel.visibleProperty());
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
        final String xDimensionStr = xDimensionField.getText().trim();
        final String yDimensionStr = yDimensionField.getText().trim();

        hideAllErrorInfo();

        Set<DataInputError> errors = dataInputValidationService.validateDimensionData(xDimensionStr, yDimensionStr);

        // ADD DATA TO THE MODEL IF THERE ARE NO VALIDATION ERRORS
        if(errors.isEmpty())
        {
            // INTEGER PARSING CANNOT THROW NFE BECAUSE THE VALIDATION SERVICE ALREADY CHECKED FOR IT
            simulationDataModel.setInitialLawnDimensions(Integer.parseInt(xDimensionStr), Integer.parseInt(yDimensionStr));

            // TODO: REMOVE THIS AFTER, JUST TESTING THAT THIS WORKS PRIOR TO ADDING MODAL
            simulationDataModel.addNewMowerToModel(2, 3, Direction.SOUTH, false);
            simulationDataModel.addNewMowerToModel(2, 3, Direction.NORTH, false);
            simulationDataModel.addNewMowerToModel(2, 3, Direction.NORTHWEST, false);

            stageManager.switchScene(FXMLView.DATA_INPUT_MOWERS);
        }

        // DISPLAY VALIDATION ERROR MESSAGES IF APPLICABLE
        else{
            if(errors.contains(DataInputError.INVALID_X_COORDINATE) && !xDimensionErrorLabel.isVisible())
            {
                xDimensionErrorLabel.setVisible(true);
            }

            if(errors.contains(DataInputError.INVALID_Y_COORDINATE) && !yDimensionErrorLabel.isVisible())
            {
                yDimensionErrorLabel.setVisible(true);
            }

            if(!errorContainer.isVisible())
            {
                errorContainer.setVisible(true);
            }
        }
    }

    // PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Hides all of the error info
     */
    private void hideAllErrorInfo()
    {
        if(errorContainer.isVisible())
        {
            errorContainer.setVisible(false);
        }

        if(xDimensionErrorLabel.isVisible())
        {
            xDimensionErrorLabel.setVisible(false);
        }

        if(yDimensionErrorLabel.isVisible())
        {
            yDimensionErrorLabel.setVisible(false);
        }
    }
}
