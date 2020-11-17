package modosdeoperacion;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;

public class ModosDeOperacion {
    public static void main(String[] args) throws IOException {
        String thunder="C:\\Users\\Christian\\Desktop\\Chris\\ESCOM\\Cryptography\\thundercats.bmp", 
                paisaje="C:\\Users\\Christian\\Desktop\\Chris\\ESCOM\\Cryptography\\paisaje.bmp",
                texto="C:\\Users\\Christian\\Desktop\\Chris\\ESCOM\\Cryptography\\Practica2\\thunder.txt";
        BufferedImage img=ImageIO.read(new File(thunder));
        FileWriter arc= new FileWriter(texto);
        PrintWriter pw=new PrintWriter(arc);
        BufferedImage ecba=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){
                int rgb=img.getRGB(i,j);
                Color color=new Color(rgb,true);
                int r=color.getRed();
                int g=color.getGreen();
                int b=color.getBlue();
                pw.print("["+r+","+g+","+b+"]");
                ecb(r,g,b);
                cbc(r,g,b);
                cfb(r,g,b);
                ofb(r,g,b);
                Color nuevo=new Color(((r*3)%256),((g*3)%256),((b*3)%256));
                ecba.setRGB(i, j, nuevo.getRGB());
            }
            pw.print("\n");
        }
        ImageIO.write(ecba, "bmp", new File("prueba.bmp"));
        pw.close();
    }    

    private static void ecb(int r, int g, int b) {
        
    }

    private static void cbc(int r, int g, int b) {
        
    }

    private static void cfb(int r, int g, int b) {
        
    }

    private static void ofb(int r, int g, int b) {
        
    }
}
