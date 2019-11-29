package com.osmowsis.osmowsisfinalproject.view.controller;

import com.osmowsis.osmowsisfinalproject.constant.FXMLView;
import com.osmowsis.osmowsisfinalproject.pojo.LawnSquare;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Scope("prototype")
@Controller
public class LawnGridCellController
{
    @FXML
    @Getter
    private HBox lawnGridCellContainer;

    @Getter
    @Setter
    private LawnSquare lawnSquare;

    public LawnGridCellController()
    {
        // PULL THE CELL FROM THE FXML FILE AND LOAD IT HERE, THIS IS HOW TO ACHIEVE DYNAMICALLY LOADING FXML FILES
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLView.LAWN_GRID_CELL.getFxmlFile()));
        loader.setController(this);

        try
        {
            loader.load();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
