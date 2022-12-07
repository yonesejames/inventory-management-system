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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class that views the main screen after logging into the application.
 *
 * @author Yonese James
 */
public class MainScreenController implements Initializable {
    @FXML
    private Button mainScreenAddPartButton;
    @FXML
    private Button mainScreenModifyPartButton;
    @FXML
    private Button mainScreenDeletePartButton;
    @FXML
    private TableView<Part> mainScreenPartTable;
    @FXML
    private TableColumn<Part, Integer> mainScreenPartIDColumn;
    @FXML
    private TableColumn<Part, String>  mainScreenPartNameColumn;
    @FXML
    private TableColumn<Part, Integer>  mainScreenPartInventoryColumn;
    @FXML
    private TableColumn<Part, Double>  mainScreenPartPriceColumn;
    @FXML
    private Button mainScreenAddProductButton;
    @FXML
    private Button mainScreenModifyProductButton;
    @FXML
    private Button mainScreenDeleteProductButton;
    @FXML
    private TableView<Product> mainScreenProductTable;
    @FXML
    private TableColumn<Product, Integer> mainScreenProductIDColumn;
    @FXML
    private TableColumn<Product, String>  mainScreenProductNameColumn;
    @FXML
    private TableColumn<Product, Integer>  mainScreenProductInventoryColumn;
    @FXML
    private TableColumn<Product, Double>  mainScreenProductPriceColumn;
    @FXML
    private Button mainScreenExitButton;
    @FXML
    private TextField mainScreenPartSearch;
    @FXML
    private TextField mainScreenProductSearch;

    private static Part selectedPart;

    private static Product selectedProduct;

    /**
     * Initialize method for the MainScreenController to initialize the stage and items.
     *
     * @param url for the url path.
     * @param resourceBundle for the resource.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainScreenPartTable.setItems(Inventory.getAllParts());
        mainScreenPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainScreenPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainScreenPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainScreenPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        mainScreenProductTable.setItems(Inventory.getAllProducts());
        mainScreenProductIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainScreenProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainScreenProductInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainScreenProductPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    public void mainScreenAddPartButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/AddPartView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    public void mainScreenModifyPartButtonAction(ActionEvent actionEvent) throws IOException {
        selectedPart = mainScreenPartTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("ERROR");
            errorAlert.setContentText("NO PART WAS SELECTED");
            errorAlert.showAndWait();
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/ModifyPartView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Modify Part");
            stage.setScene(scene);
            stage.show();
        }

    }

    public static Part getSelectedPart() {
        return selectedPart;
    }

    public void mainScreenDeletePartButtonAction(ActionEvent actionEvent) throws IOException {
        Part selectedPart = mainScreenPartTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("ERROR");
            errorAlert.setContentText("NO PART WAS SELECTED");
            errorAlert.showAndWait();
        }
        else
        {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("CONFIRMATION");
            confirmationAlert.setContentText("PLEASE CONFIRM IF YOU WOULD LIKE TO DELETE THIS PART");
            Optional<ButtonType> confirmationButton = confirmationAlert.showAndWait();

            if (confirmationButton.isPresent() && confirmationButton.get() == ButtonType.YES)
            {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/MainScreenView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Inventory Management System");
                stage.setScene(scene);
                stage.show();
                Inventory.deletePart(selectedPart);
            }
            else
            {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/MainScreenView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Inventory Management System");
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    public void mainScreenPartTableAction(SortEvent<TableView> tableViewSortEvent) {
    }

    public void mainScreenPartSearchAction(ActionEvent actionEvent) {
        String partTextSearch = mainScreenPartSearch.getText();
        ObservableList<Part> temporaryParts = Inventory.lookupPart(partTextSearch);
        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(partTextSearch))
            {
                temporaryParts.add(part);
            }
            if (part.getName().contains(partTextSearch))
            {
                temporaryParts.add(part);
            }
        }

        mainScreenPartTable.setItems(temporaryParts);

        if (temporaryParts.size() == 0) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("ERROR");
            errorAlert.setContentText("NO PART WAS FOUND");
            errorAlert.showAndWait();
        }
    }

    public void mainScreenPartKeyPressedAction(KeyEvent keyEvent) {
        if (mainScreenPartSearch.getText().isEmpty()) {
            mainScreenPartTable.setItems(Inventory.getAllParts());
        }
    }

    public void mainScreenAddProductButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/AddProductView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    public static Product getSelectedProduct() {
        return selectedProduct;
    }

    public void mainScreenModifyProductButtonAction(ActionEvent actionEvent) throws IOException {
        selectedProduct = mainScreenProductTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("ERROR");
            errorAlert.setContentText("NO PRODUCT WAS SELECTED");
            errorAlert.showAndWait();
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/ModifyProductView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Modify Product");
            stage.setScene(scene);
            stage.show();
        }

    }

    public void mainScreenDeleteProductButtonAction(ActionEvent actionEvent) throws IOException {
        Product selectedProduct = mainScreenProductTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("ERROR");
            errorAlert.setContentText("NO PRODUCT WAS SELECTED");
            errorAlert.showAndWait();
        }
        else
        {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("CONFIRMATION");
            confirmationAlert.setContentText("PLEASE CONFIRM IF YOU WOULD LIKE TO DELETE THIS PRODUCT");
            Optional<ButtonType> confirmationButton = confirmationAlert.showAndWait();

            if (confirmationButton.isPresent() && confirmationButton.get() == ButtonType.YES)
            {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/MainScreenView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Inventory Management System");
                stage.setScene(scene);
                stage.show();

                if (!selectedProduct.getAllAssociatedParts().isEmpty())
                {
                    Alert warningAlert = new Alert(Alert.AlertType.WARNING);
                    warningAlert.setTitle("WARNING");
                    warningAlert.setContentText("PRODUCT HAS ASSOCIATED PARTS");
                    warningAlert.showAndWait();
                }
            }
            else
            {
                confirmationAlert.setTitle("CONFIRMATION");
                confirmationAlert.setContentText("PRODUCT HAS BEEN DELETED");
                confirmationAlert.showAndWait();

                Inventory.deleteProduct(selectedProduct);
            }
        }

    }

    public void mainScreenProductTableAction(SortEvent<TableView> tableViewSortEvent) {
    }

    public void mainScreenProductSearchAction(ActionEvent actionEvent) {
        String productTextSearch = mainScreenPartSearch.getText();
        ObservableList<Product> temporaryProducts = Inventory.lookupProduct(productTextSearch);
        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(productTextSearch))
            {
                temporaryProducts.add(product);
            }
            if (product.getName().contains(productTextSearch))
            {
                temporaryProducts.add(product);
            }
        }

        mainScreenProductTable.setItems(temporaryProducts);

        if (temporaryProducts.size() == 0) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("ERROR");
            errorAlert.setContentText("NO PRODUCT WAS FOUND");
            errorAlert.showAndWait();
        }
    }

    public void mainScreenProductKeyPressedAction(KeyEvent keyEvent) {
        if (mainScreenProductSearch.getText().isEmpty()) {
            mainScreenProductTable.setItems(Inventory.getAllProducts());
        }
    }

    public void mainScreenExitButtonAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}