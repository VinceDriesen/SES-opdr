package be.kuleuven.candycrush.model;

import be.kuleuven.CheckNeighboursInGrid;
import be.kuleuven.candycrush.controller.CandycrushController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class CandycrushModel {
    private static String speler;
    private ArrayList<Integer> speelbord;
    private int width;
    private int height;

    private int score;



    public CandycrushModel(String speler) {
        this.speler = speler;
        speelbord = new ArrayList<>();
        width = 10;
        height = 10;

        for (int i = 0; i < width*height; i++){
            Random random = new Random();
            int randomGetal = random.nextInt(5) + 1;
            speelbord.add(randomGetal);
        }
    }

    public String getSpeler() {
        return speler;
    }

    public ArrayList<Integer> getSpeelbord() {
        return speelbord;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void candyWithIndexSelected(int index){
        if (index != -1){
            Random random = new Random();
            int randomGetal = random.nextInt(5) + 1;
            speelbord.set(index,randomGetal);
        }else{
            System.out.println("model:candyWithIndexSelected:indexWasMinusOne");
        }
    }

    public int getIndexFromRowColumn(int row, int column) {
        return column+row*width;
    }

    public void changeNeighbours(int index) {
        Iterable<Integer> neighBoursWithSameNumber = CheckNeighboursInGrid.getSameNeighboursIds(this.speelbord, this.width, this.height, index);
        if(getSizeIterable(neighBoursWithSameNumber) >= 2) {
            candyWithIndexSelected(index);
            score++;
            for(Integer neighbourIndex : neighBoursWithSameNumber) {
                candyWithIndexSelected(neighbourIndex);
                score++;
            }
        }
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

        for (int i = 0; i < width*height; i++){
            Random random = new Random();
            int randomGetal = random.nextInt(5) + 1;
            speelbord.add(randomGetal);
        }
    }

    public void setGrid(ArrayList<Integer> array) {
        speelbord.clear();
        speelbord.addAll(array);
    }
}
