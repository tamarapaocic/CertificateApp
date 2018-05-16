package at.dccs.jsfmin.api;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import at.dccs.jsfmin.entity.Certificate;
import at.dccs.jsfmin.entity.User;

public interface CertificateUserService extends Serializable {

  List<User> getUsersByCertificate(Certificate certificate);

  void deleteUserFromCertificate(User user, Certificate certificate);
}
