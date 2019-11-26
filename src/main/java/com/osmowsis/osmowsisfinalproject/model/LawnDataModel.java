package com.osmowsis.osmowsisfinalproject.model;

import javafx.beans.property.SimpleIntegerProperty;
import org.springframework.stereotype.Repository;
import sun.java2d.pipe.SpanShapeRenderer;

/**
 * This is the central data model that the UI relies on for the lawn info
 *
 * Created on 11/26/2019
 */

@Repository
public class LawnDataModel implements BaseDataModel
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private SimpleIntegerProperty lawnXDimension;
    private SimpleIntegerProperty lawnYDimension;

    // PUBLIC METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Completely resets the data model
     */
    @Override
    public void resetDataModel()
    {
        lawnXDimension = null;
        lawnYDimension = null;
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
        if(lawnXDimension == null)
        {
            lawnXDimension = new SimpleIntegerProperty();
        }

        if(lawnYDimension == null)
        {
            lawnYDimension = new SimpleIntegerProperty();
        }

        lawnXDimension.set(xValue);
        lawnYDimension.set(yValue);
    }
}
