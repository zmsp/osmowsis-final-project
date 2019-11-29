package com.osmowsis.osmowsisfinalproject.view.controller;

import com.osmowsis.osmowsisfinalproject.constant.FXMLView;
import com.osmowsis.osmowsisfinalproject.pojo.Gopher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Scope("prototype")
public class SidebarGopherCellController
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String GOPHER_NAME_PREFIX = "Gopher";
    private static final String GOPHER_POSITION_DELIMITER = ", ";

    @FXML
    @Getter
    private HBox sidebarGopherCell;

    @FXML
    private Label gopherName;

    @FXML
    private Label gopherPositionLabel;

    @Getter
    private Gopher gopher;

    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public SidebarGopherCellController()
    {
        // PULL THE CELL FROM THE FXML FILE AND LOAD IT HERE, THIS IS HOW TO ACHIEVE DYNAMICALLY LOADING FXML FILES
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLView.SIDEBAR_GOPHER_CELL.getFxmlFile()));
        loader.setController(this);

        try
        {
            loader.load();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    // PUBLIC METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Sets the cell info based on the gopher info
     *
     * @param gopher- The gopher that the cell info should correlate to
     */
    public void setCellInfo(final Gopher gopher)
    {
        this.gopher = gopher;

        updateGopherName();
        updateGopherPositionLabel();
    }

    // PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Updates the gopher name on the UI
     */
    private void updateGopherName()
    {
        gopherName.setText(GOPHER_NAME_PREFIX + " " + (gopher.getGopherNumber() + 1));
    }

    /**
     * Updates the gopher position on the UI
     */
    private void updateGopherPositionLabel()
    {
        gopherPositionLabel.setText(gopher.getXCoordinate() + GOPHER_POSITION_DELIMITER + gopher.getYCoordinate());
    }
}
