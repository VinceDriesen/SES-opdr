package be.kuleuven.candycrush.model;

import org.junit.jupiter.api.Test;
import be.kuleuven.candycrush.model.Candies.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BoardTests {

    @Test
    public void gegevenPosition_getCellAt_geeftDeCellTerug() {
        BoardSize boardSize = new BoardSize(10,10);
        Function<Position, Candy> cellCreator = position -> new NormalCandy(0);
        Board<Candy> board = new Board<>(boardSize, cellCreator);

        Candy uitkomst = board.getCallAt(new Position(2,2,boardSize));
        assertThat(uitkomst).isEqualTo(new NormalCandy(0));
    }

    @Test
    public void gegevenPositionEnNewCell_replaceCellAt_veranderdHuidigeCellNaarNieuweCell() {
        BoardSize boardSize = new BoardSize(10,10);
        Function<Position, Candy> cellCreator = position -> new NormalCandy(0);
        Board<Candy> board = new Board<>(boardSize, cellCreator);

        Position position = new Position(2, 2, boardSize);
        board.replaceCellAt(position, new UltimateCandy());
        assertThat(board.getCallAt(position)).isEqualTo(new UltimateCandy());
    }

    @Test
    public void gegevenCellCreator_fillBoard_vultAlleCellenMetGegevenCellCreator() {
        BoardSize boardSize = new BoardSize(10,10);
        Function<Position, Candy> cellCreator = position -> new NormalCandy(0);
        Board<Candy> board = new Board<>(boardSize, cellCreator);

        for(int i = 0; i < boardSize.height(); i++) {
            for(int j = 0; j < boardSize.width(); j++) {
                Position checkPosition = new Position(i, j, boardSize);
                assertThat(board.getCallAt(checkPosition)).isEqualTo(new NormalCandy(0));
            }
        }
    }

    @Test
    public void gegevenOtherBoard_CopyTo_kopieerdHuidigeNaarAnderBoard() {
        BoardSize boardSize = new BoardSize(10,10);
        Function<Position, Candy> cellCreator = position -> new NormalCandy(0);
        Board<Candy> board = new Board<>(boardSize, cellCreator);

        BoardSize otherBoardSize = new BoardSize(10,10);
        Function<Position, Candy> otherCellCreator = position -> new UltimateCandy();
        Board<Candy> otherBoard = new Board<>(boardSize, cellCreator);

        board.copyTo(otherBoard);

        for(int i = 0; i < otherBoardSize.height(); i++) {
            for(int j = 0; j < otherBoardSize.width(); j++) {
                Position checkPosition = new Position(i, j, otherBoardSize);
                assertThat(otherBoard.getCallAt(checkPosition)).isEqualTo(new NormalCandy(0));
            }
        }
    }


}
