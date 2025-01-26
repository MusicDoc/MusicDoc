package io.github.musicdoc.bean;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import io.github.mmm.bean.Bean;
import io.github.mmm.bean.WritableBean;
import io.github.musicdoc.config.Config;

/**
 * Abstract {@link BeanMapper} mapping {@link Bean}s to XML.
 */
public abstract class BeanXmlMapper extends BeanMapper {

  private String rootTag;

  /** @see #isIncludeGroupTags() */
  protected boolean includeGroupTags;

  /**
   * The constructor.
   *
   * @param bean the {@link Bean} to map.
   */
  public BeanXmlMapper(WritableBean bean) {

    super(bean);
    if (bean instanceof Config) {
      this.rootTag = "myprofile";
    }
  }

  /**
   * @return the XML root tag.
   */
  public String getRootTag() {

    return this.rootTag;
  }

  public void setRootTag(String rootTag) {

    this.rootTag = rootTag;
  }

  public boolean isIncludeGroupTags() {

    return this.includeGroupTags;
  }

  public void setIncludeGroupTags(boolean includeGroupTags) {

    this.includeGroupTags = includeGroupTags;
  }

  public abstract void loadXml(InputStream in);

  public abstract void loadXml(Reader reader);

  public void loadXmlFromString(String xml) {

    StringReader reader = new StringReader(xml);
    loadXml(reader);
  }

  public abstract void saveXml(OutputStream out);

  public abstract void saveXml(Writer writer);

  public String saveXmlAsString() {

    StringWriter sw = new StringWriter(1024);
    saveXml(sw);
    return sw.toString();
  }

}
