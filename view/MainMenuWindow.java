package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainMenuWindow extends JFrame {

    private JPanelGradient jPanelGradient;
    private JButton newGameBtn;
    private JButton loadGameBtn;
    private JButton tutorialBtn;
    private JLabel title;
    
    public MainMenuWindow(){
        initComponents();
    }

    private void initComponents(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        // initializing components
        jPanelGradient = new JPanelGradient();
        newGameBtn = new JButton();
        loadGameBtn = new JButton();
        tutorialBtn = new JButton();
        title = new JLabel();

        // setting up components
        jPanelGradient.setColorEnd(new Color(0, 204, 204));
        jPanelGradient.setColorStart(new Color(255, 0, 153));

        title.setFont(new Font("Cambria", 1, 48));
        title.setForeground(new Color(255, 255, 255));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setText("TALABIA CHESS");

        newGameBtn.setFont(new Font("Bahnschrift", 0, 18));
        newGameBtn.setText("New Game");
        newGameBtn.setFocusable(false);
        newGameBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                newGameMouseClicked(e);
            }
        });

        loadGameBtn.setFont(new Font("Banschrift", 0, 18));
        loadGameBtn.setText("Load Game");
        loadGameBtn.setFocusable(false);
        loadGameBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadGameActionPerformed(e);
            }
        });

        tutorialBtn.setFont(new Font("Banschrift", 0, 18));
        tutorialBtn.setText("Tutorial");
        tutorialBtn.setFocusable(false);
        tutorialBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                tutorialMouseClicked(e);
            }
        });

        GroupLayout jPanelGradientLayout = new GroupLayout(jPanelGradient);
        jPanelGradient.setLayout(jPanelGradientLayout);
        jPanelGradientLayout.setHorizontalGroup(
            jPanelGradientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGradientLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(title, GroupLayout.PREFERRED_SIZE, 502, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, jPanelGradientLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelGradientLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(newGameBtn, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
                    .addComponent(tutorialBtn, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadGameBtn, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
                .addGap(241, 241, 241))
        );

        jPanelGradientLayout.setVerticalGroup(
            jPanelGradientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGradientLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(title, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(newGameBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(loadGameBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(tutorialBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanelGradient, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanelGradient, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void newGameMouseClicked(MouseEvent e){
        new Window();
    }

    private void loadGameActionPerformed(ActionEvent e){
        Window window = new Window();
        window.loadOldGame();
    }

    private void tutorialMouseClicked(MouseEvent e){
        new TutorialFrame();
    }
    
}
