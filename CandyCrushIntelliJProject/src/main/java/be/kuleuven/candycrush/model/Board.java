package be.kuleuven.candycrush.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import com.google.common.collect.Multimap;
import com.google.common.collect.ArrayListMultimap;

public class Board <Type> {
    private volatile BoardSize boardSize;
    private volatile Map<Position, Type> list = new ConcurrentHashMap<>();
    private volatile Multimap<Type, Position> omgekeerdeList = ArrayListMultimap.create();

    public Board(BoardSize boardSize, Function<Position, Type> cellCreator) {
        this.boardSize = boardSize;
        this.fill(cellCreator);
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }

    public Map<Position, Type> getList() {
        return list;
    }

    public Multimap<Type, Position> getOmgekeerdeList() {
        return omgekeerdeList;
    }

    public synchronized Type getCellAt(Position position) {
        if(list.size() <= position.toIndex()) {
            throw new IndexOutOfBoundsException();
        }
        return list.get(position);
    }

    public synchronized void replaceCellAt(Position position, Type newCell) {
        if(list.size() <= position.toIndex()) {
            throw new IndexOutOfBoundsException();
        }
        Type tempType = getCellAt(position);
        omgekeerdeList.remove(tempType, position);
        omgekeerdeList.put(newCell, position);
        list.replace(position, newCell);
    }

    public synchronized void fill(Function<Position, Type> cellCreator) {
        for(int i = 0; i < boardSize.height(); i++) {
            for(int j = 0; j < boardSize.width(); j++) {
                Position position = new Position(i,j,this.getBoardSize());
                list.put(position, cellCreator.apply(position));
                omgekeerdeList.put(cellCreator.apply(position), position);
            }
        }
    }

    public synchronized void copyTo(Board<Type> otherBoard) {
        if(!otherBoard.boardSize.equals(boardSize)) {
            throw new IllegalArgumentException("Board is not of the same size");
        }
        for(int i = 0; i < boardSize.height(); i++) {
            for(int j = 0; j < boardSize.width(); j++) {
                Position tempPosition = new Position(i,j,boardSize);
                otherBoard.replaceCellAt(tempPosition, getCellAt(tempPosition));
            }
        }
    }

    public List<Position> getPositionsOfElement(Type cell) {
        return Collections.unmodifiableList((List<Position>) omgekeerdeList.get(cell));
    }

}
