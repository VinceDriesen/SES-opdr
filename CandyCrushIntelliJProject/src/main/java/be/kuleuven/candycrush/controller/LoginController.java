package be.kuleuven.candycrush.controller;

import be.kuleuven.candycrush.CandycrushApplication;
import be.kuleuven.candycrush.LoginApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Start;

    @FXML
    private TextField Name;

    private static String passName;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        Name.setOnKeyTyped(this::update);
        Start.setStyle("-fx-background-color : RED");
        Start.setOnMouseClicked(event -> startPressed("candycrush-view.fxml"));
    }

    private void startPressed(String fxmlNaam) {
        try {
            if (!Name.getCharacters().isEmpty()) {
                CandycrushApplication application = new CandycrushApplication();
                Stage stage = new Stage();

                application.start(stage);
                LoginApplication.close();

                passName = Name.getText();
            }
        }
        catch(Exception e) {
            throw new RuntimeException("Vind " + fxmlNaam + " NIET" ,e);
        }
    }


    private void update(KeyEvent event) {

        if(!Name.getCharacters().isEmpty()) {
            Start.setStyle("-fx-background-color : GREEN");
        }

        else {
            Start.setStyle("-fx-background-color : RED");
        }
    }

    public static String getName() {
        return passName;
    }
}
