package model.pieces;

import java.util.ArrayList;

import model.Position;

public abstract class  Piece {
    
    private String colour;
    private String pieceType;
    private Position currentPosition = null;


    public Piece(String colour){
        this.colour = colour;
    }

    public void setCurrentPosition(Position position){
        this.currentPosition = position;
    }

    public Position getCurrentPosition(){
        return this.currentPosition;
    }

    public String getColour(){
        return this.colour;
    }

    public abstract ArrayList<Position> getPossibleMoves();

    public String getPieceType(){
        return this.pieceType;
    }

    public void setPieceType(String pieceType){
        this.pieceType = pieceType;
    }
    
}
