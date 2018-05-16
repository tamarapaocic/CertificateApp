package at.dccs.jsfmin.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Named;

import at.dccs.jsfmin.entity.Department;

@Named
@ApplicationScoped

public class DepartmentServiceBean implements Serializable {

  private List<Department> departments_;

  @PostConstruct
  private void initDepartment() {
    departments_ = new ArrayList<Department>();
    departments_.add(new Department(1, "ITM/FP", 96));
    departments_.add(new Department(2, "ITM/FP", 94));
    departments_.add(new Department(3, "ITM/FP", 92));
  }

  public List<Department> getAllDepartments() {
    return departments_;
  }

}
