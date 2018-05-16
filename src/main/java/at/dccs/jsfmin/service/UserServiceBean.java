package at.dccs.jsfmin.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;

import at.dccs.jsfmin.api.UserService;
import at.dccs.jsfmin.entity.Department;
import at.dccs.jsfmin.entity.User;
import at.dccs.jsfmin.helper.IdProducer;
import at.dccs.jsfmin.helper.SearchUserCriteria;

@Named
@ApplicationScoped
@Alternative
public class UserServiceBean implements Serializable, UserService {

  private List<User> users_;
  @Inject
  private IdProducer idProducer_;


  @PostConstruct
  private void initUser() {
    Department department1 = new Department(idProducer_.generateId(), "ITM/FP", 96);
    Department department2 = new Department(idProducer_.generateId(), "ITM/FP", 94);
    Department department3 = new Department(idProducer_.generateId(), "ITM/FP", 92);
    users_ = new ArrayList<User>();
    users_.add(new User(idProducer_.generateId(), "Doris", "Laco", "doriis@gnail.com", department1));
    users_.add(new User(idProducer_.generateId(), "Martina", "Andrejas", "martina@yahoo.com", department2));
    users_.add(new User(idProducer_.generateId(), "Mirela", "Dzindo", "mirela.dz@email.com", department3));
    users_.add(new User(idProducer_.generateId(), "Vanesa", "Tomic", "vane@email.com", department1));
    users_.add(new User(idProducer_.generateId(), "Azur", "Odobasic", "azur.osobasic@email.com", department3));
    users_.add(new User(idProducer_.generateId(), "Emir", "Jahic", "emir@email.com", department3));
  }

  public List<User> getAllUsers() {
    return users_;
  }

  public User findUser(int userId) {
    for (User user : users_) {
      if (user.getUserID() == userId) {
        return user;
      }
    }
    return null;
  }

  public void deleteUser(User user) {
    users_.remove(user);
  }

  @Override
  public List<User> findUsers(SearchUserCriteria criteria) {
    return null;
  }
}
