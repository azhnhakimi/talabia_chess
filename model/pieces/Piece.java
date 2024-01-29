package model.pieces;

import java.util.ArrayList;
import model.Position;

// Piece class is the abstract blueprint class for creating other more specific pieces 
// Holds common methods and variables for all various pieces
// Class created by : Azhan

public abstract class Piece {
    
    private String colour;
    private String pieceType;
    private Position currentPosition = null;

    public Piece(String colour){
        this.colour = colour;
    }

    // Sets the current position of respective piece
    public void setCurrentPosition(Position position){
        this.currentPosition = position;
    }

    // Gets the current position of respective piece
    public Position getCurrentPosition(){
        return this.currentPosition;
    }

    // Gets the respective piece color
    public String getColour(){
        return this.colour;
    }

    // Gets the respective piece type, e.g. hourglass, sun, point,...
    public String getPieceType(){
        return this.pieceType;
    }
    
    // Sets the respective piece type of a piece
    public void setPieceType(String pieceType){
        this.pieceType = pieceType;
    }

    // Abstract method to get possible moves of respective pieces 
    public abstract ArrayList<Position> getPossibleMoves();
}
