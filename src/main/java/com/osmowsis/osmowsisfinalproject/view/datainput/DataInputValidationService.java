package com.osmowsis.osmowsisfinalproject.view.datainput;

import org.springframework.stereotype.Component;

/**
 * Validation service responsible for validating the data input into the data screen
 *
 * Created on 11/25/2019
 */

@Component
public class DataInputValidationService
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final int MIN_X_WIDTH = 1;
    private static final int MAX_X_WIDTH = 15;

    private static final int MIN_Y_HEIGHT = 1;
    private static final int MAX_Y_HEIGHT = 10;

    // PUBLIC METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Validated the X and Y dimensions that were input by the user
     *
     * @param xDimensionStr - The X dimension for the lawn
     * @param yDimensionStr - The Y dimension for the lawn
     *
     * @return - True if the dimensions are valid
     */
    public boolean validateDimensionData(final String xDimensionStr,
                                         final String yDimensionStr)
    {
        boolean response = true;

        try
        {
            int xDimension = Integer.parseInt(xDimensionStr.trim());
            int yDimension = Integer.parseInt(yDimensionStr.trim());

            if(xDimension < MIN_X_WIDTH
                    || xDimension > MAX_X_WIDTH
                    || yDimension < MIN_Y_HEIGHT
                    || yDimension > MAX_Y_HEIGHT)
            {
                response = false;
            }
        }
        catch(NumberFormatException e)
        {
            response = false;
        }

        return response;
    }
}
