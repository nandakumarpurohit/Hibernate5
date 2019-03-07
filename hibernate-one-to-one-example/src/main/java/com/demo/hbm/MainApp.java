package com.demo.hbm;

import java.time.LocalDate;
import java.time.Month;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.hbm.entity.User;
import com.demo.hbm.entity.UserDetail;

/**
 * @author vincent
 *
 */
public class MainApp {
   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.getTransaction();
         transaction.begin();

         User user = new User();
         user.setUsername("johnsmith");
         user.setPassword("jsmith");

         UserDetail userDetail = new UserDetail();
         userDetail.setFirstName("John");
         userDetail.setLastName("Smith");
         userDetail.setEmail("smith.john@example.com");
         userDetail.setDob(LocalDate.of(1985, Month.MAY, 1));
         userDetail.setUser(user);

         user.setUserDetail(userDetail);

         session.persist(user);
         transaction.commit();

         System.out.println("User saved successfully");

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
}
