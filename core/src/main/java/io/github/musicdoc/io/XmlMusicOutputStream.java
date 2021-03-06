package io.github.musicdoc.io;

import java.io.OutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import io.github.musicdoc.music.format.TextPosition;

/**
 * Implementation of {@link MusicOutputStream} for XML based formats such as MusicXML.
 */
public class XmlMusicOutputStream extends AbstractMusicStream implements MusicOutputStream {

  private XMLStreamWriter xmlWriter;

  private final TextPosition position;

  private XmlMusicOutputStream(XMLStreamWriter xmlWriter, TextPosition position) {

    super();
    this.xmlWriter = xmlWriter;
    this.position = position;
  }

  @Override
  public int getLine() {

    try {
      this.xmlWriter.flush();
      return this.position.getLine();
    } catch (XMLStreamException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public int getColumn() {

    try {
      this.xmlWriter.flush();
      return this.position.getColumn();
    } catch (XMLStreamException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public void append(Object value) {

    try {
      if (value == null) {
        return;
      }
      String text = value.toString();
      this.xmlWriter.writeCharacters(text);
    } catch (XMLStreamException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public void startProperty(String propertyName) {

    try {
      this.xmlWriter.writeStartElement(propertyName);
    } catch (XMLStreamException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public void endProperty(String propertyName) {

    try {
      this.xmlWriter.writeEndElement();
    } catch (XMLStreamException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public void close() throws Exception {

    if (this.xmlWriter == null) {
      return;
    }
    this.xmlWriter.writeEndElement();
    this.xmlWriter.writeEndDocument();
    this.xmlWriter.close();
    this.xmlWriter = null;
  }

  /**
   * @param outStream the {@link OutputStream} to write to.
   * @param rootTag the XML root tag to use.
   * @return the {@link XmlMusicOutputStream}.
   */
  public static XmlMusicOutputStream of(OutputStream outStream, String rootTag) {

    try {
      TextPositionOutputStream tpos = new TextPositionOutputStream(outStream);
      XMLStreamWriter xmlWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(tpos);
      xmlWriter.writeStartDocument();
      xmlWriter.writeStartElement(rootTag);
      return new XmlMusicOutputStream(xmlWriter, tpos);
    } catch (XMLStreamException e) {
      throw new IllegalStateException("Failed to save XML.", e);
    }
  }

}
