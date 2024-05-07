package be.kuleuven.candycrush.model;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public record Position(int x, int y, BoardSize boardSize) {
    public Position {
        if(x >= boardSize.width() || x < 0) throw  new IllegalArgumentException("Waarde is niet geldig");
        if(y >= boardSize.height() || y < 0) throw  new IllegalArgumentException("Waarde is niet geldig");
    }

    public int toIndex() {
        return x + y*boardSize.width();
    }

    public static Position fromIndex(int index, BoardSize boardSize) {
        int x = index % boardSize.width();
        int y = index/boardSize.height();

        return new Position(x, y, boardSize);
    }

    public Iterable<Position> neighborPositions() {
        ArrayList<Position> list = new ArrayList<>();

        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                try {
                    list.add(new Position(x() + i, y()+j, boardSize));
                    System.out.println((x() + i) + " "  + (y() + j));
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e);
                }
            }
        }
        return list;
    }

//    public static void main(String[] args) {
//        Position position = new Position(2,2,new BoardSize(10,10));
//        ArrayList<Position> uitkomst = (ArrayList<Position>) position.neighborPositions();
//        for(int i = 0; i < uitkomst.size(); i++) {
//            System.out.println(uitkomst.get(i));
//        }
//    }

    public boolean isLastColumn() {
        return x == boardSize().width() - 1;
    }
}
