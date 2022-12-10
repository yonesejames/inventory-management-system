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
    /**
     *  FXML add part button variable to add a part.
     */
    @FXML
    private Button mainScreenAddPartButton;

    /**
     *  FXML modify part button variable to modify a selected part.
     */
    @FXML
    private Button mainScreenModifyPartButton;

    /**
     *  FXML delete part button variable to delete a selected part.
     */
    @FXML
    private Button mainScreenDeletePartButton;

    /**
     *  FXML table view variable for the part's table in the main screen.
     */
    @FXML
    private TableView<Part> mainScreenPartTable;

    /**
     *  FXML table column variable for the table part's ID.
     */
    @FXML
    private TableColumn<Part, Integer> mainScreenPartIDColumn;

    /**
     *  FXML table column variable for the table part's name.
     */
    @FXML
    private TableColumn<Part, String>  mainScreenPartNameColumn;

    /**
     *  FXML table column variable for the table part's stock.
     */
    @FXML
    private TableColumn<Part, Integer>  mainScreenPartInventoryColumn;

    /**
     *  FXML table column variable for the table part's price.
     */
    @FXML
    private TableColumn<Part, Double>  mainScreenPartPriceColumn;

    /**
     *  FXML add product button variable to add a product.
     */
    @FXML
    private Button mainScreenAddProductButton;

    /**
     *  FXML modify product button variable to modify a product.
     */
    @FXML
    private Button mainScreenModifyProductButton;

    /**
     *  FXML delete product button variable to delete a selected product.
     */
    @FXML
    private Button mainScreenDeleteProductButton;

    /**
     *  FXML table view variable for the product's table in the main screen.
     */
    @FXML
    private TableView<Product> mainScreenProductTable;

    /**
     *  FXML table column variable for the table product's ID.
     */
    @FXML
    private TableColumn<Product, Integer> mainScreenProductIDColumn;

    /**
     *  FXML table column variable for the table product's name.
     */
    @FXML
    private TableColumn<Product, String>  mainScreenProductNameColumn;

    /**
     *  FXML table column variable for the table product's stock.
     */
    @FXML
    private TableColumn<Product, Integer>  mainScreenProductInventoryColumn;

    /**
     *  FXML table column variable for the table product's price.
     */
    @FXML
    private TableColumn<Product, Double>  mainScreenProductPriceColumn;

    /**
     *  FXML button variable to exit the application.
     */
    @FXML
    private Button mainScreenExitButton;

    /**
     *  FXML text field variable for the part table's search.
     */
    @FXML
    private TextField mainScreenPartSearch;

    /**
     *  FXML text field variable for the product table's search.
     */
    @FXML
    private TextField mainScreenProductSearch;

    /**
     *  Part variable for a part that is selected.
     */
    private static Part selectedPart;

    /**
     *  Product variable for a product that is selected.
     */
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

    /**
     * Method to add a part and directs to the add part window.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void mainScreenAddPartButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/AddPartView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to modify a part by selecting a part in the part table and directs to the modify part window, but
     * if a part is not selected then an error message pops up.
     *
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * Method to return the selectedPart.
     *
     * @return the selectedPart.
     */
    public static Part getSelectedPart() {
        return selectedPart;
    }

    /**
     * Method that deletes the part from the part's table by grabbing the part being selected by clicking on
     * the part and ensures you can delete the part by first confirming the deletion of the part
     * and if confirmed then the part is deleted from the table.
     *
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * Method to search through the part search bar and add the parts to a temporaryParts ObservableList and if
     * the part has the same ID or name as the text in the search bar and returns the parts in the list. If the
     * part is not found then an error message pops up on the application.
     *
     * @param actionEvent
     */
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

    /**
     * Method that shows all parts in the part table when the search bar has no text.
     *
     * @param keyEvent
     */
    public void mainScreenPartKeyPressedAction(KeyEvent keyEvent) {
        if (mainScreenPartSearch.getText().isEmpty()) {
            mainScreenPartTable.setItems(Inventory.getAllParts());
        }
    }

    /**
     * Method to add a product and directs to the add product window.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void mainScreenAddProductButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/AddProductView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to return the selectedProduct.
     *
     * @return
     */
    public static Product getSelectedProduct() {
        return selectedProduct;
    }

    /**
     * Method to modify a product by selecting a product in the product's table and directs to the modify product window,
     * but if a product is not selected then an error message pops up.
     *
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * Method that deletes the product from the product's table by grabbing the product being selected by clicking on
     * the product and ensures you can delete the product by first confirming the deletion of the product and if the
     * product has parts associated with it then the product cannot be deleted, but if the product does not have parts
     * and if confirmed then the product is deleted from the table.
     *
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * Method to search through the product search bar and add the product to a temporaryProducts ObservableList and if
     * the product has the same ID or name as the text in the search bar and returns the products in the list. If the
     * product is not found then an error message pops up on the application.
     *
     * @param actionEvent
     */
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

    /**
     * Method that shows all products in the product table when the search bar has no text.
     *
     * @param keyEvent
     */
    public void mainScreenProductKeyPressedAction(KeyEvent keyEvent) {
        if (mainScreenProductSearch.getText().isEmpty()) {
            mainScreenProductTable.setItems(Inventory.getAllProducts());
        }
    }

    /**
     * Method that exits the application when the "EXIT" button is clicked.
     *
     * @param actionEvent
     */
    public void mainScreenExitButtonAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void mainScreenProductTableAction(SortEvent<TableView<Product>> tableViewSortEvent) {
    }

    public void mainScreenPartTableAction(SortEvent<TableView<Part>> tableViewSortEvent) {
    }
}