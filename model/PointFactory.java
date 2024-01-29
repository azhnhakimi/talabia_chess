package model;

import model.pieces.Piece;
import model.pieces.Point;

// PointFactory class serves as a factory for creating Point objects
// Class created by : Azhan

public class PointFactory implements PieceFactory{

    // Creates and returns a new Point object
    @Override
    public Piece createPiece(String color) {
        return new Point(color);
    }
    
}
