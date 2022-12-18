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

    /**
     * FXML toggle variable for the part's radio buttons: inhouse or outsourced.
     */
    @FXML
    public ToggleGroup inhouseOrOutsourced;

    /**
     *  FXML label variable for the part's ID.
     */
    @FXML
    public Label addPartID;

    /**
     *  FXML label variable for the part's machineID or companyName.
     */
    @FXML
    public Label machineIDOrCompanyName;

    /**
     *  FXML radio button variable for the part's inhouse.
     */
    @FXML
    private RadioButton addPartInhouseRadioButton;

    /**
     *  FXML radio button variable for the part's outsourced.
     */
    @FXML
    private RadioButton addPartOutsourcedRadioButton;

    /**
     *  FXML save button variable to save part.
     */
    @FXML
    private Button addPartSaveButton;

    /**
     *  FXML cancel button variable to cancel part.
     */
    @FXML
    private Button addPartCancelButton;

    /**
     *  FXML text field variable for the part's name.
     */
    @FXML
    private TextField addPartNameTextField;

    /**
     *  FXML text field variable for the part's stock.
     */
    @FXML
    private TextField addPartInventoryTextField;

    /**
     *  FXML text field variable for the part's price.
     */
    @FXML
    private TextField addPartPriceTextField;

    /**
     *  FXML text field variable for the part's minimum stock.
     */
    @FXML
    private TextField addPartMinTextField;

    /**
     *  FXML text field variable for the part's maximum stock.
     */
    @FXML
    private TextField addPartMaxTextField;

    /**
     *  FXML text field variable for the part's machineID or companyName.
     */
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

    /**
     * Method to set text for part's machineID or companyName when toggled to inHouse which will turn
     * text to "Machine ID"
     *
     * @param actionEvent
     */
    public void addPartInhouseRadioButtonAction(ActionEvent actionEvent) {
        machineIDOrCompanyName.setText("Machine ID");
        inhouseOrOutsourced.selectToggle(addPartInhouseRadioButton);
    }

    /**
     * Method to set text for part's machineID or companyName when toggled to outsourced which will turn
     * text to "Company Name"
     *
     * @param actionEvent
     */
    public void addPartOutsourcedRadioButtonAction(ActionEvent actionEvent) {
        machineIDOrCompanyName.setText("Company Name");
        inhouseOrOutsourced.selectToggle(addPartOutsourcedRadioButton);
    }

    /**
     * Method to save the part by assigning each information of each variable: id, name, inventory, price, min, and max
     * by grabbing the text from each text fields and grabbing which radio button has been selected and returning to the
     * main screen and inputting the part in the part table.
     *
     * @param actionEvent
     * @throws IOException
     */
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
        catch (NumberFormatException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("WARNING");
            errorAlert.setContentText("MUST HAVE NUMERIC VALUE FOR INVENTORY, PRICE, MIN, AND MAX");
            errorAlert.showAndWait();
        }

    }


    /**
     * Method to cancel adding part by returning back to the main screen.
     *
     * @param actionEvent
     * @throws IOException
     */
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