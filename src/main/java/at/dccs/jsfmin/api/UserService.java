package at.dccs.jsfmin.api;

import java.io.Serializable;
import java.util.List;

import at.dccs.jsfmin.entity.User;
import at.dccs.jsfmin.helper.SearchUserCriteria;

public interface UserService extends Serializable {

  List<User> getAllUsers();

  User findUser(int userId);

  List<User> findUsers(SearchUserCriteria criteria);

}
