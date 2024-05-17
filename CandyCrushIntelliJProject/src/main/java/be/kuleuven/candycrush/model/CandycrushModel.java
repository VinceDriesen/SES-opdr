package be.kuleuven.candycrush.model;

import be.kuleuven.candycrush.model.Candies.*;
import org.checkerframework.checker.units.qual.A;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CandycrushModel {
    private String speler;
    private int score = 0;
    private BoardSize boardSize;
    private Random random = new Random(0);

    private Board<Candy> board;

    public CandycrushModel(String speler) {
        this.speler = speler;
        boardSize = new BoardSize(10,10);
        this.board = new Board<>(boardSize, this::getRandomCandy);
    }

    public CandycrushModel(String speler, BoardSize boardSize) {
        this.speler = speler;
        this.boardSize = boardSize;
        this.board = new Board<>(boardSize, this::getRandomCandy);
    }

    private Candy getRandomCandy(Position position) {
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

    public Iterable<Position> getSameNeighbourPositions(Position position) {
        ArrayList<Position> neighbours = new ArrayList<>();
        for(Position p : position.neighborPositions()) {
            if(getCandyFromPosition(p).equals(getCandyFromPosition(position))) {
                neighbours.add(p);
            }
        }
        return neighbours;
    }

    public void changeNeighboursFromPosition(Position position) {
        ArrayList<Position> neighbours = (ArrayList<Position>) getSameNeighbourPositions(position);
        if(neighbours.size() >= 3) {
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
        Random random = new Random(0);
        this.board = new Board<>(boardSize, this::getRandomCandy);
    }

    public BoardSize getBoardSize() {
        return board.getBoardSize();
    }

    public Set<List<Position>> findAllMatches() {
        var streamHorizontal = horizontalStartingPosition()
                .map(this::longestMatchToRight)
                .filter(list -> list.size() >= 3);
        var streamVertical = verticalStartingPosition()
                .map(this::longestMatchToDown)
                .filter(list -> list.size() >= 3);

        return Stream.concat(streamHorizontal, streamVertical).collect(Collectors.toSet());
    }

    public boolean firstTwoHaveSameCandy(Candy candy, Stream<Position> positions) {
        return positions.limit(2)
                .allMatch(position -> candy.equals(getCandyFromPosition(position)));
    }

    public Stream<Position> horizontalStartingPosition() {
        return IntStream.range(0, getBoardSize().width() * getBoardSize().height())
                .mapToObj(i -> new Position(i % getBoardSize().width(), i / getBoardSize().width(), this.getBoardSize()))
                .filter(pos -> pos.x() >= 0 && firstTwoHaveSameCandy(getCandyFromPosition(pos), pos.walkRight()));
    }

    public Stream<Position> verticalStartingPosition() {
        return IntStream.range(0, getBoardSize().width() * getBoardSize().height())
                .mapToObj(i -> new Position(i % getBoardSize().width(), i / getBoardSize().width(), this.getBoardSize()))
                .filter(pos -> pos.y() >= 0 && firstTwoHaveSameCandy(getCandyFromPosition(pos), pos.walkDown()));
    }

    private List<Position> longestMatchToRight(Position position) {
        return position.walkRight()
                .takeWhile(position1 -> getCandyFromPosition(position1).equals(getCandyFromPosition(position)))
                .collect(Collectors.toList());
    }

    private List<Position> longestMatchToDown(Position position) {
        return position.walkDown()
                .takeWhile(position1 -> getCandyFromPosition(position1).equals(getCandyFromPosition(position)))
                .collect(Collectors.toList());
    }


    public void clearMatch(List<Position> match) {
        if(!match.isEmpty()) {
            board.replaceCellAt(match.getFirst(), new EmptyCandy());
            clearMatch(match.subList(1, match.size()));
        }
    }

    public void fallDownTo(Position position){
        if(position.y() > 0) {
            Position above = new Position(position.x(), position.y() - 1, this.getBoardSize());
            if(getCandyFromPosition(above) instanceof EmptyCandy){
                fallDownTo(above);
            }
            else {
                if(getCandyFromPosition(position) instanceof EmptyCandy) {
                    moveToLowest(above);
                }
                fallDownTo(above);
            }
        }
    }

    public void moveToLowest(Position position) {
        for(int i = 1; i < getBoardSize().height() - position.y(); i++){
            Position current = new Position(position.x(), position.y() + i - 1, this.getBoardSize());
            Position under = new Position(position.x(), position.y() + i, this.getBoardSize());
            if(!(getCandyFromPosition(under) instanceof EmptyCandy)){
                board.replaceCellAt(current, board.getCellAt(position));
                board.replaceCellAt(position, new EmptyCandy());
                return;
            }
            else if(under.y() == board.getBoardSize().height() - 1) {
                board.replaceCellAt(new Position(current.x(), current.y() + 1, this.getBoardSize()), board.getCellAt(position));
                board.replaceCellAt(position, new EmptyCandy());
                return;
            }

        }
    }

    public boolean updateBoard(){
        return updateBoard(true);
    }

    public boolean updateBoard(boolean isFirstCall){
        var matches = findAllMatches();
        if(matches.isEmpty() && isFirstCall){
            return false;
        } else if (matches.isEmpty()){
            return true;
        } else {
            matches.forEach(this::clearMatch);
            matches.forEach(list -> list.forEach(this::fallDownTo));
            return updateBoard(false);
        }
    }

    public void changeCandy(Position position1, Position position2) {
        if(this.getCandyFromPosition(position1) instanceof EmptyCandy || this.getCandyFromPosition(position2) instanceof EmptyCandy) {
            throw new IllegalArgumentException("Dit is geen geldige candy");
        }
        for (Position neighbor : position1.neighborPositions()) {
            if (neighbor.equals(position2)) {
                swapPositions(position1, position2, this.board);
                if(!updateBoard()) {
                    swapPositions(position2, position1, this.board);
                }
                return;
            }
        }
    }

    public static CandycrushModel createBoardFromString(String configuration) {
        var lines = configuration.toLowerCase().lines().toList();
        BoardSize size = new BoardSize(lines.size(), lines.getFirst().length());
        var model = new CandycrushModel("Recursie", size); // deze moet je zelf voorzien
        for (int col = 0; col < lines.size(); col++) {
            var line = lines.get(col);
            for (int row = 0; row < line.length(); row++) {
                model.board.replaceCellAt(new Position(row, col, size), characterToCandy(line.charAt(row)));
            }
        }
        return model;
    }

    private static Candy characterToCandy(char c) {
        return switch(c) {
            case '.' -> null;
            case 'o' -> new NormalCandy(0);
            case '*' -> new NormalCandy(1);
            case '#' -> new NormalCandy(2);
            case '@' -> new NormalCandy(3);
            default -> throw new IllegalArgumentException("Unexpected value: " + c);
        };
    }

    public  PositionScorePair solve() {
        PositionScorePair currentSwaps = new PositionScorePair(new ArrayList<>(), 0);
        PositionScorePair highestSwaps = new PositionScorePair(new ArrayList<>(), 0);
        return findMaximzeScore(currentSwaps, highestSwaps);
    }

    private  PositionScorePair findMaximzeScore(PositionScorePair current, PositionScorePair heighest) {
        if(this.findAllSwaps().isEmpty()) {
            return this.getBestResult(current, heighest);
        }
        for(PositionPairs pair : this.findAllSwaps()) {
            Board<Candy> boardCopy = new Board<>(this.board.getBoardSize(), this::getRandomCandy);
            this.board.copyTo(boardCopy);

            PositionScorePair copyCurrent = new PositionScorePair(new ArrayList<>(), 0);
            copyCurrent.changeScore(current.score());
            copyCurrent.pairs().addAll(current.pairs());

            this.board = swapPositions(pair.position1(), pair.position2(), this.board);
            current.pairs().add(pair);
            updateBoard();

            current = current.changeScore(this.getScore(this.board));
            heighest = findMaximzeScore(current, heighest);

            board = boardCopy;
            current = copyCurrent;
        }
        return heighest;
    }

    public PositionScorePair getBestResult(PositionScorePair current, PositionScorePair heighest) {
        if(current.score() > heighest.score()) {
            return current;
        }
        else if(current.score() == heighest.score()) {
            if(current.pairs().size() < heighest.pairs().size()) {
                return current;
            }
            else {
                return heighest;
            }
        }
        else {
            return heighest;
        }
    }

    public Integer getScore(Board<Candy> board) {
        Integer score = this.board.getList().values().stream()
                .filter(a -> a instanceof EmptyCandy)
                .toList().size();
        return score;
    }

    public Set<PositionPairs> findAllSwaps() {
        Set<PositionPairs> positionPairs= new HashSet<>();
        var swaps = new int[][] {
                {0, -1}, {1,0}
        };
        for(Map.Entry<Position, Candy> poscan : board.getList().entrySet()) {
            for(var swap : swaps) {
                if(poscan.getKey().x() + swap[0] > boardSize.width() - 1 || poscan.getKey().x() + swap[0] < 0) {continue;}
                if(poscan.getKey().y() + swap[1] > boardSize.height() - 1|| poscan.getKey().y() + swap[1] < 0) {continue;}
                Position newPosition = new Position(poscan.getKey().x() + swap[0], poscan.getKey().y() + swap[1], this.getBoardSize());
                if (isValidSwap(poscan.getKey(), newPosition)) {
                    positionPairs.add(new PositionPairs(poscan.getKey(), newPosition));
                }
            }
        }
        return positionPairs;
    }

    private boolean isValidSwap(Position beginPosition, Position endPosition) {
        if(beginPosition.equals(endPosition)) {return false;}
        if(board.getCellAt(beginPosition) instanceof EmptyCandy || board.getCellAt(endPosition) instanceof EmptyCandy) {return false;}
        Board<Candy> boardCopy = new Board<>(this.board.getBoardSize(), this::getRandomCandy);
        this.board.copyTo(boardCopy);
        this.board = swapPositions(beginPosition, endPosition, this.board);
        if(!findAllMatches().isEmpty()) {
            this.board = boardCopy;
            return true;
        }
        this.board = boardCopy;
        return false;
    }


    private Board<Candy> swapPositions(Position beginPosition, Position endPosition, Board<Candy> board) {
        Candy tempCandy = board.getCellAt(endPosition);
        board.replaceCellAt(endPosition, board.getCellAt(beginPosition));
        board.replaceCellAt(beginPosition, tempCandy);
        return board;
    }

}
