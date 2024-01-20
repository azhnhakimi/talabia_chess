package model.pieces;

import java.util.ArrayList;
import java.util.Arrays;

import model.Position;

public class Time extends Piece {
    
    String pieceType = "time";
    
    public Time(String colour){
        super(colour);
        super.setPieceType(this.pieceType);
    }

    @Override
    public ArrayList<Position> getPossibleMoves() {
        Position position = this.getCurrentPosition();

        int row = position.getRow();
        int column = position.getColumn();

        ArrayList<Position> possibleMoves = new ArrayList<>(Arrays.asList(
            new Position(row - 1, column - 1),
            new Position(row - 2, column - 2),
            new Position(row - 3, column - 3),
            new Position(row - 4, column - 4),
            new Position(row - 5, column - 5),
            new Position(row + 1, column + 1),
            new Position(row + 2, column + 2),
            new Position(row + 3, column + 3),
            new Position(row + 4, column + 4),
            new Position(row + 5, column + 5),
            new Position(row + 1, column - 1),
            new Position(row + 2, column - 2),
            new Position(row + 3, column - 3),
            new Position(row + 4, column - 4),
            new Position(row + 5, column - 5),
            new Position(row - 1, column + 1),
            new Position(row - 2, column + 2),
            new Position(row - 3, column + 3),
            new Position(row - 4, column + 4),
            new Position(row - 5, column + 5)
        ));

        return possibleMoves;
    }
    
}
