package controller;

import javax.swing.SwingUtilities;

import model.Board;
import view.BoardModel;

// Game class holds all game related information
// It begins the game and acts as the intermediate between the model and view
// Class created by : Azhan

public class Game {

    private Board board;
    private BoardModel boardModel;

    // Create a new game
    public Game(){
        this.board = new Board();
        this.boardModel = new BoardModel(6, 7, board);
    }

    // Gets the BoardModel associated with the game
    public BoardModel getBoardModel(){
        return this.boardModel;
    }

    // Gets the Board associated with the game
    public Board getBoard(){
        return this.board;
    }

    // Begins the initial playing phase of the game
    public void playGame(){
        SwingUtilities.invokeLater(() -> boardModel.draw());
    }
}
