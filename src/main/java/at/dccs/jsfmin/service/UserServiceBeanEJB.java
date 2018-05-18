package at.dccs.jsfmin.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import at.dccs.jsfmin.api.UserService;
import at.dccs.jsfmin.entity.User;
import at.dccs.jsfmin.helper.SearchUserCriteria;

@Stateless
public class UserServiceBeanEJB implements Serializable, UserService {

  @PersistenceContext
  private EntityManager entityManager_;

  /**
   * Method for retrieving all users from database
   *
   * @return list of users
   */
  @Override
  public List<User> getAllUsers() {
    CriteriaBuilder cb = entityManager_.getCriteriaBuilder();
    CriteriaQuery<User> q = cb.createQuery(User.class);
    Root<User> c = q.from(User.class);
    q.select(c);
    q.orderBy(cb.asc(c.get("firstName_")));
    TypedQuery<User> tq = entityManager_.createQuery(q);
    return tq.getResultList();
  }

  /**
   * Method for finding user from database based on user ID.
   *
   * @param userID ID of user to be find
   * @return user
   */
  @Override
  public User findUser(int userID) {
    return entityManager_.find(User.class, userID);
  }

  /**
   * Method for finding users from database based on search criteria
   *
   * @param formCriteria search criteria
   * @return list of users
   */
  @Override
  public List<User> findUsers(SearchUserCriteria formCriteria) {

    CriteriaBuilder cb = entityManager_.getCriteriaBuilder();
    CriteriaQuery<User> u = cb.createQuery(User.class);
    Root<User> user = u.from(User.class);
    u.select(user);

    List<Predicate> criteria = new ArrayList<Predicate>();

    if (!isBlank(formCriteria.getFirstName())) {
      criteria.add(cb.like(cb.lower(user.<String>get("firstName_")), fullLike(formCriteria.getFirstName().toLowerCase
              ())));
    }
    if (!isBlank(formCriteria.getLastName())) {
      criteria.add(cb.like(cb.lower(user.<String>get("lastName_")), fullLike(formCriteria.getFirstName().toLowerCase
              ())));
    }
    if (formCriteria.getUserID() != null) {
      criteria.add(cb.equal(user.get("userID_"), formCriteria.getUserID()));
    }
    if (!isBlank(formCriteria.getDepartment().getDepartmentName().toLowerCase())) {
      criteria.add(cb.like(cb.lower(user.get("department_").<String>get("departmentName_")), formCriteria
              .getDepartment().getDepartmentName().toLowerCase()));
    }
    if (formCriteria.getDepartment().getPlant() != null) {
      criteria.add(cb.equal(user.get("department_").<Integer>get("plant_"), formCriteria.getUserID()));
    }
    u.where(criteria.toArray(new Predicate[]{}));

    TypedQuery<User> q = entityManager_.createQuery(u);

    return q.getResultList();
  }

  private String fullLike(String string) {
    return "%" + string + "%";
  }

  private boolean isBlank(String string) {
    return string == null || string.isEmpty();
  }
}
