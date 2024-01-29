package model.pieces;

import java.util.ArrayList;
import java.util.Arrays;

import model.Position;

// Plus class represents the Plus piece in the game
// Class created by : Azhan

public class Plus extends Piece {
    
    String pieceType = "plus";

    public Plus(String colour){
        super(colour);
        super.setPieceType(this.pieceType);
    }

    // Gets the possible moves that the Plus piece can move
    @Override
    public ArrayList<Position> getPossibleMoves() {

        Position position = this.getCurrentPosition();

        int row = position.getRow();
        int column = position.getColumn();

        ArrayList<Position> possibleMoves = new ArrayList<>(Arrays.asList(
            new Position(row - 1, column),  // move 1 up
            new Position(row - 2, column),  // move 2 up
            new Position(row - 3, column),  // move 3 up
            new Position(row - 4, column),  // move 4 up
            new Position(row - 5, column),  // move 5 up
            new Position(row + 1, column),  // move 1 down
            new Position(row + 2, column),  // move 2 down
            new Position(row + 3, column),  // move 3 down
            new Position(row + 4, column),  // move 4 down
            new Position(row + 5, column),  // move 5 down
            new Position(row , column - 1), // move 1 left
            new Position(row , column - 2), // move 2 left
            new Position(row , column - 3), // move 3 left
            new Position(row , column - 4), // move 4 left
            new Position(row , column - 5), // move 5 left
            new Position(row , column - 6), // move 6 left
            new Position(row , column + 1), // move 1 right
            new Position(row , column + 2), // move 2 right
            new Position(row , column + 3), // move 3 right
            new Position(row , column + 4), // move 4 right
            new Position(row , column + 5), // move 5 right
            new Position(row , column + 6)  // move 6 right
        ));
        return possibleMoves;
    }
    
}
