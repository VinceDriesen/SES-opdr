package be.kuleuven.candycrush.model;

import java.util.ArrayList;
import java.util.Collection;

public record BoardSize(int width, int height) {
    public BoardSize {
        if (width < 0) throw new IllegalArgumentException("Width moet groter zijn dan 0");
        if (height < 0) throw new IllegalArgumentException("Height moet groter zijn dan 0");
    }

    public Collection<Position> positions() {
        ArrayList<Position> positions = new ArrayList<>();
        for(int i = 0; i < width*height; i++) {
            positions.add(Position.fromIndex(i,this));
        }
        return positions;
    }
}
