package view;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.Board;
import model.Position;
import model.pieces.Piece;
import java.awt.*;
import java.io.File;
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

    public boolean hasMovedPiece() {
        return hasMovedPiece;
    }

    public void setHasMovedPiece(boolean hasMoved, Tile clicked) {
        this.hasMovedPiece = hasMoved;
        this.clickedTile = clicked; // Save the tile that was clicked
    }
    
    // Add a getter for clickedTile
    public Tile getClickedTile() {
        return clickedTile;
    }

    public void seePossibleMoves(ArrayList<Position> possiblePositions, Tile tileClickedOn) {
        resetTileBackgrounds();
        this.clickedTile = tileClickedOn;
        for (Position pos : possiblePositions) {
            if (isValidPosition(pos)) {
                tiles[pos.getRow()][pos.getColumn()].setBackground(new Color(0x00ff00));
            }
        }
    }

    public void movePieceTo(Tile destinationTile) {
        if (destinationTile.getBackground().equals(new Color(0x00ff00))) {
            Position startPos = clickedTile.getPosition();
            Position endPos = destinationTile.getPosition();

            Piece piece = board.getPiece(startPos.getRow(), startPos.getColumn());
            board.removePiece(startPos.getRow(), startPos.getColumn());
            board.addPiece(endPos.getRow(), endPos.getColumn(), piece);

            clickedTile.setPiece(null);
            destinationTile.setPiece(piece);
            setHasMovedPiece(false, null);
            resetTileBackgrounds();
            draw();
        }
    }

    private void resetTileBackgrounds() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                tile.setBackground(new Color(0xEDD6B3));
            }
        }
    }

    

    private boolean isValidPosition(Position position) {
        return position.getRow() >= 0 && position.getRow() < rows &&
               position.getColumn() >= 0 && position.getColumn() < columns;
    }

    public void draw() {
        removeAll();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Piece piece = board.getPiece(i, j);
                Tile tile = tiles[i][j];
                if (piece != null) {
                    String pieceType = piece.getPieceType();
                    String casedPieceType = Character.toUpperCase(pieceType.charAt(0)) + pieceType.substring(1);
                    String filePath = "assets" + File.separator + piece.getColour() + casedPieceType + ".png";
                    ImageIcon image = new ImageIcon(filePath);
                    tile.setPiece(piece);
                    tile.setImage(image);
                } else {
                    tile.setPiece(null);
                    tile.setImage(null);
                }
                add(tile);
            }
        }
        revalidate();
        repaint();
    }

    public void flipBoard(int playerTurn) {
        if (playerTurn != 1) {
            flipped = !flipped;
            draw();
        }
    }
}
