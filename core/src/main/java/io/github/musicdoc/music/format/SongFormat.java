package io.github.musicdoc.music.format;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import io.github.musicdoc.music.rythm.beat.Beat;
import io.github.musicdoc.music.score.Score;
import io.github.musicdoc.music.score.ScoreMapper;
import io.github.musicdoc.music.song.Song;
import io.github.musicdoc.music.song.SongMapper;

/**
 * Abstract base class for a music format allowing to {@link #format(Song, OutputStream) wirte (format)} and
 * {@link #parse(InputStream) read (parse)} a {@link Song} and all its child elements..
 */
public abstract class SongFormat {

  private static final Map<String, SongFormat> FORMAT_MAP = new HashMap<>();

  /**
   * The constructor.
   */
  protected SongFormat() {

    super();
    FORMAT_MAP.put(getName(), this);
  }

  /**
   * @return the underlying {@link ScoreMapper} to parse and format {@link Score}s.
   */
  protected ScoreMapper getScoreMapper() {

    return getSongMapper().getScoreMapper();
  }

  /**
   * @return the underlying {@link SongMapper} to parse and format {@link Song}s.
   */
  protected abstract SongMapper getSongMapper();

  /**
   * @return the name of this formatSection.
   */
  public abstract String getName();

  /**
   * @param inStream the {@link InputStream} to read.
   * @return the parsed {@link Song}.
   */
  public abstract Song parse(InputStream inStream);

  /**
   * @param song the song to write.
   * @param outStream the {@link OutputStream} to write to.
   */
  public abstract void format(Song song, OutputStream outStream);

  /**
   * @param name the {@link #getName() name} of the requested {@link SongFormat}.
   * @return the {@link SongFormat} with the given {@link #getName() name} or {@code null} if not exists.
   */
  public static final SongFormat get(String name) {

    return FORMAT_MAP.get(name);
  }

  /**
   * @param beat
   * @return
   */
  Beat getUnitNoteLength(Beat beat) {

    return Beat.of(1, 4);
  }

  @Override
  public String toString() {

    return getName();
  }

}
