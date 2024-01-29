package view;

import javax.swing.JPanel;

import java.awt.*;

// This to make gradient coloured picture.

public class JPanelGradient extends JPanel {
    
    private Color colorStart = Color.BLACK;
    private Color colorEnd = Color.WHITE;

    // This JPanelGradient is what makes the MainMenu window have a gradient coloured background.
    // Worked on by : Yasmin
    @Override
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(0,0,colorStart,getWidth(),getHeight(),colorEnd);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    // This is where the color starts. 
    // Worked on by :Yasmin 
    public Color getColorStart(){
        return colorStart;
    }

    // This is where you can choose your color of choosing from the start.
    // Worked on by : Yasmin
    public void setColorStart(Color colorStart){
        this.colorStart = colorStart;
    }

    // This is where the color ends
    // Worked on by : Yasmin
    public Color getColorEnd(){
        return this.colorEnd;
    }

    // This is where you can choose your color of choosing at the end.
    // Worked on by : Yasmin
    public void setColorEnd(Color colorEnd){
        this.colorEnd = colorEnd;
    }
    
}
