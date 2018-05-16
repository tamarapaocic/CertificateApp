package at.dccs.jsfmin.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import at.dccs.jsfmin.api.CommentService;
import at.dccs.jsfmin.entity.Certificate;
import at.dccs.jsfmin.entity.Comment;

@Stateless
public class CommentServiceBeanEJB implements Serializable, CommentService {

  @PersistenceContext
  private EntityManager entityManager_;

  @Override
  public List<Comment> findComments(Integer certificateID) {
    List<Comment> list = new ArrayList<Comment>();
    Certificate cert = entityManager_.find(Certificate.class,certificateID);
    if (cert == null){
      return list;
    }
    list = cert.getComments();
    for (Comment comment : list) {
      comment.getUser();
    }
    return list;
  }

  @Override
  public void addNewComment(Comment comment) {
   entityManager_.persist(comment);
  }

  @Override
  public List<Comment> getComments() {
  return null;
  }
}
