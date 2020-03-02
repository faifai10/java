/**
 * CSCI1130 Introduction to Computing Using Java
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
 *   http://www.cuhk.edu.hk/policy/academichonesty/
 *
 * Student Name : xxx <fill in yourself>
 * Student ID   : xxx <fill in yourself>
 * Class/Section: xxx <fill in yourself>
 * Date         : xxx <fill in yourself>
 */
package imagetoonifier;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ypchui
 */
public class ImageToonifier {

  private static String dialogIconImageFilename = "toonShaded.gif";
  private static boolean isPaletteReady = false, isEdgeReady = false;

  /**
   * Show a menu of choices and get user's input
   *
   * @return an integer value: -1 means Quit, and options 0, 1, 2, 3
   */
  public int showImageWithMenu(PGM imgPGM) {

    String menuHTML = "<html>";
    menuHTML += "Please pick an action:<hr>";
    menuHTML += "0. Load a New Image<br>";
    menuHTML += "1. Generate Palette & Save<br>";
    menuHTML += "2. Generate Edge & Save<br>";
    menuHTML += "3. Blend \"Palette + Edge\", Save & Quit<br>";
    menuHTML += "<br>";
    menuHTML += "Palette ready? " + (isPaletteReady ? "Yes" : "No") + "<br>";
    menuHTML += "Edge ready?    " + (isEdgeReady ? "Yes" : "No") + "<br>";
    menuHTML += "</html>";
    String[] options = {"Load New", "Gen Palette & Save", "Gen Edge & Save", "Blend, Save & Quit"};

    int w = imgPGM.getWidth();
    int h = imgPGM.getHeight();
    int image[][] = {{0}};
    BufferedImage img;

    if (h <= 0 || w <= 0 || imgPGM.getImage() == null) {
      JOptionPane.showConfirmDialog(null, "Width x Height = " + imgPGM.getWidth() + "x" + imgPGM.getHeight(), " corrupted!", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE, null);
      w = h = 1;
    } else {
      image = imgPGM.getImage();
    }
    
    img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

    for (int row = 0; row < h; row++) {
      for (int col = 0; col < w; col++) {
        img.setRGB(col, row, new Color(image[row][col], image[row][col], image[row][col]).getRGB());
      }
    }

    ImageIcon icon = new ImageIcon(img);
    int choice = JOptionPane.showOptionDialog(null, menuHTML, this.getClass().getSimpleName(), 0, 0, icon, options, null);

    System.out.println("Choice: " + choice);
    return choice;

  }

  // Show image on screen
  public void showImageOnly(String title, PGM imgPGM) {
    if (imgPGM.getHeight() <= 0 || imgPGM.getWidth() <= 0 || imgPGM.getImage() == null) {
      JOptionPane.showConfirmDialog(null, "Width x Height = " + imgPGM.getWidth() + "x" + imgPGM.getHeight(), " corrupted!", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE, null);
      return;
    }

    BufferedImage img = new BufferedImage(imgPGM.getWidth(), imgPGM.getHeight(), BufferedImage.TYPE_INT_RGB);

    int image[][] = imgPGM.getImage();

    for (int row = 0; row < imgPGM.getHeight(); row++) {
      for (int col = 0; col < imgPGM.getWidth(); col++) {
        img.setRGB(col, row, new Color(image[row][col], image[row][col], image[row][col]).getRGB());
      }
    }
    JOptionPane.showConfirmDialog(null, "", title, JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(img));
  }

  // Show a file dialog and get user's input of filename
  public static String getNameFromFileDialog() {
    JFrame frame = new JFrame(); // frame for file dialog

    FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
    fd.setDirectory(".");
    fd.setFile("*.pgm");  // for Windows
    fd.setFilenameFilter(new FilenameFilter() { // for MacOS
          @Override
          public boolean accept(File dir, String name) {
              return name.endsWith(".pgm");
          }
      });
    fd.setVisible(true);
    String filename = fd.getFile();
    fd.dispose();
    frame.dispose();
    return filename;
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    // Write your code here



    // End of your code
  }
}
