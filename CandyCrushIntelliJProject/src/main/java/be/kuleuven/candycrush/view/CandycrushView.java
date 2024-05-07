package be.kuleuven.candycrush.view;

import be.kuleuven.candycrush.model.BoardSize;
import be.kuleuven.candycrush.model.Candies.*;
import be.kuleuven.candycrush.model.Candy;
import be.kuleuven.candycrush.model.CandycrushModel;
import be.kuleuven.candycrush.model.Position;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Iterator;

public class CandycrushView extends Region {
    private CandycrushModel model;
    private int widthCandy;
    private int heigthCandy;

    public CandycrushView(CandycrushModel model) {
        this.model = model;
        widthCandy = 60;
        heigthCandy = 60;
        update();
    }

    public void update(){
        getChildren().clear();
        for(Position position : model.getBoardSize().positions()) {
            Node node = makeShapeCandy(position, model.getCandyFromPosition(position));
            getChildren().addAll(node);
        }
    }

    public Position getPositionOfClicked(MouseEvent me){
        Position position = null;
        int row = (int) me.getX()/heigthCandy;
        int column = (int) me.getY()/widthCandy;

        System.out.println(me.getX()+" - "+me.getY()+" - "+row+" - "+column);

        if (row < model.getWidth() && column < model.getHeight()){
            position = new Position(row, column, model.getBoardSize());
            System.out.println(position.toIndex());
        }
        return position;
    }

    public Node makeShapeCandy(Position position, Candy candy) {
        if(candy instanceof NormalCandy) {
            switch(((NormalCandy) candy).color()) {
                case 0:
                    return new Circle(position.x() * widthCandy + widthCandy/2, position.y() * heigthCandy + heigthCandy/2, widthCandy / 2, Color.BLUE);
                case 1:
                    return new Circle(position.x() * widthCandy + widthCandy/2, position.y() * heigthCandy + heigthCandy/2, widthCandy / 2, Color.PURPLE);
                case 2:
                    return new Circle(position.x() * widthCandy + widthCandy/2, position.y() * heigthCandy + heigthCandy/2, widthCandy / 2, Color.RED);
                case 3:
                    return new Circle(position.x() * widthCandy + widthCandy/2, position.y() * heigthCandy + heigthCandy/2, widthCandy / 2, Color.YELLOW);

            }
        }
        else if(candy instanceof EmptyCandy) {
            Rectangle vierkant = new Rectangle(position.x() * widthCandy, position.y() * heigthCandy, widthCandy, heigthCandy);
            vierkant.setFill(Color.TRANSPARENT);
            return vierkant;
        }
        else {
            switch (candy) {
                case UltimateCandy ultimateCandy -> {
                    Rectangle vierkant = new Rectangle(position.x() * widthCandy, position.y() * heigthCandy, widthCandy, heigthCandy);
                    vierkant.setFill(Color.YELLOW);
                    return vierkant;
                }
                case BlueBerrie blueBerrie -> {
                    Rectangle vierkant = new Rectangle(position.x() * widthCandy, position.y() * heigthCandy, widthCandy, heigthCandy);
                    vierkant.setFill(Color.BLUE);
                    return vierkant;
                }
                case StrawBerrie strawBerrie -> {
                    Rectangle vierkant = new Rectangle(position.x() * widthCandy, position.y() * heigthCandy, widthCandy, heigthCandy);
                    vierkant.setFill(Color.RED);
                    return vierkant;
                }
                case RainbowCandy rainbowCandy -> {
                    Rectangle vierkant = new Rectangle(position.x() * widthCandy, position.y() * heigthCandy, widthCandy, heigthCandy);
                    vierkant.setFill(Color.PURPLE);
                    return vierkant;
                }
                case null, default -> {
                }
            }

        }
        return null;
    }


}
