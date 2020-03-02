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

/**
 * QuadraticFunction is a type of Function
 * A QuadraticFunction object is an instanceof Function
 * @author pffung
 */
public class QuadraticFunction implements Function {
    
    public double A, B, C;
    
    public QuadraticFunction(double A, double B, double C)
    {
        this.A = A;
        this.B = B;
        this.C = C;
        System.out.println("Constructing a new " + getClass().getSimpleName() + " object " + this);
        System.out.println("This is an instance of Function: " + (this instanceof Function));
    }
    
    @Override
    public String toString()
    {
        return String.format("%f^2x%+fx%+f", A, B, C);
    }
    
    @Override
    public double valueAt(double x)
    {
        return A*x*x + B*x + C;
    }
}
