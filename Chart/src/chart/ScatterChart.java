/**
 * CSCI1130 Mini-Project Chart
 * 
 * I declare that the assignment here submitted is original
 * except for source material explicitly acknowledged,
 * and that the same or closely related material has not been
 * previously submitted for another course.
 * I also acknowledge that I am aware of University policy and 
 * regulations on honesty in academic work, and of the disciplinary
 * guidelines and procedures applicable to breaches of such
 * policy and regulations, as contained in the website.
 * 
 * University Guideline on Academic Honesty:
 *  http://www.cuhk.edu.hk/policy/academichonesty
 * Faculty of Engineering Guidelines to Academic Honesty:
 *  https://www.erg.cuhk.edu.hk/erg/AcademicHonesty
 * 
 * Student Name: RAHMAN, Faiyaz
 * Student ID  : 1155116151
 * Date        : 15/12/2019
 */

package chart;

import java.awt.Color;


public class ScatterChart extends Chart {
    // declaration of some required variables
    protected double minX, maxX, minY, maxY, rangeX, rangeY;
    
    public ScatterChart(double[] X, double[] Y)
    {
        // find xMax, xMin, yMax & yMin using one for-loop
        double xMax = X[0], yMax = Y[0], xMin = X[0], yMin = Y[0];
        for (int i = 0; i < X.length; i++) {
            if (X[i] > xMax)
                xMax = X[i];
            if (X[i] < xMin)
                xMin = X[i];
            if (Y[i] > yMax)
                yMax = Y[i];
            if (Y[i] < yMin)
                yMin = Y[i];
        }
        
        this.maxX = xMax;
        this.minX = xMin;
        this.maxY = yMax;
        this.minY = yMin;
        this.rangeX = maxX - minX;
        this.rangeY = maxY - minY;
        drawAxes();
        plot(X, Y);
    }
    
    public void drawAxes()
    {   
        // determine co-ordinates of origin (0, 0) in the plot co-ordinate system
        double originX = (0 - minX) / rangeX;
        double originY = (0 - minY) / rangeY;
        
        // use the origin coordinates to draw the axes
        this.drawLine(0, originY, 1, originY, Color.BLUE);
        this.drawLine(originX, 0, originX, 1, Color.BLUE);
        validate();
    }
            
    public void plot(double[] X, double[] Y)
    {
        // iterate through all points in X, Y
        double x1, y1;
        int i = 0;
        while (i < X.length)
        {
            // (x1, y1) is the current point to be plotted
            x1 = X[i];
            y1 = Y[i];
            
            // transform (x1, y1) to co-ordinate system of the plot
            drawDot( (x1 - minX) / rangeX, (y1 - minY) / rangeY, Color.ORANGE);
            i++;
        }
        
        validate();
    }
}
