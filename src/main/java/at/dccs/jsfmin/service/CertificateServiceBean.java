package at.dccs.jsfmin.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;

import at.dccs.jsfmin.api.CertificateService;
import at.dccs.jsfmin.entity.Certificate;
import at.dccs.jsfmin.entity.Comment;
import at.dccs.jsfmin.entity.Supplier;
import at.dccs.jsfmin.entity.User;
import at.dccs.jsfmin.helper.IdProducer;

@Named
@ApplicationScoped
@Alternative
public class CertificateServiceBean implements Serializable, CertificateService {

  private List<Certificate> certificates_;
  private List<String> certificateTypes_;

  @Inject
  private IdProducer idProducer_;

  @PostConstruct
  private void initCertificate() {
    if (certificates_ != null) {
      return;
    }
    certificates_ = new ArrayList<Certificate>();
    certificates_.add(new Certificate(idProducer_.generateId(), new Supplier(idProducer_.generateId(), "DAIMLER AG",
            "Berlin"), "OHSAS 180001", dateFormat("02/02/2017"), dateFormat("05/05/2020")));
    certificates_.add(new Certificate(idProducer_.generateId(), new Supplier(idProducer_.generateId(), "ANDEMIS " +
            "GmbH", "Stuttgart"), "CCC", dateFormat("03/04/2018"), dateFormat("07/05/2019")));
    certificateTypes_ = new ArrayList<String>();
    certificateTypes_.add("OHSAS 180001");
    certificateTypes_.add("CCC");
    certificateTypes_.add("Permission of printing");
  }

  public Date dateFormat(String s) {
    SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    Date date;
    try {
      date = sd.parse(s);
      return date;
    } catch (ParseException e) {
    }
    return null;
  }

  public List<Certificate> getAllCertificates() {
    return certificates_;
  }

  public List<String> getAllCertificateTypes() {
    return certificateTypes_;
  }

  public Certificate addNewCertificate(Certificate certificate) {
    certificate.setCertificateID(idProducer_.generateId());
    certificates_.add(certificate);
    return certificate;
  }


  public void deleteCertificate(Certificate certificate) {
    certificates_.remove(certificate);
  }

  public void updateCertificate(Certificate selectedCertificate) {
    for (Certificate certificate : certificates_) {
      if (certificate.getCertificateID() == selectedCertificate.getCertificateID()) {
        certificate = selectedCertificate;
      }
    }
  }


  public Certificate getCertificateById(Integer certificateID) {
    Certificate result = new Certificate();
    for (Certificate certificate : certificates_) {
      if (certificate.getCertificateID() == certificateID) {
        result = certificate;
      }
    }
    return result;
  }


}

