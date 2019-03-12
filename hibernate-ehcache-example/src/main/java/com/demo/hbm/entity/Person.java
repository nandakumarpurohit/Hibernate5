package com.demo.hbm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * @author vincent
 *
 */
@Entity
@Table(name = "PERSONS")
@Cache(region = "personCache", usage = CacheConcurrencyStrategy.READ_ONLY)
public class Person implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue
   @Column(name = "ID")
   private Long id;

   @Column(name = "NAME")
   private String name;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return "Customer [id=" + id + ", name=" + name + "]";
   }
}
