package io.github.musicdoc.bean;

import android.content.SharedPreferences;
import io.github.musicdoc.property.BeanProperty;
import io.github.musicdoc.property.BooleanProperty;
import io.github.musicdoc.property.FloatProperty;
import io.github.musicdoc.property.IntProperty;
import io.github.musicdoc.property.Property;
import io.github.musicdoc.property.StringProperty;

public class BeanPreferencesMapper extends BeanMapper {

  private SharedPreferences preferences;

  public BeanPreferencesMapper(Bean bean, SharedPreferences preferences) {

    super(bean);
    this.preferences = preferences;
  }

  public void load() {

    load(getBean());
  }

  private void load(Bean bean) {

    for (Property<?> p : bean.getProperties()) {
      String name = p.getName();
      if (p instanceof IntProperty) {
        ((IntProperty) p).set(this.preferences.getInt(name, 0));
      } else if (p instanceof BooleanProperty) {
        ((BooleanProperty) p).set(this.preferences.getBoolean(name, false));
      } else if (p instanceof StringProperty) {
        ((StringProperty) p).setValue(this.preferences.getString(name, ""));
      } else if (p instanceof FloatProperty) {
        ((FloatProperty) p).set(this.preferences.getFloat(name, 0));
      } else if (p instanceof BeanProperty) {
        Bean childBean = ((BeanProperty) p).getValue();
        if (childBean != null) {
          load(childBean);
        }
      } else {
        throw new IllegalStateException("Unsupported property type!");
      }
    }
  }

  public void save() {

    SharedPreferences.Editor editor = this.preferences.edit();
    save(editor);
    editor.apply();
  }

  public void save(SharedPreferences.Editor preferences) {

    save(getBean(), preferences);
  }

  public void save(Bean bean, SharedPreferences.Editor preferences) {

    for (Property<?> p : bean.getProperties()) {
      String name = p.getName();
      if (p instanceof IntProperty) {
        preferences.putInt(name, ((IntProperty) p).get());
      } else if (p instanceof BooleanProperty) {
        preferences.putBoolean(name, ((BooleanProperty) p).get());
      } else if (p instanceof StringProperty) {
        preferences.putString(name, ((StringProperty) p).getValue());
      } else if (p instanceof FloatProperty) {
        preferences.putFloat(name, ((FloatProperty) p).getValue());
      } else if (p instanceof BeanProperty) {
        Bean childBean = ((BeanProperty) p).getValue();
        if (childBean != null) {
          save(childBean, preferences);
        }
      } else {
        throw new IllegalStateException("Unsupported property type!");
      }
    }
  }

}
