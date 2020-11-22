/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modosdeoperacion;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;

/**
 *
 * @author J.PEREZ
 */
public class DES {
    
    IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
    AlgorithmParameterSpec paramSpec = iv;

    //Recibe la ruta de la imagen, la contrasenia, y el modo de operacion.
    public byte[] cifrar(String rutaimagen, String contrasenia, int MO) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, Base64DecodingException, InvalidAlgorithmParameterException {
        
        BufferedImage im =ImageIO.read(new File(rutaimagen));
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(im, "bmp", bos);
        bos.flush();
            byte[] plano = bos.toByteArray();//img Cifrada
        bos.close();
        
        byte[] k = contrasenia.getBytes();//LLave

        byte[] cabecera = Arrays.copyOfRange(plano, 0, 54);
        byte[] paraCifrar = Arrays.copyOfRange(plano, 54, plano.length);     
        
        DESKeySpec ks = new DESKeySpec(k);
        SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
        Key ky = kf.generateSecret(ks);
        
        Cipher mo;
        switch(MO){
            case 0:
                mo = Cipher.getInstance("DES/ECB/PKCS5Padding");
                mo.init(Cipher.ENCRYPT_MODE, ky);
                break;
            case 1:
                mo = Cipher.getInstance("DES/CBC/PKCS5Padding");
                mo.init(Cipher.ENCRYPT_MODE, ky, paramSpec);
                break;
            case 2:
                mo = Cipher.getInstance("DES/CFB/PKCS5Padding");
                mo.init(Cipher.ENCRYPT_MODE, ky, paramSpec);
                break;
            default:
                mo = Cipher.getInstance("DES/OFB/PKCS5Padding");
                mo.init(Cipher.ENCRYPT_MODE, ky, paramSpec);
                break;
        }
        
        
        byte[] cifra = mo.doFinal(paraCifrar);
        
        System.out.println("Tamanio cifra: "+cifra.length);
        
        byte[] imagen = new byte[cabecera.length+cifra.length];//54 del encabezado
        
        System.arraycopy( cabecera, 0, imagen, 0, cabecera.length );
        System.arraycopy( cifra, 0, imagen, cabecera.length, cifra.length );
        
        System.out.println("Largo imagen cifrada[bytes]: "+imagen.length);

        
        
        ByteArrayInputStream img = new ByteArrayInputStream(imagen);
        BufferedImage Img = ImageIO.read(img);
        
        
    
        
        int indice = rutaimagen.lastIndexOf(".");
        StringBuffer rutanueva = new StringBuffer(rutaimagen);
        

        switch(MO){
            case 0:
                rutanueva.insert(indice, "_ECB");
                break;
            case 1:
                rutanueva.insert(indice, "_CBC");
                break;
            case 2:
                rutanueva.insert(indice, "_CFB");
                break;
            default:
                rutanueva.insert(indice, "_OFB");
                break;
        }
        String r = rutanueva.toString();
        //System.out.println("Ruta Imagen cifrada: "+rutanueva.toString());
        
        ImageIO.write(Img, "bmp", new File(r));
        return cifra;
    }

    public void descifrar(String rutaimagen, String contrasenia, int MO) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, Base64DecodingException, BadPaddingException, BadPaddingException, BadPaddingException, InvalidAlgorithmParameterException {
        
        BufferedImage  im = ImageIO.read(new File(rutaimagen));   
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        
        ImageIO.write(im,"bmp", bos);//Leer imagen cifrada
        bos.flush();
            byte[] cifra=bos.toByteArray();//Transformar a arreglo de bytes
        bos.close();
                
        System.out.println("Largo imagen cifrada[bytes]: "+cifra.length);
        
        byte[] k=contrasenia.getBytes();
        
        
        byte[] cabecera = Arrays.copyOfRange(cifra, 0, 54);
        byte[] paraDescifrar = Arrays.copyOfRange(cifra, 54, cifra.length);
        
       
        KeySpec ks=new DESKeySpec(k);
        SecretKeyFactory kf=SecretKeyFactory.getInstance("DES");
        SecretKey ky=kf.generateSecret(ks);

        Cipher decb;
        switch(MO){
            case 0:
                decb = Cipher.getInstance("DES/ECB/NoPadding");
                decb.init(Cipher.DECRYPT_MODE,ky);
                
                break;
            case 1:
                decb = Cipher.getInstance("DES/CBC/NoPadding");
                decb.init(Cipher.DECRYPT_MODE,ky, paramSpec);
                break;
            case 2:
                decb = Cipher.getInstance("DES/CFB/NoPadding");
                decb.init(Cipher.DECRYPT_MODE,ky, paramSpec);
                break;
            default:
                decb = Cipher.getInstance("DES/OFB/NoPadding");
                decb.init(Cipher.DECRYPT_MODE,ky, paramSpec);
                break;
        }
        
        byte [] plano = decb.doFinal(paraDescifrar); 
        
        byte [] imagen=new byte[cabecera.length+plano.length];
        

        System.arraycopy( cabecera, 0, imagen, 0, cabecera.length );
        System.arraycopy( plano, 0, imagen, cabecera.length, plano.length );
        
        
        System.out.println("Largo imagen descifrada: "+imagen.length);
        
        int indice = rutaimagen.lastIndexOf(".");
        StringBuffer rutanueva = new StringBuffer(rutaimagen);        
        switch(MO){
            case 0:
                rutanueva.insert(indice, "_dECB");
                break;
            case 1:
                rutanueva.insert(indice, "_dCBC");
                break;
            case 2:
                rutanueva.insert(indice, "_dCFB");
                break;
            default:
                rutanueva.insert(indice, "_dOFB");
                break;
        }
        String r = rutanueva.toString();
        
        System.out.println("Ruta Imagen descifrada: "+rutanueva.toString());
        
        
        
        
        ByteArrayInputStream bis = new ByteArrayInputStream(imagen);
        BufferedImage bImage2 = ImageIO.read(bis);
        
        ImageIO.write(bImage2, "bmp", new File(r) );
        bImage2.flush();
        System.out.println("Imagen descifrada creada");
        
    }

}
