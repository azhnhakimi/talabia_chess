package view;

import javax.swing.*;
import model.Board;
import model.Position;
import model.pieces.Hourglass;
import model.pieces.Piece;
import model.pieces.Sun;
import model.pieces.Point;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
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
    public int turnCount = 1;
    private String currentPlayer; // Variable to keep track of the current player's turn

    public BoardModel(int rows, int columns, Board board) {
        this.rows = rows;
        this.columns = columns;
        this.board = board;
        this.currentPlayer = "white"; // white starts the game
        setLayout(new GridLayout(this.rows, this.columns, 0, 0));
        setBackground(new Color(0xEDD6B3));
        setOpaque(true);
        initializeTiles();
    }

    private void initializeTiles() {
        tiles = new Tile[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                tiles[i][j] = new Tile(null, null, new Position(i, j), this);
                add(tiles[i][j]);
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

    public Tile getClickedTile() {
        return clickedTile;
    }

    public void seePossibleMoves(ArrayList<Position> possiblePositions, Tile tileClickedOn) {
        resetTileBackgrounds();
        this.clickedTile = tileClickedOn;
        Piece movingPiece = clickedTile.getPiece(); // Get the piece from the clicked tile
    
        for (Position pos : possiblePositions) {
            // First, check if the position is valid and within the board limits
            if (isValidPosition(pos)) {
                Piece targetPiece = board.getPiece(pos.getRow(), pos.getColumn());
                
                // Check if the target tile is not occupied by a piece of the same color
                if (targetPiece == null || !movingPiece.getColour().equals(targetPiece.getColour())) {
                    // Then, check if the path to that position is clear (for non-Hourglass pieces)
                    if (movingPiece instanceof Hourglass || isPathClear(clickedTile.getPosition(), pos, movingPiece)) {
                        tiles[pos.getRow()][pos.getColumn()].setBackground(new Color(0x00ff00));
                    }
                }
            }
        }
    }
    
    public void movePieceTo(Tile destinationTile) {
        if (destinationTile.getBackground().equals(new Color(0x00ff00))) {
            Position startPos = clickedTile.getPosition();
            Position endPos = destinationTile.getPosition();

            Piece movingPiece = board.getPiece(startPos.getRow(), startPos.getColumn());
            Piece targetPiece = board.getPiece(endPos.getRow(), endPos.getColumn());

            if (!(movingPiece instanceof Hourglass) && !isPathClear(startPos, endPos, movingPiece)) {
                return; // If the path is not clear and the piece is not an Hourglass, don't move
            }
    
            // Check if the target tile is occupied by a piece of the same color
            if (targetPiece != null && movingPiece.getColour().equals(targetPiece.getColour())) {
                return; // Do not allow capturing own pieces
            }

            if (targetPiece != null && targetPiece instanceof Sun) {
            endGame(); // Call a method to handle the end of the game
            return;
        }
    
            if (movingPiece != null && movingPiece.getColour().equals(currentPlayer)) {
                board.removePiece(startPos.getRow(), startPos.getColumn());
                board.addPiece(endPos.getRow(), endPos.getColumn(), movingPiece);
    
                clickedTile.setPiece(null);
                destinationTile.setPiece(movingPiece);
                setHasMovedPiece(false, null);
                switchPlayerTurn(); // Switch the player turn
                resetTileBackgrounds();
                draw();
            }
        }
    }

    private boolean isPathClear(Position start, Position end, Piece movingPiece) {
        // Calculate the direction of movement
        int rowDirection = Integer.compare(end.getRow(), start.getRow());
        int colDirection = Integer.compare(end.getColumn(), start.getColumn());

        int currentRow = start.getRow() + rowDirection;
        int currentCol = start.getColumn() + colDirection;

        while ((currentRow != end.getRow()) || (currentCol != end.getColumn())) {
            if (board.getPiece(currentRow, currentCol) != null) {
                return false; // Found a piece in the path
            }

            currentRow += rowDirection;
            currentCol += colDirection;
        }

        return true; // Path is clear
    }

    public void resetTileBackgrounds() {
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

    // Method to get the current player
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    // Method to switch the current player
    private void switchPlayerTurn() {
        currentPlayer = (currentPlayer.equals("white")) ? "black" : "white";
        flipBoard();
    }

    private void endGame() {
        // Display a dialog box indicating the game is over and which player won
        JOptionPane.showMessageDialog(this, "HABIS DAHH!!!! " + currentPlayer.toUpperCase() + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    // Helper method to rotate an ImageIcon
    public ImageIcon rotateImageIcon(ImageIcon icon, double degrees) {
    int width = icon.getIconWidth();
    int height = icon.getIconHeight();
    int type = BufferedImage.TYPE_INT_ARGB; 
    BufferedImage image = new BufferedImage(height, width, type);
    Graphics2D g2 = image.createGraphics();
    double x = (height - width)/2.0;
    double y = (width - height)/2.0;
    AffineTransform at = AffineTransform.getTranslateInstance(x, y);
    at.rotate(Math.toRadians(degrees), width/2.0, height/2.0);
    g2.drawImage(icon.getImage(), at, null);
    g2.dispose();
    return new ImageIcon(image);
}

    public void draw() {
        removeAll();
        int startRow, endRow, rowIncrement, startColumn, endColumn, columnIncrement;
    
        if (flipped) {
            startRow = rows - 1;
            endRow = -1;
            rowIncrement = -1;
            startColumn = columns - 1;
            endColumn = -1;
            columnIncrement = -1;
        } else {
            startRow = 0;
            endRow = rows;
            rowIncrement = 1;
            startColumn = 0;
            endColumn = columns;
            columnIncrement = 1;
        }
    
        for (int i = startRow; i != endRow; i += rowIncrement) {
            for (int j = startColumn; j != endColumn; j += columnIncrement) {
                Piece piece = board.getPiece(i, j);
                Tile tile = tiles[i][j];
                if (piece != null) {
                    String pieceType = piece.getPieceType();
                    String casedPieceType = Character.toUpperCase(pieceType.charAt(0)) + pieceType.substring(1);
                    String filePath = "assets" + File.separator + piece.getColour() + casedPieceType + ".png";
                    ImageIcon image = new ImageIcon(filePath);
    
                // Check if the piece is a 'point' and is rotated
                    if (pieceType.equals("point") && ((Point) piece).isRotated()) {
                        image = rotateImageIcon(image, 180);
                    }

                    if (flipped) {
                        image = rotateImageIcon(image, 180);
                    }
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
        if(turnCount >= 8){
            switchPieceTypes();
            turnCount = 1;
        }
        turnCount++;
    }
    
    private void switchPieceTypes() {
        // Iterate through all pieces on the board and switch their types
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Piece piece = board.getPiece(i, j);
                if (piece != null) {
                    // Switch between Time and Plus pieces
                    if (piece.getPieceType().equals("time")) {
                        piece.setPieceType("plus");
                    } else if (piece.getPieceType().equals("plus")) {
                        piece.setPieceType("time");
                    }
                }
            }
        }
    }
    
    public void flipBoard() {
        flipped = !flipped;
        draw();
    }
    
}
