package com.example.inventorymanagementapplication.model;

/**
 * Model class for InHouse.
 *
 * @author Yonese James
 */
public class InHouse extends Part {
    /**
     * Variable for the InHouse's machineID.
     */
    private int machineID;

    /**
     * Constructor that creates a new instance of the InHouse object which extends the Part abstract class.
     *
     * @param id for the ID of the InHouse.
     * @param name for the name of the InHouse.
     * @param price for the price of the InHouse.
     * @param stock for the stock of the InHouse.
     * @param min for the min of the InHouse.
     * @param max for the max of the InHouse.
     * @param machineID for the machineID of the InHouse.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /**
     * Getter for the machineID of the InHouse.
     *
     * @return the machineID.
     */
    public int getMachineID() {
        return machineID;
    }

    /**
     * Setter for the machineID of the InHouse.
     *
     * @param machineID to be set for the InHouse.
     */
    public void setMachineId(int machineID) {
        this.machineID = machineID;
    }
}