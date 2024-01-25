package model.pieces;

import java.util.ArrayList;

import model.Position;

public class Point extends Piece {

    String pieceType = "point";
    
    public Point(String colour){
        super(colour);
        super.setPieceType(this.pieceType);
    }

    public ArrayList<Position> getPossibleMoves() {
        Position position = this.getCurrentPosition();
        if (position == null) {
            return new ArrayList<>(); // Handle null position
        }

        int row = position.getRow();
        int column = position.getColumn();
        ArrayList<Position> possibleMoves = new ArrayList<>();

        // Check if the piece is at the top or bottom row to reverse direction
        int direction = (this.getColour().equals("white")) ? -1 : 1;

        // Point can move 1 step forward
        if (isWithinBounds(row + direction, column)) {
            possibleMoves.add(new Position(row + direction, column));
        }

        // Point can move 2 steps forward if it's in its starting position
        if ((this.getColour().equals("white") && row == 4) || (this.getColour().equals("black") && row == 1)) {
            if (isWithinBounds(row + 2 * direction, column)) {
                possibleMoves.add(new Position(row + 2 * direction, column));
            }
        }

        return possibleMoves;
    }

    private boolean isWithinBounds(int row, int column) {
        return row >= 0 && row < 6 && column >= 0 && column < 7;
    }
}
