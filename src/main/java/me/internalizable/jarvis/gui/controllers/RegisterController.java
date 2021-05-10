package me.internalizable.jarvis.gui.controllers;

import com.google.common.hash.Hashing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import me.internalizable.jarvis.Jarvis;
import me.internalizable.jarvis.gui.App;
import me.internalizable.jarvis.internal.users.User;
import me.internalizable.jarvis.internal.users.UserType;
import me.internalizable.jarvis.reader.CollectionType;
import me.internalizable.jarvis.utils.JarvisLists;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class RegisterController {

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button button;

    @FXML private ChoiceBox<String> choiceBox;
    @FXML private Label errorField;

    public void initialize() {
        ObservableList<String> items;

        if(JarvisLists.getUsersList().stream().noneMatch(user -> user.getType() == UserType.ADMINISTRATOR))
            items = FXCollections.observableList(Collections.singletonList("ADMINISTRATOR"));
        else
            items = FXCollections.observableArrayList(Arrays.stream(UserType.values()).map(Enum::name).toArray(String[]::new));

        choiceBox.setItems(items);
    }

    @FXML
    public void onRegister(ActionEvent event) {
        if(username.getText().isEmpty() || password.getText().isEmpty() || choiceBox.getValue() == null || choiceBox.getValue().isEmpty()) {
            errorField.setText("Error, a field is missing.");
            return;
        }

        String typedName = username.getText();

        if(JarvisLists.hasUser(typedName)) {
            errorField.setText("Error, a user with that name already exists.");
            return;
        }

        JarvisLists.registerUser(typedName, password.getText(), UserType.valueOf(choiceBox.getValue()));

        try {
            App.setRoot("menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
