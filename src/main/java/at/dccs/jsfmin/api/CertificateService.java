package at.dccs.jsfmin.api;

import java.io.Serializable;
import java.util.List;

import at.dccs.jsfmin.entity.Certificate;
import at.dccs.jsfmin.entity.Comment;
import at.dccs.jsfmin.entity.User;

public interface CertificateService extends Serializable {

  void deleteCertificate(Certificate certificate);

  void updateCertificate(Certificate selectedCertificate);

  Certificate getCertificateById(Integer certificateID);

  List<Certificate> getAllCertificates();

  List<String> getAllCertificateTypes();
}
