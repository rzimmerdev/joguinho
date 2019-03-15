package main;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class window extends JFrame {

	
	Graphics g;
	int FPS = 30;
	
	private static final long serialVersionUID = 1L;

	public window() {

        initUI();
        initG();
    }

    private void initUI() {
        
        setTitle("Simple example");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    private void initG() {
    	g = getGraphics();
    }
    
    public void draw() {
    
    	
    }
}