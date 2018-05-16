package at.dccs.jsfmin.api;

import java.io.Serializable;
import java.util.List;

import at.dccs.jsfmin.entity.Comment;

public interface CommentService extends Serializable {

  List<Comment> findComments(Integer certificateID);

  void addNewComment(Comment comment);

  List<Comment> getComments();
}
