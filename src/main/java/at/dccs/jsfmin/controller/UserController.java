package at.dccs.jsfmin.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import at.dccs.jsfmin.api.UserService;
import at.dccs.jsfmin.entity.User;
import at.dccs.jsfmin.helper.SearchUserCriteria;

@Named
@SessionScoped
public class UserController implements Serializable {

  @Inject
  @EJB
  private UserService userService_;

  private SearchUserCriteria searchUserCriteria_;

  private List<User> users_;

  @PostConstruct
  public void init() {
    users_ = userService_.getAllUsers();
    searchUserCriteria_ = new SearchUserCriteria();
  }

  public List<User> getAllUsers() {
    return userService_.getAllUsers();
  }

  public SearchUserCriteria getSearchUserCriteria() {
    return searchUserCriteria_;
  }

  public void setSearchUserCriteria(SearchUserCriteria searchUserCriteria) {
    searchUserCriteria_ = searchUserCriteria;
  }

  public List<User> getUsers() {
    return users_;
  }

  public void setUsers(List<User> users) {
    users_ = users;
  }

  public void findUsers(){
    users_ = userService_.findUsers(searchUserCriteria_);
  }

  public void reset(){
    searchUserCriteria_.setFirstName(null);
    searchUserCriteria_.setLastName(null);
    searchUserCriteria_.setUserID(null);
    searchUserCriteria_.setDepartment(null);
  }

  public void deleteUser(User user){
    users_.remove(user);
  }

}
