package com.osmowsis.osmowsisfinalproject.service;

import com.osmowsis.osmowsisfinalproject.model.SimulationDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for determining the risk profile
 *
 * Created on 11/27/2019
 */

@Service
public class SimulationRiskProfileService
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final SimulationDataModel simulationDataModel;

    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Autowired
    public SimulationRiskProfileService(final SimulationDataModel simulationDataModel)
    {
        this.simulationDataModel = simulationDataModel;
    }

    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Determines the simulation risk profile and sets the value in the data model
     */
    public void determineAndSetSimulationRiskProfile()
    {
        // TODO: PUT THE CODE HERE THAT IS USED TO DETERMINE THE RISK PROFILE
    }
}
