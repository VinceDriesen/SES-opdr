package be.kuleuven.candycrush.model;


import com.google.common.primitives.Ints;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public record Position(int x, int y, BoardSize boardSize) {
    public Position {
        if(x > boardSize.width() || x < 0) throw  new IllegalArgumentException("Waarde is niet geldig");
        if(y > boardSize.height() || y < 0) throw  new IllegalArgumentException("Waarde is niet geldig");
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

    public Stream<Position> walkLeft() {
        Stream<Position> result = IntStream.rangeClosed(0,x)
                .mapToObj(i -> new Position(x-i, y, this.boardSize));
        return result;
    }

    public Stream<Position> walkRight() {
        Stream<Position> result = IntStream.rangeClosed(x, boardSize().width()-1)
                .mapToObj(i -> new Position(i, y, this.boardSize));
        return result;
    }
    public Stream<Position> walkUp() {
        Stream<Position> result = IntStream.rangeClosed(0,y)
                .mapToObj(i -> new Position(x, y-i, this.boardSize));
        return result;
    }

    public Stream<Position> walkDown() {
        Stream<Position> result = IntStream.rangeClosed(y,boardSize.height()-1)
                .mapToObj(i -> new Position(x, i, this.boardSize));
        return result;
    }


    public boolean isLastColumn() {
        return x == boardSize().width() - 1;
    }
}