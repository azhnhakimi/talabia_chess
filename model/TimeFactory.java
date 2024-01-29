package model;

import model.pieces.Piece;
import model.pieces.Time;

// TimeFactory class serves as a factory for creating Time objects
// Class created by : Azhan

public class TimeFactory implements PieceFactory {

    // Creates and returns a new Time object
    @Override
    public Piece createPiece(String color) {
        return new Time(color);
    }
    
}
