package model;

import model.pieces.Piece;
import model.pieces.Plus;

// PlusFactory class serves as a factory for creating Plus objects
// Class created by : Azhan

public class PlusFactory implements PieceFactory {

    // Creates and returns a new Plus object
    public Piece createPiece(String color) {
        return new Plus(color);
    }
    
}
