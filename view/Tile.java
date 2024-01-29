package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.*;
import model.Position;
import model.pieces.Piece;

// Tile class is the graphical representaion of each individual tile on a game board

public class Tile extends JPanel {
    private Piece piece;
    private ImageIcon image;
    private Position position;
    private BoardModel boardModel;
    private ArrayList<Position> possibleMoves;

    public Tile(Piece piece, ImageIcon image, Position position, BoardModel boardModel) {
        this.piece = piece;
        this.image = image;
        this.position = position;
        this.boardModel = boardModel;
        this.possibleMoves = new ArrayList<>();
        setBackground(new Color(0xEDD6B3));
        addMouseListener(new ClickListener());
        setOpaque(true);
        setBorder(new LineBorder(Color.BLACK, 1));
        setLayout(new BorderLayout());
    }

    // Gets the position in which the tile is on
    // Worked on by : Syed
    public Position getPosition() {
        return position;
    }

    // Sets the position of the tile on the board
    // Worked on by : Syed
    public void setPosition(Position position) {
        this.position = position;
    }

    // Sets the piece on the tile
    // Worked on by : Syed
    public void setPiece(Piece piece) {
        this.piece = piece;
        if (this.piece != null) {
            this.piece.setCurrentPosition(this.position);
        }
        updateImage();
    }

    // Gets the piece currently on the tile
    // Worked on by : Syed
    public Piece getPiece() {
        return piece;
    }

    // Sets the image displayed on the tile
    // Worked on by : Syed
    public void setImage(ImageIcon image) {
        this.image = image;
        updateImage();
    }

    // Updates the image displayed on the tile
    // Worked on by : Syed
    private void updateImage() {
        removeAll();
        if (image != null) {
            JLabel label = new JLabel(image);
            add(label);
        }
        revalidate();
        repaint();
    }

    // Handles mouse clicks on the tile, enabling piece movement and selection
    // Worked on by : Syed, Hafizul
    private class ClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (boardModel.hasMovedPiece() && getBackground().equals(new Color(0x00ff00))) {
                // If it's a valid move, move the piece
                boardModel.movePieceTo(Tile.this);
                boardModel.setHasMovedPiece(false, null);
            } else {
                // If another piece was selected, reset the selection
                if (boardModel.hasMovedPiece()) {
                    boardModel.setHasMovedPiece(false, null);
                    boardModel.resetTileBackgrounds();
                }

                // If the clicked tile has a piece and it's the current player's turn
                if (piece != null && piece.getColour().equals(boardModel.getCurrentPlayer())) {
                    possibleMoves = piece.getPossibleMoves();
                    boardModel.seePossibleMoves(possibleMoves, Tile.this);
                    boardModel.setHasMovedPiece(true, Tile.this);
                }
            }
        }
    }
}
