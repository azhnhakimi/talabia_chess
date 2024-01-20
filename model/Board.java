package model;
import model.pieces.Hourglass;
import model.pieces.Piece;
import model.pieces.Plus;
import model.pieces.Point;
import model.pieces.Sun;
import model.pieces.Time;

public class Board {
    
    private Piece[][] boardTiles;

    public Board(){
        this.boardTiles = new Piece[6][7];
        initializeBoard();
    }

    public void addPiece(int row, int column, Piece piece){
        boardTiles[row][column] = piece;
    }

    public void removePiece(int tilePosition){
        
    }

    public Piece getPiece(int row, int column){
        return boardTiles[row][column];
    }

    public Piece[][] getBoardTiles(){
        return this.boardTiles;
    }

    public void flipBoard(int playerTurn){
        
    }

    public void initializeBoard(){
        System.out.println("Begin the game!");
        /*
         * the approach with this, is a function that sets up a new board and adds the pieces in their initial position
         */

         // initialize black pieces
        boardTiles[0][0] = new Plus("black");
        boardTiles[0][1] = new Hourglass("black");
        boardTiles[0][2] = new Time("black");
        boardTiles[0][3] = new Sun("black");
        boardTiles[0][4] = new Time("black");
        boardTiles[0][5] = new Hourglass("black");
        boardTiles[0][6] = new Plus("black");

        boardTiles[1][0] = new Point("black");
        boardTiles[1][1] = new Point("black");
        boardTiles[1][2] = new Point("black");
        boardTiles[1][3] = new Point("black");
        boardTiles[1][4] = new Point("black");
        boardTiles[1][5] = new Point("black");
        boardTiles[1][6] = new Point("black");


        // initialize white pieces
        boardTiles[4][0] = new Point("white");
        boardTiles[4][1] = new Point("white");
        boardTiles[4][2] = new Point("white");
        boardTiles[4][3] = new Point("white");
        boardTiles[4][4] = new Point("white");
        boardTiles[4][5] = new Point("white");
        boardTiles[4][6] = new Point("white");

        boardTiles[5][0] = new Plus("white");
        boardTiles[5][1] = new Hourglass("white");
        boardTiles[5][2] = new Time("white");
        boardTiles[5][3] = new Sun("white");
        boardTiles[5][4] = new Time("white");
        boardTiles[5][5] = new Hourglass("white");
        boardTiles[5][6] = new Plus("white");

    }

    public void printBoard(){
        
    }
    
}
