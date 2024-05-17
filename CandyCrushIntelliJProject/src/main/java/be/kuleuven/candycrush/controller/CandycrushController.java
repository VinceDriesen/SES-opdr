package be.kuleuven.candycrush.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import be.kuleuven.candycrush.model.CandycrushModel;
import be.kuleuven.candycrush.model.Position;
import be.kuleuven.candycrush.model.PositionPairs;
import be.kuleuven.candycrush.model.PositionScorePair;
import be.kuleuven.candycrush.view.CandycrushView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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


//    CandycrushModel model1 = CandycrushModel.createBoardFromString("""
//   #oo##
//   #@o@@
//   *##o@
//   @@*@o
//   **#*o""");
//
    CandycrushModel model1 = CandycrushModel.createBoardFromString("""
   #@#oo@
   @**@**
   o##@#o
   @#oo#@
   @*@**@
   *#@##*""");

//    CandycrushModel model1 = CandycrushModel.createBoardFromString("""
//       @@o#
//       o*#o
//       @@**
//       *#@@""");

    @FXML
    void initialize() {

//        model = new CandycrushModel(LoginController.getName(), 10);

        view = new CandycrushView(model1);
        speelbord.getChildren().add(view);

//        view.setOnMouseClicked(this::onCandyClicked);
//        Reset.setOnMouseClicked(event -> reset());
        model1.updateBoard();
        PositionScorePair scorePair = model1.solve();
        for(PositionPairs pair : scorePair.pairs()) {
            System.out.print(
                    "(r" + (pair.position1().x()+1) + ",c" + (pair.position1().y()+1) + ")" +
                            "⇄" +
                    "(r" + (pair.position2().x()+1) + ",c" + (pair.position2().y()+1) + ")"
            );
            System.out.print(" ; ");
        }
        System.out.println("Hoogste score " + scorePair.score());

        update();
    }

    public void update(){
        view.update();
        Score.setText(model1.getScore() + "");
    }

    public void onCandyClicked(MouseEvent me){
//        model.findAllSwaps().forEach(p -> System.out.println("Match: " + "x1: " + p.position1().x() + " y1: " + p.position1().y() + " x2: " +  p.position2().x() + " y2: " +p.position2().y()));
//        System.out.println(model1.solve());
    }

    private void reset() {
        model.resetAll();
        update();
    }

}
