package com.example.inventorymanagementapplication.controller;


import com.example.inventorymanagementapplication.Main;
import com.example.inventorymanagementapplication.model.Inventory;
import com.example.inventorymanagementapplication.model.Part;
import com.example.inventorymanagementapplication.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class that edits the products in the application.
 *
 * @author Yonese James
 */
public class ModifyProductController implements Initializable {
    @FXML
    public Label modifyProductID;
    @FXML
    private Button modifyProductSaveButton;
    @FXML
    private Button modifyProductCancelButton;
    @FXML
    private TextField modifyProductIDTextField;
    @FXML
    private TextField modifyProductNameTextField;
    @FXML
    private TextField modifyProductInventoryTextField;
    @FXML
    private TextField modifyProductPriceTextField;
    @FXML
    private TextField modifyProductMinTextField;
    @FXML
    private TextField modifyProductMaxTextField;
    @FXML
    private TableView<Part> modifyProductPartTable;
    @FXML
    private TableColumn<Part, Integer> modifyProductPartIDColumn;
    @FXML
    private TableColumn<Part, String>  modifyProductPartNameColumn;
    @FXML
    private TableColumn<Part, Integer>  modifyProductPartInventoryColumn;
    @FXML
    private TableColumn<Part, Double>  modifyProductPartPriceColumn;
    @FXML
    private TableView<Part> modifyProductPartTableNew;
    @FXML
    private TableColumn<Part, Integer>  modifyProductPartIDNewColumn;
    @FXML
    private TableColumn<Part, String>  modifyProductPartNameNewColumn;
    @FXML
    private TableColumn<Part, Integer>  modifyProductPartInventoryNewColumn;
    @FXML
    private TableColumn<Part, Double>  modifyProductPartPriceNewColumn;
    @FXML
    private TextField modifyProductSearchTextField;
    @FXML
    private Button modifyProductRemovePartButton;

    private Product modifiedProduct = new Product();

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Initialize method for the ModifyProductController to initialize the stage and items.
     *
     * @param url for the url path.
     * @param resourceBundle for the resource.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifyProductPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("Product ID"));
        modifyProductPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("Product Name"));
        modifyProductPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("Product Inventory"));
        modifyProductPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("Product Price"));

        modifyProductPartTable.setItems(Inventory.getAllParts());

        modifyProductPartIDNewColumn.setCellValueFactory(new PropertyValueFactory<>("Product ID"));
        modifyProductPartNameNewColumn.setCellValueFactory(new PropertyValueFactory<>("Product Name"));
        modifyProductPartInventoryNewColumn.setCellValueFactory(new PropertyValueFactory<>("Product Inventory"));
        modifyProductPartPriceNewColumn.setCellValueFactory(new PropertyValueFactory<>("Product Price"));

    }

    public void modifyProductSaveButtonAction(ActionEvent actionEvent) throws IOException {
        try {
            modifiedProduct.setId(Integer.parseInt(modifyProductID.getText()));
            modifiedProduct.setName(modifyProductNameTextField.getText());
            modifiedProduct.setPrice(Double.parseDouble(modifyProductPriceTextField.getText()));
            modifiedProduct.setStock(Integer.parseInt(modifyProductInventoryTextField.getText()));
            modifiedProduct.setMin(Integer.parseInt(modifyProductMinTextField.getText()));
            modifiedProduct.setMax(Integer.parseInt(modifyProductMaxTextField.getText()));
            modifiedProduct.setAssociatedParts(modifiedProduct.getAllAssociatedParts());

            int min = Integer.parseInt(modifyProductMinTextField.getText());
            int max = Integer.parseInt(modifyProductMaxTextField.getText());
            int inventory = Integer.parseInt(modifyProductInventoryTextField.getText());
            String name = modifyProductNameTextField.getText();

            if (min > max) {
                Alert warningAlert = new Alert(Alert.AlertType.WARNING);
                warningAlert.setTitle("WARNING");
                warningAlert.setContentText("MINIMUM MUST BE LESS THAN OR EQUAL TO MAXIMUM");
                warningAlert.showAndWait();
            }
            else if (inventory > max || inventory < min)
            {
                Alert warningAlert = new Alert(Alert.AlertType.WARNING);
                warningAlert.setTitle("WARNING");
                warningAlert.setContentText("INVENTORY MUST BE LESS THAN OR EQUAL TO MAXIMUM AND GREATER THAN OR EQUAL TO MINIMUM");
                warningAlert.showAndWait();
            }
            else if (name.isEmpty())
            {
                Alert warningAlert = new Alert(Alert.AlertType.WARNING);
                warningAlert.setTitle("WARNING");
                warningAlert.setContentText("MUST INPUT A NAME");
                warningAlert.showAndWait();
            }
            else
            {
                Inventory.updateProduct(modifiedProduct.getId(), modifiedProduct);
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

    public void selectedProduct(Product product) {
        modifiedProduct = product;

        modifyProductID.setText(String.valueOf(modifiedProduct.getId()));
        modifyProductNameTextField.setText(modifiedProduct.getName());
        modifyProductPriceTextField.setText(String.valueOf(modifiedProduct.getPrice()));
        modifyProductInventoryTextField.setText(String.valueOf(modifiedProduct.getStock()));
        modifyProductMaxTextField.setText(String.valueOf(modifiedProduct.getMax()));
        modifyProductMinTextField.setText(String.valueOf(modifiedProduct.getMin()));
        modifiedProduct.setAssociatedParts(modifiedProduct.getAllAssociatedParts());
    }

    public void modifyProductCancelButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/MainScreenView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    public void modifyProductIDTextFieldAction(ActionEvent actionEvent) {
    }

    public void modifyProductNameTextFieldAction(ActionEvent actionEvent) {
    }

    public void modifyProductInventoryTextFieldAction(ActionEvent actionEvent) {
    }

    public void modifyProductPriceTextFieldAction(ActionEvent actionEvent) {
    }

    public void modifyProductMinTextFieldAction(ActionEvent actionEvent) {
    }

    public void modifyProductMaxTextFieldAction(ActionEvent actionEvent) {
    }

    public void modifyProductPartTableAction(SortEvent<TableView> tableViewSortEvent) {
    }

    public void modifyProductPartTableNewAction(SortEvent<TableView> tableViewSortEvent) {
    }

    public void modifyProductSearchTextFieldAction(ActionEvent actionEvent) {
    }

    public void modifyProductRemovePartButtonAction(ActionEvent actionEvent) throws IOException {
        Part selectedPart = modifyProductPartTableNew.getSelectionModel().getSelectedItem();

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("CONFIRMATION");
        confirmationAlert.setContentText("PLEASE CONFIRM IF YOU WOULD LIKE TO REMOVE THIS PRODUCT");
        Optional<ButtonType> confirmationButton = confirmationAlert.showAndWait();

        if (confirmationButton.isPresent() && confirmationButton.get() == ButtonType.YES) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/MainScreenView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();
            associatedParts.remove(selectedPart);
            modifyProductPartTable.setItems(associatedParts);
        }
    }

    public void modifyProductKeyPressedAction(KeyEvent keyEvent) {
        if (modifyProductSearchTextField.getText().isEmpty()) {
            modifyProductPartTable.setItems(Inventory.getAllParts());
        }
    }
}