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
    /**
     *  FXML label variable for the modifyProduct's ID.
     */
    @FXML
    public Label modifyProductID;

    /**
     *  FXML add button variable to add a part to the modifyProduct.
     */
    @FXML
    public Button modifyProductAddPartButton;

    /**
     *  FXML save button variable to save modifyProduct.
     */
    @FXML
    private Button modifyProductSaveButton;

    /**
     *  FXML cancel button variable to cancel modifyProduct.
     */
    @FXML
    private Button modifyProductCancelButton;

    /**
     *  FXML text field variable for the modifyProduct's ID.
     */
    @FXML
    private TextField modifyProductIDTextField;

    /**
     *  FXML text field variable for the modifyProduct's name.
     */
    @FXML
    private TextField modifyProductNameTextField;

    /**
     *  FXML text field variable for the modifyProduct's stock.
     */
    @FXML
    private TextField modifyProductInventoryTextField;

    /**
     *  FXML text field variable for the modifyProduct's price.
     */
    @FXML
    private TextField modifyProductPriceTextField;

    /**
     *  FXML text field variable for the modifyProduct's minimum stock.
     */
    @FXML
    private TextField modifyProductMinTextField;

    /**
     *  FXML text field variable for the modifyProduct's maximum stock.
     */
    @FXML
    private TextField modifyProductMaxTextField;

    /**
     *  FXML table view variable for the first table modifyProduct's parts.
     */
    @FXML
    private TableView<Part> modifyProductPartTable;


    /**
     *  FXML table column variable for the first table modifyProduct part's ID.
     */
    @FXML
    private TableColumn<Part, Integer> modifyProductPartIDColumn;

    /**
     *  FXML table column variable for the first table modifyProduct part's name.
     */
    @FXML
    private TableColumn<Part, String>  modifyProductPartNameColumn;

    /**
     *  FXML table column variable for the first table modifyProduct part's stock.
     */
    @FXML
    private TableColumn<Part, Integer>  modifyProductPartInventoryColumn;

    /**
     *  FXML table column variable for the first table modifyProduct part's price.
     */
    @FXML
    private TableColumn<Part, Double>  modifyProductPartPriceColumn;

    /**
     *  FXML table view variable for the second table modifyProduct's parts.
     */
    @FXML
    private TableView<Part> modifyProductPartTableNew;

    /**
     *  FXML table column variable for the second table modifyProduct part's ID.
     */
    @FXML
    private TableColumn<Part, Integer>  modifyProductPartIDNewColumn;

    /**
     *  FXML table column variable for the second table modifyProduct part's name.
     */
    @FXML
    private TableColumn<Part, String>  modifyProductPartNameNewColumn;

    /**
     *  FXML table column variable for the second table modifyProduct part's stock.
     */
    @FXML
    private TableColumn<Part, Integer>  modifyProductPartInventoryNewColumn;

    /**
     *  FXML table column variable for the second table modifyProduct part's price.
     */
    @FXML
    private TableColumn<Part, Double>  modifyProductPartPriceNewColumn;

    /**
     *  FXML text field variable for the modifyProduct table's search.
     */
    @FXML
    private TextField modifyProductSearchTextField;

    /**
     *  FXML remove part button variable to remove a part from the product.
     */
    @FXML
    private Button modifyProductRemovePartButton;

    /**
     *  Product variable that needs to be modified.
     */
    private Product modifiedProduct;

    /**
     *  Variable for the parts associated with the product.
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     *  Variable that has been selected from the main screen.
     */
    Product selectedProduct;

    /**
     * Initialize method for the ModifyProductController to initialize the stage and items.
     *
     * @param url for the url path.
     * @param resourceBundle for the resource.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedProduct = MainScreenController.getSelectedProduct();
        associatedParts = selectedProduct.getAssociatedParts();

        modifyProductID.setText(String.valueOf(selectedProduct.getId()));
        modifyProductNameTextField.setText(selectedProduct.getName());
        modifyProductInventoryTextField.setText(String.valueOf(selectedProduct.getStock()));
        modifyProductPriceTextField.setText(String.valueOf(selectedProduct.getPrice()));
        modifyProductMinTextField.setText(String.valueOf(selectedProduct.getMin()));
        modifyProductMaxTextField.setText(String.valueOf(selectedProduct.getMax()));

        modifyProductPartTable.setItems(Inventory.getAllParts());
        modifyProductPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        System.out.println(selectedProduct.getAllAssociatedParts().size());

        modifyProductPartTableNew.setItems(selectedProduct.getAllAssociatedParts());
        modifyProductPartIDNewColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductPartNameNewColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductPartInventoryNewColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductPartPriceNewColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
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
     * @throws IOException
     */
    public void modifyProductSaveButtonAction(ActionEvent actionEvent) throws IOException {
        try {
            modifiedProduct = selectedProduct;
            int index = Inventory.getAllProducts().indexOf(modifiedProduct);
            modifiedProduct.setName(modifyProductNameTextField.getText());
            modifiedProduct.setPrice(Double.parseDouble(modifyProductPriceTextField.getText()));
            modifiedProduct.setStock(Integer.parseInt(modifyProductInventoryTextField.getText()));
            modifiedProduct.setMin(Integer.parseInt(modifyProductMinTextField.getText()));
            modifiedProduct.setMax(Integer.parseInt(modifyProductMaxTextField.getText()));

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
                Product newProduct = new Product(associatedParts, modifiedProduct.getId(), modifiedProduct.getName(),
                        modifiedProduct.getPrice(), modifiedProduct.getStock(), modifiedProduct.getMin(), modifiedProduct.getMax());
                Inventory.updateProduct(index, newProduct);
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
     * Method to cancel adding product by returning back to the main screen.
     *
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * Method to search through the product's parts search bar and add the parts to a temporaryParts ObservableList
     * and if the part has the same ID or name as the text in the search bar and returns the parts in the list. If the
     * part is not found then an error message pops up on the application.
     *
     * @param actionEvent
     */
    public void modifyProductSearchTextFieldAction(ActionEvent actionEvent) {
        String productTextSearch = modifyProductSearchTextField.getText();
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

        modifyProductPartTable.setItems(temporaryParts);

        if (modifyProductSearchTextField.getText().isEmpty()) {
            modifyProductPartTable.setItems(Inventory.getAllParts());
        }

        if (temporaryParts.size() == 0) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("ERROR");
            errorAlert.setContentText("NO PARTS WAS FOUND");
            errorAlert.showAndWait();
        }
    }

    /**
     * Method to remove part from the associatedParts and from the product by first confirming the part will be removed
     * then setting the new product's part table to the new associatedParts without the part.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void modifyProductRemovePartButtonAction(ActionEvent actionEvent) throws IOException {
        Part selectedPart = modifyProductPartTableNew.getSelectionModel().getSelectedItem();

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
    public void modifyProductKeyPressedAction(KeyEvent keyEvent) {
        if (modifyProductSearchTextField.getText().isEmpty()) {
            modifyProductPartTable.setItems(Inventory.getAllParts());
        }
    }

    /**
     * Method to add a part to the associatedParts for the product.
     *
     * @param actionEvent
     */
    public void modifyProductAddPartButtonAction(ActionEvent actionEvent) {
        Part selectedPart = modifyProductPartTable.getSelectionModel().getSelectedItem();
        associatedParts.add(selectedPart);
        modifyProductPartTableNew.setItems(associatedParts);
    }
}