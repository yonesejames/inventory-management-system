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
    /**
     *  FXML label variable for the product's ID.
     */
    @FXML
    public Label addProductID;

    /**
     *  FXML save button variable to save product.
     */
    @FXML
    private Button addProductSaveButton;

    /**
     *  FXML cancel button variable to cancel product.
     */
    @FXML
    private Button addProductCancelButton;

    /**
     *  FXML text field variable for the product's name.
     */
    @FXML
    private TextField addProductNameTextField;

    /**
     *  FXML text field variable for the product's stock.
     */
    @FXML
    private TextField addProductInventoryTextField;

    /**
     *  FXML text field variable for the product's price.
     */
    @FXML
    private TextField addProductPriceTextField;

    /**
     *  FXML text field variable for the product's minimum stock.
     */
    @FXML
    private TextField addProductMinTextField;

    /**
     *  FXML text field variable for the product's maximum stock.
     */
    @FXML
    private TextField addProductMaxTextField;

    /**
     *  FXML table view variable for the first table product's parts.
     */
    @FXML
    private TableView<Part> addProductPartTable;

    /**
     *  FXML table column variable for the first table part's ID.
     */
    @FXML
    private TableColumn<Part, Integer> addProductProductIDColumn;

    /**
     *  FXML table column variable for the first table part's name.
     */
    @FXML
    private TableColumn<Part, String>  addProductProductNameColumn;

    /**
     *  FXML table column variable for the first table part's stock.
     */
    @FXML
    private TableColumn<Part, Integer>  addProductProductInventoryColumn;

    /**
     *  FXML table column variable for the first table part's price.
     */
    @FXML
    private TableColumn<Part, Double>  addProductProductPriceColumn;

    /**
     *  FXML table view variable for the second table product's parts.
     */
    @FXML
    private TableView<Part> addProductPartTableNew;

    /**
     *  FXML table column variable for the second table part's ID.
     */
    @FXML
    private TableColumn<Part, Integer>  addProductProductIDNewColumn;

    /**
     *  FXML table column variable for the second table part's name.
     */
    @FXML
    private TableColumn<Part, String>  addProductProductNameNewColumn;

    /**
     *  FXML table column variable for the second table part's stock.
     */
    @FXML
    private TableColumn<Part, Integer>  addProductProductInventoryNewColumn;

    /**
     *  FXML table column variable for the second table part's price.
     */
    @FXML
    private TableColumn<Part, Double>  addProductProductPriceNewColumn;

    /**
     *  FXML text field variable for the first table's search.
     */
    @FXML
    private TextField addProductSearchTextField;

    /**
     *  FXML add part button variable to add a part to the product.
     */
    @FXML
    private Button addProductAddPartButton;

    /**
     *  FXML remove part button variable to remove a part from the product.
     */
    @FXML
    private Button addProductRemovePartButton;

    /**
     *  Variable for the parts associated with the product.
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     *  New product to be added.
     */
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

        addProductProductIDNewColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductProductNameNewColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductProductInventoryNewColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductProductPriceNewColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * Method to save the product by assigning each information of each variable: id, name, inventory, price,
     * min, and max by grabbing the text from each text fields and adding parts to the associated parts if the parts
     * were added from the first table to the second table and then returning to the main screen and inputting the
     * product in the product's table. However, if the min is larger than the max, or if
     * the inventory is smaller than the minimum or larger than the maximum, or if any of the text fields are empty then
     * a warning message pops up.
     *
     * @param actionEvent
     */
    public void addProductSaveButtonAction(ActionEvent actionEvent) {
        try {
            newProduct.setId(Integer.parseInt(addProductID.getText()));
            newProduct.setName(addProductNameTextField.getText());
            newProduct.setPrice(Double.parseDouble(addProductPriceTextField.getText()));
            newProduct.setStock(Integer.parseInt(addProductInventoryTextField.getText()));
            newProduct.setMin(Integer.parseInt(addProductMinTextField.getText()));
            newProduct.setMax(Integer.parseInt(addProductMaxTextField.getText()));

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
                for (Part part : associatedParts)
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
        catch (NumberFormatException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("WARNING");
            errorAlert.setContentText("MUST HAVE NUMERIC VALUE FOR INVENTORY, PRICE, MIN, AND MAX");
            errorAlert.showAndWait();
        }
    }

    /**
     * Method to cancel adding product by returning back to the main screen.
     *
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * Method to search through the product's parts search bar and add the parts to a temporaryParts ObservableList
     * and if the part has the same ID or name as the text in the search bar and returns the parts in the list. If the
     * part is not found then an error message pops up on the application.
     *
     * @param actionEvent
     */
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

        if (addProductSearchTextField.getText().isEmpty()) {
            addProductPartTable.setItems(Inventory.getAllParts());
        }

        if (temporaryParts.size() == 0) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("ERROR");
            errorAlert.setContentText("NO PARTS WAS FOUND");
            errorAlert.showAndWait();
        }
    }

    /**
     * Method to add a part to the associatedParts for the product.
     *
     * @param actionEvent
     */
    public void addProductAddPartButtonAction(ActionEvent actionEvent) {
        Part selectedPart = addProductPartTable.getSelectionModel().getSelectedItem();

        if (selectedPart != null) {
            associatedParts.add(selectedPart);
            addProductPartTableNew.setItems(associatedParts);;
        }

    }

    /**
     * Method to remove part from the associatedParts and from the product by first confirming the part will be removed
     * then setting the new product's part table to the new associatedParts without the part.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void addProductRemovePartButtonAction(ActionEvent actionEvent) throws IOException {
        Part selectedPart = addProductPartTableNew.getSelectionModel().getSelectedItem();

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("CONFIRMATION");
        confirmationAlert.setContentText("PLEASE CONFIRM IF YOU WOULD LIKE TO REMOVE THIS PRODUCT");
        Optional<ButtonType> confirmationButton = confirmationAlert.showAndWait();

        if (confirmationButton.isPresent() && confirmationButton.get() == ButtonType.OK && selectedPart != null) {
            associatedParts.remove(selectedPart);
        }
    }

    /**
     * Method that shows all parts in the product part's table when the search bar has no text.
     *
     * @param keyEvent
     */
    public void addProductKeyPressedAction(KeyEvent keyEvent) {
        if (addProductSearchTextField.getText().isEmpty()) {
            addProductPartTable.setItems(Inventory.getAllParts());
        }
    }
}