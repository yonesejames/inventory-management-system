package com.example.inventorymanagementapplication.controller;


import com.example.inventorymanagementapplication.Main;
import com.example.inventorymanagementapplication.model.InHouse;
import com.example.inventorymanagementapplication.model.Inventory;
import com.example.inventorymanagementapplication.model.Outsourced;
import com.example.inventorymanagementapplication.model.Part;
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
 * Controller class that edits the parts in the application.
 *
 * @author Yonese James
 */
public class ModifyPartController implements Initializable {
    @FXML
    public Label modifyPartID;
    @FXML
    public ToggleGroup inhouseOrOutsourced;
    @FXML
    public Label machineIDOrCompanyName;
    @FXML
    private RadioButton modifyPartInhouseRadioButton;
    @FXML
    private RadioButton modifyPartOutsourcedRadioButton;
    @FXML
    private Button modifyPartSaveButton;
    @FXML
    private Button modifyPartCancelButton;
    @FXML
    private TextField modifyPartIDTextField;
    @FXML
    private TextField modifyPartNameTextField;
    @FXML
    private TextField modifyPartInventoryTextField;
    @FXML
    private TextField modifyPartPriceTextField;
    @FXML
    private TextField modifyPartMinTextField;
    @FXML
    private TextField modifyPartMaxTextField;
    @FXML
    private TextField modifyPartMachineIDTextField;

    Part selectedPart;

    /**
     * Initialize method for the ModifyPartController to initialize the stage and items.
     *
     * @param url for the url path.
     * @param resourceBundle for the resource.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedPart = MainScreenController.getSelectedPart();
        modifyPartID.setText(String.valueOf(selectedPart.getId()));
        modifyPartNameTextField.setText(selectedPart.getName());
        modifyPartInventoryTextField.setText(String.valueOf(selectedPart.getStock()));
        modifyPartPriceTextField.setText(String.valueOf(selectedPart.getPrice()));
        modifyPartMinTextField.setText(String.valueOf(selectedPart.getMin()));
        modifyPartMaxTextField.setText(String.valueOf(selectedPart.getMax()));

        if (selectedPart instanceof InHouse) {
            modifyPartInhouseRadioButton.setSelected(true);
            machineIDOrCompanyName.setText("Machine ID");
            modifyPartMachineIDTextField.setText(String.valueOf(((InHouse) selectedPart).getMachineID()));
        }

        if (selectedPart instanceof Outsourced){
            modifyPartOutsourcedRadioButton.setSelected(true);
            machineIDOrCompanyName.setText("Company Name");
            modifyPartMachineIDTextField.setText(((Outsourced) selectedPart).getCompanyName());
        }
    }

    public void modifyPartInhouseRadioButtonAction(ActionEvent actionEvent) {
        machineIDOrCompanyName.setText("Machine ID");
        inhouseOrOutsourced.selectToggle(modifyPartInhouseRadioButton);
        modifyPartInhouseRadioButton.setSelected(true);
        modifyPartOutsourcedRadioButton.setSelected(false);
    }

    public void modifyPartOutsourcedRadioButtonAction(ActionEvent actionEvent) {
        machineIDOrCompanyName.setText("Company Name");
        inhouseOrOutsourced.selectToggle(modifyPartOutsourcedRadioButton);
        modifyPartInhouseRadioButton.setSelected(false);
        modifyPartOutsourcedRadioButton.setSelected(true);
    }

    public void modifyPartSaveButtonAction(ActionEvent actionEvent) {
        try {
            int partID = selectedPart.getId();
            String partName = modifyPartNameTextField.getText();
            int partInventory = Integer.parseInt(modifyPartInventoryTextField.getText());
            double partPrice = Double.parseDouble(modifyPartPriceTextField.getText());
            int partMin = Integer.parseInt(modifyPartMinTextField.getText());
            int partMax = Integer.parseInt(modifyPartMaxTextField.getText());

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
                if (modifyPartInhouseRadioButton.isSelected()) {
                    int partMachineID = Integer.parseInt(modifyPartMachineIDTextField.getText());
                    Inventory.addPart(new InHouse(partID, partName, partPrice, partInventory, partMin, partMax, partMachineID));
                } else if (modifyPartOutsourcedRadioButton.isSelected()) {
                    String partCompanyName = modifyPartMachineIDTextField.getText();
                    Inventory.addPart(new Outsourced(partID, partName, partPrice, partInventory, partMin, partMax, partCompanyName));
                }
                Inventory.deletePart(selectedPart);
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

    public void modifyPartCancelButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/MainScreenView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    public void modifyPartIDTextFieldAction(ActionEvent actionEvent) {
    }

    public void modifyPartNameTextField(ActionEvent actionEvent) {
    }

    public void modifyPartInventoryTextFieldAction(ActionEvent actionEvent) {
    }

    public void modifyPartPriceTextFieldAction(ActionEvent actionEvent) {
    }

    public void modifyPartMinTextFieldAction(ActionEvent actionEvent) {
    }

    public void modifyPartMaxTextFieldAction(ActionEvent actionEvent) {
    }

    public void modifyPartMachineIDTextFieldAction(ActionEvent actionEvent) {
    }
}