package com.osmowsis.osmowsisfinalproject.view.controller;

import com.osmowsis.osmowsisfinalproject.config.StageManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

/**
 * Controller for the Header component in the app container
 *
 * Created on 11/28/2019
 */

@Slf4j
@Controller
public class HeaderController
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final StageManager stageManager;

    // CONSTRUCTOR
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Autowired
    public HeaderController(@Lazy final StageManager stageManager)
    {
        this.stageManager = stageManager;
    }

    // PUBLIC METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Handles when the minimize button is clicked
     */
    public void handleMinimizeBtnClick()
    {
        stageManager.minimizeMainStage();
    }

    /**
     * Handles when the exit button is clicked
     */
    public void handleExitBtnClick()
    {
        stageManager.closeMainStage();

        System.exit(0);
    }
}
