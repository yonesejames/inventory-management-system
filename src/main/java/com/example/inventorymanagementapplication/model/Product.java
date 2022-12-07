package com.example.inventorymanagementapplication.model;

import javafx.collections.ObservableList;

/**
 * Model class for Product.
 *
 * @author Yonese James
 */
public class Product {
    /**
     * Variable for the Product's associatedParts, id, name, price, stock, min, and max.
     */
    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Constructor that creates a new instance of the Product object.
     *
     * @param associatedParts for the associatedParts of the Product.
     * @param id for the ID of the Product.
     * @param name for the name of the Product.
     * @param price for the price of the Product.
     * @param stock for the stock of the Product.
     * @param min for the min of the Product.
     * @param max for the max of the Product.
     */
    public Product(ObservableList<Part> associatedParts, int id, String name, double price, int stock, int min, int max) {
        this.associatedParts = associatedParts;
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Overloaded constructor that creates a new instance of the Product object.
     *
     */
    public Product() {
    }

    /**
     * Getter for the associatedParts of the Product.
     *
     * @return the associatedParts.
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    /**
     * Setter for the associatedParts of the Product.
     *
     * @param associatedParts to be set for the Product.
     */
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    /**
     * Getter for all associatedParts of the Product.
     *
     * @return the associatedParts.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /**
     * Method to add the associatedParts of the Product.
     *
     * Add the part to the associatedParts.
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Method to delete the associatedParts of the Product.
     *
     * Find the part in the associatedParts then delete the part to the associatedParts
     * and return true. If part is not found then return false.
     */
    public boolean deleteAssociatedPart(Part part) {
        boolean findMatch = associatedParts.stream().anyMatch(part::equals);
        if (findMatch) {
            associatedParts.remove(part);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Getter for the id of the Product.
     *
     * @return the id.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the id of the Product.
     *
     * @param id to be set for the Product.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the name of the Product.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the Product.
     *
     * @param name to be set for the Product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the price of the Product.
     *
     * @return the price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for the price of the Product.
     *
     * @param price to be set for the Product.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for the stock of the Product.
     *
     * @return the stock.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Setter for the stock of the Product.
     *
     * @param stock to be set for the Product.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Getter for the min of the Product.
     *
     * @return the min.
     */
    public int getMin() {
        return min;
    }

    /**
     * Setter for the min of the Product.
     *
     * @param min to be set for the Product.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Getter for the max of the Product.
     *
     * @return the max.
     */
    public int getMax() {
        return max;
    }

    /**
     * Setter for the max of the Product.
     *
     * @param max to be set for the Product.
     */
    public void setMax(int max) {
        this.max = max;
    }
}