package me.internalizable.jarvis.gui.controllers;

import com.google.common.hash.Hashing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import me.internalizable.jarvis.Jarvis;
import me.internalizable.jarvis.gui.App;
import me.internalizable.jarvis.utils.JarvisLists;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginController {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button button;
    @FXML private Label wrongLogin;

    public void initialize() {}

    @FXML
    public void onLogin(ActionEvent event) {

        if(username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogin.setText("Username and password fields cannot be empty.");
            return;
        }

        if(JarvisLists.hasLogin(username.getText(), password.getText())) {
            try {
                App.setRoot("menu");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            wrongLogin.setText("Wrong login details.");

    }

}