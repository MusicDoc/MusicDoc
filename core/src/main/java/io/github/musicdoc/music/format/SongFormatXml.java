package io.github.musicdoc.music.format;

import java.io.InputStream;
import java.io.OutputStream;

import io.github.musicdoc.io.XmlMusicInputStream;
import io.github.musicdoc.io.XmlMusicOutputStream;
import io.github.musicdoc.music.song.Song;

/**
 * Abstract base implementation of {@link SongFormat} for XML based formats like MusicXML or OpenSong.
 */
public abstract class SongFormatXml extends SongFormat {

  @Override
  public Song parse(InputStream inStream) {

    XmlMusicInputStream in = XmlMusicInputStream.of(inStream);
    return getSongMapper().parse(in, new SongFormatOptions(this));
  }

  @Override
  public void format(Song song, OutputStream outStream) {

    XmlMusicOutputStream out = XmlMusicOutputStream.of(outStream, getRootTag());
    getSongMapper().format(song, out, new SongFormatOptions(this));
  }

  /**
   * @return the XML root tag to use.
   */
  protected abstract String getRootTag();

}
