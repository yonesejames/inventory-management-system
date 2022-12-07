package com.example.inventorymanagementapplication.controller;

import com.example.inventorymanagementapplication.Main;
import com.example.inventorymanagementapplication.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class that edits the products in the application.
 *
 * @author Yonese James
 */
public class AddPartController implements Initializable {
    @FXML
    public ToggleGroup inhouseOrOutsourced;
    @FXML
    public Label addPartID;
    @FXML
    public Label machineIDOrCompanyName;
    @FXML
    private RadioButton addPartInhouseRadioButton;
    @FXML
    private RadioButton addPartOutsourcedRadioButton;
    @FXML
    private Button addPartSaveButton;
    @FXML
    private Button addPartCancelButton;
    @FXML
    private TextField addPartIDTextField;
    @FXML
    private TextField addPartNameTextField;
    @FXML
    private TextField addPartInventoryTextField;
    @FXML
    private TextField addPartPriceTextField;
    @FXML
    private TextField addPartMinTextField;
    @FXML
    private TextField addPartMaxTextField;
    @FXML
    private TextField addPartMachineIDTextField;

    /**
     * Initialize method for the AddPartController to initialize the stage and items.
     *
     * @param url for the url path
     * @param resourceBundle for the resource
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inhouseOrOutsourced.selectToggle(addPartInhouseRadioButton);
        addPartID.setText(String.valueOf(Inventory.setPartID()));
    }

    public void addPartInhouseRadioButtonAction(ActionEvent actionEvent) {
        machineIDOrCompanyName.setText("Machine ID");
        inhouseOrOutsourced.selectToggle(addPartInhouseRadioButton);
    }

    public void addPartOutsourcedRadioButtonAction(ActionEvent actionEvent) {
        machineIDOrCompanyName.setText("Company Name");
        inhouseOrOutsourced.selectToggle(addPartOutsourcedRadioButton);
    }

    public void addPartSaveButtonAction(ActionEvent actionEvent) throws IOException {
        try {
            int partID = Integer.parseInt(addPartID.getText());
            String partName = addPartNameTextField.getText();
            int partInventory = Integer.parseInt(addPartInventoryTextField.getText());
            double partPrice = Double.parseDouble(addPartPriceTextField.getText());
            int partMin = Integer.parseInt(addPartMinTextField.getText());
            int partMax = Integer.parseInt(addPartMaxTextField.getText());

            if (partMin > partMax) {
                Alert warningAlert = new Alert(Alert.AlertType.WARNING);
                warningAlert.setTitle("WARNING");
                warningAlert.setContentText("MINIMUM MUST BE LESS THAN OR EQUAL TO MAXIMUM");
                warningAlert.showAndWait();
            } else if (partInventory > partMax || partInventory < partMin) {
                Alert warningAlert = new Alert(Alert.AlertType.WARNING);
                warningAlert.setTitle("WARNING");
                warningAlert.setContentText("INVENTORY MUST BE LESS THAN OR EQUAL TO MAXIMUM AND GREATER THAN OR EQUAL TO MINIMUM");
                warningAlert.showAndWait();
            } else if (partName.isEmpty()) {
                Alert warningAlert = new Alert(Alert.AlertType.WARNING);
                warningAlert.setTitle("WARNING");
                warningAlert.setContentText("MUST INPUT A NAME");
                warningAlert.showAndWait();
            } else {
                if (addPartInhouseRadioButton.isSelected()) {
                    int partMachineID = Integer.parseInt(addPartMachineIDTextField.getText());
                    Inventory.addPart(new InHouse(partID, partName, partPrice, partInventory, partMin, partMax, partMachineID));
                } else if (addPartOutsourcedRadioButton.isSelected()) {
                    String partCompanyName = addPartMachineIDTextField.getText();
                    Inventory.addPart(new Outsourced(partID, partName, partPrice, partInventory, partMin, partMax, partCompanyName));
                }

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/MainScreenView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Inventory Management System");
                stage.setScene(scene);
                stage.show();
            }
        }
        catch (IOException e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("WARNING");
            errorAlert.setContentText("MUST HAVE INPUT FOR ALL VALUES");
            errorAlert.showAndWait();
        }

    }


    public void addPartCancelButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/MainScreenView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    public void addPartIDTextFieldAction(ActionEvent actionEvent) {
    }

    public void addPartNameTextFieldAction(ActionEvent actionEvent) {
    }

    public void addPartInventoryTextFieldAction(ActionEvent actionEvent) {
    }

    public void addPartPriceTextFieldAction(ActionEvent actionEvent) {
    }

    public void addPartMinTextFieldAction(ActionEvent actionEvent) {
    }

    public void addPartMaxTextFieldAction(ActionEvent actionEvent) {
    }

    public void addPartMachineIDTextFieldAction(ActionEvent actionEvent) {
    }
}