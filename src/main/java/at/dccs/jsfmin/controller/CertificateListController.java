package at.dccs.jsfmin.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import at.dccs.jsfmin.api.CertificateService;
import at.dccs.jsfmin.entity.Certificate;

@Named
@ViewScoped
public class CertificateListController implements Serializable {

  @Inject
  private CertificateService certificateService_;
  @Inject
  private CertificateController certificateController_;

  public List<Certificate> getAllCertificates() {
    return certificateService_.getAllCertificates();
  }

  public void deleteCertificate(Certificate certificate) {
    certificateService_.deleteCertificate(certificate);
  }

  public void makeNewCertificate() {
    certificateController_.initialize(null);
    redirect("addEditCertificate");
  }

  public void editCertificate(Certificate cert) {
    certificateController_.initialize(cert);
    redirect("addEditCertificate");
//    Integer certID = cert.getCertificateID();
//    String url = "addEditCertificate.xhtml?certificateId=" + certID + "&faces-redirect=true";
//    return url;
  }

  public void redirect(String page) {
    page = page + ".xhtml";
    try {
      FacesContext.getCurrentInstance().getExternalContext().redirect(page);
    } catch (IOException e) {
      System.out.print("Can not redirect to requested page.");
    }
  }

}
