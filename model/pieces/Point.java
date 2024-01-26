package model.pieces;

import java.util.ArrayList;
import model.Position;

public class Point extends Piece {

    String pieceType = "point";
    
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

        // Determine the direction based on the color of the piece
        int direction = (this.getColour().equals("white")) ? -1 : 1;

        // Point can move 1 step forward
        addMoveIfValid(possibleMoves, row + direction, column);

        // Point can move 2 steps forward 
        addMoveIfValid(possibleMoves, row + 2 * direction, column);

        return possibleMoves;
    }

    private void addMoveIfValid(ArrayList<Position> moves, int row, int column) {
        if (isWithinBounds(row, column)) {
            moves.add(new Position(row, column));
        }
    }

    private boolean isWithinBounds(int row, int column) {
        return row >= 0 && row < 6 && column >= 0 && column < 7;
    }
}
