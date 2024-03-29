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

    // Check if the Point piece is rotated or not
    // Worked on by : Syed
    public boolean isRotated() {
        return rotated;
    }

    // Allow to change the rotation status of the Point Piece
    // Worked on by : Syed
    public void setRotated(boolean rotated) {
        this.rotated = rotated;
    }

    // Gets the possible moves that the Point piece can move
    // Worked on by : Balqis, Syed
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
