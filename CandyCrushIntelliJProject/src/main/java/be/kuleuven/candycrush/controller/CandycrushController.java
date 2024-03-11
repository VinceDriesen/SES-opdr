package be.kuleuven.candycrush.controller;

import java.net.URL;
import java.util.ResourceBundle;

import be.kuleuven.candycrush.LoginApplication;
import be.kuleuven.candycrush.model.CandycrushModel;
import be.kuleuven.candycrush.view.CandycrushView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class CandycrushController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Score;


    @FXML
    private AnchorPane paneel;

    @FXML
    private AnchorPane speelbord;

    @FXML
    private Button Reset;


    private CandycrushModel model;
    private CandycrushView view;

    private LoginController loginController;

    @FXML
    void initialize() {

        model = new CandycrushModel(LoginController.getName());
        view = new CandycrushView(model);
        speelbord.getChildren().add(view);

        view.setOnMouseClicked(this::onCandyClicked);
        Reset.setOnMouseClicked(event -> reset());
    }

    public void update(){
        view.update();
        Score.setText(model.getScore() + "");
    }

    public void onCandyClicked(MouseEvent me){
        int candyIndex = view.getIndexOfClicked(me);
        model.changeNeighbours(candyIndex);
        update();
    }

    private void reset() {
        model.resetAll();
        update();
    }

}
