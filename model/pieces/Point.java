package model.pieces;

import java.util.ArrayList;
import model.Position;

public class Point extends Piece {

    String pieceType = "point";
    boolean reversedWhite = false;
    boolean reversedBlack = false;
    
    public Point(String colour) {
        super(colour);
        super.setPieceType(this.pieceType);
    }

    @Override
    public ArrayList<Position> getPossibleMoves() {
        Position position = this.getCurrentPosition();
        if (position == null) {
            return new ArrayList<>(); // Handle null position
        }
    
        int row = position.getRow();
        int column = position.getColumn();
        ArrayList<Position> possibleMoves = new ArrayList<>();
        int direction;
       
       if (this.getColour().equals("white")){
        if (reversedWhite == true){
            direction = 1;
            possibleMoves.add(new Position(row + direction, column));
            possibleMoves.add(new Position(row + 2 * direction, column));
            if (row == 5){
                reversedWhite = false;
            }
        }else {
            direction = -1;
            possibleMoves.add(new Position(row + direction, column));
            possibleMoves.add(new Position(row + 2 * direction, column));
            if (row==0){
                reversedWhite = true;
            }
        } 
        }

        if (!this.getColour().equals("white")){
            if (reversedBlack == true){
                direction = -1;
                possibleMoves.add(new Position(row + direction, column));
                possibleMoves.add(new Position(row + 2 * direction, column));
                if (row == 0){
                    reversedBlack = false;
                }
            }else {
                direction = 1;
                possibleMoves.add(new Position(row + direction, column));
                possibleMoves.add(new Position(row + 2 * direction, column));
                if (row==5){
                    reversedBlack = true;
                }
            } 
        }
        return possibleMoves;
    }
}
