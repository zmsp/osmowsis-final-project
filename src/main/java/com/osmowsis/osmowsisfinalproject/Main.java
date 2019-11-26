package com.osmowsis.osmowsisfinalproject;

import com.osmowsis.osmowsisfinalproject.config.StageManager;
import com.osmowsis.osmowsisfinalproject.view.FXMLView;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main extends Application {
    // FIELDS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected StageManager stageManager;
    protected ConfigurableApplicationContext context;

    // PUBLIC METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(final String [] args)
    {
        Application.launch(args);
    }

    // INIT METHOD
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void init() throws Exception
    {
        context = bootstrapSpringApplicationContext();
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stageManager = context.getBean(StageManager.class, stage);
        displayInitialScene();
    }

    @Override
    public void stop() throws Exception {
        context.close();
    }

    // PROTECTED METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void displayInitialScene()
    {
        stageManager.switchScene(FXMLView.WELCOME);
    }

    // PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private ConfigurableApplicationContext bootstrapSpringApplicationContext()
    {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);

        String [] args = getParameters().getRaw().toArray(new String[0]);

        builder.headless(false); // FOR TEST FX INTEGRATION

        return builder.run(args);
    }
}
