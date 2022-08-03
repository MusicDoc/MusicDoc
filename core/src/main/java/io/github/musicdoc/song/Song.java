package io.github.musicdoc.song;

import io.github.musicdoc.bean.Bean;
import io.github.musicdoc.harmony.key.MusicalKeyProperty;
import io.github.musicdoc.property.IntProperty;
import io.github.musicdoc.property.StringProperty;
import io.github.musicdoc.rythm.beat.Beat;
import io.github.musicdoc.rythm.beat.BeatProperty;
import io.github.musicdoc.rythm.tempo.TempoProperty;
import io.github.musicdoc.score.ScoreProperty;

/**
 * Representation of a song as a simple {@link Bean}.
 */
public class Song extends Bean {

  /** The display title of the song (e.g. "The winner takes it all"). */
  public final StringProperty title;

  /** The composer or author of the song. Typically used for the band (e.g. "Abba"). */
  public final StringProperty composer;

  /** The optional origin of the song (e.g. "England; Yorkshire; Bradford and Bingley."). */
  public final StringProperty origin;

  /** The album of the song. Typically used for the CD or LP (e.g. "More Abba Gold"). */
  public final StringProperty album;

  /** the additional copyright information. Typically the original artists (song composer, lyrics writer). */
  public final StringProperty copyright;

  /** The optional {@link io.github.musicdoc.harmony.key.MusicalKey}. */
  public final MusicalKeyProperty key;

  /** The optional {@link io.github.musicdoc.rythm.tempo.Tempo}. */
  public final TempoProperty tempo;

  /** The optional {@link Beat}. */
  public final BeatProperty beat;

  /** The optional <a href="https://abcnotation.com/wiki/abc:standard:v2.1#lunit_note_length">unit note length</a>. */
  public final BeatProperty unitNoteLength;

  /** The pre delay in seconds before the song starts scrolling. */
  public final IntProperty preDelay;

  /** The duration of the song in seconds for scrolling. */
  public final IntProperty duration;

  /** The fret where to place the capo (to play in original or preferred key). */
  public final IntProperty capo;

  /** The {@link io.github.musicdoc.score.Score} of the song with the lyrics. */
  public final ScoreProperty score;

  /**
   * The optional <a href="https://abcnotation.com/wiki/abc:standard:v2.1#xreference_number">reference number</a>3750.
   */
  public final IntProperty referenceNumber;

  /**
   * The constructor.
   */
  public Song() {

    super();
    this.title = register(new StringProperty("title"));
    this.composer = register(new StringProperty("composer"));
    this.album = register(new StringProperty("album"));
    this.origin = register(new StringProperty("origin"));
    this.copyright = register(new StringProperty("copyright"));
    this.score = register(new ScoreProperty("score"));
    this.key = register(new MusicalKeyProperty("key"));
    this.beat = register(new BeatProperty("beat"));
    this.tempo = register(new TempoProperty("tempo"));
    this.unitNoteLength = register(new BeatProperty("unitNoteLength"));
    this.preDelay = register(new IntProperty("preDelay"));
    this.duration = register(new IntProperty("duration"));
    this.capo = register(new IntProperty("capo"));
    this.referenceNumber = register(new IntProperty("referenceNumber"));
  }

  /**
   * @param song the {@link Song} to copy.
   */
  public Song(Song song) {

    this();
    copy(song);
  }

}
