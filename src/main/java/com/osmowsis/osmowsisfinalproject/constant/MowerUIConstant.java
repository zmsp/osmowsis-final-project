package com.osmowsis.osmowsisfinalproject.constant;

/**
 * Constants which are used to display mower info in the UI
 *
 * Created on 11/28/2019
 */

public final class MowerUIConstant
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String MOWER_NAME_PREFIX = "Mower";

    public static final int BATTERY_FULL_THRESHOLD = 75;
    public static final int BATTERY_THREE_QUARTERS_THRESHOLD = 50;
    public static final int BATTERY_HALF_THRESHOLD = 25;
    public static final int BATTERY_QUARTER_THRESHOLD = 0;

    // CONSTRUCTOR
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private MowerUIConstant()
    {
        throw new AssertionError();
    }
}
