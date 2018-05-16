package at.dccs.jsfmin.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Named;

import at.dccs.jsfmin.api.CommentService;
import at.dccs.jsfmin.entity.Comment;

@Named
@ApplicationScoped
@Alternative
public class CommentServiceBean implements Serializable, CommentService {

  private List<Comment> savedComments_;

  @PostConstruct
  private void initComments() {
    savedComments_ = new ArrayList<Comment>();
  }

  public List<Comment> getComments() {
    return savedComments_;
  }

  public void setComments(List<Comment> comments) {
    savedComments_ = comments;
  }

  public void addNewComment(Comment comment) {
    savedComments_.add(comment);
  }

  public List<Comment> findComments(Integer certificateID) {
    List<Comment> results = new ArrayList<Comment>();
    for (Comment comment : savedComments_) {
      if (comment.getCertificate().getCertificateID() == certificateID) {
        results.add(comment);
      }
    }
    return results;
  }


}
