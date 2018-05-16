package at.dccs.jsfmin.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CertificateUserId {

  @Column(name="certificateID")
  private Integer certificateID_;

  @Column(name="userID")
  private Integer userID_;

  public CertificateUserId() {
  }

  public CertificateUserId(Integer certificateID, Integer userID) {
    certificateID_ = certificateID;
    userID_ = userID;
  }

  public Integer getCertificateID() {
    return certificateID_;
  }

  public void setCertificateID(Integer certificateID) {
    certificateID_ = certificateID;
  }

  public Integer getUserID() {
    return userID_;
  }

  public void setUserID(Integer userID) {
    userID_ = userID;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CertificateUserId that = (CertificateUserId) o;
    return certificateID_.equals(that.certificateID_) && userID_.equals(that.userID_);
  }

  @Override
  public int hashCode() {
    return certificateID_.hashCode() + userID_.hashCode();
  }
}
