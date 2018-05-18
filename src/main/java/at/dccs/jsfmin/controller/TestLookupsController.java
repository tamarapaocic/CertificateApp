package at.dccs.jsfmin.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import at.dccs.jsfmin.entity.Supplier;
import at.dccs.jsfmin.entity.User;

@Named
@SessionScoped
public class TestLookupsController implements Serializable {

  private Supplier supplier_ = new Supplier();

  private List<User> users_;


  public Supplier getSupplier() {
    return supplier_;
  }

  public void setSupplier(Supplier supplier) {
    supplier_ = supplier;
  }

  public void resetSupplier() {
    supplier_ = null;
  }

  public List<User> getUsers() {
    return users_;
  }

  public void setUsers(List<User> users) {
    users_ = users;
  }

  public void deleteUser(User user) {
    users_.remove(user);
  }
}
