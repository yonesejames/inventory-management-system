package com.example.inventorymanagementapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Main class that initializes and launches the application.
 *
 * @author Yonese James
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/MainScreenView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method that launches the application.
     *
     * LOCATION OF THE JAVADOC: "E:\Users\Stargate\Downloads\InventoryManagementApplication\src\JavaDoc"
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}