/**
 * CSCI1130 Assignment 2 Future Value
 * Aim: To implement future value calculation for growing asset by writing a Java console application.
 *      To practise the use of variables/expression,looping/branching statements and basic Math functions.
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
 * Date        : 09/10/2019
 */ 

package exercise;

import java.util.Scanner;
import java.lang.*;

/**
 *
 * @author azoy
 */
public class FutureValue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        
        // validating each of the inputs, using double for each variable to avoid lossy calculations
        System.out.println("Input Principal [$10000.00 -$109700.00]: ");
        double principal = scan.nextDouble();
        while (true){
            if (principal<10000.00 || principal >109700.00){
                System.out.println("Principal out of range. Try again.");
                principal = scan.nextDouble();
            }
            else{
                break;
            }
        }
        
        System.out.println("Input Annual Interest Rate [1.0% -10.0%]: ");
        double rate = scan.nextDouble();
        while (true){
            if (rate<1 || rate >10){
                System.out.println("Interest rate out of range. Try again.");
                rate = scan.nextDouble();
            }
            else{
                break;
            }
        }
        
        System.out.println("Input Timespan [2-10 years]: ");
        double T = scan.nextDouble();
        while (true){
            if (T<2.0 || T >10.0){
                System.out.println("Timespan out of range. Try again.");
                T = scan.nextDouble();
            }
            else{
                break;
            }
        }
        
        System.out.println("Input Compounding Period [1, 6 or 12 months]: ");
        double compoundFrequency = scan.nextDouble();
        while (true){
            if (compoundFrequency==1.0 || (compoundFrequency ==6.0 || compoundFrequency==12.0)){
                compoundFrequency = 12/compoundFrequency;
                break;
            }
            else{
                System.out.println("Out of range. Try again.");
                compoundFrequency = scan.nextDouble();
            }
        }
        
        // input's done
        // checking if the timespan (in months) is exactly more than the first and second installments. the relevent message is printed
        if (T * 12 > (12 /compoundFrequency) * 1){
            if ((12 /compoundFrequency) * 1 == 1){
                System.out.printf("Future Value after %.0f month: %.2f\n", (12 /compoundFrequency) * 1, (principal * Math.pow((1 + ((rate/100) / compoundFrequency)), 1)));
            }
            else {
                System.out.printf("Future Value after %.0f months: %.2f\n", (12 /compoundFrequency) * 1, (principal * Math.pow((1 + ((rate/100) / compoundFrequency)), 1)));

            }
        }
        if (T * 12 > (12 /compoundFrequency) * 2){
            System.out.printf("Future Value after %.0f months: %.2f\n", (12 /compoundFrequency) * 2, (principal * Math.pow((1 + ((rate/100) / compoundFrequency)), 2)));
        }            
        
        if (T * 12 > (12 /compoundFrequency) * 3){
            System.out.println("...");
        }
        
        // future value calculation
        // System.out.printf("Principal value: %f rate: %f time: %f compound: %f", principal, rate, T, compoundFrequency);
        double fv = (principal * Math.pow((1 + ((rate/100) / compoundFrequency)), (T * compoundFrequency)));
        System.out.printf("Future Value at the end: %.2f\n", fv);
        
        // tried to simplify and also declutter this calculation
        double divisor = compoundFrequency * Math.log(1 + ((rate/100) / compoundFrequency));
        
        System.out.printf("Time to obstain asset x2: %.2f years\n", Math.log(2) / divisor);
        System.out.printf("Time to obstain asset x3: %.2f years\n", Math.log(3) / divisor);
        System.out.printf("Time to obstain asset x4: %.2f years\n", Math.log(4) / divisor);
    }
}
