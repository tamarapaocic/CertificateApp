package at.dccs.jsfmin.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "TAMARA", name = "CertificateUser")
public class CertificateUser {

  @EmbeddedId
  private CertificateUserId certificateUserID_;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name = "UserID", nullable = false)
  private User user_;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name = "CertificateID", nullable = false)
  private Certificate certificate_;

  public CertificateUser() {
  }

  public CertificateUser(Certificate certificate_, User user_) {
    this.certificate_ = certificate_;
    this.user_ = user_;
  }

  public User getUser() {
    certificateUserID_.getUserID();
    return user_;
  }

  public void setUser(User user_) {
    this.user_ = user_;
  }

  public Certificate getCertificate() {
    return certificate_;
  }

  public void setCertificate(Certificate certificate_) {
    this.certificate_ = certificate_;
  }

  public CertificateUserId getCertificateUserID() {
    return certificateUserID_;
  }

  public void setCertificateUserID(CertificateUserId certificateUserID) {
    certificateUserID_ = certificateUserID;
  }


}
