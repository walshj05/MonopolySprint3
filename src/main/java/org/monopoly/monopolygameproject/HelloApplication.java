package org.monopoly.monopolygameproject;

import javafx.application.Application;
import javafx.stage.Stage;
import org.monopoly.View.GUI;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GUI gui = new GUI(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}