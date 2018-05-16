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

//  @Override
//  public boolean equals(Object o) {
//    if (this == o) {
//      return true;
//    }
//    if (o == null || getClass() != o.getClass()) {
//      return false;
//    }
//    CertificateUser that = (CertificateUser) o;
//    return user_.equals(that.user_) && certificate_.equals(that.certificate_);
//  }
//
//  @Override
//  public int hashCode() {
//    int result = 17;
//    result = 31 * result + user_.hashCode();
//    result = 31 * result + certificate_.hashCode();
//    return result;
//  }


}
