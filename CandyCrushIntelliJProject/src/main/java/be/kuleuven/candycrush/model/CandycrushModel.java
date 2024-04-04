package be.kuleuven.candycrush.model;

import be.kuleuven.candycrush.model.Candies.*;

import java.util.ArrayList;
import java.util.Random;

public class CandycrushModel{
    private static String speler;

    private int score;

    private Board<Candy> board;


    public CandycrushModel(String speler) {
        this.speler = speler;
        this.board = new Board<>(new BoardSize(10,10), this::getRandomCandy);
    }

    private Candy getRandomCandy(Position position) {
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

    public int getWidth() {
        return board.getBoardSize().width();
    }

    public int getHeight() {
        return board.getBoardSize().height();
    }

    public void candyWithPositionSelected(Position position){
        if (position.toIndex() != -1){
            board.replaceCellAt(position, getRandomCandy(null));
            System.out.println(position + " veranderd");
        }else{
            System.out.println("model:candyWithIndexSelected:indexWasMinusOne");
        }
    }

    public Candy getCandyFromPosition(Position position) {
        return board.getCellAt(position);
    }

    public Iterable<Position> getSameNeighboutPositions(Position position) {
        ArrayList<Position> neighbours = new ArrayList<>();
        for(Position p : position.neighborPositions()) {
            if(getCandyFromPosition(p).equals(getCandyFromPosition(position))) {
                neighbours.add(p);
            }
        }
        return neighbours;
    }

    public void changeNeighboursFromPosition(Position position) {
        ArrayList<Position> neighbours = (ArrayList<Position>)getSameNeighboutPositions(position);
        if(neighbours.size() >= 4) {
            candyWithPositionSelected(position);
            score++;
            for(Position p : neighbours) {
                candyWithPositionSelected(p);
                score++;
            }
        }
    }
    public int getScore() {
        return score;
    }

    public void resetAll() {
        score = 0;
        this.board = new Board<>(new BoardSize(10,10), this::getRandomCandy);
    }

    public BoardSize getBoardSize() {
        return board.getBoardSize();
    }
}
