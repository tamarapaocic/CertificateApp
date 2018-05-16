package at.dccs.jsfmin.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "commentSequence", sequenceName = "comment_seq", allocationSize = 1)
@Table(schema = "TAMARA", name = "CommentData")
public class Comment implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "commentID", length = 8)
  private Integer commentID_;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "participantID")
  private User user_;

  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  @JoinColumn(name = "certificateID")
  private Certificate certificate_;

  @Basic
  @Column(name = "commentText", length = 500)
  private String comment_;

  public Comment() {
  }

  public Comment(Integer commentID, User user, Certificate certificate, String comment) {
    this.commentID_ = commentID;
    this.user_ = user;
    this.certificate_ = certificate;
    this.comment_ = comment;
  }

  public Comment(User user, Certificate certificate, String comment) {
    this.user_ = user;
    this.certificate_ = certificate;
    this.comment_ = comment;
  }

  public Integer getCommentID() {
    return commentID_;
  }

  public void setCommentID(Integer commentID) {
    commentID_ = commentID;
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

  public String getComment() {
    return comment_;
  }

  public void setComment(String comment) {
    comment_ = comment;
  }

}
