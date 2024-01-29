package model.pieces;

import java.util.ArrayList;
import java.util.Arrays;

import model.Position;

// Sun class represents the Sun piece in the game
// Class created by : Azhan

public class Sun extends Piece{
    
    String pieceType = "sun";

    public Sun(String colour){
        super(colour);
        super.setPieceType(this.pieceType);
    }

    // Gets the possible moves that the Sun piece can move
    public ArrayList<Position> getPossibleMoves(){
        Position position = this.getCurrentPosition();

        int row = position.getRow();
        int column = position.getColumn();

        ArrayList<Position> possibleMoves = new ArrayList<>(Arrays.asList(
            new Position(row - 1, column),  // move up 1
            new Position(row + 1, column),  // move down 1
            new Position(row, column - 1),  // move left 1
            new Position(row, column + 1),  // move right 1
            new Position(row - 1, column - 1),  // move left diagonal up
            new Position(row - 1, column + 1),  // move right diagonal up
            new Position(row + 1, column - 1),  // move left diagonal down
            new Position(row + 1, column + 1)   // move right diagonal down
        ));
        return possibleMoves;
    }
    
}
