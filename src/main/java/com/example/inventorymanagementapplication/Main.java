package com.example.inventorymanagementapplication;

import com.example.inventorymanagementapplication.model.*;
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

        Part p1 = new InHouse(Inventory.setPartID(), "Mouse", 20, 4, 1, 10, 0);
        Inventory.addPart(p1);
        Part p2 = new Outsourced(Inventory.setPartID(), "Keyboard", 50, 5, 1, 10, "DELL");
        Inventory.addPart(p2);

        Product pr1 = new Product(Inventory.setProductID(), "Computer", 500, 1, 1, 10);
        Inventory.addProduct(pr1);

        launch(args);
    }
}