package com.osmowsis.osmowsisfinalproject.view.controller;

import com.osmowsis.osmowsisfinalproject.constant.CSS;
import com.osmowsis.osmowsisfinalproject.model.SimulationDataModel;
import com.osmowsis.osmowsisfinalproject.pojo.LawnSquare;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.*;

/**
 * Controller for the lawn grid view
 *
 * Created on 11/28/2019
 */

@Slf4j
@Controller
public class LawnGridController implements Initializable
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final SimulationDataModel simulationDataModel;

    @FXML
    private HBox gridPaneContainer;

    @FXML
    private GridPane lawnGridPane;

    private Map<LawnSquare, HBox> cellMap;

    // CONSTRUCTOR
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Autowired
    public LawnGridController(final SimulationDataModel simulationDataModel)
    {
        this.simulationDataModel = simulationDataModel;
    }

    // INIT METHOD
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        buildLawnModel();
    }

    // PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Builds the lawn model based on the lawn squares in the
     */
    private void buildLawnModel()
    {
        final int prefGridCellWidth = (int) gridPaneContainer.getPrefWidth() / simulationDataModel.getLawnXDimension();
        final int prefGridCellHeight = (int) gridPaneContainer.getPrefHeight() / simulationDataModel.getLawnYDimension();

        log.info("[PREF HEIGHT] - {}", prefGridCellHeight);
        log.info("[PREF WIDTH] - {}", prefGridCellWidth);

        cellMap = new HashMap<>();

        // ADD THE COLUMN CONSTRAINTS
        for(int i = 0; i < simulationDataModel.getLawnXDimension(); i++)
        {
            ColumnConstraints constraint = new ColumnConstraints();
            constraint.setPrefWidth(prefGridCellWidth);
            constraint.setMaxWidth(250);
            constraint.setHgrow(Priority.SOMETIMES);
            lawnGridPane.getColumnConstraints().add(constraint);
        }

        // ADD ROW CONSTRAINTS
        for(int i = 0; i < simulationDataModel.getLawnYDimension(); i++)
        {
            RowConstraints constraint = new RowConstraints();
            constraint.setPrefHeight(prefGridCellHeight);
            constraint.setMaxHeight(250);
            constraint.setVgrow(Priority.SOMETIMES);
            lawnGridPane.getRowConstraints().add(constraint);
        }

        // ADD THE LAWN CONTENT TO THE GRID
        for(LawnSquare square : simulationDataModel.getLawnSquares())
        {
            int x = square.getxCoordinate();
            int y = square.getyCoordinate();

            final LawnGridCellController controller = getLawnGridCellController();
            controller.setLawnSquare(square);

            final HBox gridCell = controller.getLawnGridCellContainer();

            gridCell.setPrefHeight(prefGridCellHeight);
            gridCell.setPrefWidth(prefGridCellWidth);

            gridCell.getStyleClass().add(CSS.GRASS_GOPHER_CLASS_NAME);

            cellMap.put(square, gridCell);

            lawnGridPane.add(gridCell, x, getInvertedYAxisValue(y));

            log.info("[ACTUAL HEIGHT] - {}", gridCell.getHeight());
            log.info("[ACTUAL WIDTH] - {}", gridCell.getWidth());
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

    @Lookup
    LawnGridCellController getLawnGridCellController(){ return null; }
}
