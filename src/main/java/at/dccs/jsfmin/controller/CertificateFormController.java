package at.dccs.jsfmin.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import at.dccs.jsfmin.api.CertificateService;
import at.dccs.jsfmin.entity.Certificate;
import at.dccs.jsfmin.entity.Comment;
import at.dccs.jsfmin.entity.User;
import at.dccs.jsfmin.helper.SessionHelper;

@Named
@SessionScoped
public class CertificateFormController implements Serializable {

  private Certificate selectedCertificate_;
  private String commentText_;
  private boolean commentAreaVisible_;

  @Inject
  private CertificateService certificateService_;
  @Inject
  private SessionHelper sessionHelper_;

  /**
   * Method for initializing new or existing certificate.
   *
   * @param certificate - this is certificate that is selected
   */
  public void initialize(Certificate certificate) {
    if (certificate == null) {
      selectedCertificate_ = new Certificate();
    } else {
      selectedCertificate_ = certificateService_.getCertificateById(certificate.getCertificateID());
    }
  }

  /**
   * Method for updating certificate in database
   * with accurate data.
   */
  public void updateCertificate() {
    certificateService_.updateCertificate(selectedCertificate_);
  }

  /**
   * Method for saving new/edited certificate
   * and redirecting to certificatesOverview page.
   */
  public void saveCertificate() {
    updateCertificate();
    redirect("certificatesOverview");
  }

  /**
   * Method for adding comment on selected certificate comment's list
   * and assigning user to comment.
   */
  public void addComment() {
    Comment comment = new Comment();
    comment.setComment(commentText_);
    comment.setUser(sessionHelper_.getCurrentUser());
    if (selectedCertificate_.getComments() == null) {
      selectedCertificate_.setComments(new ArrayList<Comment>());
    }
    selectedCertificate_.getComments().add(comment);
    hideCommentArea();
  }


  /**
   * Method for resetting selected certificate values to null.
   */
  public void reset() {
    selectedCertificate_ = new Certificate();
    selectedCertificate_.setSupplier(null);
    selectedCertificate_.setCertificateType(null);
    selectedCertificate_.setValidFrom(null);
    selectedCertificate_.setValidTo(null);
    selectedCertificate_.setUsers(null);
    commentAreaVisible_ = false;
    selectedCertificate_.setComments(null);
    setCommentText(null);
  }

  public void redirect(String page) {
    page = page + ".xhtml";
    try {
      FacesContext.getCurrentInstance().getExternalContext().redirect(page);
    } catch (IOException e) {
      System.out.print("Can not redirect to requested page.");
    }
  }

  public void resetSupplier() {
    selectedCertificate_.setSupplier(null);
  }


  public void deleteUserFromCertificate(User user) {
    selectedCertificate_.getUsers().remove(user);
  }

  public void deleteCommentFromCertificate(Comment comment) {
    selectedCertificate_.getComments().remove(comment);
  }


  public Certificate getSelectedCertificate() {
    return selectedCertificate_;
  }

  public void setSelectedCertificate(Certificate selectedCertificate) {
    this.selectedCertificate_ = selectedCertificate;
  }

  public List<String> getAllCertificateTypes() {
    return certificateService_.getAllCertificateTypes();
  }

  public String getCommentText() {
    return commentText_;
  }

  public void setCommentText(String commentText) {
    commentText_ = commentText;
  }

  public boolean isVisible() {
    return commentAreaVisible_;
  }

  public void setVisible(boolean visible) {
    this.commentAreaVisible_ = visible;
  }

  public void showCommentArea() {
    commentAreaVisible_ = true;
  }

  public void hideCommentArea() {
    commentAreaVisible_ = false;
  }

}

