package view;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.Board;
import model.Position;
import model.pieces.Piece;

import java.awt.*;
import java.util.ArrayList;

public class BoardModel extends JPanel {

    Board board;

    int rows;
    int columns;
    boolean flipped = false;
    boolean hasMovedPiece = false;
    Tile[][] tiles;

    Position currentPosition;
    Tile clickedTile;

    public BoardModel(int rows, int columns, Board board) {
        this.rows = rows;
        this.columns = columns;
        this.setLayout(new GridLayout(this.rows, this.columns, 0, 0));
        this.setBackground(new Color(0xEDD6B3));
        this.setOpaque(true);
        this.board = board;

        tiles = new Tile[rows][columns];

        // Initialize the Tile objects
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                tiles[i][j] = new Tile(null, null, new Position(i, j), this);
                this.add(tiles[i][j]);
            }
        }
        
    }

    public void seePossibleMoves(ArrayList<Position> possiblePositions, Tile tileClickedOn){
        this.currentPosition = tileClickedOn.getPosition();
        this.clickedTile = tileClickedOn;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Tile tile = tiles[i][j];
                for(Position possiblePosition : possiblePositions){
                    if(tile.position.getRow() == possiblePosition.getRow() && tile.position.getColumn() == possiblePosition.getColumn()){
                        // to do : make sure cannot click opponent pieces
                        hasMovedPiece = true;
                        tile.setBackground(new Color(0x00ff00));
                        break;
                    }else{
                        tile.setBackground(new Color(0xEDD6B3));
                    }
                }
            }
        }
    }


    // draws the current position of the pieces (add them) onto the board
    public void draw() {

        Piece[][] pieces = board.getBoardTiles();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                
                Piece piece = pieces[i][j];
                Tile tile = tiles[i][j];

                if (piece != null) {
                    String pieceType = piece.getPieceType();
                    String casedPieceType = Character.toUpperCase(pieceType.charAt(0)) + pieceType.substring(1);
                    String filePath = "./assets/" + piece.getColour() + casedPieceType + ".png";
                    ImageIcon image = new ImageIcon(filePath);

                    piece.setCurrentPosition(new Position(i, j));
    
                    tile.setPiece(piece);
                    tile.setImage(image);   
                }   
                
            }
        }
    }

    public void flipBoard(int playerTurn){
        if(playerTurn != 1){
            flipped = !flipped;
            repaint();
        }
    }
}
