package at.dccs.jsfmin.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import at.dccs.jsfmin.api.SupplierService;
import at.dccs.jsfmin.entity.Supplier;
import at.dccs.jsfmin.helper.SearchSupplierCriteria;

@Stateless
public class SupplierServiceBeanEJB implements Serializable, SupplierService {

  @PersistenceContext
  private EntityManager entityManager_;

  /**
   * Method for retrieving all suppliers from database
   *
   * @return list of suppliers
   */
  @Override
  public List<Supplier> getAllSuppliers() {
    CriteriaBuilder cb = entityManager_.getCriteriaBuilder();
    CriteriaQuery<Supplier> q = cb.createQuery(Supplier.class);
    Root<Supplier> s = q.from(Supplier.class);
    q.select(s);
    q.orderBy(cb.asc(s.get("supplierName_")));
    TypedQuery<Supplier> tq = entityManager_.createQuery(q);
    return tq.getResultList();
  }

  /**
   * Method for finding supplier from database based on supplier ID.
   *
   * @param supplierID ID of supplier to be find
   * @return user
   */
  @Override
  public Supplier findSupplier(int supplierID) {
    return entityManager_.find(Supplier.class, supplierID);
  }

  /**
   * Method for finding suppliers from database based on search criteria
   *
   * @param formCriteria search criteria
   * @return list of suppliers
   */
  @Override
  public List<Supplier> findSuppliers(SearchSupplierCriteria formCriteria) {
    CriteriaBuilder cb = entityManager_.getCriteriaBuilder();
    CriteriaQuery<Supplier> s = cb.createQuery(Supplier.class);
    Root<Supplier> supplier = s.from(Supplier.class);
    s.select(supplier);
    List<Predicate> criteria = new ArrayList<Predicate>();

    if (!isBlank(formCriteria.getSupplierName())) {
      criteria.add(cb.like(cb.lower(supplier.<String>get("supplierName_")), fullLike(formCriteria.getSupplierName()
              .toLowerCase())));
    }

    if (formCriteria.getSupplierID() != null) {
      criteria.add(cb.equal(supplier.get("supplierID_"), formCriteria.getSupplierID()));
    }

    if (!isBlank(formCriteria.getCity())) {
      criteria.add(cb.like(cb.lower(supplier.<String>get("city_")), fullLike(formCriteria.getCity())));
    }

    s.where(criteria.toArray(new Predicate[]{}));

    TypedQuery<Supplier> q = entityManager_.createQuery(s);

    return q.getResultList();
  }

  private String fullLike(String string) {
    return "%" + string + "%";
  }

  private boolean isBlank(String string) {
    return string == null || string.isEmpty();
  }

}