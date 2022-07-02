package io.github.musicdoc.io;

/**
 * Interface to read and write the {@link #getPropertySuffix() property suffix}.
 */
public interface AttributePropertySuffix {

  /**
   * @return the current property suffix that terminates a property value. Typically a newline.
   */
  String getPropertySuffix();

  /**
   * @param propertySuffix new value of {@link #getPropertySuffix()}.
   */
  void setPropertySuffix(String propertySuffix);

}
