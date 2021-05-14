package me.internalizable.jarvis.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import me.internalizable.jarvis.gui.App;
import me.internalizable.jarvis.utils.JarvisLists;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button button;
    @FXML
    private Label wrongLogin;

    public void initialize() {}

    @FXML
    public void onLogin(ActionEvent event) {

        if (username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogin.setText("Username and password fields cannot be empty.");
            return;
        }

        if (JarvisLists.hasLogin(username.getText(), password.getText())) {
            try {
                App.setRoot("menu");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            wrongLogin.setText("Wrong login details.");

    }

}