package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.Game;

import java.awt.*;
import java.awt.event.WindowEvent;

public class Window extends JFrame{

    Game game;
    BoardModel boardModel;

    int BOARD_WIDTH = 560;
    int BOARD_HEIGHT = 480;
    
    public Window(){

        // setting up the frame structure
        this.setSize(new Dimension(700, 700));
        this.setTitle("Talabia Chess");
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.addComponentListener(new ResizeAdapter(this));
        this.addWindowStateListener(this::handleWindowStateChange);
        getContentPane().setBackground(new Color(0x292625));
        
        createNewGame();
        
        this.setJMenuBar(new MenuBar(this.game.getBoard(), this.boardModel));
        
        resizeContentPanel();
        this.setVisible(true);
    }

    private void createNewGame(){
        this.game = new Game();
        this.boardModel = game.getBoardModel();
        this.add(boardModel);
        game.playGame();
    }






    //methods to handle window frame changes
    
    private void handleWindowStateChange(WindowEvent e) {
        if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
            resizeContentPanel();
        }
    }
    
    public void resizeContentPanel() {
        SwingUtilities.invokeLater(() -> {
            Dimension newBounds = getNewBoardBounds(boardModel);
            Dimension newDimensions = getNewBoardDimensions(boardModel);
            boardModel.setBounds((int) newBounds.getWidth(), (int) newBounds.getHeight(),
                    (int) newDimensions.getWidth(), (int) newDimensions.getHeight());
        });
    }

    public Dimension getNewBoardBounds(JPanel panel){
        int frameWidth = getContentPane().getSize().width;
        int frameHeight = getContentPane().getSize().height;

        int xBound = (frameWidth / 2) - (panel.getWidth() / 2);
        int yBound = (frameHeight / 2) - (panel.getHeight() / 2);

        return new Dimension(xBound, yBound);
    }

    public Dimension getNewBoardDimensions(JPanel panel){
        int frameHeight = getContentPane().getSize().height;
        int newHeight = (frameHeight / 5) * 3;
        int newWidth = (newHeight / 6) * 7;

        return new Dimension(newWidth, newHeight);
    }
    
}
