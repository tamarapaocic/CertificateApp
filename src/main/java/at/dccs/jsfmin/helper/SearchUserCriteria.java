package at.dccs.jsfmin.helper;

import java.io.Serializable;
import java.util.List;

import at.dccs.jsfmin.entity.Department;
import at.dccs.jsfmin.entity.User;


public class SearchUserCriteria implements Serializable {

  private Integer userID_;
  private String firstName_;
  private String lastName_;
  private String email_;
  private Department department_ = new Department();
  private List<User> users_;

  public List<User> getUsers() {
    return users_;
  }

  public void setUsers(List<User> users) {
    users_ = users;
  }

  public Integer getUserID() {
    return userID_;
  }

  public void setUserID(Integer userID) {
    userID_ = userID;
  }

  public String getFirstName() {
    return firstName_;
  }

  public void setFirstName(String firstName) {
    firstName_ = firstName;
  }

  public String getLastName() {
    return lastName_;
  }

  public void setLastName(String lastName) {
    lastName_ = lastName;
  }

  public String getEmail() {
    return email_;
  }

  public void setEmail(String email) {
    email_ = email;
  }

  public Department getDepartment() {
    return department_;
  }

  public void setDepartment(Department department) {
    department_ = department;
  }
}
