/**
 * CSCI1130 Assignment 1 Shaky Display
 * Aim: Get acquainted with the JDK + NetBeans programming environment
 *      Learn the structure and format of a Java program by example
 *
 * Remark: Key in class names, variable names, method names, etc. AS IS
 *         You should type also ALL the comment lines (text in grey)
 *
 * I declare that the assignment here submitted is original
 * except for the source material explicitly acknowledged,
 * and that the same or closely related material has not been
 * previously submitted for another course,
 * I also acknowledge that I am aware of University policy and 
 * regulations on honesty in academic work, and of the disciplinary 
 * guidelines and procedures applicable to breaches of such 
 * policy and regulations, as contained in the website,
 *
 * University Guideline on Academic Honesty:
 *  http://www.cuhk.edu.hk/policy/academichonesty
 * Faculty of Engineering Guidelines to Academic Honesty:
 *  https://www.erg.cuhk.edu.hk/erg/AcademicHonesty
 *
 * Student Name: RAHMAN, Faiyaz
 * Student ID  : 1155116151
 * Date        : 23/09/2019
 */ 

package exercise;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

/** 
 * ShakyDisplay
 * Introduction to Computing: Java Assignment
 * @Author Micheal FUNG
 * @since 21 August 2019
 */
public class ShakyDisplay extends JFrame implements ActionListener{

    // instance fields
    protected int width, height;
    protected JButton buttons[][];

    // default constructor
    public ShakyDisplay()
    {
    	width = 10;
    	height = 10;
    	initDisplay();
    }

    // constructor with given width and height of ShakyDisplay object
    public ShakyDisplay(int w, int h)
    {
    	width = w;
    	height = h;
    	initDisplay();
    }
    
    // initialize the ShakyDisplay window
    public final void initDisplay()
    {
    	try {
           	UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch(ClassNotFoundException
        	| IllegalAccessException
            	| InstantiationException
        	| UnsupportedLookAndFeelException exceptionobject) {
        }
		
        setTitle("Java Shaky Display");
        setLayout(new GridLayout(height, width));
        buttons = new JButton[height][width];
	for (int row = 0; row<height; row++)
            for (int col = 0; col < width; col++)
            {
		buttons[row][col]=new JButton("(" + row + ", "+ col + ")");
		buttons[row][col].addActionListener(this);
		add(buttons[row][col]);
            }
	setSize(width * 70, height * 70);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // change button text color on user clicks
    @Override
    public void actionPerformed(ActionEvent eventObject)
    {
	JButton target = (JButton) (eventObject.getSource());
	if (target.getForeground() == Color.GREEN)
        	target.setForeground(Color.BLUE);
        else if (target.getForeground() == Color.BLUE)
            {
		target.setForeground(null);
		shake();
            }
	else 
            target.setForeground(Color.GREEN);
    }
    
    // slow down this process by sleeping this thread 
    private void delay(long sleepInMS){
    	try {
            TimeUnit.MILLISECONDS.sleep(sleepInMS);
       	} catch (InterruptedException exceptionObject) {
            Thread.currentThread().interrupt();
	}
    }
    
    // shake the ShakyDisplay
    private void shake()
	{
            Point windowLocation = getLocation();

            double round = 5, max_radius = 10, step = 100;

            double limit = 2 * Math.PI * round;
            double angle_increment = limit/step;
            double radius_increment = max_radius / step;

            for (double angle = 0, radius = 0;
		angle < limit;
                angle += angle_increment, radius += radius_increment)
            {
		setLocation((int) (Math.cos(angle) * radius) + windowLocation.x,
                            (int) (Math.sin(angle) * radius) + windowLocation.y);
		this.delay(6);
            }
            this.setLocation(windowLocation);
	}
    // *** students should customize this method ***
    // - to show the last 5 digits of your SID in YELLOW
    // - to show your surname char-by-char as button text on the bottom
    public void showMyInfo()
    {
        int c = 0; 
        
        // 1
        c=1;
        buttons[c++][2].setBackground(Color.YELLOW);
        buttons[c++][2].setBackground(Color.YELLOW);
        buttons[c++][2].setBackground(Color.YELLOW);
        buttons[c++][2].setBackground(Color.YELLOW);
        buttons[c++][2].setBackground(Color.YELLOW);
        
        // 6
        buttons[1][7].setBackground(Color.YELLOW);
        buttons[1][6].setBackground(Color.YELLOW);
        buttons[1][8].setBackground(Color.YELLOW);
        buttons[2][6].setBackground(Color.YELLOW);
        buttons[3][6].setBackground(Color.YELLOW);
        buttons[3][7].setBackground(Color.YELLOW);
        buttons[3][8].setBackground(Color.YELLOW);
        buttons[4][6].setBackground(Color.YELLOW);
        buttons[4][8].setBackground(Color.YELLOW);
        buttons[5][6].setBackground(Color.YELLOW);
        buttons[5][7].setBackground(Color.YELLOW);
        buttons[5][8].setBackground(Color.YELLOW);
        
        // 1
        c=1;
        buttons[c++][11].setBackground(Color.YELLOW);
        buttons[c++][11].setBackground(Color.YELLOW);
        buttons[c++][11].setBackground(Color.YELLOW);
        buttons[c++][11].setBackground(Color.YELLOW);
        buttons[c++][11].setBackground(Color.YELLOW);
            
            
        // 5
        buttons[1][16].setBackground(Color.YELLOW);
        buttons[1][16].setBackground(Color.YELLOW);
        buttons[1][15].setBackground(Color.YELLOW);
        buttons[1][17].setBackground(Color.YELLOW);
        buttons[2][15].setBackground(Color.YELLOW);
        buttons[3][15].setBackground(Color.YELLOW);
        buttons[3][16].setBackground(Color.YELLOW);
        buttons[3][17].setBackground(Color.YELLOW);
        buttons[4][17].setBackground(Color.YELLOW);
        buttons[5][15].setBackground(Color.YELLOW);
        buttons[5][16].setBackground(Color.YELLOW);
        buttons[5][17].setBackground(Color.YELLOW);
        
        // 1
        c=1;
        buttons[c++][21].setBackground(Color.YELLOW);
        buttons[c++][21].setBackground(Color.YELLOW);
        buttons[c++][21].setBackground(Color.YELLOW);
        buttons[c++][21].setBackground(Color.YELLOW);
        buttons[c++][21].setBackground(Color.YELLOW);
        
            
        // My Surname
        c = 0;
        buttons[height-1][c++].setText("R");
        buttons[height-1][c++].setText("A");
        buttons[height-1][c++].setText("H");
        buttons[height-1][c++].setText("M");
        buttons[height-1][c++].setText("A");
        buttons[height-1][c++].setText("N");
    }
    
    /**
     * main() method, starting point of the java application
     * @param args are command line arguments in a String array
     */
    public static void main(String[] args) {
	ShakyDisplay dpy;
        // may change this line to create a ShakyDisplay of a different size
	dpy = new ShakyDisplay(25,10);
	dpy.showMyInfo();
    }
}
