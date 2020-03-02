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

/**
 * FunctionChart outlines a Function for x in [+/- extremeX] with interval size of stepX
 * @author pffung
 */
public class FunctionChart extends Chart {
    protected Function f;
    protected double minX, maxX, stepX, minY, maxY;
    
    public FunctionChart(Function f_x, double extremeX, double extremeY, double stepX)
    {
        f = f_x;
        this.maxX = Math.abs(extremeX);
        this.minX = -maxX;
        this.stepX = Math.abs(stepX);
        this.maxY = Math.abs(extremeY);
        this.minY = -maxY;
        this.setTitle(getClass().getSimpleName() + ": " + f);
        
        drawAxes();
        plot();
    }
    
    public void drawAxes()
    {
        this.drawLine(0, 0.5, 1, 0.5, Color.BLUE);
        this.drawLine(0.5, 0, 0.5, 1, Color.BLUE);
        validate();
    }
            
    public void plot()
    {
        double rangeX = maxX - minX;
        double rangeY = maxY - minY;
        double x1 = minX;
        double y1 = f.valueAt(x1);
        drawDot( (x1 - minX) / rangeX, (y1 - minY) / rangeY, Color.ORANGE);
        double x2 = x1 + stepX, y2;
        while (x2 <= maxX)
        {
            x2 = x1 + stepX;
            y2 = f.valueAt(x2);
            
            drawDot( (x2 - minX) / rangeX, (y2 - minY) / rangeY, Color.ORANGE);

            drawLine((x1 - minX) / rangeX, (y1 - minY) / rangeY,
                     (x2 - minX) / rangeX, (y2 - minY) / rangeY, Color.GREEN);

            x1 = x2;
            y1 = y2;
        }
        
        validate();
    }
}
