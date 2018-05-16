package at.dccs.jsfmin.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import at.dccs.jsfmin.api.CertificateUserService;
import at.dccs.jsfmin.entity.Certificate;
import at.dccs.jsfmin.entity.CertificateUser;
import at.dccs.jsfmin.entity.User;

@Stateless
public class CertificateUserServiceBeanEJB implements Serializable, CertificateUserService {

  @PersistenceContext
  private EntityManager entityManager_;

  @Override
  public List<User> getUsersByCertificate(Certificate certificate) {
    List<User> users = new ArrayList<User>();
    Integer id = certificate.getCertificateID();
    Certificate cert = entityManager_.find(Certificate.class, id);

    if (cert == null) {
      return users;
    }

    List<CertificateUser> certUser = cert.getCertificateUserList();
    for (CertificateUser cu : certUser) {
      users.add(cu.getUser());
    }
    return users;
  }

  @Override
  public void deleteUserFromCertificate(User user, Certificate certificate) {

    String query = "DELETE FROM CertificateUser cu WHERE cu.certificate_.certificateID_ = ?1 AND cu.user_.userID_ = ?2";
    entityManager_.createQuery(query, CertificateUser.class).setParameter(1, certificate.getCertificateID())
            .setParameter(2, user.getUserID()).executeUpdate();
  }


}

