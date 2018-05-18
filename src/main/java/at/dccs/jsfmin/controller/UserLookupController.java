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
public class UserLookupController implements Serializable {

  @Inject
  private UserService userService_;

  private SearchUserCriteria searchUserCriteria_;

  @PostConstruct
  public void init() {
    searchUserCriteria_ = new SearchUserCriteria();
  }

  /**
   * Method for calling user service method which retrieves all users from database.
   *
   * @return List of users
   */
  public List<User> getAllUsers() {
    return userService_.getAllUsers();
  }

  /**
   * Method for calling user service method and passing it search criteria based on which
   * user from database will be retrieved.
   */
  public void findUsers() {
    userService_.findUsers(searchUserCriteria_);
  }

  public void reset() {
    searchUserCriteria_.setFirstName(null);
    searchUserCriteria_.setLastName(null);
    searchUserCriteria_.setUserID(null);
    searchUserCriteria_.setDepartment(null);
  }

  public SearchUserCriteria getSearchUserCriteria() {
    return searchUserCriteria_;
  }

  public void setSearchUserCriteria(SearchUserCriteria searchUserCriteria) {
    searchUserCriteria_ = searchUserCriteria;
  }

}
