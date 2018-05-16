package at.dccs.jsfmin.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import at.dccs.jsfmin.api.CertificateUserService;
import at.dccs.jsfmin.entity.Certificate;
import at.dccs.jsfmin.entity.CertificateUser;
import at.dccs.jsfmin.entity.User;

@Named
@SessionScoped
public class CertificateUserController implements Serializable {

  private List<CertificateUser> certificateUsers_;

  @Inject
  @EJB
  private CertificateUserService certificateUserService_;

  public List<User> getUsersByCertificate(Certificate certificate) {
    return certificateUserService_.getUsersByCertificate(certificate);
  }

  public void deleteUserFromCertificate(User user, Certificate certificate) {
    certificateUserService_.deleteUserFromCertificate(user, certificate);
  }

  public List<CertificateUser> getCertificateUsers() {
    return certificateUsers_;
  }

  public void setCertificateUsers(List<CertificateUser> certificateUsers) {
    certificateUsers_ = certificateUsers;
  }


}
