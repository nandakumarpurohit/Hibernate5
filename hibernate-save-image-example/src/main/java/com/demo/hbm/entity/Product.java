package com.demo.hbm.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vincent
 *
 */
@Entity
@Table(name = "PRODUCT")
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID")
   private long id;

   @Column(name = "NAME")
   private String name;

   @Column(name = "IMAGE")
   private Blob image;

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Blob getImage() {
      return image;
   }

   public void setImage(Blob image) {
      this.image = image;
   }
}
