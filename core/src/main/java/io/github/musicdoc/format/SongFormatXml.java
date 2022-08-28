package io.github.musicdoc.format;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.io.XmlMusicInputStream;
import io.github.musicdoc.io.XmlMusicOutputStream;
import io.github.musicdoc.song.Song;

/**
 * Abstract base implementation of {@link SongFormat} for XML based formats like MusicXML or OpenSong.
 */
public abstract class SongFormatXml extends SongFormat {

  @Override
  protected MusicInputStream createInputStream(String payload) {

    try {
      XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(payload));
      return new XmlMusicInputStream(xmlReader);
    } catch (XMLStreamException e) {
      throw new IllegalStateException("Failed to load XML.", e);
    }
  }

  @Override
  protected MusicOutputStream createOutputStream(Appendable out) {

    return XmlMusicOutputStream.of(out, getRootTag());
  }

  @Override
  public Song read(InputStream inStream) {

    XmlMusicInputStream in = XmlMusicInputStream.of(inStream);
    return getSongMapper().read(in, new SongFormatContext(this));
  }

  @Override
  public void write(Song song, OutputStream outStream) {

    XmlMusicOutputStream out = XmlMusicOutputStream.of(outStream, getRootTag());
    getSongMapper().write(song, out, new SongFormatContext(this));
  }

  @Override
  public String write(Song song) {

    StringWriter sw = new StringWriter(128);
    try (XmlMusicOutputStream out = XmlMusicOutputStream.of(sw, getRootTag())) {
      getSongMapper().write(song, out, new SongFormatContext(this));
    }
    return sw.toString();
  }

  /**
   * @return the XML root tag to use.
   */
  protected abstract String getRootTag();

}
