package at.dccs.jsfmin.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import at.dccs.jsfmin.api.CertificateService;
import at.dccs.jsfmin.api.CommentService;
import at.dccs.jsfmin.entity.Certificate;
import at.dccs.jsfmin.entity.Comment;
import at.dccs.jsfmin.entity.User;
import at.dccs.jsfmin.helper.SessionHelper;

@Named
@SessionScoped
public class CertificateController implements Serializable {

  private Certificate selectedCertificate_;
  private List<User> users_;
  private String commentText_;
  private boolean visible_;

  @Inject
  private CertificateService certificateService_;
  @Inject
  private CommentService commentService_;
  @Inject
  private SessionHelper sessionHelper_;
  @Inject
  private CertificateUserController certificateUserController_;

  @PostConstruct
  public void init() {

    String queryString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("certificateId");
    if (queryString == null || queryString.equals("")) {
      selectedCertificate_ = new Certificate();
    } else {
      selectedCertificate_ = certificateService_.getCertificateById(Integer.parseInt(queryString));
    }
  }

  public void initialize(Certificate certificate){
    if(certificate == null){
      reset();
      selectedCertificate_ = new Certificate();
      users_ = new ArrayList<User>();
      selectedCertificate_.setComments(new ArrayList<Comment>());
    } else {
      setSelectedCertificate(certificate);
      users_ = certificateUserController_.getUsersByCertificate(selectedCertificate_);
      selectedCertificate_.setComments(commentService_.findComments(selectedCertificate_.getCertificateID()));
      visible_ = false;
    }
  }

  public void updateCertificate() {
    certificateService_.updateCertificate(selectedCertificate_, users_);
  }

  public void saveCertificate() {
    updateCertificate();
    redirect("allCertificates");
  }

  public void redirect(String page) {
    page = page + ".xhtml";
    try {
      FacesContext.getCurrentInstance().getExternalContext().redirect(page);
    } catch (IOException e) {
      System.out.print("Can not redirect to requested page.");
    }
  }

  public void reset() {
    selectedCertificate_ = new Certificate();
    selectedCertificate_.setSupplier(null);
    selectedCertificate_.setCertificateType(null);
    selectedCertificate_.setValidFrom(null);
    selectedCertificate_.setValidTo(null);
    setCommentText(null);
    users_ = null;
    selectedCertificate_.setComments(null);
    visible_ = false;
  }

  public void resetSupplier() {
    selectedCertificate_.setSupplier(null);
  }


  public void deleteUserFromCertificate(User user) {
    users_.remove(user);
  }

  public void deleteCommentFromCertificate(Comment comment){
    selectedCertificate_.getComments().remove(comment);
  }
  public void addComment() {
    Comment comment = new Comment();
    comment.setComment(commentText_);
    comment.setUser(sessionHelper_.getCurrentUser());
    selectedCertificate_.getComments().add(comment);
    hide();
  }

  public Certificate getSelectedCertificate() {
    return selectedCertificate_;
  }

  public void setSelectedCertificate(Certificate selectedCertificate) {
    this.selectedCertificate_ = selectedCertificate;
  }

  public List<User> getUsers() {
    return users_;
  }

  public void setUsers(List<User> users) {
    users_ = users;
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
    return visible_;
  }

  public void setVisible(boolean visible) {
    this.visible_ = visible;
  }

  public void show() {
    visible_ = true;
  }

  public void hide() {
    visible_ = false;
  }

}

