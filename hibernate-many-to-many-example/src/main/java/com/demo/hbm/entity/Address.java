package com.demo.hbm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author vincent
 *
 */
@Entity
@Table(name = "ADDRESS")
public class Address {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ADDR_ID")
   private long id;

   @Column(name = "CITY")
   private String city;

   @Column(name = "STATE")
   private String state;

   @Column(name = "COUNTRY")
   private String country;

   @Column(name = "ZIP")
   private String zip;

   @ManyToMany(fetch=FetchType.LAZY, mappedBy = "addresses")
   private List<Employee> employees = new ArrayList<>();

   public Address() {  }
   
   public Address(String city, String state, String country, String zip) {
      this.city = city;
      this.state = state;
      this.country = country;
      this.zip = zip;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getState() {
      return state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getCountry() {
      return country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public String getZip() {
      return zip;
   }

   public void setZip(String zip) {
      this.zip = zip;
   }

   public List<Employee> getEmployees() {
      return employees;
   }

   public void setEmployees(List<Employee> employees) {
      this.employees = employees;
   }
}
