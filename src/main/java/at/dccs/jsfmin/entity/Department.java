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
@SequenceGenerator(name = "departmentSequence", sequenceName = "department_seq", allocationSize = 1)
@Table(schema = "TAMARA", name = "Department")
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departmentSequence")
  @Column(name = "departmentID", length = 8)
  private Integer departmentID_;

  @Basic
  @Column(name = "departmentName", length = 50)
  private String departmentName_;

  @Basic
  @Column(name = "plant")
  private Integer plant_;

  @OneToMany(mappedBy = "department_")
  private List<User> users_;

  public Department(Integer departmentID_, String departmentName_, Integer plant_) {
    this.departmentID_ = departmentID_;
    this.departmentName_ = departmentName_;
    this.plant_ = plant_;
  }

  public Department() {
  }

  public List<User> getUsers() {
    return users_;
  }

  public void setUsers(List<User> users) {
    users_ = users;
  }

  public Integer getDepartmentID() {
    return departmentID_;
  }

  public void setDepartmentID(Integer departmentID_) {
    this.departmentID_ = departmentID_;
  }

  public String getDepartmentName() {
    return departmentName_;
  }

  public void setDepartmentName(String departmentName_) {
    this.departmentName_ = departmentName_;
  }

  public Integer getPlant() {
    return plant_;
  }

  public void setPlant(Integer plant_) {
    this.plant_ = plant_;
  }
}
