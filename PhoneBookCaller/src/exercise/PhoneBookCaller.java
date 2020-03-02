/**
 * CSCI1130 Assignment 4 Phone Book Caller
 * Aim: Build a practical phone book application using Java
 *      Practice using existing classes and objects
 *      Practice creating our own methods and sending messages
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
 * Date        : 06/11/2019
 */ 

package exercise;

import java.util.HashMap;
import javax.swing.*;
import java.io.*;
import java.lang.*;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.decoder.JavaLayerException;


public class PhoneBookCaller {
    
    private static String dialogQuestionMarkIconImageFilename = "question-mark-1451232961b8W_from_publicdomainpictures.net_en_images=142577.gif";
    private HashMap<String, String> phoneBook; // a String-String mapping type
    
    public PhoneBookCaller(){
        clearAndSetupDefaultPhoneBook();
    }
    
    private void clearAndSetupDefaultPhoneBook() {         
    // drops, if any, existing phone book and creating a new one         
        phoneBook = new HashMap<>();         
        addContact("Rocky", "39437000");         
        addContact("Kitty", "852-87654321");         
        addContact("Betty", "(853) 91239123");         
        addContact("Donald", "(86)-90018900");         
        addContact("Roy", "+886 51903481");         
        addContact("Shelly", "85271237890");     
    }     
    /**      
     * Show all contacts in the phone book on System.out in some order AND      
     * @return a String representation of the phone book in a HTML table      
     **/     
     
    public String showAllContacts() {         
        String table = "<table>";
        table += "<tr>";         
        table += "<td>Name<td>Phone";         
        table += "</tr>";
        // for-each: iterating all records in the phoneBook which is a HashMap         
        System.out.printf("%-20s %s\n", "Name", "Phone"); 
        for (String name : phoneBook.keySet()) {             
            // String name will loop through all names (keys)             
            String phone = phoneBook.get(name);             
            table += "<tr>";             
            table += "<td>" + name + "<td>" + phone;             
            table += "</tr>";
            System.out.printf("%-20s %s\n", name, phone ); 
        }         
        table += "</table><hr>";         
        System.out.printf("-------------------- --------------\n");         
        /*** student's work here to print the phone book on System.out ***/         
        return table;  
    }
        // a String representation of the phone book in HTML     }     
        /**      * Show a menu of choices and get user's input      
         * @return an integer value: 0 means Quit, and options 1, 2, ...      
         */     
    public int showMenu()     {         
        String menuHTML = "<html>";
        menuHTML += showAllContacts();         
        menuHTML += "Please pick an action:<hr>";         
        menuHTML += "0. Quit<br>";         
        menuHTML += "1. Add contact<br>";         
        menuHTML += "2. Make a call<br>";         
        menuHTML += "3. Clear ALL contact<br>";         
        menuHTML += "4. Save ALL contact<br>";         
        menuHTML += "</html>";         
        String[] options = {"Quit", "Add", "Call", "Clear", "Save"};         
        ImageIcon icon = new ImageIcon(dialogQuestionMarkIconImageFilename);         
        int choice = JOptionPane.showOptionDialog(null, menuHTML, this.getClass().getSimpleName(), 0, 0, icon, options, null);         
        if (choice == JOptionPane.CLOSED_OPTION) 
        // CLOSED_OPTION = -1             
            choice = 0;         
        System.out.println("Choice: " + choice);         
        return choice;     
    }     
    public void addContact(String name, String phone) {
        phoneBook.put(name.toUpperCase(), phone);
    }     
    
    public void call(String name) throws JavaLayerException, FileNotFoundException { 
        // When calling a number, "dial" each digit by playing the corresponding given MP3 recording file: "DTMF_DialTone_MP3/DTMF-n.mp3" where n is a digit in 0 – 9.    }     
        for (char theChar: phoneBook.get(name).toCharArray()){
            if (Character.isDigit(theChar)){
                playMP3File("DTMF_DialTone_MP3/DTMF-" + theChar + ".mp3");
            }
        }
    }
    
    public void loadPhoneBook(){
        phoneBook.clear();
        addContact("Rocky", "39437000");         
        addContact("Kitty", "852-87654321");         
        addContact("Betty", "(853) 91239123");         
        addContact("Donald", "(86)-90018900");         
        addContact("Roy", "+886 51903481");         
        addContact("Shelly", "85271237890");
    }
    
    public void savePhoneBook(String filename) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(filename)) {
            phoneBook.keySet().forEach((name) -> {
                // String name will loop through all names (keys)
                String phone = phoneBook.get(name);
                writer.print(name + "\n" + phone + "\n");
            });
        }
    }     
    
    public static void playMP3File(String filename) throws JavaLayerException, FileNotFoundException {                      
        FileInputStream mp3Stream = new FileInputStream(filename);             
        AdvancedPlayer  mp3Player = new AdvancedPlayer(mp3Stream);             
        mp3Player.play();     
    } 
    
    public static void main(String[] args) throws JavaLayerException, FileNotFoundException {
        PhoneBookCaller pbk = new PhoneBookCaller();
        playMP3File("DTMF_DialTone_MP3/DialTone.mp3");
        pbk.showAllContacts();
        int choice = pbk.showMenu();
        while (choice != 0){
            switch (choice) {
                case 1:
                    {
                        String name = JOptionPane.showInputDialog("Name?");
                        String number = JOptionPane.showInputDialog("Number?");
                        pbk.addContact(name, number);
                        break;
                    }
                case 2:
                    {
                        String name = JOptionPane.showInputDialog("Name?");
                        pbk.call(name.toUpperCase());
                        break;
                    }
                case 3:
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Are you Sure?","Warning",dialogButton);
                    if (dialogResult == JOptionPane.YES_OPTION){
                        pbk.loadPhoneBook();
                    }   break;
                case 4:
                    pbk.savePhoneBook("phonebook.txt");
                    break;
                default:
                    break;
            }
        choice = pbk.showMenu();
        }    
    }
}