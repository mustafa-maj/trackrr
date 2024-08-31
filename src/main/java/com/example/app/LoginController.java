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


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Label loginMessageLabel;

    @FXML
    protected void loginButtonOnAction(ActionEvent event) {
        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()){
            if (validateLogin()){
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("dashboard.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 850, 600);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        else{
            loginMessageLabel.setText("Please enter username or password");
        }
    }

    public boolean validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM useraccounts WHERE username = '" + usernameTextField.getText() + "' AND password = '" + hashPassword(passwordPasswordField.getText()) +"'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    return true;
                }else{
                    loginMessageLabel.setText("Invalid Login! Try Again");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static String hashPassword(String plainTextPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainTextPassword.getBytes());
            byte[] digest = md.digest();
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Convert byte array to hex string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}