package at.dccs.jsfmin.helper;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import at.dccs.jsfmin.api.UserService;
import at.dccs.jsfmin.entity.User;


@Named
@ApplicationScoped
public class UserConverter implements Converter, Serializable {

  @Inject
  private UserService userService_;

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    return userService_.findUser(Integer.valueOf(value));
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    if (!(value instanceof User)) {
      throw new IllegalArgumentException("UserConverter can convert only users");
    }
    User user = (User) value;
    return String.valueOf(user.getUserID());
  }
}
