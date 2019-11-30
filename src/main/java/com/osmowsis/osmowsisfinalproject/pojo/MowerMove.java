package com.osmowsis.osmowsisfinalproject.pojo;

import com.osmowsis.osmowsisfinalproject.constant.Direction;
import com.osmowsis.osmowsisfinalproject.constant.MowerMovementType;
import lombok.ToString;

/**
 * Class represents a move a mower can attempt to make
 *
 * Created by L. Arroyo onm 9/11/2019
 */

@ToString
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

    // ACCESS METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Mower getMower() {
        return mower;
    }

    public MowerMovementType getMowerMovementType() {
        return mowerMovementType;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCurrentXCoordinate() {
        return currentXCoordinate;
    }

    public int getCurrentYCoordinate() {
        return currentYCoordinate;
    }

    public int getNewXCoordinate() {
        return newXCoordinate;
    }

    public int getNewYCoordinate() {
        return newYCoordinate;
    }
}