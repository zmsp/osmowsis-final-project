package com.osmowsis.osmowsisfinalproject.view.controller;

import com.jfoenix.controls.JFXListView;
import com.osmowsis.osmowsisfinalproject.constant.SidebarCssConstant;
import com.osmowsis.osmowsisfinalproject.model.SimulationDataModel;
import com.osmowsis.osmowsisfinalproject.pojo.Gopher;
import com.osmowsis.osmowsisfinalproject.pojo.Mower2;
import com.osmowsis.osmowsisfinalproject.view.cell.SidebarGopherCell;
import com.osmowsis.osmowsisfinalproject.view.cell.SidebarMowerCell;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Sidebar component in the app container
 *
 * Created on 11/28/2019
 */

@Slf4j
@Controller
public class SidebarController implements Initializable
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final SimulationDataModel simulationDataModel;

    @FXML
    private FontAwesomeIconView simulationDetailsCollapsibleIcon;

    @FXML
    private FontAwesomeIconView mowerDetailsCollapsibleIcon;

    @FXML
    private FontAwesomeIconView gopherDetailsCollapsibleIcon;

    @FXML
    private VBox simulationDetailsArea;

    @FXML
    private JFXListView<Mower2> mowerListView;

    @FXML
    private JFXListView<Gopher> gopherListView;

    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Autowired
    public SidebarController(final SimulationDataModel simulationDataModel)
    {
        this.simulationDataModel = simulationDataModel;
    }

    // INIT METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // BINDS THE MOWER LIST VIEW TO THE ITEMS IN THE MODEL
        mowerListView.setItems(simulationDataModel.getMowers());
        mowerListView.setCellFactory(column -> getSidebarMowerCell());

        gopherListView.setItems(simulationDataModel.getGophers());
        gopherListView.setCellFactory(column -> getSidebarGopherCell());

        // BINDS THE HEIGHT OF THE LIST VIEW TO THE HEIGHTS OF THE CELLS TO SUPPORT DYNAMIC RE-SIZING
        mowerListView.minHeightProperty().bind(Bindings.size(simulationDataModel.getMowers())
                .multiply(getSidebarMowerCell().getSidebarMowerCellController().getSidebarMowerCell().getPrefHeight()));
        mowerListView.prefHeightProperty().bind(mowerListView.minHeightProperty());

        gopherListView.minHeightProperty().bind(Bindings.size(simulationDataModel.getGophers())
                .multiply(getSidebarGopherCell().getSidebarGopherCellController()
                        .getSidebarGopherCell().getPrefHeight()));
        gopherListView.prefHeightProperty().bind(gopherListView.minHeightProperty());

        // BINDS THE MANAGED PROPERTY TO THE VISIBLE PROPERTY FOR DYNAMIC RE-SIZING
        simulationDetailsArea.managedProperty().bind(simulationDetailsArea.visibleProperty());

        mowerListView.managedProperty().bind(mowerListView.visibleProperty());

        gopherListView.managedProperty().bind(gopherListView.visibleProperty());

    }


    // PUBLIC METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Handles when the simulation details section is clicked
     */
    public void handleSimulationDetailsBtnClick()
    {
        handleDetailsBtnClickInternal(simulationDetailsArea, simulationDetailsCollapsibleIcon);
    }

    /**
     * Expands or collapses the section when the mower details section is clicked
     */
    public void handleMowerDetailsBtnClick()
    {
        handleDetailsBtnClickInternal(mowerListView, mowerDetailsCollapsibleIcon);
    }

    /**
     * Expands or collapses the section when the gopher details section is clicked
     */
    public void handleGopherDetailsBtnClick()
    {
        handleDetailsBtnClickInternal(gopherListView, gopherDetailsCollapsibleIcon);
    }

    /**
     * Handles when the settings button is clicked
     */
    public void handleSimulationSettingsBtnClick()
    {
        log.info("The settings button was clicked");
    }

    /**
     * Handles when the start button is clicked
     */
    public void handleStartBtnClick()
    {
        log.info("The start button was clicked");
    }

    // PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Collapses or expands a header and the corresponding list view
     *
     * @param detailsArea - The details area to expand or collapse
     * @param icon - The icon to change
     */
    private void handleDetailsBtnClickInternal(final Region detailsArea, final FontAwesomeIconView icon)
    {
        detailsArea.setVisible(!detailsArea.isVisible());

        if(detailsArea.isVisible())
        {
            icon.setGlyphName(SidebarCssConstant.EXPANDED_SECTION_ICON_NAME);
        }
        else{
            icon.setGlyphName(SidebarCssConstant.COLLAPSED_SECTION_ICON_NAME);
        }
    }

    // SPRING LOOKUPS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Spring magic for handling prototype scoped tings : )
     *
     * @return - A new instance of the Sidebar Mower Cell
     */
    @Lookup
    SidebarMowerCell getSidebarMowerCell(){ return null; }

    /**
     * Spring magic for handling prototype scoped tings : )
     *
     * @return - A new instance of the Sidebar Gopher Cell
     */
    @Lookup
    SidebarGopherCell getSidebarGopherCell() { return null; }
}
