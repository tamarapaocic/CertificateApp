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


  /**
   * Method for deleting certificate from database
   *
   * @param certificate selected certificate
   */
  @Override
  public void deleteCertificate(Certificate certificate) {
    CriteriaBuilder cb = entityManager_.getCriteriaBuilder();
    CriteriaQuery<CertificateUser> q = cb.createQuery(CertificateUser.class);
    Root<CertificateUser> c = q.from(CertificateUser.class);
    q.select(c);
    q.where(cb.equal(c.get("certificate_").get("certificateID_"), certificate.getCertificateID()));

    List<CertificateUser> certificateUsersToRemove = entityManager_.createQuery(q).getResultList();
    for (CertificateUser cu : certificateUsersToRemove) {
      entityManager_.remove(cu);
    }

    deleteCommentsFromCertificate(certificate);

    entityManager_.remove(entityManager_.find(Certificate.class, certificate.getCertificateID()));
  }


  /**
   * Method for updating live version of selected certificate in database
   *
   * @param selectedCertificate detached certificate
   */
  @Override
  public void updateCertificate(Certificate selectedCertificate) {
    List<User> users = selectedCertificate.getUsers();
    if (selectedCertificate.getCertificateID() == null) {
      entityManager_.persist(selectedCertificate);
      if (users != null) {
        addUsersToCertificate(selectedCertificate, users);
      }
      if (selectedCertificate.getComments() != null) {
        for (Comment comment : selectedCertificate.getComments()) {
          comment.setCertificate(selectedCertificate);
        }
      }
    } else {

      Integer id = selectedCertificate.getCertificateID();

      Certificate current = entityManager_.find(Certificate.class, id);
      current.setValidTo(selectedCertificate.getValidTo());
      current.setValidFrom(selectedCertificate.getValidFrom());
      current.setCertificateType(selectedCertificate.getCertificateType());

      if (selectedCertificate.getSupplier() != null) {
        Supplier supplier = entityManager_.find(Supplier.class, selectedCertificate.getSupplier().getSupplierID());
        current.setSupplier(supplier);
      }


      for (int i = current.getComments().size(); i < selectedCertificate.getComments().size(); i++) {
        Comment newComment = selectedCertificate.getComments().get(i);
        newComment.setCertificate(current);
        entityManager_.persist(newComment);
         entityManager_.flush();
        current.getComments().add(newComment);
      }

      current.setUsers(new ArrayList<User>());
      for(CertificateUser cu: current.getCertificateUserList()){
        current.getUsers().add(cu.getUser());
      }

       selectedCertificate.getUsers().removeAll(current.getUsers());

      if (users != null) {
        addUsersToCertificate(current, users);
      }
    }
  }

  /**
   * Method for assigning chosen users on certificate.
   *
   * @param certificate selected certificate
   * @param users       list of users who need to be assigned on certificate
   */
  private void addUsersToCertificate(Certificate certificate, List<User> users) {
    for (User user : users) {
      CertificateUser certificateUser = new CertificateUser();
      CertificateUserId cuId = new CertificateUserId(certificate.getCertificateID(), user.getUserID());
      certificateUser.setCertificateUserID(cuId);
      entityManager_.persist(certificateUser);
    }
  }

  /**
   * Method for retrieving certificate from database
   * and getting assigned users and comments of that certificate.
   *
   * @param certificateID ID of selected certificate
   * @return certificate object
   */
  @Override
  public Certificate getCertificateById(Integer certificateID) {
    Certificate certificate = entityManager_.find(Certificate.class, certificateID);
    List<Comment> comments;
    comments = certificate.getComments();
    for (Comment comment : comments) {
      comment.getUser();
    }
    List<User> users = new ArrayList<User>();

    List<CertificateUser> certificateUsers;
    certificateUsers = certificate.getCertificateUserList();
    for (CertificateUser cu : certificateUsers) {
      users.add(cu.getUser());
    }
    certificate.setUsers(users);
    return certificate;
  }


  /**
   * Method for retrieving all certificates from database
   *
   * @return list of certificates
   */
  @Override
  public List<Certificate> getAllCertificates() {
    CriteriaBuilder cb = entityManager_.getCriteriaBuilder();
    CriteriaQuery<Certificate> cq = cb.createQuery(Certificate.class);
    Root<Certificate> c = cq.from(Certificate.class);
    cq.select(c);
    TypedQuery<Certificate> tq = entityManager_.createQuery(cq);
    return tq.getResultList();
  }

  private void deleteCommentsFromCertificate(Certificate certificate) {
    CriteriaBuilder cb = entityManager_.getCriteriaBuilder();
    CriteriaQuery<Comment> q2 = cb.createQuery(Comment.class);
    Root<Comment> cm = q2.from(Comment.class);
    q2.select(cm);
    q2.where(cb.equal(cm.get("certificate_").get("certificateID_"), certificate.getCertificateID()));

    List<Comment> commentsToRemove = entityManager_.createQuery(q2).getResultList();
    for (Comment comment : commentsToRemove) {
      entityManager_.remove(comment);
    }
  }

  @Override
  public List<String> getAllCertificateTypes() {
    String query = "SELECT DISTINCT e.certificateType_ FROM Certificate e ORDER BY e.certificateType_";
    return entityManager_.createQuery(query, String.class).getResultList();

  }

  public Certificate getCertificateDataById(Integer certificateID) {
    return entityManager_.find(Certificate.class, certificateID);
  }
}