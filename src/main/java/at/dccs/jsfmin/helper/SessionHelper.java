package at.dccs.jsfmin.helper;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import at.dccs.jsfmin.api.UserService;
import at.dccs.jsfmin.entity.User;
import at.dccs.jsfmin.service.UserServiceBean;

@Named
@SessionScoped
public class SessionHelper implements Serializable {

  @Inject
  private UserService userService_;

  private User currentUser_;

  @PostConstruct
  private void init() {
    List<User> users = userService_.getAllUsers();
    setCurrentUser(users.get(0));
  }

  public User getCurrentUser() {
    return currentUser_;
  }

  public void setCurrentUser(User currentUser) {
    currentUser_ = currentUser;
  }
}
