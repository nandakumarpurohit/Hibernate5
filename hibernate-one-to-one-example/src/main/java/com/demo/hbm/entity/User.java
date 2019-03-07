package com.demo.hbm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author vincent
 *
 */
@Entity
@Table(name = "USERS")
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "USR_ID")
   private long id;

   @Column(name = "USERNAME", nullable = false, unique = true)
   private String username;

   @Column(name = "PASSWORD")
   private String password;

   @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
   private UserDetail userDetail;

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public UserDetail getUserDetail() {
      return userDetail;
   }

   public void setUserDetail(UserDetail userDetail) {
      this.userDetail = userDetail;
   }
}
