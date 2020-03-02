/**
 * CSCI1130 Assignment 5 Image Toonifier
 * Aim: Build an image processing app to convert PGM images to cartoon-like style ones using Java
 *      Practice using existing classes/objects and creating your own methods
 *      Practice file I/O, String, and 1D/2D array processing
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
 * Date        : 22/11/2019
 */ 
package imagetoonifier;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.ImageIcon;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ImageToonifier {

  private static String dialogIconImageFilename = "toonShaded.gif";
  private static boolean isPaletteReady = false, isEdgeReady = false;


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

  public void showWarning(String message) {
      JOptionPane.showConfirmDialog(null, message, "Warning", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE, null);
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
  public static int blend(double[][] arr1, int[][] arr2){
      int sum = 0;
      for (int i = 0; i < 3; i++){
          for (int j = 0; j < 3; j++){
              sum += arr1[i][j] * arr2[i][j];
          }
      }
      if (sum > 255) sum = 255;
      if (sum < 0) sum = 0;
      return sum;
  }
  
  public static int medianFilter(int[][] box){
    int[] list = new int[box.length*box[0].length];
    int listPos = 0;
    // iterate over the entire 2d array adding each integer
    for(int i = 0 ; i < box.length; i++) {
        for(int j = 0; j < box.length; j++) {
            list[listPos++] = box[i][j];
        }
    }
    // sort the list.
    Arrays.sort(list);
    int medianVal = median(list);
    return medianVal;
  }
  
  public static int median(int[] m) {
    int middle = m.length/2;
    if (m.length%2 == 1) {
        return m[middle];
    } else {
        return (m[middle-1] + m[middle]) / 2;
    }
}
  
  public static int[][] boxify(int ypos, int xpos, int[][] imageArray){
      int pixelVal;
      int[][] boxArr = new int[3][3];
      double[][] filterArr = new double[3][3];
      int flag1 = -1, flag2 = -1;
      
      for (int i = 0; i < 3; i++){
          for (int j = 0; j < 3; j++){
              filterArr[i][j] = 0.1;
          }
      }
      
      flag1 = -1;
      for (int i = 0; i < 3; i++){
          flag2 = -1;
          for (int j = 0; j < 3; j++){
              try {
                  boxArr[i][j] = imageArray[ypos + flag1][xpos + flag2];
              }
              catch (Exception e){
                  boxArr[i][j] = 0;
              }flag2++;
          }flag1++;          
      }
      return boxArr;
  }
  
  public static int invert(int i, int j, int[][] image){
      return (255 - image[i][j]);
  }
  
  public static int blur(int i, int j, int[][] image){
      double[][] filterArr = new double[3][3];
      for (int m = 0; m < 3; m++) {for(int n = 0; n < 3; n++) {filterArr[m][n] = 0.1;}}
      return (blend(filterArr, boxify(i, j, image)));
  }
  
  public static int laplace(int i, int j, int[][] image){
      double[][] filterArr = new double[3][3];
      for (int m = 0; m < 3; m++) {for(int n = 0; n < 3; n++) {filterArr[m][n] = -1;}}
      filterArr[1][1] = 8;
      return (blend(filterArr, boxify(i, j, image)));
  }
  
  public static PGM pallette(PGM img) throws IOException{
      int[][] imageArray = img.getImage();
      int imageWidth = img.getWidth();
      int imageHeight = img.getHeight();
      int[][] newImageArray = new int[imageHeight][imageWidth];
      PGM dummy = new PGM();
      
      for (int i = 0; i < imageHeight; i++){
          for (int j = 0; j < imageWidth; j++){
              newImageArray[i][j] = medianFilter(boxify(i, j, imageArray));
          }
      }
      dummy.write("pallette", newImageArray);
      return new PGM("pallette.pgm");
  }
  public static int mean(int i, int j, int[][] arr1, int[][] arr2){
      return ((arr1[i][j] + arr2[i][j]) / 2);
  }
  public static PGM edge(PGM img) throws IOException{
      int[][] imageArray = img.getImage();
      int imageWidth = img.getWidth();
      int imageHeight = img.getHeight();
      int[][] newImageArray = new int[imageHeight][imageWidth];
      PGM dummy = new PGM();
      
      for (int i = 0; i < imageHeight; i++){
          for (int j = 0; j < imageWidth; j++){
              newImageArray[i][j] = blur(i, j, imageArray);
          }
      }
      for (int i = 0; i < imageHeight; i++){
          for (int j = 0; j < imageWidth; j++){
              newImageArray[i][j] = laplace(i, j, imageArray);
              newImageArray[i][j] = invert(i, j, newImageArray);
          }
      }

      dummy.write("edge", newImageArray);
      return new PGM("edge.pgm");
  }
  
  public static PGM toonify(PGM palletteImg, PGM edgeImg) throws IOException{
      int[][] palletteArr = palletteImg.getImage();
      int[][] edgeArr = edgeImg.getImage();
      int imageWidth = palletteImg.getWidth();
      int imageHeight = palletteImg.getHeight();
      int[][] newImageArray = new int[imageHeight][imageWidth];
      PGM dummy = new PGM();
      
      for (int i = 0; i < imageHeight; i++){
          for (int j = 0; j < imageWidth; j++){
              newImageArray[i][j] = mean(i, j, palletteArr, edgeArr);
          }
      }
      dummy.write("toonified", newImageArray);
      return new PGM("toonified.pgm");
  }

  public static void main(String[] args) throws IOException {
    ImageToonifier toonifier = new ImageToonifier();
    PGM imgDefault, palletteFile, edgeFile, toonifiedFile;
    palletteFile = new PGM(); edgeFile = new PGM(); toonifiedFile = new PGM();
    imgDefault = new PGM();
    toonifier.showImageOnly("Simple PGM", imgDefault);
    
    PGM imgBlank;
    imgBlank = new PGM("Mid Gray", 40, 30);
    toonifier.showImageOnly("Blank PGM", imgBlank);
    
    String filename = "boat_316x316.pgm";
    PGM imgFileCurrent;
    imgFileCurrent = new PGM(filename);
    int choice = 0;
    PGM imgFile = imgFileCurrent;
    
    while (choice != 5){
        choice = toonifier.showImageWithMenu(imgFile);
        switch (choice) {
            case 0:
                filename = toonifier.getNameFromFileDialog();
                imgFile = new PGM(filename);
                isPaletteReady = false;
                isEdgeReady = false;
                break;
            case 1:
                palletteFile = pallette(imgFile);
                isPaletteReady = true;
                toonifier.showImageOnly("Pallette generated. Click OK to save.", palletteFile);
                break;
            case 2:
                edgeFile = edge(imgFile);
                isEdgeReady = true;
                toonifier.showImageOnly("Edge generated. Click OK to save.", edgeFile);
                break;
            case 3:
                if (isEdgeReady == true && isEdgeReady == true){
                    toonifiedFile = toonify(palletteFile, edgeFile);
                    toonifier.showImageOnly("Toonified image generated. Click OK to save.", toonifiedFile);
                    choice = 5;
                }
                else{
                    System.out.println("didnt work :c");
                    toonifier.showWarning("Pallete and Edge not ready yet");
                }   break;
            default:
                break;
        }
    }  
  }
}
