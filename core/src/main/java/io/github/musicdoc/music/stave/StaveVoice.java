package io.github.musicdoc.music.stave;

import java.util.Objects;

import io.github.musicdoc.format.FormatConstants;
import io.github.musicdoc.music.instrument.Instrument;

/**
 * Represents a voice inside a {@link Stave}.
 */
public class StaveVoice {

  /** {@link StaveVoice} for soprano (treble). */
  public static final StaveVoice SOPRANO = new StaveVoice("Soprano", "S");

  /** {@link StaveVoice} for alto. */
  public static final StaveVoice ALTO = new StaveVoice("Alto", "A");

  /** {@link StaveVoice} for tenor. */
  public static final StaveVoice TENOR = new StaveVoice("Tenor", "T");

  /** {@link StaveVoice} for bass. */
  public static final StaveVoice BASS = new StaveVoice("Bass", "B");

  private final Instrument instrument;

  private final String name;

  private final String abbreviation;

  /**
   * The constructor.
   *
   * @param name the {@link #getName() voice name}.
   */
  public StaveVoice(String name) {

    this(name, null, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() voice name}.
   * @param abbreviation the {@link #getAbbreviation() voice abbreviation}.
   */
  public StaveVoice(String name, String abbreviation) {

    this(name, abbreviation, null);
  }

  /**
   * The constructor.
   *
   * @param name the {@link #getName() voice name}.
   * @param abbreviation the {@link #getAbbreviation() voice abbreviation}.
   * @param instrument the {@link #getInstrument() voice instrument}.
   */
  public StaveVoice(String name, String abbreviation, Instrument instrument) {

    super();
    this.name = name;
    this.abbreviation = abbreviation;
    this.instrument = instrument;
  }

  /**
   * @return the display name of this voice (e.g. "Bass").
   */
  public String getName() {

    return this.name;
  }

  /**
   * @return the abbreviation of the {@link #getName() name}. Used to display the voice {@link #getName() name} for
   *         repeated staves to save space.
   */
  public String getAbbreviation() {

    return this.abbreviation;
  }

  /**
   * @return the {@link Instrument} used to play this {@link StaveVoice}. May be {@code null} for undefined or human
   *         singing voice.
   */
  public Instrument getInstrument() {

    return this.instrument;
  }

  @Override
  public int hashCode() {

    return this.name.hashCode();
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == this) {
      return true;
    } else if ((obj == null) || (obj.getClass() != getClass())) {
      return false;
    }
    StaveVoice other = (StaveVoice) obj;
    if (!Objects.equals(this.name, other.name) || !Objects.equals(this.abbreviation, other.abbreviation)
        || !Objects.equals(this.instrument, other.instrument)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder(this.name);
    if (this.abbreviation != null) {
      sb.append(FormatConstants.VOICE_SEPARATOR);
      sb.append(this.abbreviation);
    }
    if (this.instrument != null) {
      if (this.abbreviation == null) {
        sb.append(FormatConstants.VOICE_SEPARATOR);
      }
      sb.append(FormatConstants.VOICE_SEPARATOR);
      sb.append(this.instrument);
    }
    return sb.toString();
  }
}
