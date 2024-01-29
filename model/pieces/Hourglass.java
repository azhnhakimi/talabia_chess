package model.pieces;

import java.util.ArrayList;
import java.util.Arrays;

import model.Position;

// Hourglass class represents the Hourglass piece in the game
// Class created by : Azhan

public class Hourglass extends Piece{

    private String pieceType = "hourglass";
    
    public Hourglass(String color){
        super(color);
        super.setPieceType(this.pieceType);
    }

    // Gets the possible moves that the Hourglass piece can move
    @Override
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
