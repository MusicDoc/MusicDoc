package io.github.musicdoc.music.song;

import io.github.musicdoc.bean.Bean;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;
import io.github.musicdoc.music.harmony.MusicalKey;
import io.github.musicdoc.music.partiture.Partiture;
import io.github.musicdoc.property.IntProperty;
import io.github.musicdoc.property.MusicalKeyProperty;
import io.github.musicdoc.property.StringProperty;

/**
 * Representation of a song as a simple Java bean. This is a stupid data container with no logic. For advanced logic see
 * {@link SongWithContext}.
 */
public class Song extends Bean {

  /** The display title of the song (e.g. "The winner takes it all"). */
  public final StringProperty title;

  /** The author of the song. Typically used for the band (e.g. "Abba"). */
  public final StringProperty author;

  /** the additional copyright information. Typically the original artists (song composer, lyrics writer). */
  public final StringProperty copyright;

  /**
   * The lyrics of the song potentially annotated with metadata such as chords, tabs, or partiture.
   *
   * @see Partiture
   * @see SongWithContext#getPartiture()
   */
  public final StringProperty lyrics;

  /** The optional {@link MusicalKey}. */
  public final MusicalKeyProperty key;

  /** The pre delay in seconds before the song starts scrolling. */
  public final IntProperty preDelay;

  /** The duration of the song in seconds for scrolling. */
  public final IntProperty duration;

  /** The fret where to place the capo (to play in original or preferred key). */
  public final IntProperty capo;

  private Partiture partiture;

  /**
   * The constructor.
   */
  public Song() {

    super();
    this.title = register(new StringProperty("title"));
    this.author = register(new StringProperty("author"));
    this.copyright = register(new StringProperty("copyright"));
    this.lyrics = register(new StringProperty("lyrics"));
    this.key = register(new MusicalKeyProperty("key"));
    this.preDelay = register(new IntProperty("preDelay"));
    this.duration = register(new IntProperty("duration"));
    this.capo = register(new IntProperty("capo"));
  }

  /**
   * @param song the {@link Song} to copy.
   */
  public Song(Song song) {

    this();
    copy(song);
  }

  /**
   * @return the {@link Partiture}.
   */
  public Partiture getPartiture() {

    if (this.partiture == null) {
      String lyrics = this.lyrics.getValue();
      if (lyrics == null) {
        return null;
      }
      SongFormat format = SongFormatMusicDoc.INSTANCE;
      this.partiture = format.parse(lyrics);
    }
    return this.partiture;
  }
}
