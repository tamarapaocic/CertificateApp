package at.dccs.jsfmin.helper;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

@Named
@SessionScoped
public class LanguageHelper implements Serializable {
  private static final long serialVersionUID = 1L;

  private String localeCode_;
  private Locale locale_ = FacesContext.getCurrentInstance().getViewRoot().getLocale();
  private static Map<String, Object> languages_;

  static {
    languages_ = new LinkedHashMap<String, Object>();
    languages_.put("English", Locale.ENGLISH);
    languages_.put("German", Locale.GERMAN);
  }

  public Map<String, Object> getLanguagesInMap() {
    return languages_;
  }

  public String getLocaleCode() {
    return localeCode_;
  }

  public void setLocaleCode(String localeCode) {
    this.localeCode_ = localeCode;
  }

  public Locale getLocale() {
    return locale_;
  }

  //value change event listener
  public String languageLocaleCodeChanged(ValueChangeEvent e) {

    String newLocaleValue = e.getNewValue().toString();

    //loop country map to compare the locale code
    for (Map.Entry<String, Object> entry : languages_.entrySet()) {

      if (entry.getValue().toString().equals(newLocaleValue)) {

        FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
        locale_ = FacesContext.getCurrentInstance().getViewRoot().getLocale();
      }
    }
    return null;
  }

}