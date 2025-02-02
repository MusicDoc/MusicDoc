package io.github.musicdoc.io;

import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * {@link MusicInputStream} for XML based formats such as MusicXML.
 */
public class XmlMusicInputStream extends AbstractTextMusicInputStream {

  private XMLStreamReader xmlReader;

  /**
   * The constructor.
   *
   * @param xmlReader the {@link #getXmlReader() XML reader} to read from.
   */
  public XmlMusicInputStream(XMLStreamReader xmlReader) {

    super("", 0, -1);
    this.xmlReader = xmlReader;
    try {
      int event = xmlReader.nextTag();
      assert (event == XMLStreamConstants.START_ELEMENT);
      // skip root element
      event = xmlReader.nextTag();
      assert (event == XMLStreamConstants.START_ELEMENT);
    } catch (XMLStreamException e) {
      throw new IllegalStateException("Failed to load XML.", e);
    }
  }

  @Override
  public int getLine(boolean relative) {

    int result = this.scanner.getLine();
    if (!relative) {
      result += this.xmlReader.getLocation().getLineNumber() - 1;
    }
    return result;
  }

  @Override
  public int getColumn(boolean relative) {

    int result = this.scanner.getColumn();
    if (!relative && (this.scanner.getLine() == 1)) {
      result += this.xmlReader.getLocation().getColumnNumber() - 1;
    }
    return result;
  }

  /**
   * @return the underlying {@link XMLStreamReader}.
   */
  public XMLStreamReader getXmlReader() {

    return this.xmlReader;
  }

  @Override
  public boolean isPropertyStart(String property) {

    int event = this.xmlReader.getEventType();
    if (event == XMLStreamConstants.START_ELEMENT) {
      if (property.equals(this.xmlReader.getLocalName())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String readPropertyStart() {

    try {
      int event = this.xmlReader.getEventType();
      if (event != XMLStreamConstants.START_ELEMENT) {
        return null;
      }
      String property = this.xmlReader.getLocalName();
      setString(this.xmlReader.getElementText().trim());
      return property;
    } catch (XMLStreamException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public String readPropertyValue() {

    try {
      assert (this.xmlReader.getEventType() == XMLStreamConstants.END_ELEMENT);
      String value = this.scanner.read(Integer.MAX_VALUE);
      this.xmlReader.nextTag();
      return value;
    } catch (XMLStreamException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public void close() {

    if (this.xmlReader == null) {
      return;
    }
    try {
      assert (this.xmlReader.next() == XMLStreamConstants.END_ELEMENT);
      assert (this.xmlReader.next() == XMLStreamConstants.END_DOCUMENT);
      this.xmlReader.close();
      this.xmlReader = null;
    } catch (XMLStreamException e) {
      throw new IllegalStateException(e);
    }
  }

  /**
   * @param inStream the {@link InputStream} to read from.
   * @return the {@link XmlMusicInputStream} reading from the given {@link InputStream}.
   */
  public static XmlMusicInputStream of(InputStream inStream) {

    try {
      XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(inStream);
      return new XmlMusicInputStream(xmlReader);
    } catch (XMLStreamException e) {
      throw new IllegalStateException("Failed to load XML.", e);
    }
  }

}
