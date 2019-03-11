package com.demo.hbm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.hbm.entity.Product;

/**
 * @author vincent
 *
 */
public class ImageLoadExample {
   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.getTransaction();
         transaction.begin();

         Product product = session.get(Product.class, 7L);
         System.out.println("Product Name: "+product.getName());
         
         InputStream imgStream = product.getImage().getBinaryStream();
         saveImage(imgStream);

         transaction.commit();
      } catch (Exception e) {
         if (transaction != null) {
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

   public static void saveImage(InputStream stream) {
      File file = new File("output.png");
      try(FileOutputStream outputStream = new FileOutputStream(file)) {
         BufferedImage bufferedImage = ImageIO.read(stream);
         ImageIO.write(bufferedImage, "png", outputStream);
         System.out.println("Image file location: "+file.getCanonicalPath());
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
