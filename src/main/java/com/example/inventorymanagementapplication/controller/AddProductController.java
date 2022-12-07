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
 * Controller class that adds products in the application.
 *
 * @author Yonese James
 */
public class AddProductController implements Initializable {
    @FXML
    public Label addProductID;
    @FXML
    private Button addProductSaveButton;
    @FXML
    private Button addProductCancelButton;
    @FXML
    private TextField addProductIDTextField;
    @FXML
    private TextField addProductNameTextField;
    @FXML
    private TextField addProductInventoryTextField;
    @FXML
    private TextField addProductPriceTextField;
    @FXML
    private TextField addProductMinTextField;
    @FXML
    private TextField addProductMaxTextField;
    @FXML
    private TableView<Part> addProductPartTable;
    @FXML
    private TableColumn<Part, Integer> addProductProductIDColumn;
    @FXML
    private TableColumn<Part, String>  addProductProductNameColumn;
    @FXML
    private TableColumn<Part, Integer>  addProductProductInventoryColumn;
    @FXML
    private TableColumn<Part, Double>  addProductProductPriceColumn;
    @FXML
    private TableView<Part> addProductPartTableNew;
    @FXML
    private TableColumn<Part, Integer>  addProductProductIDNewColumn;
    @FXML
    private TableColumn<Part, String>  addProductProductNameNewColumn;
    @FXML
    private TableColumn<Part, Integer>  addProductProductInventoryNewColumn;
    @FXML
    private TableColumn<Part, Double>  addProductProductPriceNewColumn;
    @FXML
    private TextField addProductSearchTextField;
    @FXML
    private Button addProductAddPartButton;
    @FXML
    private Button addProductRemovePartButton;

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    private Product newProduct = new Product();

    /**
     * Initialize method for the AddProductController to initialize the stage and items.
     *
     * @param url for the url path.
     * @param resourceBundle for the resource.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addProductPartTable.setItems(Inventory.getAllParts());
        addProductProductIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductProductInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductProductPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        addProductID.setText(String.valueOf(Inventory.setProductID()));

        addProductPartTableNew.setItems(Inventory.getAllParts());
        addProductProductIDNewColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductProductNameNewColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductProductInventoryNewColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductProductPriceNewColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void addProductSaveButtonAction(ActionEvent actionEvent) {
        try {
            newProduct.setId(Integer.parseInt(addProductID.getText()));
            newProduct.setName(addProductNameTextField.getText());
            newProduct.setPrice(Double.parseDouble(addProductPriceTextField.getText()));
            newProduct.setStock(Integer.parseInt(addProductInventoryTextField.getText()));
            newProduct.setMin(Integer.parseInt(addProductMinTextField.getText()));
            newProduct.setMax(Integer.parseInt(addProductMaxTextField.getText()));
            newProduct.setAssociatedParts(associatedParts);

            int min = Integer.parseInt(addProductMinTextField.getText());
            int max = Integer.parseInt(addProductMaxTextField.getText());
            int inventory = Integer.parseInt(addProductInventoryTextField.getText());
            String name = addProductNameTextField.getText();

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
                for (Part part: associatedParts)
                {
                    newProduct.addAssociatedPart(part);
                }
                Inventory.addProduct(newProduct);
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/MainScreenView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Inventory Management System");
                stage.setScene(scene);
                stage.show();
            }
        }
        catch (IOException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("WARNING");
            errorAlert.setContentText("MUST HAVE INPUT FOR ALL VALUES");
            errorAlert.showAndWait();
        }
    }

    public void addProductCancelButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/MainScreenView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    public void addProductIDTextFieldAction(ActionEvent actionEvent) {
    }

    public void addProductNameTextFieldAction(ActionEvent actionEvent) {
    }

    public void addProductInventoryTextFieldAction(ActionEvent actionEvent) {
    }

    public void addProductPriceTextFieldAction(ActionEvent actionEvent) {
    }

    public void addProductMinTextFieldAction(ActionEvent actionEvent) {
    }

    public void addProductMaxTextFieldAction(ActionEvent actionEvent) {
    }

    public void addProductPartTableAction(SortEvent<TableView> tableViewSortEvent) {
    }

    public void addProductPartTableNewAction(SortEvent<TableView> tableViewSortEvent) {
    }

    public void addProductSearchTextFieldAction(ActionEvent actionEvent) {
        String productTextSearch = addProductSearchTextField.getText();
        ObservableList<Part> temporaryParts = Inventory.lookupPart(productTextSearch);
        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(productTextSearch))
            {
                temporaryParts.add(part);
            }
            if (part.getName().contains(productTextSearch))
            {
                temporaryParts.add(part);
            }
        }

        addProductPartTable.setItems(temporaryParts);

        if (temporaryParts.size() == 0) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("ERROR");
            errorAlert.setContentText("NO PARTS WAS FOUND");
            errorAlert.showAndWait();
        }
    }

    public void addProductAddPartButtonAction(ActionEvent actionEvent) {
        Part selectedPart = addProductPartTable.getSelectionModel().getSelectedItem();
        associatedParts.add(selectedPart);
        addProductPartTable.setItems(associatedParts);
    }

    public void addProductRemovePartButtonAction(ActionEvent actionEvent) throws IOException {
        Part selectedPart = addProductPartTableNew.getSelectionModel().getSelectedItem();

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
            addProductPartTable.setItems(associatedParts);
        }
    }

    public void addProductKeyPressedAction(KeyEvent keyEvent) {
        if (addProductSearchTextField.getText().isEmpty()) {
            addProductPartTable.setItems(Inventory.getAllParts());
        }
    }
}