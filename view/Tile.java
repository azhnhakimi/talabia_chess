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
        setBackground(new Color(0xEDD6B3));
        addMouseListener(new ClickListener());
        setOpaque(true);
        setBorder(new LineBorder(Color.BLACK, 1));
        setLayout(new BorderLayout());
        possibleMoves = new ArrayList<>();
    }

    public Position getPosition() {
        return position;
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
        return piece;
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
