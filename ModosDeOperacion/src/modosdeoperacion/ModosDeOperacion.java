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
        ecb(img,pw);//Metodo que nos lleva a el modo de operacion respectivo
        cbc(img);//Metodo que nos lleva a el modo de operacion respectivo
        cfb(img);//Metodo que nos lleva a el modo de operacion respectivo
        ofb(img);//Metodo que nos lleva a el modo de operacion respectivo
        pw.print("\n");//Salto de linea para separar por linea el ancho de la imagen
        pw.close();//Se cierra el editor del txt
    }    

    private static void ecb(BufferedImage img, PrintWriter pw) throws IOException {
        BufferedImage ecba=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);//Se crea una imagen del tamaño de la original
        for(int i=0;i<img.getWidth();i++){
            for(int j=0;j<img.getHeight();j++){//Se inician los ciclos para recorrer la imagen pixel por pixel
                int rgb=img.getRGB(i,j);//Se  obtiene el codigo RGB del pixel actual
                Color color=new Color(rgb,true);//Se crea objeto para descomponer RGB del pixel en el que nos encontramos
                int r=color.getRed();//Se guarda en una var independiente cada color
                int g=color.getGreen();
                int b=color.getBlue();
                
                pw.print("["+r+","+g+","+b+"]");//Se experimenta enviar de esta forma el codigo rgb a un txt, lo mas probable es que se borre
                Color nuevo=new Color(100,80,170);//Se le asigna color cifrado a la nueva imagen
                ecba.setRGB(i, j, nuevo.getRGB());//Se le envia el color a la posicion correspondiente
            }}
        ImageIO.write(ecba, "bmp", new File("prueba2.bmp"));//Finalmente se crea la imagen ya completamente cifrada
    }

    private static void cbc(BufferedImage img) {
        
    }

    private static void cfb(BufferedImage img) {
        
    }

    private static void ofb(BufferedImage img) {
        
    }
}
