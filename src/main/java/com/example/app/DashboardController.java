package com.example.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DashboardController {

    @FXML
    private Label successFailLabel;

    @FXML
    private TextField firstNameLabel;

    @FXML
    private TextField lastNameLabel;

    @FXML
    private TextField positionLabel;

    @FXML
    private TextField phoneLabel;

    @FXML
    private TextField emailLabel;

    @FXML
    private TextField genderLabel;

    @FXML
    private TextField employeeIDLabel;

    @FXML
    private TableView<Employee> employeeTableView;

    @FXML
    private TableColumn<Employee, Integer> employeeIDColumn;

    @FXML
    private TableColumn<Employee, String> firstNameColumn;

    @FXML
    private TableColumn<Employee, String> lastNameColumn;

    @FXML
    private TableColumn<Employee, String> positionColumn;

    @FXML
    private TableColumn<Employee, String> genderColumn;

    @FXML
    private TableColumn<Employee, Integer> phoneColumn;

    @FXML
    private TableColumn<Employee, String> emailColumn;

    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up the columns in the table
        employeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Load data from the database
        loadEmployeeData();

        // Set the data in the TableView
        employeeTableView.setItems(employeeList);
    }

    private void loadEmployeeData() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "SELECT * FROM parks_and_recreation.employee_data";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                employeeList.add(new Employee(
                        resultSet.getInt("employeeID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("position"),
                        resultSet.getString("gender"),
                        resultSet.getInt("phone"),
                        resultSet.getString("email")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void addButtonOnAction(ActionEvent event) {
        if (!firstNameLabel.getText().isBlank() && !lastNameLabel.getText().isBlank() && !positionLabel.getText().isBlank() &&
                !genderLabel.getText().isBlank() && !emailLabel.getText().isBlank() && validateEmployeeID() && validatePhone()){
            if (validateAdd()){
                successFailLabel.setText("Addition Success");
            }
        }
        else{
            if (successFailLabel.getText().isBlank())
                successFailLabel.setText("Field can not be blank");
        }
    }

    public boolean validateAdd(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        String addQuery = "INSERT INTO parks_and_recreation.employee_data (employeeID, firstName, lastName, position, gender, phone, email) VALUES " +
                "('" + employeeIDLabel.getText() + "' ,'" + firstNameLabel.getText() + "' ,'" + lastNameLabel.getText() + "' ,'" + positionLabel.getText() + "' ,'" +
                genderLabel.getText() + "' ,'" + phoneLabel.getText() + "' ,'" + emailLabel.getText() + "')";

        try {
            Statement statement = connectDB.createStatement();
            int result = statement.executeUpdate(addQuery);
            return true;
        } catch (Exception e){
            successFailLabel.setText("Details do not meet criteria! Try Again");
            e.printStackTrace();
        }

        return false;
    }

    public boolean validateUpdate(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String updateQuery  = "UPDATE parks_and_recreation.employee_data SET " +
                "firstName = '" + firstNameLabel.getText() + "', " +
                "lastName = '" + lastNameLabel.getText() + "', " +
                "position = '" + positionLabel.getText() + "', " +
                "gender = '" + genderLabel.getText() + "', " +
                "phone = '" + phoneLabel.getText() + "', " +
                "email = '" + emailLabel.getText() + "' " +
                "WHERE employeeID = '" + employeeIDLabel.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            int result = statement.executeUpdate(updateQuery);
            return true;
        } catch (Exception e){
            successFailLabel.setText("Details do not meet criteria! Try Again");
            e.printStackTrace();
        }

        return false;
    }

    public boolean validateEmployeeID(){
        // Validate that employeeID is not empty and is a valid integer
        String employeeIDText = employeeIDLabel.getText();
        if (employeeIDText.isEmpty()) {
            successFailLabel.setText("Employee ID cannot be empty!");
            return false;
        }

        try {
            Integer.parseInt(employeeIDText); // Check if it's a valid integer
        } catch (NumberFormatException e) {
            successFailLabel.setText("Employee ID must be a valid integer!");
            return false;
        }
        return true;
    }

    public boolean validatePhone(){
        // Validate that employeeID is not empty and is a valid integer
        String phoneText = phoneLabel.getText();
        if (phoneText.isEmpty()) {
            successFailLabel.setText("Phone number cannot be empty!");
            return false;
        }

        try {
            Integer.parseInt(phoneText); // Check if it's a valid integer
        } catch (NumberFormatException e) {
            successFailLabel.setText("Phone must be a valid integer!");
            return false;
        }
        return true;
    }

    @FXML
    protected void updateButtonOnAction(ActionEvent event) {
        if (!firstNameLabel.getText().isBlank() && !lastNameLabel.getText().isBlank() && !positionLabel.getText().isBlank() &&
                !genderLabel.getText().isBlank() && !emailLabel.getText().isBlank() && validateEmployeeID() && validatePhone()){
            if (validateUpdate()){
                successFailLabel.setText("Update Success");
            }
        }
        else{
            if (successFailLabel.getText().isBlank())
                successFailLabel.setText("Field can not be blank");
        }
    }
}
