package be.kuleuven.candycrush.model;

import be.kuleuven.candycrush.controller.CandycrushController;
import be.kuleuven.candycrush.model.Candies.*;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class CandycrushModel{
    private static String speler;
    private ArrayList<Candy> speelbord;

    private int score;

    private BoardSize boardSize;


    public CandycrushModel(String speler) {
        this.speler = speler;
        speelbord = new ArrayList<>();
        this.boardSize = new BoardSize(10,10);

        for (int i = 0; i < boardSize.width()*boardSize.height(); i++){
            speelbord.add(getRandomCandy());
        }
    }

    private Candy getRandomCandy() {
        Random random = new Random();
        int randomGetal = random.nextInt(9) + 1;
        Candy candy = switch (randomGetal) {
            case 1 -> new RainbowCandy();
            case 2 -> new StrawBerrie();
            case 3 -> new BlueBerrie();
            case 4 -> new UltimateCandy();
            default -> new NormalCandy(random.nextInt(4));
        };

        return candy;
    }

    public String getSpeler() {
        return speler;
    }

    public ArrayList<Candy> getSpeelbord() {
        return speelbord;
    }

    public int getWidth() {
        return boardSize.width();
    }

    public int getHeight() {
        return boardSize.height();
    }

    public void candyWithPositionSelected(Position position){
        if (position.toIndex() != -1){
            speelbord.set(position.toIndex(),getRandomCandy());
            System.out.println(position + " veranderd");
        }else{
            System.out.println("model:candyWithIndexSelected:indexWasMinusOne");
        }
    }

    public Candy getCandyFromPosition(Position position) {
        return speelbord.get(position.toIndex());
    }

    public Iterable<Position> getSameNeighboutPositions(Position position) {
        ArrayList<Position> neighbours = new ArrayList<>();
        for(Position p : position.neighborPositions()) {
            if(getCandyFromPosition(p).equals(getCandyFromPosition(position))) {

                neighbours.add(p);
                score++;
            }
        }
        return neighbours;
    }

    public Iterable<Position>  changeNeighboursFromPosition(Position position) {
        ArrayList<Position> neighbours = (ArrayList<Position>)getSameNeighboutPositions(position);
        if(neighbours.size() >= 4) {
            candyWithPositionSelected(position);
            score++;
            for(Position p : neighbours) {
                candyWithPositionSelected(p);
                score++;
            }
        }
        return neighbours;
    }

    public int getSizeIterable(Iterable<Integer> iterable) {
        int counter = 0;
        for (Integer i : iterable) {
            counter++;
        }
        return counter;
    }

    public int getScore() {
        return score;
    }

    public void resetAll() {
        score = 0;

        speelbord = new ArrayList<>();

        for (int i = 0; i < boardSize.width()*boardSize.height(); i++){
            speelbord.add(getRandomCandy());
        }
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }
}
