package model;

import model.pieces.Piece;
import model.pieces.Sun;

// SunFactory class serves as a factory for creating Sun objects
// Class created by : Azhan

public class SunFactory implements PieceFactory{

    // Creates and returns a new Sun object
    @Override
    public Piece createPiece(String color) {
        return new Sun(color);
    }
    
}
