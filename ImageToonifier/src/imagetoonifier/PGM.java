package imagetoonifier;

import java.io.*;
import java.util.*;
import java.lang.*;

public class PGM {
    private String imageName;
    private int width, height;
    private int maxValue;
    private int[][] image;
    
    public PGM(){
        imageName = "Simple";
        width = 2;
        height = 3;
        maxValue = 255;
        image  = new int[height][width];
        image[0][0] = image[1][1] = 255;
        image[0][1] = image[2][0] = 127;
        image[1][0] = image[2][1] = 0;
    }
    
    public PGM(String name, int w, int h){
        imageName = name;
        width = w;
        height = h;
        maxValue = 255;
        image  = new int[height][width];
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                image[i][j] = 127;
            }
        }
    }
    
    public PGM(String filename){
        this.imageName = filename;
        read(filename);
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int[][] getImage() { return image; }
    
    public void read(String filename){
        try {
            File f = new File(filename);
            Scanner reader = new Scanner(f);
            String header = reader.nextLine();
            
            if (header == null || header.length() < 2 ||
                    header.charAt(0) != 'P' || header.charAt(1) != '2') {
                throw new Exception("Wrong PGM Header");
            }
           
            do {
                header = reader.nextLine();
            } while (header.charAt(0) == '#');
        
            Scanner readStr = new Scanner(header);
            width = readStr.nextInt();
            height = readStr.nextInt();
            
            maxValue = reader.nextInt();
            image  = new int[height][width];
            for (int i = 0 ; i < height; i++){
                for (int j = 0; j < width; j++){
                    image[i][j] = reader.nextInt();
                }
            }
        } catch(Exception e){
           System.out.println(e);                    
        }
    }
    
    public void write(String filename, int[][]image) throws IOException{
        File f = new File(filename + ".pgm");
        if (!f.exists()) {
                f.createNewFile();
            }
        FileWriter fw = new FileWriter(f.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.append("P2\n");
        bw.append(image[0].length + " " + image.length + "\n");
        bw.append("255\n");
        
        for (int i = 0; i < image.length; i++){
            for (int j = 0; j < image[0].length; j++){
                bw.append(" ");
                bw.append(Integer.toString(image[i][j]));
            }
            bw.append("\n");
        }
    }
    
    public void write(String filename) throws IOException{
        File f = new File(filename + ".pgm");
        if (!f.exists()) {
                f.createNewFile();
            }
        FileWriter fw = new FileWriter(f.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.append("P2\n");
        bw.append(this.image[0].length + " " + this.image.length + "\n");
        bw.append("255\n");
        
        for (int i = 0; i < this.height; i++){
            for (int j = 0; j < this.width; j++){
                bw.append(" ");
                bw.append(Integer.toString(this.image[i][j]));
            }
            bw.append("\n");
        }
    }
    
}