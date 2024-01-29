package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.Game;

import java.awt.*;
import java.awt.event.WindowEvent;

// Window class is the main window which holds all graphical elements in the game graphics
// Class created by : Azhan

public class Window extends JFrame{

    private Game game;
    private BoardModel boardModel;
    private MenuBar menuBar;

    int BOARD_WIDTH = 560;
    int BOARD_HEIGHT = 480;
    
    public Window(){

        // setting up the frame structure
        this.setSize(new Dimension(700, 700));
        this.setTitle("Talabia Chess");
        this.setResizable(true);
        this.setLayout(null);
        this.addComponentListener(new ResizeAdapter(this));
        this.addWindowStateListener(this::handleWindowStateChange);
        getContentPane().setBackground(new Color(0x292625));
        
        createNewGame();

        menuBar = new MenuBar(this.game.getBoard(), this.boardModel);
        
        this.setJMenuBar(menuBar);
        
        resizeContentPanel();
        this.setVisible(true);
    }

    // Creates a new game
    public void createNewGame(){
        this.game = new Game();
        this.boardModel = game.getBoardModel();
        this.boardModel.setFrame(this);
        this.add(boardModel);
        game.playGame();
    }

    // Loads a game from saved data
    public void loadOldGame(){
        menuBar.loadGameState();
    }

    //methods to handle window frame changes
    
    // Event handler for window state changes
    // Created to handle sudden maximize frame changes
    private void handleWindowStateChange(WindowEvent e) {
        if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
            resizeContentPanel();
        }
    }
    
    // Resizes the BoardModel based on the Window dimenstions
    public void resizeContentPanel() {
        SwingUtilities.invokeLater(() -> {
            Dimension newBounds = getNewBoardBounds(boardModel);
            Dimension newDimensions = getNewBoardDimensions(boardModel);
            boardModel.setBounds((int) newBounds.getWidth(), (int) newBounds.getHeight(),
                    (int) newDimensions.getWidth(), (int) newDimensions.getHeight());
            revalidate();
            repaint();
        });
    }

    // Calculates the new bounds for the BoardModel within the frame
    public Dimension getNewBoardBounds(JPanel panel){
        int frameWidth = getContentPane().getSize().width;
        int frameHeight = getContentPane().getSize().height;

        int xBound = (frameWidth / 2) - (panel.getWidth() / 2);
        int yBound = (frameHeight / 2) - (panel.getHeight() / 2);

        return new Dimension(xBound, yBound);
    }

    // Calculates the new dimensions (width and height) of the BoardModel
    public Dimension getNewBoardDimensions(JPanel panel){
        int frameHeight = getContentPane().getSize().height;
        int newHeight = (frameHeight / 5) * 3;
        int newWidth = (newHeight / 6) * 7;
        return new Dimension(newWidth, newHeight);
    }
    
}
