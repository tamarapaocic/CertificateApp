package at.dccs.jsfmin.helper;

import java.io.Serializable;


public class SearchSupplierCriteria implements Serializable {

  private Integer supplierID_;
  private String supplierName_;
  private String city_;

  public SearchSupplierCriteria() {
  }

  public SearchSupplierCriteria(Integer supplierID, String supplierName, String city) {
    supplierID_ = supplierID;
    supplierName_ = supplierName;
    city_ = city;
  }

  public Integer getSupplierID() {
    return supplierID_;
  }

  public void setSupplierID(Integer supplierID) {
    supplierID_ = supplierID;
  }

  public String getSupplierName() {
    return supplierName_;
  }

  public void setSupplierName(String supplierName) {
    supplierName_ = supplierName;
  }

  public String getCity() {
    return city_;
  }

  public void setCity(String city) {
    city_ = city;
  }


}
