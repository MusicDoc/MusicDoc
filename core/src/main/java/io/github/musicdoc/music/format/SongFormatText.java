package io.github.musicdoc.music.format;

import java.io.InputStream;
import java.io.OutputStream;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.io.PropertyState;
import io.github.musicdoc.io.TextMusicInputStream;
import io.github.musicdoc.io.TextMusicOutputStream;
import io.github.musicdoc.music.song.Song;

/**
 * Abstract base class for {@link SongFormat} based on plain text like MusicDoc or ABC.
 */
public abstract class SongFormatText extends SongFormat {

  /**
   * @return a new {@link PropertyState} for this {@link SongFormat}.
   */
  protected PropertyState newPropertyState() {

    return PropertyState.of();
  }

  @Override
  protected MusicInputStream createInputStream(String payload) {

    return new TextMusicInputStream(payload, newPropertyState());
  }

  @Override
  protected MusicOutputStream createOutputStream(Appendable out) {

    return new TextMusicOutputStream(out, newPropertyState());
  }

  @Override
  public Song read(InputStream inStream) {

    TextMusicInputStream tmis = TextMusicInputStream.of(inStream, newPropertyState());
    return getSongMapper().read(tmis, new SongFormatContext(this));
  }

  @Override
  public void write(Song song, OutputStream outStream) {

    TextMusicOutputStream tmos = TextMusicOutputStream.of(outStream, newPropertyState());
    getSongMapper().write(song, tmos, new SongFormatContext(this));
  }

}
