package at.dccs.jsfmin.api;

import java.io.Serializable;
import java.util.List;

import at.dccs.jsfmin.entity.Supplier;
import at.dccs.jsfmin.helper.SearchSupplierCriteria;
import at.dccs.jsfmin.helper.SearchUserCriteria;

public interface SupplierService extends Serializable {

  List<Supplier> getAllSuppliers();

  Supplier findSupplier(int supplierId);

  List<Supplier> findSuppliers(SearchSupplierCriteria searchSupplierCriteria);

  }
