package at.dccs.jsfmin.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import at.dccs.jsfmin.api.SupplierService;
import at.dccs.jsfmin.entity.Supplier;
import at.dccs.jsfmin.helper.SearchSupplierCriteria;

@Named
@SessionScoped
public class SupplierLookupController implements Serializable {

  @Inject
  private SupplierService supplierService_;

  private SearchSupplierCriteria searchSupplierCriteria_;

  private Supplier supplier_;

  @PostConstruct
  public void init() {
    searchSupplierCriteria_ = new SearchSupplierCriteria();
  }

  /**
   * Method for calling supplier service method which retrieves all suppliers from database.
   *
   * @return List of suppliers
   */
  public List<Supplier> getAllSuppliers() {
    return supplierService_.getAllSuppliers();
  }

  /**
   * Method for calling supplier service method and passing it search criteria based on which
   * suppliers from database will be retrieved.
   */
  public void findSuppliers() {
    supplierService_.findSuppliers(searchSupplierCriteria_);
  }

  public void reset() {
    searchSupplierCriteria_.setCity(null);
    searchSupplierCriteria_.setSupplierID(null);
    searchSupplierCriteria_.setSupplierName(null);
  }

  public SearchSupplierCriteria getSearchSupplierCriteria() {
    return searchSupplierCriteria_;
  }

  public void setSearchSupplierCriteria(SearchSupplierCriteria searchSupplierCriteria) {
    searchSupplierCriteria_ = searchSupplierCriteria;
  }

  public Supplier getSupplier() {
    return supplier_;
  }

  public void setSupplier(Supplier supplier) {
    supplier_ = supplier;
  }

}
