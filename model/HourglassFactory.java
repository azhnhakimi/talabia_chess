package model;

import model.pieces.Hourglass;
import model.pieces.Piece;

// HourglassFactory class serves as a factory for creating Hourglass objects
// Class created by : Azhan

public class HourglassFactory implements PieceFactory {

    // Creates and returns a new Hourglass object
    @Override
    public Piece createPiece(String color) {
        return new Hourglass(color);
    }
    
}
