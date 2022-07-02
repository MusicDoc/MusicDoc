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
  }

  @Override
  public int getLine() {

    return this.xmlReader.getLocation().getLineNumber() + super.getLine() - 1;
  }

  @Override
  public int getColumn() {

    return this.xmlReader.getLocation().getColumnNumber() + super.getColumn() - 1;
  }

  @Override
  public long getIndex() {

    return this.xmlReader.getLocation().getCharacterOffset() + super.getIndex();
  }

  /**
   * @return the underlying {@link XMLStreamReader}.
   */
  public XMLStreamReader getXmlReader() {

    return this.xmlReader;
  }

  @Override
  public String readPropertyStart() {

    try {
      int event = this.xmlReader.nextTag();
      if (event == XMLStreamConstants.START_ELEMENT) {
        String property = this.xmlReader.getLocalName();
        setString(this.xmlReader.getElementText().trim());
        return property;
      } else {
        addError("Invalid XML - expected start element.");
        assert (event == XMLStreamConstants.END_ELEMENT);
        return null;
      }
    } catch (XMLStreamException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public String readPropertyValue() {

    try {
      int event = this.xmlReader.nextTag();
      if (event == XMLStreamConstants.END_ELEMENT) {
        return read(Integer.MAX_VALUE);
      } else {
        addError("Invalid XML - expected end element.");
        assert (event == XMLStreamConstants.START_ELEMENT);
        return null;
      }
    } catch (XMLStreamException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public void close() throws Exception {

    if (this.xmlReader == null) {
      return;
    }
    assert (this.xmlReader.next() == XMLStreamConstants.END_ELEMENT);
    assert (this.xmlReader.next() == XMLStreamConstants.END_DOCUMENT);
    this.xmlReader.close();
    this.xmlReader = null;
  }

  /**
   * @param inStream the {@link InputStream} to read from.
   * @return the {@link XmlMusicInputStream} reading from the given {@link InputStream}.
   */
  public static XmlMusicInputStream of(InputStream inStream) {

    try {
      XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(inStream);
      int event;
      do {
        event = xmlReader.next();
      } while (event != XMLStreamConstants.START_DOCUMENT);
      event = xmlReader.nextTag();
      assert (event == XMLStreamConstants.START_ELEMENT);
      return new XmlMusicInputStream(xmlReader);
    } catch (XMLStreamException e) {
      throw new IllegalStateException("Failed to load XML.", e);
    }
  }

}
