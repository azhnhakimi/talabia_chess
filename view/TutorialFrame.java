package view;

import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class TutorialFrame extends JFrame{

    private JLabel label;
    private JScrollBar jScrollBar;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;

    
    public TutorialFrame(){
        initComponents();
    }

    private void initComponents(){

        // this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        label = new JLabel();
        jScrollBar = new JScrollBar();
        jScrollPane1 = new JScrollPane();
        jScrollPane2 = new JScrollPane();

        String filePath = "assets" + File.separator + "TalabiaChessGuide.png";
        label.setIcon(new ImageIcon(filePath));
        jScrollPane2.setViewportView(label);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
        );

        pack();
    }
    
}
