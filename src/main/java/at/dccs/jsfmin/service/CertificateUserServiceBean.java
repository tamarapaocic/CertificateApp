package at.dccs.jsfmin.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;

import at.dccs.jsfmin.api.CertificateService;
import at.dccs.jsfmin.api.UserService;
import at.dccs.jsfmin.entity.Certificate;
import at.dccs.jsfmin.entity.CertificateUser;
import at.dccs.jsfmin.entity.User;

@Named
@ApplicationScoped
@Alternative
public class CertificateUserServiceBean implements Serializable {

  private List<CertificateUser> certificateUserList_;

  @Inject
  private UserService userService_;

  @Inject
  private CertificateService certificateService_;

  @PostConstruct
  private void initCertificateUser() {
    if (certificateUserList_ != null) {
      return;
    }
    certificateUserList_ = new ArrayList<CertificateUser>();
    certificateUserList_.add(new CertificateUser(certificateService_.getCertificateById(1), userService_.findUser(4)));
    certificateUserList_.add(new CertificateUser(certificateService_.getCertificateById(1), userService_.findUser(5)));
    certificateUserList_.add(new CertificateUser(certificateService_.getCertificateById(3), userService_.findUser(6)));
    certificateUserList_.add(new CertificateUser(certificateService_.getCertificateById(3), userService_.findUser(4)));
    certificateUserList_.add(new CertificateUser(certificateService_.getCertificateById(1), userService_.findUser(7)));

  }


  public List<CertificateUser> getCertificateUserList() {
    return certificateUserList_;
  }

  public void setCertificateUserList(List<CertificateUser> certificateUserList) {
    certificateUserList_ = certificateUserList;
  }

  public List<User> getUsersByCertificate(Certificate certificate) {
    List<User> results = new ArrayList<User>();
    for (CertificateUser certificateUser : certificateUserList_) {
      if (certificate.getCertificateID() == certificateUser.getCertificate().getCertificateID()) {
        results.add(certificateUser.getUser());
      }
    }
    return results;
  }

  public void deleteUserFromCertificate(User user, Certificate certificate) {
    for (CertificateUser certificateUser : certificateUserList_) {
      if (certificateUser.getUser().getUserID() == user.getUserID() && certificateUser.getCertificate()
              .getCertificateID() == certificate.getCertificateID()) {
        certificateUserList_.remove(certificateUser);
      }
    }
  }
}
