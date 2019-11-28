package com.osmowsis.osmowsisfinalproject.view.controller;

import com.osmowsis.osmowsisfinalproject.constant.MowerUIConstant;
import com.osmowsis.osmowsisfinalproject.model.SimulationDataModel;
import com.osmowsis.osmowsisfinalproject.pojo.Mower2;
import com.osmowsis.osmowsisfinalproject.constant.FXMLView;
import com.osmowsis.osmowsisfinalproject.constant.DataInputMowerCellCssConstant;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * This is the controller for the DataInputMowerCell, each cell (Mower) gets its own controller
 *
 * Note:
 * All of these fields are needed, even though IntelliJ will mark them as unused, this is because
 * we are dynamically creating the cells and instances of this controller as they get added to the model
 * so when you are looking in the IDE, it does not know to link the FXML annotated fields to the FXML file
 * and the fields annotated with the lombok getter annotation are called through the getter in the actual cell
 *
 * Created on 11/26/2019
 */

@Component
@Scope("prototype")
public class DataInputMowerCellController
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final SimulationDataModel simulationDataModel;

    @FXML
    @Getter
    private HBox dataInputMowerCell;

    @FXML
    private FontAwesomeIconView powerIcon;

    @FXML
    private Label mowerName;

    @FXML
    private FontAwesomeIconView batteryIcon;

    @FXML
    private Label batteryEnergyLabel;

    @FXML
    private Label directionAbbreviation;

    @FXML
    private FontAwesomeIconView directionIcon;

    @Getter
    private Mower2 mower;

    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Lazy
    @Autowired
    public DataInputMowerCellController(final SimulationDataModel simulationDataModel)
    {
        this.simulationDataModel = simulationDataModel;

        // PULL THE CELL FROM THE FXML FILE AND LOAD IT HERE, THIS IS HOW TO ACHIEVE DYNAMICALLY LOADING FXML FILES
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLView.DATA_INPUT_MOWER_CELL.getFxmlFile()));
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
     * Sets the cell info based on the mower info
     *
     * @param mower - The mower that the cell info should correlate to
     */
    public void setCellInfo(final Mower2 mower)
    {
        updatePowerIcon(mower);
        updateMowerName(mower);
        updateBatteryIcon(mower);
        updateBatteryEnergyLabel(mower);
        updateDirectionInfo(mower);
    }

    // PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Updates the power icon to be active (green) or disabled (red) based on the status of the mower
     *
     * @param mower - The mower
     */
    private void updatePowerIcon(final Mower2 mower)
    {
        if(mower.isDisabled()
                && !powerIcon.getStyleClass().contains(DataInputMowerCellCssConstant.POWER_ICON_DISABLED_CLASS))
        {
            powerIcon.getStyleClass().remove(DataInputMowerCellCssConstant.POWER_ICON_ACTIVE_CLASS);
            powerIcon.getStyleClass().add(DataInputMowerCellCssConstant.POWER_ICON_DISABLED_CLASS);
        }
        else if(!mower.isDisabled()
                && !powerIcon.getStyleClass().contains(DataInputMowerCellCssConstant.POWER_ICON_ACTIVE_CLASS))
        {
            powerIcon.getStyleClass().remove(DataInputMowerCellCssConstant.POWER_ICON_DISABLED_CLASS);
            powerIcon.getStyleClass().add(DataInputMowerCellCssConstant.POWER_ICON_ACTIVE_CLASS);
        }
    }

    /**
     * Updates the name of the mower for the label
     *
     * @param mower - The mower
     */
    private void updateMowerName(final Mower2 mower)
    {
        mowerName.setText(MowerUIConstant.MOWER_NAME_PREFIX + " " + (mower.getMowerNumber() + 1));
    }

    /**
     * Updates the battery icon based on the remaining energy vs the starting energy
     *
     * @param mower - The mower
     */
    private void updateBatteryIcon(final Mower2 mower)
    {
        double percentRemaining = convertRemainingEnergyToPercent(mower.getCurrentEnergy());

        if(percentRemaining > MowerUIConstant.BATTERY_FULL_THRESHOLD)
        {
            batteryIcon.setGlyphName(DataInputMowerCellCssConstant.FULL_BATTERY_ICON_NAME);
        }
        else if(percentRemaining > MowerUIConstant.BATTERY_THREE_QUARTERS_THRESHOLD)
        {
            batteryIcon.setGlyphName(DataInputMowerCellCssConstant.THREE_QUARTERS_BATTERY_ICON_NAME);
        }
        else if(percentRemaining > MowerUIConstant.BATTERY_HALF_THRESHOLD)
        {
            batteryIcon.setGlyphName(DataInputMowerCellCssConstant.HALF_BATTERY_ICON_NAME);
        }
        else if(percentRemaining > MowerUIConstant.BATTERY_QUARTER_THRESHOLD)
        {
            batteryIcon.setGlyphName(DataInputMowerCellCssConstant.QUARTER_BATTERY_ICON_NAME);
        }
        else{
            batteryIcon.setGlyphName(DataInputMowerCellCssConstant.EMPTY_BATTERY_ICON_NAME);
        }
    }

    /**
     * Converts the mowers remaining energy to a percent based on the starting value
     *
     * @param remainingEnergy - The mowers current remaining energy
     *
     * @return - The remaining energy converted to a percent
     */
    private double convertRemainingEnergyToPercent(int remainingEnergy)
    {
        return Math.floor(remainingEnergy / (double) simulationDataModel.getStartingMowerEnergy() * 100);
    }

    /**
     * Updates the remaining battery energy based on the mower
     *
     * @param mower - The mower
     */
    private void updateBatteryEnergyLabel(final Mower2 mower)
    {
        batteryEnergyLabel.setText(Integer.toString(mower.getCurrentEnergy()));
    }

    /**
     * Updates the direction abbreviation and arrow based on the mower
     *
     * @param mower - The mower
     */
    private void updateDirectionInfo(final Mower2 mower)
    {
        directionAbbreviation.setText(mower.getCurrentDirection().getAbbreviation());
        directionIcon.setRotate(mower.getCurrentDirection().getIconAngle());
    }
}
