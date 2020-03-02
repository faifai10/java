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

/**
 *
 * @author azoy
 */
public class PieSharing {
    // main method
    public static void main(String[] args) {
        
        PanelDisplay display = new PanelDisplay();
        // trial run
        for (int i = 1; i < 4; i++) {
            PieQuestion question = new PieQuestion("Trial " + i );
            String answer = question.getUserInputAnswer();
            if (question.checkAnswer(answer)) {
                display.updateHints(0, 1, ' ', 0, 1);
            }
            else if (!question.checkAnswer(answer)) {
                display.updateHints(question.num1, question.den1, question.oper, question.num2, question.den2);
                answer = question.getUserInputAnswer();
                if (question.checkAnswer(answer)) {
                    display.setScore(display.getScore()+1);
                    display.updateHints(0, 1, ' ', 0, 1);
                }
                else i--;
            }
        }
        
        // 10 question run
        for (int i = 0; i >= 0; i++){
            
            PieQuestion question = new PieQuestion("Q." + ( i + 1 ));
            String answer = question.getUserInputAnswer();
            // terminate if score 10
            if (display.getScore() == 10) System.exit(1); 
            if (question.checkAnswer(answer) && display.getScore() != 10) {
                display.setScore(display.getScore()+1);
                display.updateHints(0, 1, ' ', 0, 1);
            }
            else if (!question.checkAnswer(answer) && display.getScore() != 10) {
                display.updateHints(question.num1, question.den1, question.oper, question.num2, question.den2);
                answer = question.getUserInputAnswer();
                if (question.checkAnswer(answer)) {
                    display.setScore(display.getScore()+1);
                    display.updateHints(0, 1, ' ', 0, 1);
                }

            }
        }
        // attempt at terminating program
        display = null;        
    }
    
}
