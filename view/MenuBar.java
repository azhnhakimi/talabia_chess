package view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MenuBar extends JMenuBar implements ActionListener{

    JMenu options;
    JMenuItem saveGame;
    ImageIcon downIcon;

    MenuBar(){
        options = new JMenu("Options");
        saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(this);

        downIcon = new ImageIcon("./assets/down.png");
        Image scaledIcon = downIcon.getImage();
        if (scaledIcon != null) {
            scaledIcon = scaledIcon.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            options.setIcon(new ImageIcon(scaledIcon));
        }
        
        options.add(saveGame);
        this.add(options);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveGame){
            
        }
    }
    
}
