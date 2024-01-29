package model.pieces;

import java.util.ArrayList;
import model.Position;

// Point class represents the Point piece in the game

public class Point extends Piece {

    String pieceType = "point";
    boolean reversedWhite = false;
    boolean reversedBlack = false;
    boolean rotated = false; 
    
    public Point(String colour) {
        super(colour);
        super.setPieceType(this.pieceType);
    }

    // Explanation
    // Worked on by : 
    public boolean isRotated() {
        return rotated;
    }

    // Explanation
    // Worked on by : 
    public void setRotated(boolean rotated) {
        this.rotated = rotated;
    }

    // Gets the possible moves that the Point piece can move
    // Worked on by : 
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
                    this.rotated = !this.rotated; 
                }
            } else {
                direction = -1;
                possibleMoves.add(new Position(row + direction, column));
                possibleMoves.add(new Position(row + 2 * direction, column));
                if (row == 0) {
                    reversedWhite = true;
                    this.rotated = !this.rotated; 
                }
            } 
        } else {
            if (reversedBlack) {
                direction = -1;
                possibleMoves.add(new Position(row + direction, column));
                possibleMoves.add(new Position(row + 2 * direction, column));
                if (row == 0) {
                    reversedBlack = false;
                    this.rotated = !this.rotated; 
                }
            } else {
                direction = 1;
                possibleMoves.add(new Position(row + direction, column));
                possibleMoves.add(new Position(row + 2 * direction, column));
                if (row == 5) {
                    reversedBlack = true;
                    this.rotated = !this.rotated; 
                }
            } 
        }
        return possibleMoves;
    }
}
