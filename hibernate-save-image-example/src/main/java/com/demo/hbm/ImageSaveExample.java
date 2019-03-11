package com.demo.hbm;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.BlobProxy;

import com.demo.hbm.entity.Product;

/**
 * @author vincent
 *
 */
public class ImageSaveExample {
   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.getTransaction();
         transaction.begin();
         
         Product product=new Product();
         product.setName("Java - The Complete Reference");
         session.doWork(conn->{
            product.setImage(BlobProxy.generateProxy(getImage()));
         });
         session.save(product);
         transaction.commit();
         
         System.out.println("Product is saved successfully.");
         
      } catch (Exception e) {
         if (transaction != null) {
            System.out.println("Transaction is being rolled back.");
            transaction.rollback();
         }
         e.printStackTrace();
      } finally {
         if (session != null) {
            session.close();
         }
      }

      HibernateUtil.shutdown();
   }

   public static byte[] getImage() {
      File file =new File("Java.png");
      if(file.exists()){
         try {
            BufferedImage bufferedImage=ImageIO.read(file);
            ByteArrayOutputStream byteOutStream=new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteOutStream);
            return byteOutStream.toByteArray();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      return null;
   }
}
