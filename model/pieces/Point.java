package model.pieces;

import java.util.ArrayList;
import java.util.Arrays;

import model.Position;

public class Point extends Piece {

    String pieceType = "point";
    
    public Point(String colour){
        super(colour);
        super.setPieceType(this.pieceType);
    }

    public ArrayList<Position> getPossibleMoves(){
        Position position = this.getCurrentPosition();

        int row = position.getRow();
        int column = position.getColumn();

        ArrayList<Position> possibleMoves = new ArrayList<>(Arrays.asList(
            new Position(row - 1, column),
            new Position(row - 2, column)
        ));

        return possibleMoves;
        
    }
}
