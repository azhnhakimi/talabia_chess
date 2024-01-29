package model;

import model.pieces.Piece;

// PieceFactory interface defines a contract for class that are responsible for creating objects
// Class created by : Azhan

public interface PieceFactory {

    // Creates and returns a new object
    Piece createPiece(String color);
    
}
