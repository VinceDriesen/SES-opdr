package be.kuleuven;
import java.util.ArrayList;
import java.util.Collections;

public class CheckNeighboursInGrid {
    /**
     * This method takes a 1D Iterable and an element in the array and gives back an iterable containing the indexes of all neighbours with the same value as the specified element
     *@return - Returns a 1D Iterable of ints, the Integers represent the indexes of all neighbours with the same value as the specified element on index 'indexToCheck'.
     *@param grid - This is a 1D Iterable containing all elements of the grid. The elements are integers.
     *@param width - Specifies the width of the grid.
     *@param height - Specifies the height of the grid (extra for checking if 1D grid is complete given the specified width)
     *@param indexToCheck - Specifies the index of the element which neighbours that need to be checked
     */
    public static Iterable<Integer> getSameNeighboursIds(Iterable<Integer> grid,int width, int height, int indexToCheck) {
        ArrayList<Integer> list = new ArrayList<>();

        for (Integer integer : grid) {
            list.add(integer);
        }

        int numberToCheck = list.get(indexToCheck); //Kijken welk nummer het is
        int posVertical = indexToCheck / width; //Y-coördinaat van het nummer
        int posHorizontal = indexToCheck - (posVertical * height); //X-coördinaat van het nummer

        return getNeighbours(width, posVertical, posHorizontal, numberToCheck, list);
    }

    /**
     * Finds the arrayList of the neighbours
     * @param width of playingfield
     * @param VerticalPosition Y-position of the index to check
     * @param horizontalPosition X-position of the index to check
     * @param number the number of index to check
     * @param list the arraylist of all integers
     * @return Arraylist of all the neighbouring integers
     */
    private static ArrayList<Integer> getNeighbours(int width, int VerticalPosition, int horizontalPosition, int number, ArrayList<Integer> list) {
        ArrayList<Integer> neighBours = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(i == 0 && j == 0) { continue; } //Als het eigen cijfer is, mag deze geskipt worden
                int indexNumber =(VerticalPosition + j)*width + (horizontalPosition + i); //Berekenen wat de index is van het cijfer
                try {
                    if (list.get(indexNumber) == number) {
                        neighBours.add(indexNumber);
                    }
                }
                catch (IndexOutOfBoundsException ignored) {
                    //Hier kan nog een melding bij maar moet niet echt
                }
            }
        }
        Collections.sort(neighBours);
        return neighBours;
    }
}