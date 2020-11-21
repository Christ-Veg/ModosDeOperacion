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
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.imageio.ImageIO;

/**
 *
 * @author J.PEREZ
 */
public class DES {

    //Recibe la ruta de la imagen, la contrasenia, y el modo de operacion.
    public void cifrar(String rutaimagen, String contrasenia, int MO) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, Base64DecodingException {
        
        BufferedImage  im=ImageIO.read(new File(rutaimagen));
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(im, "bmp", bos);
        bos.flush();
        String base64 = Base64.encode(bos.toByteArray());
        bos.close();
        byte[] text = base64.getBytes();
        byte[] k = contrasenia.getBytes();
        byte[] m = new byte[(text.length) - 53];
        for (int i = 54, j = 0; i < text.length; i++, j++) {
            m[j] = text[i];
        }

        KeySpec ks = new DESKeySpec(k);
        SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
        SecretKey ky = kf.generateSecret(ks);
        
        Cipher mo;
        switch(MO){
            case 0:
                mo = Cipher.getInstance("DES/ECB/PKCS5Padding");
                break;
            case 1:
                mo = Cipher.getInstance("DES/CBC/PKCS5Padding");
                break;
            case 2:
                mo = Cipher.getInstance("DES/CFB/PKCS5Padding");
                break;
            default:
                mo = Cipher.getInstance("DES/OFB/PKCS5Padding");
                break;
        }
        
        mo.init(Cipher.ENCRYPT_MODE, ky);
        byte[] cifra = mo.doFinal(m);

        byte[] imagen = new byte[cifra.length + 54];
        text = Base64.decode(base64);
        for (int i = 0, j = 0; i < imagen.length; i++) {
            if (i < 54) {
                imagen[i] = text[i];
            } else {
                imagen[i] = cifra[j];
                j++;
            }
        }
        System.out.println(text.length + "    " + cifra.length);

        BufferedImage imag = ImageIO.read(new ByteArrayInputStream(imagen));
        
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
        System.out.println("Imagen cifrada: "+rutanueva.toString());
        ImageIO.write(imag, "bmp", new File(r));
        
    }

    public void descifrar(String rutaimagen, String contrasenia, int MO) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, Base64DecodingException, BadPaddingException, BadPaddingException, BadPaddingException {
        
        BufferedImage  im=ImageIO.read(new File(rutaimagen));
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(im, "bmp", bos);
        bos.flush();
        String base64 = Base64.encode(bos.toByteArray());
        bos.close();
        
        byte[] text = base64.getBytes();
        byte[] k = contrasenia.getBytes();
        byte[] cifra = new byte[(text.length) - 53];
        for (int i = 54, j = 0; i < text.length; i++, j++) {
            cifra[j] = text[i];
        }

        KeySpec ks = new DESKeySpec(k);
        SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
        SecretKey ky = kf.generateSecret(ks);   
        
        Cipher decb;
        switch(MO){
            case 0:
                decb = Cipher.getInstance("DES/ECB/NoPadding");
                break;
            case 1:
                decb = Cipher.getInstance("DES/CBC/NoPadding");
                break;
            case 2:
                decb = Cipher.getInstance("DES/CFB/NoPadding");
                break;
            default:
                decb = Cipher.getInstance("DES/OFB/NoPadding");
                break;
        }
                
        decb.init(Cipher.DECRYPT_MODE, ky);
        byte[] descifra = decb.doFinal(cifra);

        byte[] imagen = new byte[cifra.length + 54];
        text = Base64.decode(base64);
        for (int i = 0, j = 0; i < imagen.length; i++) {
            if (i < 54) {
                imagen[i] = text[i];
            } else {
                imagen[i] = descifra[j];
                j++;
            }
        }

        
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
        System.out.println("Imagen descifrada: "+rutanueva.toString());
        
        BufferedImage imag = ImageIO.read(new ByteArrayInputStream(text));
        ImageIO.write(imag, "bmp", new File(r));
    }

}
