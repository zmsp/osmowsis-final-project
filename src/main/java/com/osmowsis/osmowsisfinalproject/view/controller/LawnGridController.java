package com.osmowsis.osmowsisfinalproject.view.controller;

import com.osmowsis.osmowsisfinalproject.model.SimulationDataModel;
import com.osmowsis.osmowsisfinalproject.pojo.LawnSquare;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the lawn grid view
 *
 * Created on 11/28/2019
 */

@Controller
public class LawnGridController implements Initializable
{
    private final SimulationDataModel simulationDataModel;

    @FXML
    private HBox gridPaneContainer;

    @FXML
    private GridPane lawnGridPane;

    @Autowired
    public LawnGridController(final SimulationDataModel simulationDataModel)
    {
        this.simulationDataModel = simulationDataModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        buildLawnModel();
    }

    private void buildLawnModel()
    {
        final int prefGridCellWidth = (int) gridPaneContainer.getPrefWidth() / simulationDataModel.getLawnXDimension();
        final int prefGridCellHeight = (int) gridPaneContainer.getPrefHeight() / simulationDataModel.getLawnYDimension();

        // ADD THE COLUMN CONSTRAINTS
        for(int i = 0; i < simulationDataModel.getLawnXDimension(); i++)
        {
            ColumnConstraints constraint = new ColumnConstraints();
            constraint.setPrefWidth(prefGridCellWidth);
            constraint.setHgrow(Priority.SOMETIMES);
            lawnGridPane.getColumnConstraints().add(constraint);
        }

        // ADD ROW CONSTRAINTS
        for(int i = 0; i < simulationDataModel.getLawnYDimension(); i++)
        {
            RowConstraints constraint = new RowConstraints();
            constraint.setPrefHeight(prefGridCellHeight);
            constraint.setVgrow(Priority.SOMETIMES);
            lawnGridPane.getRowConstraints().add(constraint);
        }

        // ADD THE LAWN CONTENT TO THE GRID
        for(LawnSquare square : simulationDataModel.getLawnSquares())
        {
            int x = square.getxCoordinate();
            int y = square.getyCoordinate();

            lawnGridPane.add(new Label("x=" + x + " y=" + y), x, getInvertedYAxisValue(y));
        }
    }

    /**
     * Since JavaFX by default inverts the Y-Axis so that the origin (0) is at the top
     * instead of the bottom, we need convert the original value to the inverted value
     *
     * @param originalValue - The original value
     *
     * @return - The inverted value
     */
    private int getInvertedYAxisValue(final int originalValue)
    {
        final int yMax = simulationDataModel.getLawnYDimension() - 1;

        return yMax - originalValue;
    }
}
