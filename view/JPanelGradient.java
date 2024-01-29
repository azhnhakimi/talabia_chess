package view;

import javax.swing.JPanel;

import java.awt.*;

// Explanation

public class JPanelGradient extends JPanel {
    
    private Color colorStart = Color.BLACK;
    private Color colorEnd = Color.WHITE;

    // Explanation
    // Worked on by : 
    @Override
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(0,0,colorStart,getWidth(),getHeight(),colorEnd);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    // Explanation
    // Worked on by : 
    public Color getColorStart(){
        return colorStart;
    }

    // Explanation
    // Worked on by : 
    public void setColorStart(Color colorStart){
        this.colorStart = colorStart;
    }

    // Explanation
    // Worked on by : 
    public Color getColorEnd(){
        return this.colorEnd;
    }

    // Explanation
    // Worked on by : 
    public void setColorEnd(Color colorEnd){
        this.colorEnd = colorEnd;
    }
    
}
