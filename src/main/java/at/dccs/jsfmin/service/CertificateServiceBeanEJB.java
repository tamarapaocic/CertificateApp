package at.dccs.jsfmin.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import at.dccs.jsfmin.api.CertificateService;
import at.dccs.jsfmin.entity.Certificate;
import at.dccs.jsfmin.entity.CertificateUser;
import at.dccs.jsfmin.entity.CertificateUserId;
import at.dccs.jsfmin.entity.Comment;
import at.dccs.jsfmin.entity.Supplier;
import at.dccs.jsfmin.entity.User;

@Stateless
public class CertificateServiceBeanEJB implements Serializable, CertificateService {

  @PersistenceContext
  private EntityManager entityManager_;


  @Override
  public void deleteCertificate(Certificate certificate) {
    CriteriaBuilder cb = entityManager_.getCriteriaBuilder();
    CriteriaQuery<CertificateUser> q = cb.createQuery(CertificateUser.class);
    Root<CertificateUser> c = q.from(CertificateUser.class);
    q.select(c);
    ParameterExpression<Integer> p = cb.parameter(Integer.class);
    q.where(cb.equal(c.get("certificate_").get("certificateID_"), certificate.getCertificateID()));

    List<CertificateUser> certificateUsersToRemove = entityManager_.createQuery(q).getResultList();
    for (CertificateUser cu : certificateUsersToRemove) {
      entityManager_.remove(cu);
    }

    deleteCommentsFromCertificate(certificate);

    entityManager_.remove(entityManager_.find(Certificate.class, certificate.getCertificateID()));
  }

  private void deleteCommentsFromCertificate(Certificate certificate){
    CriteriaBuilder cb = entityManager_.getCriteriaBuilder();
    CriteriaQuery<Comment> q2 = cb.createQuery(Comment.class);
    Root<Comment> cm = q2.from(Comment.class);
    q2.select(cm);
    ParameterExpression<Integer> p2 = cb.parameter(Integer.class);
    q2.where(cb.equal(cm.get("certificate_").get("certificateID_"), certificate.getCertificateID()));

    List<Comment> commentsToRemove = entityManager_.createQuery(q2).getResultList();
    for (Comment comment : commentsToRemove) {
      entityManager_.remove(comment);
    }
  }


  @Override
  public void updateCertificate(Certificate selected, List<User> users) {
    if (selected.getCertificateID() == null) {
      entityManager_.persist(selected);
      if (users != null) {
        addUsersToCertificate(selected, users);
      }
      for (Comment comment : selected.getComments()) {
        comment.setCertificate(selected);
      }
    } else {

      Integer id = selected.getCertificateID();

      Certificate current = entityManager_.find(Certificate.class, id);
      current.setValidTo(selected.getValidTo());
      current.setValidFrom(selected.getValidFrom());
      current.setCertificateType(selected.getCertificateType());

      if (selected.getSupplier() != null) {
        Supplier supplier = entityManager_.find(Supplier.class, selected.getSupplier().getSupplierID());
        current.setSupplier(supplier);
      }

      for (CertificateUser cu : current.getCertificateUserList()) {
        entityManager_.remove(cu);
      }

      addUsersToCertificate(current, users);

      for (int i = current.getComments().size(); i < selected.getComments().size(); i++) {
        Comment newComment = selected.getComments().get(i);
        newComment.setCertificate(current);
        entityManager_.persist(newComment);
        entityManager_.flush();
      }
    }
  }

  private void addUsersToCertificate(Certificate certificate, List<User> users) {
    for (User user : users) {
      CertificateUser certificateUser = new CertificateUser();
      CertificateUserId cuId = new CertificateUserId(certificate.getCertificateID(), user.getUserID());
      certificateUser.setCertificateUserID(cuId);
      entityManager_.persist(certificateUser);
    }
  }


  @Override
  public Certificate getCertificateById(Integer certificateID) {
    return entityManager_.find(Certificate.class, certificateID);
  }


  @Override
  public List<Certificate> getAllCertificates() {
    CriteriaBuilder cb = entityManager_.getCriteriaBuilder();
    CriteriaQuery<Certificate> cq = cb.createQuery(Certificate.class);
    Root<Certificate> c = cq.from(Certificate.class);
    cq.select(c);
    TypedQuery<Certificate> tq = entityManager_.createQuery(cq);
    return tq.getResultList();
  }

  @Override
  public List<String> getAllCertificateTypes() {
    String query = "SELECT DISTINCT e.certificateType_ FROM Certificate e ORDER BY e.certificateType_";
    return entityManager_.createQuery(query, String.class).getResultList();

  }


}