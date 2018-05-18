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
public class CertificatesOverviewController implements Serializable {

  @Inject
  private CertificateService certificateService_;
  @Inject
  private CertificateFormController addEditCertificateController_;

  public List<Certificate> getAllCertificates() {
    return certificateService_.getAllCertificates();
  }

  public void deleteCertificate(Certificate certificate) {
    certificateService_.deleteCertificate(certificate);
  }

  /**
   * Method for making new certificate
   * and redirecting to appropriate page.
   */
  public void makeNewCertificate() {
    addEditCertificateController_.initialize(null);
    redirect("certificateForm");
  }

  /**
   * Method for editing selected certificate
   * and redirecting to appropriate page.
   *
   * @param certificate - selected certificate
   */
  public void editCertificate(Certificate certificate) {
    addEditCertificateController_.initialize(certificate);
    redirect("certificateForm");
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
