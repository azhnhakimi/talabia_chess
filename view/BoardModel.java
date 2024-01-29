package view;

import javax.swing.*;
import model.Board;
import model.Position;
import model.pieces.Hourglass;
import model.pieces.Piece;
import model.pieces.Plus;
import model.pieces.Sun;
import model.pieces.Time;
import model.pieces.Point;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

// BoardModel class is the graphical representation of the Board class

public class BoardModel extends JPanel {

    private JFrame frame;
    private Board board;
    private int rows;
    private int columns;
    private boolean flipped = false;
    private boolean hasMovedPiece = false;
    private Tile[][] tiles;
    private Tile clickedTile;
    public int turnCount = 1;
    private String currentPlayer; 

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

    // Initialize all the tiles on the board model when first starting the game
    // Worked on by : Azhan
    private void initializeTiles() {
        tiles = new Tile[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                tiles[i][j] = new Tile(null, null, new Position(i, j), this);
                add(tiles[i][j]);
            }
        }
    }

    // Check if a piece been moved during a turn
    // Worked on by : Syed
    public boolean hasMovedPiece() {
        return hasMovedPiece;
    }

    // Updates whether a piece has been moved during the current turn and keep tracked of the clicked tile
    // Worked on by : Syed
    public void setHasMovedPiece(boolean hasMoved, Tile clicked) {
        this.hasMovedPiece = hasMoved;
        this.clickedTile = clicked; 
    } 

    // Shows visibly on the tiles where a piece can move to 
    // Worked on by : Azhan, Syed
    public void seePossibleMoves(ArrayList<Position> possiblePositions, Tile tileClickedOn) {
        resetTileBackgrounds();
        this.clickedTile = tileClickedOn;
        Piece movingPiece = clickedTile.getPiece(); 
    
        for (Position pos : possiblePositions) {
            if (isValidPosition(pos)) {
                Piece targetPiece = board.getPiece(pos.getRow(), pos.getColumn());
                if (targetPiece == null || !movingPiece.getColour().equals(targetPiece.getColour())) {
                    if (movingPiece instanceof Hourglass || isPathClear(clickedTile.getPosition(), pos, movingPiece)) {
                        tiles[pos.getRow()][pos.getColumn()].setBackground(new Color(0x00ff00));
                    }
                }
            }
        }
    }

    // Move a piece to specified tile
    // Worked on by : Syed, Hafizul
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

    // Check if the path between two position is clear for a moving piece
    // Worked on by : Syed
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

    // Reset the background colour of all tiles to the default colour
    // Worked on by : Syed
    public void resetTileBackgrounds() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                tile.setBackground(new Color(0xEDD6B3));
            }
        }
    }

    // Check if a position is within bound of the board
    // Worked on by : Syed
    private boolean isValidPosition(Position position) {
        return position.getRow() >= 0 && position.getRow() < rows && position.getColumn() >= 0 && position.getColumn() < columns;
    }

    // Method to get the current player
    // Worked on by : Syed
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    // Sets the current player
    // Worked on by : Syed
    public void setCurrentPlayer(String currentPlayer){
        this.currentPlayer = currentPlayer;
    }

    // Method to switch the current player
    // Worked on by : Syed
    private void switchPlayerTurn() {
        currentPlayer = (currentPlayer.equals("white")) ? "black" : "white";
        flipBoard();
    }

    // Shows a message dialog when game has ended
    // Worked on by : Azhan, Syed
    private void endGame() {
        JOptionPane.showMessageDialog(this, "Winner Winner Chicken Dinner! " + currentPlayer.toUpperCase() + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();
    }

    // Helper method to rotate an ImageIcon
    // Worked on by : Syed
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

    // Draws the pieces, tiles onto the board
    // Worked on by : Azhan, Syed, Balqis
    public void draw() {
        removeAll();
        int startRow, endRow, rowIncrement, startColumn, endColumn, columnIncrement;

        if(currentPlayer.equals("white")){
            flipped = false;
        }else if(currentPlayer.equals("black")){
            flipped = true;
        }
    
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
            return;
        }
        turnCount++;
    }
    
    // Iterate through all pieces on the board and switch their types
    // Worked on by : Balqis
    private void switchPieceTypes() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Piece piece = board.getPiece(i, j);
                if (piece != null) {
                    String pieceColor = piece.getColour();
                    if (piece.getPieceType().equals("time")) {
                        board.removePiece(i, j);
                        board.addPiece(i, j, new Plus(pieceColor));
                    } else if (piece.getPieceType().equals("plus")) {
                        board.removePiece(i, j);
                        board.addPiece(i, j, new Time(pieceColor));
                    }
                }
            }
        }
    }

    // Sets the Frame associated with the BoardModel
    // Worked on by : Azhan
    public void setFrame(JFrame frame){
        this.frame = frame;
    }
    
    // Boards flip for each player
    // Worked on by : Balqis
    public void flipBoard() {
        flipped = !flipped;
        draw();
    }
    
}
