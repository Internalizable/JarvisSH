package me.internalizable.jarvis.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.internalizable.jarvis.gui.App;
import me.internalizable.jarvis.internal.Accessory;
import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.internal.users.User;
import me.internalizable.jarvis.internal.users.UserType;
import me.internalizable.jarvis.utils.JarvisLists;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MenuController {

    @FXML
    private ChoiceBox<String> itemChoice;

    @FXML
    private TableView table;

    @FXML
    private MenuItem createUser;

    public void initialize() {
        App.getStage().setResizable(true);

        buildOperationTable();

        ObservableList<String> items = FXCollections.observableList(Arrays.asList("Operations", "Accessories", "Users"));
        itemChoice.setItems(items);
        itemChoice.setValue("Operations");

        itemChoice.setOnAction(actionEvent -> {

            table.getItems().clear();
            table.getColumns().clear();

            if (itemChoice.getValue().equals("Operations"))
                buildOperationTable();
            else if (itemChoice.getValue().equalsIgnoreCase("Accessories"))
                buildAccessoriesTable();
            else if (itemChoice.getValue().equalsIgnoreCase("Users"))
                buildUsersTable();
        });

        createUser.setOnAction(actionEvent -> {
            App.getStage().setResizable(false);
            try {
                App.setRoot("register");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void buildOperationTable() {
        TableColumn<Operation, Long> idColumn = new TableColumn<>("Operation ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Operation, Integer> idUser = new TableColumn<>("User ID");
        idUser.setCellValueFactory(new PropertyValueFactory<>("userID"));

        TableColumn<Operation, String> roomName = new TableColumn<>("Room Name");
        roomName.setCellValueFactory(new PropertyValueFactory<>("roomName"));

        TableColumn<Operation, String> date = new TableColumn<>("Operation Date");
        date.setCellValueFactory(new PropertyValueFactory<>("dateString"));

        TableColumn<Operation, String> accessoryId = new TableColumn<>("Accessory ID");
        accessoryId.setCellValueFactory(new PropertyValueFactory<>("accessoryID"));

        TableColumn<Operation, List<String>> status = new TableColumn<>("Operation Status");
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().add(idColumn);
        table.getColumns().add(idUser);
        table.getColumns().add(roomName);
        table.getColumns().add(date);
        table.getColumns().add(accessoryId);
        table.getColumns().add(status);

        table.getItems().addAll(JarvisLists.getOperationList());
    }

    public void buildAccessoriesTable() {
        TableColumn<Accessory, Long> idColumn = new TableColumn<>("Accessory ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Accessory, String> accessoryName = new TableColumn<>("Accessory Name");
        accessoryName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Accessory, String> accessoryManufacturer = new TableColumn<>("Accessory Man.");
        accessoryManufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));

        TableColumn<Accessory, Accessory.AccessoryType> accessoryType = new TableColumn<>("Accessory Type");
        accessoryType.setCellValueFactory(new PropertyValueFactory<>("type"));

        table.getColumns().add(idColumn);
        table.getColumns().add(accessoryName);
        table.getColumns().add(accessoryManufacturer);
        table.getColumns().add(accessoryType);

        table.getItems().addAll(JarvisLists.getAccessoryList());
    }

    public void buildUsersTable() {
        TableColumn<User, Long> idColumn = new TableColumn<>("User ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> userName = new TableColumn<>("User Name");
        userName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, UserType> accessoryType = new TableColumn<>("User Type");
        accessoryType.setCellValueFactory(new PropertyValueFactory<>("type"));

        table.getColumns().add(idColumn);
        table.getColumns().add(userName);
        table.getColumns().add(accessoryType);

        table.getItems().addAll(JarvisLists.getUsersList());
    }
}
