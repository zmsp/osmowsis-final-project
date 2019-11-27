package com.osmowsis.osmowsisfinalproject.model;

import com.osmowsis.osmowsisfinalproject.constant.Direction;
import com.osmowsis.osmowsisfinalproject.mower.Mower2;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.springframework.stereotype.Repository;

/**
 * Central data model for the simulation
 *
 * Created on 11/26/2019
 */

@Repository
public class SimulationDataModel implements BaseDataModel
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private SimpleIntegerProperty lawnXDimension;
    private SimpleIntegerProperty lawnYDimension;
    private SimpleIntegerProperty startingMowerEnergy;

    @Getter
    private ObservableList<Mower2> mowers;

    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public SimulationDataModel()
    {
        // SET DEFAULT VALUES
        mowers = FXCollections.observableArrayList();

        lawnXDimension = new SimpleIntegerProperty();
        lawnYDimension = new SimpleIntegerProperty();
        startingMowerEnergy = new SimpleIntegerProperty();
    }

    // PUBLIC METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Resets the data model
     */
    @Override
    public void resetDataModel()
    {
        mowers = FXCollections.observableArrayList();

        lawnXDimension = new SimpleIntegerProperty();
        lawnYDimension = new SimpleIntegerProperty();
    }

    /**
     * Updates the lawn dimensions
     *
     * Note:
     * This method should only be getting called when initially processing the file or data input
     *
     * @param xValue - The X Dimension
     * @param yValue - The Y Dimension
     */
    public void updateLawnDimensions(final int xValue, final int yValue)
    {
        lawnXDimension.set(xValue);
        lawnYDimension.set(yValue);
    }

    /**
     * Updates the starting mower energy
     *
     * Note:
     * This method should only be getting called when initially processing the file or data input
     *
     * @param startingEnergy - The starting mower energy
     */
    public void updateStartingMowerEnery(final int startingEnergy)
    {
        startingMowerEnergy.set(startingEnergy);
    }

    /**
     * Adds a new mower to the model
     *
     * Note:
     * This method should only be getting called when initially processing the file or data input
     *
     * @param xCoordinate - The X Coordinate that the mower is starting at
     * @param yCoordinate - The Y Coordinate that the mower is starting at
     * @param startingDirection - The direction that the mower is facing
     * @param strategic - Is the mower following a strategic algorithm (true) or random (false)
     */
    public void addNewMowerToModel(final int xCoordinate,
                                   final int yCoordinate,
                                   final Direction startingDirection,
                                   final boolean strategic)
    {
        Mower2 mower = new Mower2();
        mower.setMowerNumber(mowers.size());
        mower.setCurrentDirection(startingDirection);
        mower.setStrategic(strategic);
        mower.setDisabled(false); // DISABLED FALSE BY DEFAULT
        mower.setCurrentEnergy(getStartingMowerEnergy());

        mowers.add(mower);

        // TODO: IF WE CAN SET THE X AND Y COORDINATE ON THE MOWER, DO SO, IF NOT THEN WE WILL NEED TO UPDATE THOSE LATER

        // TODO: USE THE X AND Y COORDINATE TO UPDATE THE LAWN MODEL SO IT CHANGES THE CONTENT OF THAT SQUARE TO A MOWER
    }

    // GETTER ACCESS METHODS FOR SIMPLE PROPERTIES
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getStartingMowerEnergy()
    {
        return startingMowerEnergy.get();
    }
}
