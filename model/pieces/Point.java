package model.pieces;

import java.util.ArrayList;
import model.Position;

public class Point extends Piece {

    String pieceType = "point";
    boolean reversedWhite = false;
    boolean reversedBlack = false;
    boolean rotated = false; // Field to track rotation state
    
    public Point(String colour) {
        super(colour);
        super.setPieceType(this.pieceType);
    }

    public boolean isRotated() {
        return rotated;
    }

    public void setRotated(boolean rotated) {
        this.rotated = rotated;
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
       
        if (this.getColour().equals("white")) {
            if (reversedWhite) {
                direction = 1;
                possibleMoves.add(new Position(row + direction, column));
                possibleMoves.add(new Position(row + 2 * direction, column));
                if (row == 5) {
                    reversedWhite = false;
                    this.rotated = !this.rotated; // Toggle the rotation state
                }
            } else {
                direction = -1;
                possibleMoves.add(new Position(row + direction, column));
                possibleMoves.add(new Position(row + 2 * direction, column));
                if (row == 0) {
                    reversedWhite = true;
                    this.rotated = !this.rotated; // Toggle the rotation state
                }
            } 
        } else {
            if (reversedBlack) {
                direction = -1;
                possibleMoves.add(new Position(row + direction, column));
                possibleMoves.add(new Position(row + 2 * direction, column));
                if (row == 0) {
                    reversedBlack = false;
                    this.rotated = !this.rotated; // Toggle the rotation state
                }
            } else {
                direction = 1;
                possibleMoves.add(new Position(row + direction, column));
                possibleMoves.add(new Position(row + 2 * direction, column));
                if (row == 5) {
                    reversedBlack = true;
                    this.rotated = !this.rotated; // Toggle the rotation state
                }
            } 
        }
        return possibleMoves;
    }
}
