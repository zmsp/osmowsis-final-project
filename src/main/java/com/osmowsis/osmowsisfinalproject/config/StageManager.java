package com.osmowsis.osmowsisfinalproject.config;

import com.osmowsis.osmowsisfinalproject.constant.FXMLView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * Component made to manage the stage and scenes through out the application
 *
 * Created 11/25/2019
 */

@Slf4j
public class StageManager
{
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Getter
    private final Stage primaryStage;

    private final SpringFXMLLoader springFXMLLoader;

    private double xOffset = 0;
    private double yOffset = 0;

    // CONSTRUCTORS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public StageManager(SpringFXMLLoader springFXMLLoader, Stage stage)
    {
        this.springFXMLLoader = springFXMLLoader;
        this.primaryStage = stage;
    }

    // PUBLIC METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Switches scenes of the stage
     *
     * @param view - The view to switch to
     */
    public void switchScene(final FXMLView view)
    {
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());
        show(viewRootNodeHierarchy);
    }

    /**
     * Minimizes the main stage
     */
    public void minimizeMainStage()
    {
        primaryStage.setIconified(true);
    }

    /**
     * Closes the main stage
     */
    public void closeMainStage()
    {
        primaryStage.close();
    }

    /**
     * Logs the error message and exits the application
     *
     * @param msg - The error message
     * @param e - The exception
     */
    public void logAndExit(String msg, Exception e)
    {
        log.error(msg, e);

        primaryStage.close();

        System.exit(0);
    }

    // PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Shows the scene
     *
     * @param rootNode - The parent root node
     */
    private void show(final Parent rootNode)
    {
        rootNode.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        rootNode.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
        });

        Scene scene = prepareScene(rootNode);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();

        if(xOffset == 0 && yOffset == 0)
        {
            primaryStage.centerOnScreen();
        }

        try
        {
            primaryStage.show();
        }
        catch(Exception e)
        {
            logAndExit("Unable to show scene", e);
        }
    }

    /**
     * Prepares the scene before it is set
     *
     * @param rootNode - The root parent node of the new scene
     *
     * @return - The new scene
     */
    private Scene prepareScene(Parent rootNode)
    {
        Scene scene = primaryStage.getScene();

        if(scene == null)
        {
            scene = new Scene(rootNode);
        }

        scene.setRoot(rootNode);
        return scene;
    }

    /**
     * Loads the object hierarchy from the FXML document and returns to the root node of that hierarchy
     *
     * @param fxmlFilePath - Path to the FXML
     *
     * @return - The parent root node of the FXML document hierarchy
     */
    private Parent loadViewNodeHierarchy(String fxmlFilePath)
    {
        Parent rootNode = null;

        try
        {
            rootNode = springFXMLLoader.load(fxmlFilePath);
            Objects.requireNonNull(rootNode, "A Root FXML node must not be null");
        }
        catch(Exception e)
        {
            logAndExit("Unable to load FXML View", e);
        }

        return rootNode;
    }
}