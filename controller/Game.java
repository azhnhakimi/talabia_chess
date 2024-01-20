package controller;

import javax.swing.SwingUtilities;

import model.Board;
import view.BoardModel;

public class Game {
    
    int playerTurn;
    int turnCount;
    private Board board;
    BoardModel boardModel;

    public Game(){
        this.playerTurn = 1;
        this.turnCount = 0;
        this.board = new Board();
        this.boardModel = new BoardModel(6, 7, board);
    }

    public BoardModel getBoardModel(){
        return this.boardModel;
    }



    public Board getBoard(){
        return this.board;
    }

    public void playGame(){

        SwingUtilities.invokeLater(() -> boardModel.draw());
        
    }

    
}
