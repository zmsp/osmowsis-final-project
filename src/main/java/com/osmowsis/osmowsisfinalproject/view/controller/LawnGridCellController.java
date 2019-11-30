package com.osmowsis.osmowsisfinalproject.view.controller;

import com.osmowsis.osmowsisfinalproject.constant.CSS;
import com.osmowsis.osmowsisfinalproject.constant.FXMLView;
import com.osmowsis.osmowsisfinalproject.constant.LawnSquareContent;
import com.osmowsis.osmowsisfinalproject.model.SimulationDataModel;
import com.osmowsis.osmowsisfinalproject.pojo.Gopher;
import com.osmowsis.osmowsisfinalproject.pojo.LawnSquare;
import com.osmowsis.osmowsisfinalproject.pojo.Mower;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Scope("prototype")
@Controller
public class LawnGridCellController implements Initializable
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String MOWER_ABBREVIATION_PREFIX = "M";
    private static final String GOPHER_ABBREVIATION_PREFIX = "G";

    private final SimulationDataModel simulationDataModel;

    @FXML
    @Getter
    private HBox lawnGridCellContainer;

    @FXML
    private VBox mowerInfoContainer;

    @FXML
    private Label mowerAbbreviationLabel;

    @FXML
    private Label directionAbbreviation;

    @FXML
    private FontAwesomeIconView directionIcon;

    @FXML
    private HBox gopherInfoContainer;

    @FXML
    private Label gopherAbbreviationLabel;

    @FXML
    private FontAwesomeIconView chargerIcon;

    @Getter
    @Setter
    private LawnSquare lawnSquare;

    // CONSTRUCTOR
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Autowired
    @Lazy
    public LawnGridCellController(final SimulationDataModel simulationDataModel)
    {
        this.simulationDataModel = simulationDataModel;

        // PULL THE CELL FROM THE FXML FILE AND LOAD IT HERE, THIS IS HOW TO ACHIEVE DYNAMICALLY LOADING FXML FILES
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLView.LAWN_GRID_CELL.getFxmlFile()));
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

    // INIT METHOD
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // BIND THE MANAGED PROPERTIES WITH THE VISIBLE PROPERTIES
        mowerInfoContainer.managedProperty().bind(mowerInfoContainer.visibleProperty());
        gopherInfoContainer.managedProperty().bind(gopherInfoContainer.visibleProperty());
        chargerIcon.managedProperty().bind(chargerIcon.visibleProperty());
    }

    // PUBLIC METHOD
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void updateLawnCellUI()
    {
        final LawnSquareContent content = lawnSquare.getLawnSquareContent();

        final int x = lawnSquare.getxCoordinate();
        final int y = lawnSquare.getyCoordinate();

        // SET THE GRASS OR EMPTY BACK DROP BASED ON THE CONTENT
        if(content == LawnSquareContent.GRASS || content == LawnSquareContent.GRASS_GOPHER)
        {
            lawnGridCellContainer.getStyleClass().remove(CSS.EMPTY_CLASS_NAME);
            lawnGridCellContainer.getStyleClass().add(CSS.GRASS_CLASS_NAME);
        }
        else{
            lawnGridCellContainer.getStyleClass().remove(CSS.GRASS_CLASS_NAME);
            lawnGridCellContainer.getStyleClass().add(CSS.EMPTY_CLASS_NAME);
        }

        // SET THE MOWER INFO AND DIRECTION IF APPLICABLE
        if(content == LawnSquareContent.EMPTY_MOWER
                || content == LawnSquareContent.EMPTY_MOWER_CHARGER)
        {
            Mower mower = simulationDataModel.getMowerByCoordinates(x, y);

            mowerAbbreviationLabel.setText(MOWER_ABBREVIATION_PREFIX + (mower.getMowerNumber() + 1));
            directionAbbreviation.setText(mower.getCurrentDirection().getAbbreviation());
            directionIcon.setRotate(mower.getCurrentDirection().getIconAngle());

            mowerInfoContainer.setVisible(true);
        }
        else{
            mowerInfoContainer.setVisible(false);
        }

        // SET THE GOPHER ICON IF APPLICABLE
        if(content == LawnSquareContent.EMPTY_GOPHER
                || content == LawnSquareContent.EMPTY_GOPHER_CHARGER
                || content == LawnSquareContent.GRASS_GOPHER)
        {
            Gopher gopher = simulationDataModel.getGopherByCoordinates(x, y);

            gopherAbbreviationLabel.setText(GOPHER_ABBREVIATION_PREFIX + (gopher.getGopherNumber() + 1));

            gopherInfoContainer.setVisible(true);
        }
        else{
            gopherInfoContainer.setVisible(false);
        }

        // SET THE CHARGER ICON IF APPLICABLE
        if(content == LawnSquareContent.EMPTY_CHARGER
                || content == LawnSquareContent.EMPTY_MOWER_CHARGER
                || content == LawnSquareContent.EMPTY_GOPHER_CHARGER)
        {
            chargerIcon.setVisible(true);
        }
        else{
            chargerIcon.setVisible(false);
        }
    }
}
