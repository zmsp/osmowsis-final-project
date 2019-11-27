package com.osmowsis.osmowsisfinalproject.constant;

import lombok.Getter;

/**
 * Enum that represents the various directions a mower can face or travel
 *
 * Created on 9/11/19
 */
public enum Direction
{
    // VALUES
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    NORTH(0, 0, 1, "N", 0),
    NORTHEAST(1, 1, 1, "NE", 45),
    EAST(2, 1, 0, "E", 90),
    SOUTHEAST(3, 1, -1, "SE", 135),
    SOUTH(4, 0, -1, "S", 180),
    SOUTHWEST(5, -1, -1, "SW", 225),
    WEST(6, -1, 0, "W", 270),
    NORTHWEST(7, -1, 1, "NW", 315);

    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final int index;
    private final int xIncrement;
    private final int yIncrement;
    private final int iconAngle;
    private final String abbreviation;


    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Direction(final int index, final int xIncrement, final int yIncrement, final String abbreviation, final int iconAngle)
    {
        this.index = index;
        this.xIncrement = xIncrement;
        this.yIncrement = yIncrement;
        this.abbreviation = abbreviation;
        this.iconAngle = iconAngle;
    }

    // ACCESS METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getIndex() {
        return index;
    }

    public int getxIncrement() {
        return xIncrement;
    }

    public int getyIncrement() {
        return yIncrement;
    }

    public int getIconAngle(){ return iconAngle; }

    public String getAbbreviation(){ return abbreviation; }
    // STATIC METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Gets a direction based on the index value
     *
     * @param index - The index value
     *
     * @return - The direction
     */
    public static Direction getDirectionByIndex(final int index)
    {
        Direction result = null;

        for(Direction direction : values())
        {
            if(direction.index == index)
            {
                result = direction;
            }
        }

        return result;
    }
}
