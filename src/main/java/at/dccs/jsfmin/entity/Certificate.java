package at.dccs.jsfmin.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "certificateSequence", sequenceName = "certificate_seq", allocationSize = 1)
@Table(schema = "TAMARA", name = "Certificate")
public class Certificate {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "certificateSequence")
  @Column(name = "certificateID", length = 8)
  private Integer certificateID_;

  @ManyToOne
  @JoinColumn(name = "supplierID")
  private Supplier supplier_;

  @Basic
  @Column(name = "certificateType", length = 40)
  private String certificateType_;

  @Basic
  @Column(name = "validFrom")
  @Temporal(TemporalType.DATE)
  private Date validFrom_;

  @Basic
  @Column(name = "validTo")
  @Temporal(TemporalType.DATE)
  private Date validTo_;

  @OneToMany(mappedBy = "certificate_", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Comment> comments_;


  @OneToMany(mappedBy = "certificate_",cascade = CascadeType.ALL)
  private List<CertificateUser> certificateUserList_;


  public Certificate() {
  }

  public Certificate(Integer certificateID, Supplier supplier, String certificateType, Date validFrom, Date validTo) {
    this.certificateID_ = certificateID;
    this.supplier_ = supplier;
    this.certificateType_ = certificateType;
    this.validFrom_ = validFrom;
    this.validTo_ = validTo;
  }

  public Certificate(String certificateType_, Date validFrom_, Date validTo_) {
    this.certificateType_ = certificateType_;
    this.validFrom_ = validFrom_;
    this.validTo_ = validTo_;
  }


  public List<Comment> getComments() {
    return comments_;
  }

  public void setComments(List<Comment> comments) {
    comments_ = comments;
  }

  public Supplier getSupplier() {
    return supplier_;
  }

  public void setSupplier(Supplier supplier) {

    this.supplier_ = supplier;
  }

  public String getCertificateType() {
    return certificateType_;
  }

  public void setCertificateType(String certificateType) {
    certificateType_ = certificateType;
  }

  public Date getValidFrom() {

    return validFrom_;
  }

  public void setValidFrom(Date validFrom) {

    this.validFrom_ = validFrom;
  }

  public Date getValidTo() {
    return validTo_;
  }

  public void setValidTo(Date validTo) {

    this.validTo_ = validTo;
  }

  public Integer getCertificateID() {
    return certificateID_;
  }

  public void setCertificateID(Integer certificateID) {
    certificateID_ = certificateID;
  }

  public List<CertificateUser> getCertificateUserList() {
    return certificateUserList_;
  }

}

