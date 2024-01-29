package model;

import model.pieces.Piece;

// Board class represents the Board in the game
// Contains various pieces inside of it

public class Board {

    private Piece[][] boardTiles;
    private PieceFactory[] pieceFactories;

    public Board() {
        this.boardTiles = new Piece[6][7];
        this.pieceFactories = new PieceFactory[] {
            new PointFactory(),
            new PlusFactory(),
            new HourglassFactory(),
            new TimeFactory(),
            new SunFactory(),
        };
        initializeBoard();
    }

    // Add a piece to a position on the board
    // Worked on by : Azhan
    public void addPiece(int row, int column, Piece piece) {
        if(piece == null){
            boardTiles[row][column] = null;
        }else{
            boardTiles[row][column] = piece;
            piece.setCurrentPosition(new Position(row, column)); 
        }
    }

    // Removes a piece from the board given position
    // Worked on by : Azhan
    public void removePiece(int row, int column) {
        boardTiles[row][column] = null;
    }

    // Clears the whole board of any pieces
    // Worked on by : Azhan
    public void clearBoard(){
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 7; col++){
                boardTiles[row][col] = null;
            }
        }
    }

    // Gets the piece associated with given position
    // Worked on by : Azhan
    public Piece getPiece(int row, int column) {
        return boardTiles[row][column];
    }

    // Gets all the pieces on the board
    // Worked on by : Azhan
    public Piece[][] getBoardTiles() {
        return this.boardTiles;
    }

    // Sets up the board with the correct positioning of pieces when the game first starts
    // Worked on by : Azhan
    public void initializeBoard() {

        // Initialize black pieces
        for (int col = 0; col < 7; col++) {
            addPiece(0, col, createPieceFactory("black", col));
            addPiece(1, col, pieceFactories[0].createPiece("black"));
        }

        // Initialize white pieces
        for (int col = 0; col < 7; col++) {
            addPiece(4, col, pieceFactories[0].createPiece("white"));
            addPiece(5, col, createPieceFactory("white", col));
        }
    }

    // Method to call respective factory methods given column positioning
    // Used only when initializing the game
    // Worked on by : Azhan
    private Piece createPieceFactory(String color, int col) {
        if (col == 0 || col == 6) {
            return pieceFactories[1].createPiece(color); // PlusFactory
        }else if (col == 1 || col == 5) {
            return pieceFactories[2].createPiece(color); // HourglassFactory
        }else if(col == 2 || col == 4){
            return pieceFactories[3].createPiece(color); // TimeFactory
        }else if(col == 3){
            return pieceFactories[4].createPiece(color); // SunFactory
        }
        return null;
    }

}
