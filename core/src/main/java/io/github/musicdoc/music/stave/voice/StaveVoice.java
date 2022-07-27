package io.github.musicdoc.music.stave.voice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.music.instrument.Instrument;
import io.github.musicdoc.music.note.StemDirection;
import io.github.musicdoc.music.stave.Stave;

/**
 * Represents a voice inside a {@link Stave}.
 */
public class StaveVoice implements MutableObject<StaveVoice> {

  /** Property name of {@link #getId() ID}. */
  public static final String PROPERTY_ID = "id";

  /** Property name of {@link #getName() name}. */
  public static final String PROPERTY_NAME = "name";

  /** Property name of {@link #getAbbreviation() abbreviation}. */
  public static final String PROPERTY_ABBREVIATION = "abbreviation";

  /** Property name of {@link #getStemDirection() stemDirection}. */
  public static final String PROPERTY_STEM_DIRECTION = "stemDirection";

  /** Property name of {@link #getInstrument() instrument}. */
  public static final String PROPERTY_INSTRUMENT = "instrument";

  /** Property name of {@link #getOctaveShift() octave shift}. */
  public static final String PROPERTY_OCTAVE_SHIFT = "octaveShift";

  /** Property name of {@link #getTranspose }. */
  public static final String PROPERTY_TRANSPOSE = "transpose";

  private static final Map<String, StaveVoice> ID2VOICE_MAP = new HashMap<>();

  /** {@link StaveVoice} for soprano (treble). */
  public static final StaveVoice SOPRANO = ofInternal("S", "Soprano");

  /** {@link StaveVoice} for alto. */
  public static final StaveVoice ALTO = ofInternal("A", "Alto");

  /** {@link StaveVoice} for tenor. */
  public static final StaveVoice TENOR = ofInternal("T", "Tenor");

  /** {@link StaveVoice} for bass. */
  public static final StaveVoice BASS = ofInternal("B", "Bass");

  /** Empty {@link StaveVoice}. */
  public static final StaveVoice EMPTY = ofInternal("", "");

  private String id;

  private String name;

  private String abbreviation;

  private Instrument instrument;

  private StemDirection stemDirection;

  private int octaveShift;

  private int transpose;

  private Stave stave;

  private boolean immutable;

  /**
   * The constructor.
   */
  public StaveVoice() {

    super();
  }

  /**
   * The constructor.
   *
   * @param id the {@link #getId() ID}.
   */
  public StaveVoice(String id) {

    super();
    Objects.requireNonNull(id, "ID");
    this.id = id;
  }

  /**
   * The constructor.
   *
   * @param id the {@link #getId() ID}.
   * @param name the {@link #getName() name}.
   * @param abbreviation the {@link #getAbbreviation() abbreviation}.
   */
  public StaveVoice(String id, String name, String abbreviation) {

    this(id, name, abbreviation, null, null, 0, 0);
    Objects.requireNonNull(id, "ID");
    Objects.requireNonNull(name, "name");
    Objects.requireNonNull(abbreviation, "abbreviation");
  }

  private StaveVoice(String id, String name, String abbreviation, Instrument instrument, StemDirection stemDirection, int transpose,
      int octaveShift) {

    super();
    this.name = name;
    if (abbreviation == null) {
      abbreviation = "";
      if (!name.isEmpty()) {
        abbreviation = name.substring(0, 1);
      }
    }
    this.abbreviation = abbreviation;
    if (id == null) {
      this.id = this.abbreviation;
    } else {
      this.id = id;
    }
    this.instrument = instrument;
    if (stemDirection == null) {
      this.stemDirection = StemDirection.AUTO;
    } else {
      this.stemDirection = stemDirection;
    }
    this.octaveShift = octaveShift;
    this.transpose = transpose;
  }

  private StaveVoice(StaveVoice copy, MutableObjecteCopier copier) {

    super();
    this.id = copy.id;
    this.name = copy.name;
    this.abbreviation = copy.abbreviation;
    this.instrument = copy.instrument;
    this.octaveShift = copy.octaveShift;
    this.transpose = copy.transpose;
  }

  @Override
  public StaveVoice copy(MutableObjecteCopier copier) {

    return new StaveVoice(this, copier);
  }

  /**
   * @return the ID used as internal reference to this voice (e.g. "T2" for "Tentor II"). Often the same as
   *         {@link #getAbbreviation()}. However, this key is only used internally as reference and never displayed in
   *         the score.
   */
  public String getId() {

    return this.id;
  }

  /**
   * @param newId the new {@link #getId() ID}.
   * @return a {@link StaveVoice} with the given {@link #getId() ID} and all other properties like {@code this} one.
   *         Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public StaveVoice setId(String newId) {

    if (Objects.equals(this.id, newId)) {
      return this;
    }
    StaveVoice voice = makeMutable();
    voice.id = newId;
    return voice;
  }

  /**
   * @return the display name of this voice (e.g. "Bass").
   */
  public String getName() {

    return this.name;
  }

  /**
   * @param newName the new {@link #getName() name}.
   * @return a {@link StaveVoice} with the given {@link #getName() name} and all other properties like {@code this} one.
   *         Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public StaveVoice setName(String newName) {

    if (Objects.equals(this.name, newName)) {
      return this;
    }
    StaveVoice voice = makeMutable();
    voice.name = newName;
    return voice;
  }

  /**
   * @return the abbreviation of the {@link #getName() name}. Used to display the voice {@link #getName() name} for
   *         repeated staves to save space. This is called "subname" ("snm") in ABC notation.
   */
  public String getAbbreviation() {

    return this.abbreviation;
  }

