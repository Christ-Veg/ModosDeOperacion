/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modosdeoperacion;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author J.PEREZ
 */
public class Inicio extends javax.swing.JFrame {

    /**
     * Creates new form Inicio
     */
    DES des = new DES();
    
    //byte[] c0, c1, c2, c3;
    

    Boolean imagencargada = false; //Bandera para saber si ya se cargo una imagen
    String rutaimagen = "";//Ruta de la imagen
    int cECB = 0, cCBC = 0, cCFB = 0, cOFB = 0;
    int dECB = 0, dCBC = 0, dCFB = 0, dOFB = 0;

    public Inicio() {
        initComponents();

        agregaListenerCheckBox();
        agregaListenerRadios();
    }

    public void agregaListenerCheckBox() {
        cbECB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    System.out.println("ECB Seleccionado ");
                    cECB = 1;
                } else {
                    System.out.println("ECB Deseleccionado");
                    cECB = 0;
                }
            }
        });
        cbCBC.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    System.out.println("CBC Seleccionado ");
                    cCBC = 1;
                } else {
                    System.out.println("CBC Deseleccionado");
                    cCBC = 0;
                }
            }
        });
        cbCFB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    System.out.println("CFB Seleccionado ");
                    cCFB = 1;
                } else {
                    System.out.println("CFB Deseleccionado");
                    cCFB = 0;
                }
            }
        });
        cbOFB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    System.out.println("OFB Seleccionado ");
                    cOFB = 1;
                } else {
                    System.out.println("OFB Deseleccionado");
                    cOFB = 0;
                }
            }
        });
    }

    public void agregaListenerRadios() {
        rbECB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    System.out.println("ECB Seleccionado ");
                    dECB = 1;
                } else {
                    System.out.println("ECB Deseleccionado");
                    dECB = 0;
                }
            }
        });
        rbCBC.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    System.out.println("CBC Seleccionado ");
                    dCBC = 1;
                } else {
                    System.out.println("CBC Deseleccionado");
                    dCBC = 0;
                }
            }
        });
        rbCFB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    System.out.println("CFB Seleccionado ");
                    dCFB = 1;
                } else {
                    System.out.println("CFB Deseleccionado");
                    dCFB = 0;
                }
            }
        });
        rbOFB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    System.out.println("OFB Seleccionado ");
                    dOFB = 1;
                } else {
                    System.out.println("OFB Deseleccionado");
                    dOFB = 0;
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MOGroup = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbCBC = new javax.swing.JCheckBox();
        cbCFB = new javax.swing.JCheckBox();
        cbECB = new javax.swing.JCheckBox();
        cbOFB = new javax.swing.JCheckBox();
        CifrarBtn = new javax.swing.JButton();
        ContraCtxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        rbCBC = new javax.swing.JRadioButton();
        rbCFB = new javax.swing.JRadioButton();
        rbOFB = new javax.swing.JRadioButton();
        rbECB = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        ContraDtxt = new javax.swing.JTextField();
        DescifrarBtn = new javax.swing.JButton();
        CargarImagenBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblRuta = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Source Code Pro", 0, 10)); // NOI18N
        setName("AGENCIA DE CIFRADO ESCOM"); // NOI18N

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setFont(new java.awt.Font("Source Code Pro", 0, 36)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Source Code Pro", 0, 36)); // NOI18N
        jLabel4.setText("Selecciona el modo de operacion:");

        cbCBC.setFont(new java.awt.Font("Source Code Pro Light", 0, 36)); // NOI18N
        cbCBC.setLabel("Cipher Block Chaining (CBC)");

        cbCFB.setFont(new java.awt.Font("Source Code Pro Light", 0, 36)); // NOI18N
        cbCFB.setLabel("Cipher Feedback (CFB)");

        cbECB.setFont(new java.awt.Font("Source Code Pro Light", 0, 36)); // NOI18N
        cbECB.setLabel("Electronic Codebook (ECB)");

        cbOFB.setFont(new java.awt.Font("Source Code Pro Light", 0, 36)); // NOI18N
        cbOFB.setLabel("Output Feedback (OFB)");

        CifrarBtn.setFont(new java.awt.Font("Source Code Pro", 0, 36)); // NOI18N
        CifrarBtn.setText("Cifrar Imagen");
        CifrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CifrarBtnActionPerformed(evt);
            }
        });

        ContraCtxt.setFont(new java.awt.Font("Source Code Pro Black", 0, 36)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Source Code Pro", 0, 36)); // NOI18N
        jLabel1.setText("Contraseña:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ContraCtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(CifrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbECB)
                            .addComponent(cbCBC)
                            .addComponent(cbCFB)
                            .addComponent(cbOFB))
                        .addComponent(jLabel4)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbECB)
                .addGap(18, 18, 18)
                .addComponent(cbCBC)
                .addGap(18, 18, 18)
                .addComponent(cbCFB)
                .addGap(18, 18, 18)
                .addComponent(cbOFB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ContraCtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CifrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        jTabbedPane1.addTab("CIFRADO", jPanel2);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Source Code Pro", 0, 36)); // NOI18N
        jLabel6.setText("Selecciona el modo de operacion:");

        MOGroup.add(rbCBC);
        rbCBC.setFont(new java.awt.Font("Source Code Pro Light", 0, 36)); // NOI18N
        rbCBC.setText("Cipher Block Chaining (CBC)");

        MOGroup.add(rbCFB);
        rbCFB.setFont(new java.awt.Font("Source Code Pro Light", 0, 36)); // NOI18N
        rbCFB.setText("Cipher Feedback (CFB)");

        MOGroup.add(rbOFB);
        rbOFB.setFont(new java.awt.Font("Source Code Pro Light", 0, 36)); // NOI18N
        rbOFB.setText("Output Feedback (OFB)");

        MOGroup.add(rbECB);
        rbECB.setFont(new java.awt.Font("Source Code Pro Light", 0, 36)); // NOI18N
        rbECB.setText("Electronic Codeboock (ECB)");

        jLabel7.setFont(new java.awt.Font("Source Code Pro", 0, 36)); // NOI18N
        jLabel7.setText("Contraseña:");

        ContraDtxt.setFont(new java.awt.Font("Source Code Pro Black", 0, 36)); // NOI18N

        DescifrarBtn.setFont(new java.awt.Font("Source Code Pro", 0, 36)); // NOI18N
        DescifrarBtn.setText("Descifrar Imagen");
        DescifrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DescifrarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ContraDtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                                .addComponent(DescifrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbCBC)
                            .addComponent(rbECB)
                            .addComponent(rbCFB)
                            .addComponent(rbOFB))))
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(rbECB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbCBC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbCFB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbOFB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(ContraDtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DescifrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        jTabbedPane1.addTab("DESCIFRADO", jPanel1);

        CargarImagenBtn.setFont(new java.awt.Font("Source Code Pro", 0, 36)); // NOI18N
        CargarImagenBtn.setText("Cargar Imagen");
        CargarImagenBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarImagenBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Source Code Pro Black", 1, 48)); // NOI18N
        jLabel2.setText("CIFRADOR DES");

        lblRuta.setFont(new java.awt.Font("Source Code Pro Light", 0, 36)); // NOI18N
        lblRuta.setText("Nungun archivo seleccionado.");

        jLabel5.setFont(new java.awt.Font("Source Code Pro Semibold", 1, 48)); // NOI18N
        jLabel5.setText("DATA ENCRYPTION STANDARD");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CargarImagenBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblRuta))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(488, 488, 488)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(331, 331, 331)
                        .addComponent(jLabel5)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CargarImagenBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRuta))
                .addGap(63, 63, 63)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("CIFRADO");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CargarImagenBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarImagenBtnActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagenes", "jpg");

        JFileChooser f = new JFileChooser();
        f.setFileFilter(filtro);
        f.setMultiSelectionEnabled(false);

        int r = f.showOpenDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {

            rutaimagen = f.getSelectedFile().getPath();//Ruta de la imagen

            String nombreimagen = f.getSelectedFile().getName();
            lblRuta.setText(nombreimagen);

            imagencargada = true;
        } else {
            imagencargada = false;
        }
    }//GEN-LAST:event_CargarImagenBtnActionPerformed

    private void CifrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CifrarBtnActionPerformed

        String contrasenia = ContraCtxt.getText();

        if (imagencargada == false) {//Verificar que se ha cargado una imagen
            JOptionPane.showMessageDialog(null,
                    "Debes cargar una imagen", "INFO",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if ((cECB + cCBC + cCFB + cOFB) == 0) {//Verificar seleecion de MO
            JOptionPane.showMessageDialog(null,
                    "Debes seleccionar un Modo de Operacion", "INFO",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (contrasenia.length() != 8) {//Verificar el tam de contrasenia
            JOptionPane.showMessageDialog(null,
                    "La contrasena debe tener longitud de 8", "INFO",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                if (cECB == 1) //CIFRADO DES/ ECB
                    des.cifrar(rutaimagen, contrasenia, 0);
                if (cCBC == 1) //CIFRADO DES/ CBC
                    des.cifrar(rutaimagen, contrasenia, 1);
                if (cCFB == 1) //CIFRADO DES/ CFB
                    des.cifrar(rutaimagen, contrasenia, 2);
                if (cOFB == 1) //CIFRADO DES/ OFB
                    des.cifrar(rutaimagen, contrasenia, 3);

                JOptionPane.showMessageDialog(null,
                        "La imagen se cifro correctamente.", "EXITO",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Base64DecodingException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidAlgorithmParameterException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_CifrarBtnActionPerformed

    private void DescifrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DescifrarBtnActionPerformed
        // TODO add your handling code here:
        String contrasenia = ContraDtxt.getText();

        if (imagencargada == false) {//Verificar que se ha cargado una imagen
            JOptionPane.showMessageDialog(null,
                    "Debes cargar una imagen", "INFO",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if ((dECB + dCBC + dCFB + dOFB) == 0) {//Verificar seleecion de MO
            JOptionPane.showMessageDialog(null,
                    "Debes seleccionar un Modo de Operacion", "INFO",
                    JOptionPane.INFORMATION_MESSAGE);

        } else if (contrasenia.length() != 8) {//Verificar el tam de contrasenia
            JOptionPane.showMessageDialog(null,
                    "La contrasena debe tener longitud de 8", "INFO",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                if (dECB == 1)//DESCIFRADO DES/ ECB
                    des.descifrar(rutaimagen, contrasenia, 0);
                if (dCBC == 1) //DESCIFRADO DES/ CBC
                    des.descifrar(rutaimagen, contrasenia, 1);
                if (dCFB == 1) //DESCIFRADO DES/ CFB
                     des.descifrar(rutaimagen, contrasenia, 2);
                if (dOFB == 1)//DESCIFRADO DES/OFB
                     des.descifrar(rutaimagen, contrasenia, 3);
                
                JOptionPane.showMessageDialog(null,
                    "La imagen se descifro correctamente.", "EXITO",
                    JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Base64DecodingException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidAlgorithmParameterException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_DescifrarBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CargarImagenBtn;
    private javax.swing.JButton CifrarBtn;
    private javax.swing.JTextField ContraCtxt;
    private javax.swing.JTextField ContraDtxt;
    private javax.swing.JButton DescifrarBtn;
    private javax.swing.ButtonGroup MOGroup;
    private javax.swing.JCheckBox cbCBC;
    private javax.swing.JCheckBox cbCFB;
    private javax.swing.JCheckBox cbECB;
    private javax.swing.JCheckBox cbOFB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblRuta;
    private javax.swing.JRadioButton rbCBC;
    private javax.swing.JRadioButton rbCFB;
    private javax.swing.JRadioButton rbECB;
    private javax.swing.JRadioButton rbOFB;
    // End of variables declaration//GEN-END:variables
}
