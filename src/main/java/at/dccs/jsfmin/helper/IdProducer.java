package at.dccs.jsfmin.helper;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named
public class IdProducer implements Serializable {

  private Integer id_ = 0;

  public Integer generateId() {
    return id_ += 1;
  }


}
