package be.kuleuven.candycrush.model;

import be.kuleuven.candycrush.model.Candies.*;
import java.util.Random;

public class MultithreadingClient extends Thread{
    private Board<Candy> board = new Board<>(new BoardSize(10,10),this::getRandomCandy);

    public static void main(String[] args) {
        MultithreadingClient thread1 = new MultithreadingClient();
        MultithreadingClient thread2 = new MultithreadingClient();
        thread1.start();
        thread2.start();
    }

    public void run() {
        Random r = new Random();
        while(true) {
            Position position = new Position(r.nextInt(board.getBoardSize().width()), r.nextInt(board.getBoardSize().height()), board.getBoardSize());
            board.replaceCellAt(position, getRandomCandy(null));
            System.out.println("x: " + position.x() + "y: " + position.y() + " Thread: " + Thread.currentThread());
            try {
                Thread.sleep(r.nextInt(1000,5000));
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
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
}
