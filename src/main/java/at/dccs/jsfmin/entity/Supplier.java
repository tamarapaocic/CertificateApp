package at.dccs.jsfmin.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "supplierSequence", sequenceName = "supplier_seq", allocationSize = 1)
@Table(schema = "TAMARA", name = "Supplier")
public class Supplier {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplierSequence")
  @Column(name = "supplierID", length = 8)
  private Integer supplierID_;

  @Basic
  @Column(name = "supplierName", length = 40)
  private String supplierName_;

  @Basic
  @Column(name = "city", length = 30)
  private String city_;

  @OneToMany(mappedBy = "supplier_")
  private List<Certificate> certificates_;

  public Supplier() {
  }

  public Supplier(int supplierID, String supplierName, String city) {
    this.supplierID_ = supplierID;
    this.supplierName_ = supplierName;
    this.city_ = city;
  }

  public Integer getSupplierID() {
    return supplierID_;
  }

  public void setSupplierID(Integer supplierID) {
    this.supplierID_ = supplierID;
  }

  public String getSupplierName() {
    return supplierName_;
  }

  public void setSupplierName(String supplierName) {
    this.supplierName_ = supplierName;
  }

  public String getCity() {
    return city_;
  }

  public void setCity(String city) {
    this.city_ = city;
  }

  public List<Certificate> getCertificates() {
    return certificates_;
  }

  public void setCertificates(List<Certificate> certificates) {
    certificates_ = certificates;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Supplier supplier = (Supplier) o;
    return supplierID_.equals(supplier.supplierID_) && supplierName_.equals(supplier.supplierName_) && city_.equals
            (supplier.city_);
  }

  @Override
  public int hashCode() {
    return supplierID_.hashCode() + supplierName_.hashCode();
  }
}
