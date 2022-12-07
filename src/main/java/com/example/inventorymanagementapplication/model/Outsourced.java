package com.example.inventorymanagementapplication.model;

/**
 * Model class for Outsourced.
 *
 * @author Yonese James
 */
public class Outsourced extends Part {
    /**
     * Variable for the Outsourced's companyName.
     */
    private String companyName;

    /**
     * Constructor that creates a new instance of the Outsourced object which extends the Part abstract class.
     *
     * @param id for the ID of the Outsourced.
     * @param name for the name of the Outsourced.
     * @param price for the price of the Outsourced.
     * @param stock for the stock of the Outsourced.
     * @param min for the min of the Outsourced.
     * @param max for the max of the Outsourced.
     * @param companyName for the companyName of the Outsourced.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Getter for the companyName of the Outsourced.
     *
     * @return the companyName.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Setter for the companyName of the Outsourced.
     *
     * @param companyName to be set for the Outsourced.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}