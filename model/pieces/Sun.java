package model.pieces;

import java.util.ArrayList;
import java.util.Arrays;

import model.Position;

public class Sun extends Piece{
    
    String pieceType = "sun";

    public Sun(String colour){
        super(colour);
        super.setPieceType(this.pieceType);
    }

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
            new Position(row + 1, column + 1)   // mvoe right diagonal down
        ));

        return possibleMoves;
        
    }
    
}
