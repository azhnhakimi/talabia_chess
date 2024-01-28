package model;

import model.pieces.Hourglass;
import model.pieces.Piece;
import model.pieces.Plus;
import model.pieces.Point;
import model.pieces.Sun;
import model.pieces.Time;

public class Board {

    private Piece[][] boardTiles;

    public Board() {
        this.boardTiles = new Piece[6][7];
        initializeBoard();
    }

    public void addPiece(int row, int column, Piece piece) {
        if(piece == null){
            boardTiles[row][column] = null;
        }else{
            boardTiles[row][column] = piece;
            piece.setCurrentPosition(new Position(row, column)); // Set the piece's current position
        }
    }

    public void removePiece(int row, int column) {
        boardTiles[row][column] = null;
    }

    public Piece getPiece(int row, int column) {
        return boardTiles[row][column];
    }

    public Piece[][] getBoardTiles() {
        return this.boardTiles;
    }

    public void flipBoard(int playerTurn) {
    
    }

    public void initializeBoard() {
        System.out.println("Begin the game!");

        // Initialize black pieces
        for (int col = 0; col < 7; col++) {
            addPiece(0, col, getNewPiece("black", col));
            addPiece(1, col, new Point("black"));
        }

        // Initialize white pieces
        for (int col = 0; col < 7; col++) {
            addPiece(4, col, new Point("white"));
            addPiece(5, col, getNewPiece("white", col));
        }
    }

    private Piece getNewPiece(String color, int col) {
        switch (col) {
            case 0:
            case 6:
                return new Plus(color);
            case 1:
            case 5:
                return new Hourglass(color);
            case 2:
            case 4:
                return new Time(color);
            case 3:
                return new Sun(color);
            default:
                return null; // Should never happen
        }
    }

    public void clearBoard(){
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 7; col++){
                boardTiles[row][col] = null;
            }
        }
    }

}
