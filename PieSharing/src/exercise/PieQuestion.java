/**
 * CSCI1130 Assignment 3 PieSharing
 * Aim: Practice defining classes, as well as creating and using objects
 *      Practice random number generation
 *      Practice interacting with user via JOptionPane dialogs
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
 * Date        : 23/10/2019
 */ 

package exercise;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author azoy
 */
public class PieQuestion {
    
    // bunch of fields
    public String titl;
    public int num1;
    public int den1;
    public int num2;
    public int den2;
    public char oper;
    public int ans;
    public int den;
        
    
    // oneliner GCD function to reduce
    private int GCD(int a, int b) { return b==0 ? a : GCD(b, a%b); }
    
    public PieQuestion(String title) {
        
        this.titl= title;
        
        Random rngObj = new Random(System.currentTimeMillis());
        
        oper = rngObj.nextInt()%2 == 0 ? '+' : '-';
        // initiate values
        int a=1; int b = 1; int c=1; int d = 1;
        
        a = Math.abs(rngObj.nextInt()) % 7 + 1;
        while (b <= a) {
            b = Math.abs(rngObj.nextInt()) % 7 + 2;
        }
        // System.out.println((float)a / b);
        
        float frac = -10;
        
        // repeat until proper faction inputs
        while (frac <= 0 || frac >=1){
            a = Math.abs(rngObj.nextInt()) % 7 + 1;
            while (b <= a) {
                b = Math.abs(rngObj.nextInt()) % 7 + 2;
            }
            // System.out.println((float)a / b);
            c = Math.abs(rngObj.nextInt()) % 7 + 1;
            while ( d <= c ) {
                d = Math.abs(rngObj.nextInt()) % 8 + 1;
                // System.out.println((float)c/d);
            }
            
            if (oper == '+') {
                frac = ( (float) a / b) + ( (float) c / d);
            }
            else {
                frac = ( (float) a / b) - ( (float) c / d);
            }
            
            // System.out.println("fraction is : " + frac);
        }
        
        this.num1 = a / GCD(a, b); 
        this.den1 = b / GCD(a, b); 
        this.num2 = c / GCD(c, d); 
        this.den2 = d / GCD(c, d);
            
        int tempans; int tempden;
        tempden = den1 * den2;
        if (oper == '+') tempans = num1 * den2 + num2 * den1;
        else tempans = num1 * den2 - num2 * den1;
        this.den = tempden / GCD(tempans, tempden);
        this.ans = tempans / GCD(tempans, tempden);
        
        // System.out.println(num1 + ", " + den1 + ", " + oper + ", " + num2 + ", " + den2 + ", " + ans + ", " + den);
        
    }
    // automated
    public PieQuestion(String title, int a, int b, char operator, int c, int d) {
        this.titl = title;
        
        if (operator != '+' || operator != '-') {num1 = 1; den1 = 4; oper = '+'; num2 = 1; den2 = 2;}
        else if (b < 2 || b > 8) {num1 = 1; den1 = 4; oper = '+'; num2 = 1; den2 = 2;}
        else if (d < 2 || d > 8) {num1 = 1; den1 = 4; oper = '+'; num2 = 1; den2 = 2;}
        else if (a < 1 || a > 7) {num1 = 1; den1 = 4; oper = '+'; num2 = 1; den2 = 2;}
        else if (c < 1 || c > 7) {num1 = 1; den1 = 4; oper = '+'; num2 = 1; den2 = 2;}
        else {
            this.num1 = a / GCD(a, b); 
            this.den1 = b / GCD(a, b); 
            this.oper = operator; 
            this.num2 = c / GCD(c, d); 
            this.den2 = d / GCD(c, d);
        }
        
        int tempans; int tempden;
        
        tempden = den1 * den2;
        if (oper == '+') tempans = num1 * den2 + num2 * den1;
        else tempans = num1 * den2 - num2 * den1;
        den = tempden / GCD(tempans, tempden);
        ans = tempans / GCD(tempans, tempden);
    }
    
    public String getUserInputAnswer() {
        String prompt = this.titl + ": " + this.num1 + "/" + this.den1 + " " + this.oper + " " + this.num2 + "/" + this.den2 + " = ?/" + this.den;
        String answer = JOptionPane.showInputDialog(prompt);
        while (answer == null) {
            answer = JOptionPane.showInputDialog(prompt);
        }
        // System.out.println(this.ans);
        return answer;
    }
    
    public boolean checkAnswer(String parameter) {
        // System.out.println(this.ans == Integer.parseInt(parameter) ? "Correct" : this.ans);
        return this.ans == Integer.parseInt(parameter);
    }
}
