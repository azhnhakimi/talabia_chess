package view;

import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

// Displays the Tutorial Frame when option in main menu is selected

public class TutorialFrame extends JFrame{

    private JLabel label;
    private JScrollPane jScrollPane;

    
    public TutorialFrame(){
        initComponents();
    }

    // This is the settings to open the Tutorial Frame for the game.
    // Worked on by : Yasmin
    private void initComponents(){

        // this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        label = new JLabel();
        jScrollPane = new JScrollPane();

        String filePath = "assets" + File.separator + "TalabiaChessGuide.png";
        label.setIcon(new ImageIcon(filePath));
        jScrollPane.setViewportView(label);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
        );

        pack();
    }
    
}
