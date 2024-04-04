package be.kuleuven.candycrush.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public class Board <Type> {
    private BoardSize boardSize;
    private ArrayList<Type> list;


    public Board(BoardSize boardSize, Function<Position, Type> cellCreator) {
        this.boardSize = boardSize;
        this.list = new ArrayList<>(Collections.nCopies(boardSize.height()*boardSize.width() , null));
        this.fill(cellCreator);
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }

    public Type getCallAt(Position position) {
        if(list.size() <= position.toIndex()) {
            throw new IndexOutOfBoundsException();
        }
        return list.get(position.toIndex());
    }

    public void replaceCellAt(Position position, Type newCell) {
        if(list.size() <= position.toIndex()) {
            throw new IndexOutOfBoundsException();
        }
        list.set(position.toIndex(), newCell);
    }

    public void fill(Function<Position, Type> cellCreator) {
        for(int i = 0; i < boardSize.height(); i++) {
            for(int j = 0; j < boardSize.width(); j++) {
                list.set(j * boardSize.width() + i, cellCreator.apply(new Position(i,j,boardSize)));
            }
        }
    }

    public void copyTo(Board<Type> otherBoard) {
        if(!otherBoard.boardSize.equals(boardSize)) {
            throw new IllegalArgumentException("Board is not of the same size");
        }
        for(int i = 0; i < boardSize.height(); i++) {
            for(int j = 0; j < boardSize.width(); j++) {
                Position tempPosition = new Position(i,j,boardSize);
                otherBoard.replaceCellAt(tempPosition, getCallAt(tempPosition));
            }
        }
    }

}
