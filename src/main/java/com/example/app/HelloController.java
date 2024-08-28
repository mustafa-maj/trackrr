package com.example.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
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
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("dashboard.fxml"));
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

        String verifyLogin = "SELECT count(1) FROM useraccounts WHERE username = '" + usernameTextField.getText() + "' AND password = '" + passwordPasswordField.getText() +"'";
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

}