package modosdeoperacion;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.imageio.ImageIO;

public class ModosDeOperacion {
    
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeyException, InvalidKeySpecException, InvalidKeySpecException, IllegalBlockSizeException, IllegalBlockSizeException, BadPaddingException, Base64DecodingException {
        String thunder="C:\\Users\\J. PEREZ\\Desktop\\thundercats.bmp", 
            paisaje="C:\\Users\\J. PEREZ\\Desktop\\paisaje.bmp";
        
        BufferedImage img=ImageIO.read(new File(thunder));
        ecb(img);//Metodo que nos lleva a el modo de operacion respectivo
        decb(img);
        //cbc(img);//Metodo que nos lleva a el modo de operacion respectivo
        //cfb(img);//Metodo que nos lleva a el modo de operacion respectivo
        //ofb(img);//Metodo que nos lleva a el modo de operacion respectivo
        //Ek obj=new Ek();
        //obj.cifrado();
    }    

    private static void ecb(BufferedImage im) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, Base64DecodingException {
        String contraseña="uncifrado";
        ByteArrayOutputStream bos =new ByteArrayOutputStream();
        ImageIO.write(im, "bmp", bos);
        bos.flush();
        String base64=Base64.encode(bos.toByteArray());
        bos.close();
        byte[] text=base64.getBytes();
        byte[] k=contraseña.getBytes();
        byte[] m =new byte[(text.length)-53];
        for(int i=54,j=0;i<text.length;i++,j++){
            m[j]=text[i];
        }
       
        KeySpec ks=new DESKeySpec(k);
        SecretKeyFactory kf=SecretKeyFactory.getInstance("DES");
        SecretKey ky=kf.generateSecret(ks);
        Cipher ecb= Cipher.getInstance("DES/ECB/PKCS5Padding");
        ecb.init(Cipher.ENCRYPT_MODE,ky);
        byte [] cifra=ecb.doFinal(m);
        
        byte [] imagen=new byte[cifra.length+54];
        text=Base64.decode(base64);
        for(int i=0,j=0;i<imagen.length;i++)
            if(i<54)
                imagen[i]=text[i];
            else{
                imagen[i]=cifra[j];
                j++;
            }
        System.out.println(text.length+"    "+cifra.length);
        
        
        BufferedImage imag=ImageIO.read(new ByteArrayInputStream(imagen));
        ImageIO.write(imag,"bmp",new File("Prueba.bmp"));
        }
    
    private static void decb(BufferedImage im) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, Base64DecodingException, BadPaddingException, BadPaddingException, BadPaddingException {
        String contraseña="uncifrado";
        ByteArrayOutputStream bos =new ByteArrayOutputStream();
        ImageIO.write(im,"bmp", bos);
        bos.flush();
        String base64=Base64.encode(bos.toByteArray());
        bos.close();
        byte[] text=base64.getBytes();
        byte[] k=contraseña.getBytes();
        byte[] m =new byte[(text.length)-53];
        for(int i=54,j=0;i<text.length;i++,j++){
            m[j]=text[i];
        }
       
        KeySpec ks=new DESKeySpec(k);
        SecretKeyFactory kf=SecretKeyFactory.getInstance("DES");
        SecretKey ky=kf.generateSecret(ks);
        Cipher ecb= Cipher.getInstance("DES/ECB/PKCS5Padding");
        ecb.init(Cipher.ENCRYPT_MODE,ky);
        byte [] cifra=ecb.doFinal(m);
        
        
        Cipher decb = Cipher.getInstance("DES/ECB/NoPadding");
        decb.init(Cipher.DECRYPT_MODE,ky);
        byte [] descifra=decb.doFinal(cifra);
        
        byte [] imagen=new byte[cifra.length+54];
        text=Base64.decode(base64);
        for(int i=0,j=0;i<imagen.length;i++)
            if(i<54)
                imagen[i]=text[i];
            else{
                imagen[i]=descifra[j];
                j++;
            }
        
        BufferedImage imag=ImageIO.read(new ByteArrayInputStream(text));
        ImageIO.write(imag,"bmp",new File("Prueba2.bmp"));
    }
    private static void cbc(BufferedImage img) {
        
    }
    private static void cfb(BufferedImage img) {
        
    }
    private static void ofb(BufferedImage img) {
        
    }

    
}
