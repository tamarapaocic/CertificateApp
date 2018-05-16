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

  private List<Supplier> suppliers_;

  private Supplier supplier_;

  public Supplier getSupplier() {
    return supplier_;
  }

  public void setSupplier(Supplier supplier) {
    supplier_ = supplier;
  }

  @PostConstruct
  public void init() {
    suppliers_ = supplierService_.getAllSuppliers();
    searchSupplierCriteria_ = new SearchSupplierCriteria();
  }

  public SearchSupplierCriteria getSearchSupplierCriteria() {
    return searchSupplierCriteria_;
  }

  public void setSearchSupplierCriteria(SearchSupplierCriteria searchSupplierCriteria) {
    searchSupplierCriteria_ = searchSupplierCriteria;
  }

  public void findSuppliers() {
    suppliers_ = supplierService_.findSuppliers(searchSupplierCriteria_);
  }

  public void reset() {
    searchSupplierCriteria_.setCity(null);
    searchSupplierCriteria_.setSupplierID(null);
    searchSupplierCriteria_.setSupplierName(null);
  }

  public List<Supplier> getSuppliers() {
    return suppliers_;
  }

  public void setSuppliers(List<Supplier> suppliers) {
    suppliers_ = suppliers;
  }
}
