package me.internalizable.jarvis.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import me.internalizable.jarvis.internal.users.UserType;
import me.internalizable.jarvis.utils.JarvisLists;

import java.io.IOException;

public class App extends Application {

    @Getter
    private static Scene scene;

    @Getter
    private static Stage stage;

    @Override
    public void start(Stage stg) throws IOException {
        stg.setResizable(false);
        stg.setTitle("Jarvis SmartHome");

        if(JarvisLists.getUsersList().stream().noneMatch(user -> user.getType() == UserType.ADMINISTRATOR))
            scene = new Scene(loadFXML("register"));
        else
            scene = new Scene(loadFXML("login"));

        stg.setScene(scene);
        stg.show();

        stage = stg;
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getClassLoader().getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void open() {
        launch();
    }

}