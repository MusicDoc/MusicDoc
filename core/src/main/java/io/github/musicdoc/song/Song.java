package io.github.musicdoc.song;

import io.github.mmm.bean.Bean;
import io.github.mmm.bean.BeanFactory;
import io.github.mmm.entity.property.link.LinkProperty;
import io.github.mmm.property.number.integers.IntegerProperty;
import io.github.mmm.property.string.StringProperty;
import io.github.mmm.property.string.StringSetProperty;
import io.github.musicdoc.album.Album;
import io.github.musicdoc.artist.Artist;
import io.github.musicdoc.entity.MusicalEntity;
import io.github.musicdoc.genre.Genre;
import io.github.musicdoc.harmony.key.MusicalKeyProperty;
import io.github.musicdoc.rhythm.fraction.PlainFractionProperty;
import io.github.musicdoc.rhythm.metre.Metre;
import io.github.musicdoc.rhythm.metre.MetreProperty;
import io.github.musicdoc.rhythm.tempo.TempoProperty;
import io.github.musicdoc.score.ScoreProperty;

/**
 * Representation of a song as a simple {@link Bean}.
 */
public interface Song extends MusicalEntity {

  /**
   * @return the {@link Artist} who performed the song. Typically used for the band (e.g. "Abba").
   */
  LinkProperty<Artist> Artist();

  /** @return the optional origin of the song (e.g. "England; Yorkshire; Bradford and Bingley."). */
  StringProperty Origin();

  /** @return the {@link Album} of the song. Typically used for the CD or LP (e.g. "More Abba Gold"). */
  LinkProperty<Album> Album();

  /** @return the composer of the music from the song. */
  StringProperty Composer();

  /** @return the author of the lyrics from the song. */
  StringProperty Lyricist();

  /** @return the additional copyright information with e.g. copyright holder, transcriber, etc. */
  StringProperty Copyright();

  /** @return the optional {@link io.github.musicdoc.harmony.key.MusicalKey}. */
  MusicalKeyProperty Key();

  /** @return the optional {@link io.github.musicdoc.rhythm.tempo.Tempo}. */
  TempoProperty Tempo();

  /** @return the optional {@link Metre}. */
  MetreProperty Metre();

  /**
   * @return the optional <a href="https://abcnotation.com/wiki/abc:standard:v2.1#lunit_note_length">unit note
   *         length</a>.
   */
  PlainFractionProperty UnitNoteLength();

  /** @return the pre delay in seconds before the song starts scrolling. */
  IntegerProperty PreDelay();

  /** @return the duration of the song in seconds for scrolling. */
  IntegerProperty Duration();

  /** @return the fret where to place the capo (to play in original or preferred key). */
  IntegerProperty Capo();

  /** @return the {@link io.github.musicdoc.score.Score} of the song with the lyrics. */
  ScoreProperty Score();

  /**
   * @return the optional <a href="https://abcnotation.com/wiki/abc:standard:v2.1#xreference_number">reference
   *         number</a>3750.
   */
  IntegerProperty ReferenceNumber();

  /** @return the tags that classify this song. */
  StringSetProperty Tags();

  /** @return the musical {@link Genre} of this song. */
  LinkProperty<Genre> Genre();

  /**
   * @return a new instance of {@link Song}.
   */
  static Song of() {

    return BeanFactory.get().create(Song.class);
  }

}
