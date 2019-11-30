package com.osmowsis.osmowsisfinalproject.pojo;

import com.osmowsis.osmowsisfinalproject.constant.Direction;
import com.osmowsis.osmowsisfinalproject.constant.MowerMovementType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Class represents a move a mower can attempt to make
 *
 * Created by L. Arroyo onm 9/11/2019
 */

@Getter
@ToString
@EqualsAndHashCode
public class MowerMove
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final Mower mower;
    private final MowerMovementType mowerMovementType;
    private final Direction direction;
    private final int currentXCoordinate;
    private final int currentYCoordinate;
    private int newXCoordinate;
    private int newYCoordinate;

    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public MowerMove(final Mower mower,
                     final MowerMovementType mowerMovementType,
                     final Direction direction,
                     final int currentXCoordinate,
                     final int currentYCoordinate)
    {
        this.mower = mower;
        this.mowerMovementType = mowerMovementType;
        this.direction = direction;
        this.currentXCoordinate = currentXCoordinate;
        this.currentYCoordinate = currentYCoordinate;
    }

    public MowerMove(final Mower mower,
                     final MowerMovementType mowerMovementType,
                     final Direction direction,
                     final int currentXCoordinate,
                     final int currentYCoordinate,
                     final int newXCoordinate,
                     final int newYCoordinate)
    {
        this.mower = mower;
        this.mowerMovementType = mowerMovementType;
        this.direction = direction;
        this.currentXCoordinate = currentXCoordinate;
        this.currentYCoordinate = currentYCoordinate;
        this.newXCoordinate = newXCoordinate;
        this.newYCoordinate = newYCoordinate;
    }
}