package com.osmowsis.osmowsisfinalproject.pojo;

import com.osmowsis.osmowsisfinalproject.constant.LawnSquareContent;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class that represents an individual lawn square
 *
 * Created on 9/11/2019
 */

@Data
@AllArgsConstructor
public class LawnSquare
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final int xCoordinate;
    private final int yCoordinate;
    private LawnSquareContent lawnSquareContent;
}