  /**
   * @param newAbbreviation the new {@link #getAbbreviation() abbreviation}.
   * @return a {@link StaveVoice} with the given {@link #getAbbreviation() abbreviation} and all other properties like
   *         {@code this} one. Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public StaveVoice setAbbreviation(String newAbbreviation) {

    if (Objects.equals(this.abbreviation, newAbbreviation)) {
      return this;
    }
    StaveVoice voice = makeMutable();
    voice.abbreviation = newAbbreviation;
    return voice;
  }

  /**
   * @return the {@link Instrument} used to play this {@link StaveVoice}. May be {@code null} for undefined or human
   *         singing voice.
   */
  public Instrument getInstrument() {

    return this.instrument;
  }

  /**
   * @param newInstrument the new {@link #getInstrument() instrument}.
   * @return a {@link StaveVoice} with the given {@link #getInstrument() instrument} and all other properties like
   *         {@code this} one. Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public StaveVoice setInstrument(Instrument newInstrument) {

    if (Objects.equals(this.instrument, newInstrument)) {
      return this;
    }
    StaveVoice voice = makeMutable();
    voice.instrument = newInstrument;
    return voice;
  }

  /**
   * @return the {@link StemDirection}.
   */
  public StemDirection getStemDirection() {

    return this.stemDirection;
  }

  /**
   * @param newStemDirection the new {@link #getStemDirection() stemDirection}.
   * @return a {@link StaveVoice} with the given {@link #getStemDirection() stemDirection} and all other properties like
   *         {@code this} one. Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public StaveVoice setStemDirection(StemDirection newStemDirection) {

    if (Objects.equals(this.stemDirection, newStemDirection)) {
      return this;
    }
    StaveVoice voice = makeMutable();
    voice.stemDirection = newStemDirection;
    return voice;
  }

  /**
   * @return the number of semitones to transpose this voice. Only affects playback but not the printed score, so use
   *         with caution.
   */
  public int getTranspose() {

    return this.transpose;
  }

  /**
   * @param newTranspose the new {@link #getTranspose() transpose}.
   * @return a {@link StaveVoice} with the given {@link #getTranspose() transpose} and all other properties like
   *         {@code this} one. Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public StaveVoice setTranspose(int newTranspose) {

    if (this.transpose == newTranspose) {
      return this;
    }
    StaveVoice voice = makeMutable();
    voice.transpose = newTranspose;
    return voice;
  }

  /**
   * @return the shift to raise or lower the music code in this voice by one or more octaves. Can be used to avoid the
   *         need to write lots of apostrophes or commas to raise or lower notes.
   */
  public int getOctaveShift() {

    return this.octaveShift;
  }

  /**
   * @param newOctaveShift the new {@link #getOctaveShift() octaveShift}.
   * @return a {@link StaveVoice} with the given {@link #getOctaveShift() octaveShift} and all other properties like
   *         {@code this} one. Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public StaveVoice setOctaveShift(int newOctaveShift) {

    if (this.octaveShift == newOctaveShift) {
      return this;
    }
    StaveVoice voice = makeMutable();
    voice.octaveShift = newOctaveShift;
    return voice;
  }

  /**
   * @return the {@link Stave} owning this {@link StaveVoice}. Gets automatically assigned when the {@link StaveVoice}
   *         is {@link Stave#addVoice(StaveVoice) added}.
   */
  public Stave getStave() {

    return this.stave;
  }

  /**
   * @param newStave the new {@link #getStave() stave}.
   * @return a {@link StaveVoice} with the given {@link #getOctaveShift() octaveShift} and all other properties like
   *         {@code this} one. Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public StaveVoice setStave(Stave newStave) {

    if (this.stave == newStave) {
      return this;
    }
    StaveVoice voice = makeMutable();
    voice.stave = newStave;
    return voice;
  }

  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  @Override
  public StaveVoice makeImmutable() {

    this.immutable = true;
    return this;
  }

  @Override
  public int hashCode() {

    return this.id.hashCode();
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == this) {
      return true;
    } else if ((obj == null) || (obj.getClass() != getClass())) {
      return false;
    }
    StaveVoice other = (StaveVoice) obj;
    if (!Objects.equals(this.id, other.id) || !Objects.equals(this.name, other.name)
        || !Objects.equals(this.abbreviation, other.abbreviation) || !Objects.equals(this.instrument, other.instrument)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append(this.id);
    if (this.name != null) {
      sb.append('=');
      sb.append(this.name);
    }
    if ((this.abbreviation != null) && !Objects.equals(this.abbreviation, this.id)) {
      sb.append('=');
      sb.append(this.abbreviation);
    }
    if (this.instrument != null) {
      sb.append('@');
      sb.append(this.instrument);
    }
    if ((this.stemDirection != null) && (this.stemDirection != StemDirection.AUTO)) {
      sb.append('|');
      sb.append(this.stemDirection);
    }
    return sb.toString();
  }

  private static StaveVoice ofInternal(String id, String name) {

    StaveVoice voice = new StaveVoice(id, name, null, null, null, 0, 0);
    voice.makeImmutable();
    ID2VOICE_MAP.put(id, voice);
    return voice;
  }

  /**
   * @param id the {@link #getId() ID} of the requested build-in {@link StaveVoice}.
   * @return the pre-defined {@link StaveVoice} with the given {@code id} or {@code null} if no such {@link StaveVoice}
   *         exists.
   */
  public static StaveVoice get(String id) {

    return ID2VOICE_MAP.get(id);
  }

}
