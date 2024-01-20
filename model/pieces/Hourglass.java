package model.pieces;

import java.util.ArrayList;
import java.util.Arrays;

import model.Position;

public class Hourglass extends Piece{

    String pieceType = "hourglass";
    
    public Hourglass(String color){
        super(color);
        super.setPieceType(this.pieceType);
    }

        public ArrayList<Position> getPossibleMoves(){
        Position position = this.getCurrentPosition();

        int row = position.getRow();
        int column = position.getColumn();

        ArrayList<Position> possibleMoves = new ArrayList<>(Arrays.asList(
            new Position(row - 2, column + 1),
            new Position(row - 2, column - 1),
            new Position(row - 1, column - 2),
            new Position(row + 1, column - 2),
            new Position(row - 1, column + 2),
            new Position(row + 1, column + 2),
            new Position(row + 2, column - 1),
            new Position(row + 2, column + 1)
        ));

        return possibleMoves;
        
    }
    
}
