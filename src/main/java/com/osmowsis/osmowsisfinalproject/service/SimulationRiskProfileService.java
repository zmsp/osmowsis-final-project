package com.osmowsis.osmowsisfinalproject.service;

import com.osmowsis.osmowsisfinalproject.constant.SimulationRiskProfile;
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
    private static final int LOW_RISK_FACTOR_THRESHOLD = 5;
    private static final int MED_RISK_FACTOR_THRESHOLD = 3;

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
    public void updateSimulationRiskProfile()
    {
        final int remainingMoves = (
                simulationDataModel.getMaxTurns().get() - simulationDataModel.getCurrentTurn().get())
                * simulationDataModel.getActiveMowerCount().get();

        final int remainingGrass = simulationDataModel.getRemainingGrassToCut().get();

        final int riskFactor = remainingMoves / remainingGrass;

        if (riskFactor >= LOW_RISK_FACTOR_THRESHOLD)
        {
            simulationDataModel.setSimulationRiskProfile(SimulationRiskProfile.LOW);
        }
        else if (riskFactor >= MED_RISK_FACTOR_THRESHOLD)
        {
            simulationDataModel.setSimulationRiskProfile(SimulationRiskProfile.MEDIUM);
        }
        else
            {
            simulationDataModel.setSimulationRiskProfile(SimulationRiskProfile.HIGH);
        }
    }

    /**
     * Gets the current simulation risk profile
     *
     * @return - The current simulation risk profile
     */
    public SimulationRiskProfile getCurrentSimulationRiskProfile()
    {
        return simulationDataModel.getSimulationRiskProfile();
    }
}
