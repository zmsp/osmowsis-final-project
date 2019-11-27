package com.osmowsis.osmowsisfinalproject.view.datainput.validation;

import com.osmowsis.osmowsisfinalproject.constant.LawnConstant;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Validation service responsible for validating the data input into the data screen
 *
 * Created on 11/25/2019
 */

@Service
public class DataInputValidationService
{
    // PUBLIC METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Validate the X and Y dimensions that were input by the user
     *
     * @param xDimensionStr - The X dimension string for the lawn
     * @param yDimensionStr - The Y dimension string for the lawn
     *
     * @return - A collection of the validation errors, an empty collection means no errors
     */
    public Set<DataInputError> validateDimensionData(final String xDimensionStr,
                                                     final String yDimensionStr)
    {
        Set<DataInputError> errors = new HashSet<>();

        validateDimensionInternal(errors, xDimensionStr, DataInputError.INVALID_X_COORDINATE, LawnConstant.LAWN_MIN_X_WIDTH, LawnConstant.LAWN_MAX_X_WIDTH);
        validateDimensionInternal(errors, yDimensionStr, DataInputError.INVALID_Y_COORDINATE, LawnConstant.LAWN_MIN_Y_HEIGHT, LawnConstant.LAWN_MAX_Y_HEIGHT);

        return errors;
    }

    // PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Validates the dimension and adds applicable errors if validation fails
     *
     * @param errors - The collection of errors
     * @param dimensionStr - The dimension string to try to convert to a int
     * @param error - The error to be added to the collection if the validation fails
     * @param min - The minimum value that the dimension can be (inclusive)
     * @param max - The maximum value that the dimension can be (inclusive)
     */
    private void validateDimensionInternal(final Set<DataInputError> errors,
                                           final String dimensionStr,
                                           final DataInputError error,
                                           final int min,
                                           final int max)
    {
        try
        {
            int dimension = Integer.parseInt(dimensionStr);

            if(dimension < min || dimension > max)
            {
                errors.add(error);
            }
        }
        catch(NumberFormatException e)
        {
            errors.add(error);
        }
    }
}
