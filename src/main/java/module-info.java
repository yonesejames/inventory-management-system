module com.example.inventorymanagementapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.inventorymanagementapplication to javafx.fxml;
    exports com.example.inventorymanagementapplication;
    exports com.example.inventorymanagementapplication.controller;
    opens com.example.inventorymanagementapplication.controller to javafx.fxml;
}