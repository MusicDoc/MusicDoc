package io.github.musicdoc.song;

import io.github.mmm.bean.Bean;
import io.github.mmm.bean.BeanHelper;
import io.github.mmm.property.number.integers.IntegerProperty;
import io.github.mmm.property.string.StringSetProperty;
import io.github.mmm.property.string.TagsProperty;
import io.github.musicdoc.album.AlbumProperty;
import io.github.musicdoc.artist.ArtistProperty;
import io.github.musicdoc.entity.MusicalEntity;
import io.github.musicdoc.genre.GenreProperty;
import io.github.musicdoc.harmony.key.MusicalKeyProperty;
import io.github.musicdoc.rhythm.fraction.PlainFractionProperty;
import io.github.musicdoc.rhythm.metre.Metre;
import io.github.musicdoc.rhythm.metre.MetreProperty;
import io.github.musicdoc.rhythm.tempo.TempoProperty;
import io.github.musicdoc.score.ScoreProperty;
import io.github.musicdoc.search.SearchableStringProperty;

/**
 * Representation of a song as a simple {@link Bean}.
 */
public class Song extends Bean implements MusicalEntity {

  /** The title (display name) of the song (e.g. "The winner takes it all"). */
  public final SearchableStringProperty Title;

  /** The artist or performer of the song. Typically used for the band (e.g. "Abba"). */
  public final ArtistProperty Artist;

  /** The optional origin of the song (e.g. "England; Yorkshire; Bradford and Bingley."). */
  public final SearchableStringProperty Origin;

  /** The album of the song. Typically used for the CD or LP (e.g. "More Abba Gold"). */
  public final SearchableStringProperty Album;

  /** The composer of the music from the song. */
  public final SearchableStringProperty Composer;

  /** The author of the lyrics from the song. */
  public final SearchableStringProperty Lyricist;

  /** The additional copyright information with e.g. copyright holder, transcriber, etc. */
  public final SearchableStringProperty Copyright;

  /** The optional {@link io.github.musicdoc.harmony.key.MusicalKey}. */
  public final MusicalKeyProperty Key;

  /** The optional {@link io.github.musicdoc.rhythm.tempo.Tempo}. */
  public final TempoProperty Tempo;

  /** The optional {@link Metre}. */
  public final MetreProperty Metre;

  /** The optional <a href="https://abcnotation.com/wiki/abc:standard:v2.1#lunit_note_length">unit note length</a>. */
  public final PlainFractionProperty UnitNoteLength;

  /** The pre delay in seconds before the song starts scrolling. */
  public final IntegerProperty PreDelay;

  /** The duration of the song in seconds for scrolling. */
  public final IntegerProperty Duration;

  /** The fret where to place the capo (to play in original or preferred key). */
  public final IntegerProperty Capo;

  /** The {@link io.github.musicdoc.score.Score} of the song with the lyrics. */
  public final ScoreProperty Score;

  /**
   * The optional <a href="https://abcnotation.com/wiki/abc:standard:v2.1#xreference_number">reference number</a>3750.
   */
  public final IntegerProperty ReferenceNumber;

  /** The tags that classify this song. */
  public final StringSetProperty Tags;

  /** The musical genre of this song. */
  public final GenreProperty Genre;

  /**
   * The constructor.
   */
  public Song() {

    super();
    this.Title = add(new SearchableStringProperty("Title"));
    this.Artist = add(new ArtistProperty());
    this.Album = add(new AlbumProperty());
    this.Origin = add(new SearchableStringProperty("Origin"));
    this.Composer = add(new SearchableStringProperty("Composer"));
    this.Lyricist = add(new SearchableStringProperty("Lyricist"));
    this.Copyright = add(new SearchableStringProperty("Copyright"));
    this.Score = add(new ScoreProperty());
    this.Key = add(new MusicalKeyProperty());
    this.Metre = add(new MetreProperty());
    this.Tempo = add(new TempoProperty());
    this.UnitNoteLength = add(new PlainFractionProperty("UnitNoteLength"));
    this.PreDelay = add().newInteger("PreDelay");
    this.Duration = add().newInteger("Duration");
    this.Capo = add().newInteger("Capo");
    this.ReferenceNumber = add().newInteger("ReferenceNumber");
    this.Tags = add(new TagsProperty());
    this.Genre = add(new GenreProperty());
  }

  /**
   * @param song the {@link Song} to copy.
   */
  public Song(Song song) {

    this();
    BeanHelper.copy(this, song, false);
  }

  @Override
  public String getName() {

    return this.Title.get();
  }

  @Override
  public String getNormalizedName() {

    return this.Title.getNormalized();
  }

}
