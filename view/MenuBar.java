package view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.Board;
import model.pieces.Hourglass;
import model.pieces.Piece;
import model.pieces.Plus;
import model.pieces.Point;
import model.pieces.Sun;
import model.pieces.Time;

// MenuBar class creates a custom JMenuBar to be used in program

public class MenuBar extends JMenuBar implements ActionListener{

    private JMenu options;
    private JMenuItem saveGame;
    private JMenuItem loadGame;
    private ImageIcon downIcon;

    private Board board;
    private Piece[][] boardTiles;
    private BoardModel boardModel;

    MenuBar(Board board, BoardModel boardModel){
        this.board = board;
        this.boardTiles = board.getBoardTiles();
        this.boardModel = boardModel;
        
        options = new JMenu("Options");
        saveGame = new JMenuItem("Save Game");
        loadGame = new JMenuItem("Load Game");

        saveGame.addActionListener(this);
        loadGame.addActionListener(this);

        downIcon = new ImageIcon("./assets/down.png");
        Image scaledIcon = downIcon.getImage();
        if (scaledIcon != null) {
            scaledIcon = scaledIcon.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            options.setIcon(new ImageIcon(scaledIcon));
        }
        
        options.add(saveGame);
        options.add(loadGame);        
        this.add(options);
    }

    // Perform actions associated with respective selected options
    // Worked on by : Azhan
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveGame){
            saveGameState();          
        }else if(e.getSource() == loadGame){
            loadGameState();
        }
    }
    
    // Saves the game data onto a file
    // Worked on by : Azhan
    public void saveGameState(){
        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showSaveDialog(this);
        String currentPlayer = boardModel.getCurrentPlayer();
        if(userSelection == JFileChooser.APPROVE_OPTION){
            try (FileWriter writer = new FileWriter(fileChooser.getSelectedFile())) {
                writer.write(currentPlayer + "\n");
                for (int row = 0; row < 6; row++) {
                    for (int col = 0; col < 7; col++) {
                        Piece piece = boardTiles[row][col];
                        if (piece != null) {
                            writer.write(piece.getCurrentPosition().getRow() + " ");
                            writer.write(piece.getCurrentPosition().getColumn() + " ");
                            writer.write(piece.getPieceType() + " ");
                            writer.write(piece.getColour() + "\n");
                        } else {
                            writer.write(row + " ");
                            writer.write(col + " ");
                            writer.write("null\n");
                        }
                    }
                }
                JOptionPane.showMessageDialog(this, "Game state saved successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving game state: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Loads saved game data
    // Worked on by : Azhan
    public void loadGameState(){
        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
                
                board.clearBoard();
                String line;
                int row = 0;
                while ((line = reader.readLine()) != null && row < 43) {
                    if(row == 0){
                        String currentPlayer = line;
                        boardModel.setCurrentPlayer(currentPlayer);
                        row++;
                        continue;
                    }
                    
                    String[] tokens = line.split(" ");
                    for (int col = 0; col < tokens.length; col += 4) {
                        int pieceRow = Integer.parseInt(tokens[col]);
                        int pieceColumn = Integer.parseInt(tokens[col + 1]);
                        String pieceType = tokens[col + 2];
                        if(!pieceType.equals("null")){
                            String pieceColor = tokens[col + 3];
                            board.addPiece(pieceRow, pieceColumn, getNewPiece(pieceType, pieceColor));
                        }else{
                            board.addPiece(pieceRow, pieceColumn, null);
                        }
                    }
                    row++;
                };
                JOptionPane.showMessageDialog(this, "Game state loaded successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading game state: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        boardModel.draw();
    }

    // Creates and returns a new piece based on given piece type and piece color
    // Worked on by : Azhan
    public Piece getNewPiece(String pieceType, String pieceColor){
        switch(pieceType){
            case "plus":
                return new Plus(pieceColor);
            case "hourglass":
                return new Hourglass(pieceColor);
            case "time":
                return new Time(pieceColor);
            case "sun":
                return new Sun(pieceColor);
            case "point":
                return new Point(pieceColor);
            default:
                return null;
        }
        
    }
}
