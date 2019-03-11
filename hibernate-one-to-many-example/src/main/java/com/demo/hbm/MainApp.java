package com.demo.hbm;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.hbm.entity.Department;
import com.demo.hbm.entity.Employee;


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

         Department department = new Department();
         department.setName("IT Department");

         Employee employee1 = new Employee();
         employee1.setName("Adam");
         employee1.setDesignation("Manager");
         employee1.setDepartment(department);

         Employee employee2 = new Employee();
         employee2.setName("Miller");
         employee2.setDesignation("Software Engineer");
         employee2.setDepartment(department);

         Employee employee3 = new Employee();
         employee3.setName("Smith");
         employee3.setDesignation("Associate  Engineer");
         employee3.setDepartment(department);

         department.getEmployees().add(employee1);
         department.getEmployees().add(employee2);
         department.getEmployees().add(employee3);

         session.persist(department);

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
}
