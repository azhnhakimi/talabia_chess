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

public class Tile extends JPanel {

    Piece piece;
    ImageIcon image;
    Position position;
    BoardModel boardModel;
    ArrayList<Position> possibleMoves;

    public Tile(Piece piece, ImageIcon image, Position position, BoardModel boardModel) {
        this.piece = piece;
        this.image = image;
        this.position = position;
        this.boardModel = boardModel;
        this.setBackground(new Color(0xEDD6B3));
        this.addMouseListener(new ClickListener());
        this.setOpaque(true);
        this.setBorder(new LineBorder(Color.BLACK, 1));
        this.setLayout(new BorderLayout());
        this.possibleMoves = new ArrayList<>();
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        if (this.piece != null) {
            this.piece.setCurrentPosition(this.position);
        }
        updateImage();
    }

    public Piece getPiece() {
        return this.piece;
    }

    public ArrayList<Position> getPossibleMoves() {
        return this.possibleMoves;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
        updateImage();
    }

    private void updateImage() {
        removeAll();
        if (image != null) {
            JLabel label = new JLabel(image);
            add(label);
        }
        revalidate();
        repaint();
    }

    public void movePieceTo(Tile newTile) {
        Piece movingPiece = this.piece;
        this.setPiece(null); // Remove piece from current tile
        newTile.setPiece(movingPiece); // Set piece on the new tile
        if (movingPiece != null) {
            movingPiece.moveTo(newTile.getPosition()); // Update piece's position
        }
        boardModel.draw(); // Redraw the board to reflect the move
    }

    private class ClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (piece != null) {
                possibleMoves = piece.getPossibleMoves();
                boardModel.seePossibleMoves(possibleMoves, Tile.this);
                boardModel.setHasMovedPiece(true, Tile.this);
            } else if (boardModel.hasMovedPiece() && Tile.this.getBackground().equals(new Color(0x00ff00))) {
                boardModel.movePieceTo(Tile.this);
                boardModel.setHasMovedPiece(false, null);
            }
        }
    }
}
