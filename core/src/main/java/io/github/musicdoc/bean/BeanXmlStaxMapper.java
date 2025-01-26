package io.github.musicdoc.bean;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import io.github.mmm.bean.WritableBean;
import io.github.mmm.bean.property.BeanProperty;
import io.github.mmm.property.WritableProperty;
import io.github.mmm.property.object.WritableSimpleProperty;
import io.github.musicdoc.config.Config;

/**
 * Implementation of {@link BeanXmlMapper} using StAX.
 */
public class BeanXmlStaxMapper extends BeanXmlMapper {

  /**
   * The constructor.
   *
   * @param bean the {@link WritableBean} to map.
   */
  public BeanXmlStaxMapper(WritableBean bean) {

    super(bean);
  }

  @Override
  public void saveXml(OutputStream out) {

    try {
      XMLStreamWriter xmlWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(out);
      saveXml(xmlWriter);
    } catch (XMLStreamException e) {
      throw new IllegalStateException("Failed to save XML.", e);
    }
  }

  @Override
  public void saveXml(Writer writer) {

    try {
      XMLStreamWriter xmlWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(writer);
      saveXml(xmlWriter);
    } catch (XMLStreamException e) {
      throw new IllegalStateException("Failed to save XML.", e);
    }
  }

  /**
   * @param xmlWriter the {@link XMLStreamWriter} to write the XML to.
   * @throws XMLStreamException if writing XML failed.
   */
  public void saveXml(XMLStreamWriter xmlWriter) throws XMLStreamException {

    xmlWriter.writeStartDocument();
    String rootTag = getRootTag();
    if (rootTag != null) {
      xmlWriter.writeStartElement(rootTag);
    }
    saveXml(xmlWriter, getBean());
    if (rootTag != null) {
      xmlWriter.writeEndElement();
    }
    xmlWriter.writeEndDocument();
  }

  private void saveXml(XMLStreamWriter xmlWriter, WritableBean bean) throws XMLStreamException {

    for (WritableProperty<?> property : bean.getProperties()) {
      if (property instanceof BeanProperty) {
        WritableBean childBean = ((BeanProperty<?>) property).get();
        if (childBean != null) {
          if (this.includeGroupTags) {
            xmlWriter.writeStartElement(property.getName());
          }
          saveXml(xmlWriter, childBean);
          if (this.includeGroupTags) {
            xmlWriter.writeEndElement();
          }
        }
      } else {
        saveXml(xmlWriter, property);
      }
    }
  }

  private void saveXml(XMLStreamWriter xmlWriter, WritableProperty<?> property) throws XMLStreamException {

    Object value = property.get();
    if (value == null) {
      return;
    }
    String tag = property.getName();
    xmlWriter.writeStartElement(tag);
    xmlWriter.writeCharacters(value.toString());
    xmlWriter.writeEndElement();
  }

  @Override
  public void loadXml(Reader reader) {

    try {
      XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(reader);
      loadXml(xmlReader);
    } catch (XMLStreamException e) {
      throw new IllegalStateException("Failed to load XML.", e);
    }
  }

  @Override
  public void loadXml(InputStream in) {

    try {
      XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(in);
      loadXml(xmlReader);
    } catch (XMLStreamException e) {
      throw new IllegalStateException("Failed to load XML.", e);
    }
  }

  private void expectTag(XMLStreamReader xmlReader, String tag, boolean closing) throws XMLStreamException {

    expectTag(xmlReader, tag, closing, false);
  }

  private void expectTag(XMLStreamReader xmlReader, String tag, boolean closing, boolean preventNext)
      throws XMLStreamException {

    if (tag == null) {
      return;
    }
    int event;
    if (preventNext) {
      event = xmlReader.getEventType();
    } else {
      event = xmlReader.nextTag();
    }
    int expectedEvent;
    if (closing) {
      expectedEvent = XMLStreamConstants.END_ELEMENT;
    } else {
      expectedEvent = XMLStreamConstants.START_ELEMENT;
    }
    if (event == expectedEvent) {
      String name = xmlReader.getName().getLocalPart();
      if (!name.equals(tag)) {
        throw new IllegalStateException("Expected XML tag " + tag + " but found " + name + ".");
      }
    } else {
      String type = closing ? "closing" : "opening";
      throw new IllegalStateException("Expected " + type + " '" + tag + "' element XML event (but found '"
          + xmlReader.getName().getLocalPart() + "' with type " + event + ").");
    }
  }

  /**
   * @param xmlReader the {@link XMLStreamReader} to read the XML from.
   * @throws XMLStreamException if reading XML failed.
   */
  public void loadXml(XMLStreamReader xmlReader) throws XMLStreamException {

    String rootTag = getRootTag();
    expectTag(xmlReader, rootTag, false);
    loadXml(xmlReader, getBean());
    expectTag(xmlReader, rootTag, true, true);
  }

  private void loadXml(XMLStreamReader xmlReader, WritableBean bean) throws XMLStreamException {

    int event = xmlReader.next();
    while ((event != XMLStreamConstants.END_ELEMENT) && (event != XMLStreamConstants.END_DOCUMENT)) {
      if (event == XMLStreamConstants.START_ELEMENT) {
        String name = xmlReader.getName().getLocalPart();
        WritableProperty<?> property;
        if (bean instanceof Config) {
          Config config = (Config) bean;
          property = config.getProperty(name, !this.includeGroupTags);
        } else {
          property = bean.getProperty(name);
        }
        if (property instanceof BeanProperty) {
          assert (this.includeGroupTags);
          WritableBean childBean = ((BeanProperty<?>) property).get();
          if (childBean != null) {
            loadXml(xmlReader, childBean);
          }
        } else if (property instanceof WritableSimpleProperty) {
          String value = readText(xmlReader).trim();
          ((WritableSimpleProperty<?>) property).setAsString(value);
        } else {
          if (property == null) {
            throw new IllegalStateException(name);
          } else {
            throw new IllegalStateException(property.toString());
          }
        }
        expectTag(xmlReader, name, true, true);
      }
      event = xmlReader.next();
    }
  }

  private static String readText(XMLStreamReader xmlReader) throws XMLStreamException {

    return xmlReader.getElementText();
  }

}
