package com.osmowsis.osmowsisfinalproject.constant;

import lombok.Getter;

/**
 * Enum that represents that type of content that can be in a lawn square
 *
 * Created on 9/11/2019
 */

@Getter
public enum LawnSquareContent
{
    // VALUES
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    EMPTY("empty"),
    EMPTY_MOWER(""),
    EMPTY_GOPHER(""),
    EMPTY_CHARGER(""),
    EMPTY_MOWER_GOPHER(""),
    EMPTY_MOWER_CHARGER(""),
    EMPTY_GOPHER_CHARGER(""),
    EMPTY_MOWER_GOPHER_CHARGER(""),

    GRASS("grass"),
    GRASS_GOPHER(""),

    CRATER(""), // NEEDS TO BE DEPRECIATED

    FENCE(""),

    MOWER(""), // NEEDS TO BE DEPRECIATED

    UNKNOWN("");

    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final String cssClass;

    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    LawnSquareContent(final String cssClass)
    {
        this.cssClass = cssClass;
    }
}
