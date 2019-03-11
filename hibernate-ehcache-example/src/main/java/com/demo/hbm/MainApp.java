package com.demo.hbm;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.hbm.entity.Person;


/**
 * @author vincent
 *
 */
public class MainApp {

   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {
    	  
    	  	  Session session0 = HibernateUtil.getSessionFactory().openSession();
          Transaction t1 = session0.beginTransaction();
          Person p1 = new Person();
          p1.setId(2L);
          p1.setName("James");
          session0.save(p1);
          t1.commit();
          session0.close();
        
         //Retrieve the person object from database
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.getTransaction();
         transaction.begin();
         Person person=session.get(Person.class, 1L);
         System.out.println(person);
         transaction.commit();
         session.close();
         
         //Retrieve the person object from cache
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.getTransaction();
         transaction.begin();
         Person person2=session.get(Person.class, 1L);
         System.out.println(person2);
         transaction.commit();
         session.close();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      
      HibernateUtil.shutdown();
   }
}
