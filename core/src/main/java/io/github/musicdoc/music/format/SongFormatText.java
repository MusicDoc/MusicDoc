package io.github.musicdoc.music.format;

import java.io.InputStream;
import java.io.OutputStream;

import io.github.musicdoc.io.TextMusicInputStream;
import io.github.musicdoc.io.TextMusicOutputStream;
import io.github.musicdoc.music.song.Song;

/**
 * Abstract base class for {@link SongFormat} based on plain text like MusicDoc or ABC.
 */
public abstract class SongFormatText extends SongFormat {

  @Override
  public Song parse(InputStream inStream) {

    TextMusicInputStream tmis = TextMusicInputStream.of(inStream);
    return getSongMapper().parse(tmis, new SongFormatOptions(this));
  }

  @Override
  public void format(Song song, OutputStream outStream) {

    TextMusicOutputStream tmos = TextMusicOutputStream.of(outStream);
    getSongMapper().format(song, tmos, new SongFormatOptions(this));
  }

}
