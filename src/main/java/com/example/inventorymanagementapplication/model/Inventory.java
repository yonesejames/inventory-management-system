package com.example.inventorymanagementapplication.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Model class for Inventory.
 *
 * @author Yonese James
 */
public class Inventory {
    /**
     * Variables for the Inventory's partID, productID, allParts, and allProducts.
     */
    private static int partID = 0;
    private static int productID = 0;
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Getter for the partID of the Inventory.
     *
     * @return the partID.
     */
    public static int getPartID() {
        return partID;
    }

    /**
     * Setter for the partID of the Inventory.
     *
     * @return partID to be set for the part in the Inventory.
     */
    public static int setPartID() {
        return partID++;
    }

    /**
     * Getter for the productID of the Inventory.
     *
     * @return the productID.
     */
    public static int getProductID() {
        return productID;
    }

    /**
     * Setter for the productID of the Inventory.
     *
     * @return productID to be set for the product in the Inventory.
     */
    public static int setProductID() {
        return productID++;
    }

    /**
     * Method to add part.
     *
     * Add the part to allParts.
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /**
     * Method to add product.
     *
     * Add the product to allProducts.
     */
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    /**
     * Method to look up parts by ID.
     *
     * Loop through each part in allParts and if the part ID matches partID argument then
     * assign it to partIDFound and return. If all IDs do not match then return partIDFound which has
     * been initiated to null.
     */
    public static Part lookupPart (int partID) {
        Part partIDFound = null;

        for (Part part : allParts) {
            if (part.getId() == partID) {
                partIDFound = part;
            }
        }

        return partIDFound;
    }

    /**
     * Method to look up products by ID.
     *
     * Loop through each product in allProducts and if the product ID matches productID argument then
     * assign it to productIDFound and return. If all IDs do not match then return productIDFound which has
     * been initiated to null.
     */
    public static Product lookupProduct (int productID) {
        Product productIDFound = null;

        for (Product product : allProducts) {
            if (product.getId() == productID) {
                productIDFound = product;
            }
        }

        return productIDFound;
    }

    /**
     * Method to look up parts by name.
     *
     * Loop through each part in allParts and if the part name matches partName argument then
     * assign it to partNameFound and return. If all names do not match then return partNameFound which has
     * been initiated to an empty observableArrayList.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partNameFound = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName() == partName) {
                partNameFound.add(part);
            }
        }

        return partNameFound;
    }

    /**
     * Method to look up products by name.
     *
     * Loop through each product in allProducts and if the product name matches productName argument then
     * assign it to productNameFound and return. If all names do not match then return productNameFound which has
     * been initiated to an empty observableArrayList.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productNameFound = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName() == productName) {
                productNameFound.add(product);
            }
        }

        return productNameFound;
    }

    /**
     * Method to update part.
     *
     * Update the part based on the index argument and set newPart.
     */
    public static void updatePart(int index, Part newPart) {
        allParts.set(index, newPart);
    }

    /**
     * Method to update product.
     *
     * Update the product based on the index argument and set newProduct.
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * Method to delete the selectedPart in allParts.
     *
     * Find the selectedPart in allParts then delete the selectedPart
     * and return true. If part is not found then return false.
     */
    public static boolean deletePart(Part selectedPart) {
        boolean findMatch = allParts.stream().anyMatch(selectedPart::equals);
        if (findMatch) {
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Method to delete allProducts in allProducts.
     *
     * Find the product in allProducts then delete the selectedProduct
     * and return true. If product is not found then return false.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        boolean findMatch = allProducts.stream().anyMatch(selectedProduct::equals);
        if (findMatch) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Getter for allParts.
     *
     * @return allParts.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Getter for allProducts.
     *
     * @return allProducts.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}