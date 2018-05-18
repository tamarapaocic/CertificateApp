package at.dccs.jsfmin.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "userSequence", sequenceName = "user_seq", allocationSize = 1)
@Table(schema = "TAMARA", name = "Participant")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
  @Column(name = "userID", length = 8)
  private Integer userID_;

  @Basic
  @Column(name = "firstName", length = 50)
  private String firstName_;

  @Basic
  @Column(name = "lastName", length = 50)
  private String lastName_;

  @Basic
  @Column(name = "email", length = 50)
  private String email_;

  @ManyToOne
  @JoinColumn(name = "departmentID")
  private Department department_;

  @OneToMany(mappedBy = "user_")
  private List<CertificateUser> certificateUserList_;

  @OneToMany(mappedBy = "user_")
  private List<Comment> comments_;

  public User() {
  }

  public User(String firstName, String lastName) {
    this.firstName_ = firstName;
    this.lastName_ = lastName;
  }

  public User(Integer userID, String firstName_, String lastName_, String email_, Department department_) {
    this.userID_ = userID;
    this.firstName_ = firstName_;
    this.lastName_ = lastName_;
    this.email_ = email_;
    this.department_ = department_;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return userID_.equals(user.userID_);
  }

  @Override
  public int hashCode() {

    return userID_.hashCode();
  }

  public String getFirstName() {
    return firstName_;
  }

  public void setFirstName(String firstName) {
    this.firstName_ = firstName;
  }

  public String getLastName() {
    return lastName_;
  }

  public void setLastName(String lastName) {
    this.lastName_ = lastName;
  }

  public Integer getUserID() {
    return userID_;
  }

  public void setUserID(Integer userID_) {
    this.userID_ = userID_;
  }

  public String getEmail() {
    return email_;
  }

  public void setEmail(String email_) {
    this.email_ = email_;
  }

  public Department getDepartment() {
    return department_;
  }

  public void setDepartment(Department department_) {
    this.department_ = department_;
  }

}
