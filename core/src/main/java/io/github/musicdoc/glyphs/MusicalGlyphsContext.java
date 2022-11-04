package io.github.musicdoc.glyphs;

import java.util.Objects;

import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.glyphs.smufl.SmuflGlyphs;
import io.github.musicdoc.glyphs.unicode.UnicodeGlyphs;
import io.github.musicdoc.note.StemDirection;
import io.github.musicdoc.rhythm.item.ValuedItem;

/**
 * Interface for the context to customize {@link MusicalGlyphs#getGlyphs(MusicalGlyphsContext) glyphs}.
 *
 * @see MusicalGlyphsContextOptions
 */
public class MusicalGlyphsContext implements MusicalGlyphsOptions, MutableObject<MusicalGlyphsContext> {

  /** The immutable default instance. */
  public static final MusicalGlyphsContext DEFAULT = new MusicalGlyphsContext().makeImmutable();

  private final boolean enforceUnicode;

  private StemDirection stemDirection;

  private Clef clef;

  private MusicalGlyphsContextOptions options;

  private StemDirection currentStemDirection;

  private boolean immutable;

  /**
   * The constructor.
   */
  public MusicalGlyphsContext() {

    this(StemDirection.AUTO, Clef.TREBLE, MusicalGlyphsContextOptions.DEFAULT, false);
  }

  /**
   * The constructor.
   *
   * @param stemDirection the {@link #getStemDirection() stem direction}.
   * @param clef the {@link #getClef() clef}.
   * @param options the {@link #getOptions() options}.
   * @param enforceUnicode the {@link #isEnforceUnicode() enforce unicode} flag.
   */
  public MusicalGlyphsContext(StemDirection stemDirection, Clef clef, MusicalGlyphsContextOptions options,
      boolean enforceUnicode) {

    super();
    Objects.requireNonNull(stemDirection);
    Objects.requireNonNull(clef);
    Objects.requireNonNull(options);
    this.stemDirection = stemDirection;
    this.clef = clef;
    this.options = options;
    this.enforceUnicode = enforceUnicode;
    this.currentStemDirection = stemDirection;
  }

  private MusicalGlyphsContext(MusicalGlyphsContext copy, MutableObjecteCopier copier) {

    this(copy.stemDirection, copy.clef, copy.options, copy.enforceUnicode);
  }

  /**
   * @return immutable {@code true} if immutable, {@code false} otherwise.
   */
  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  @Override
  public MusicalGlyphsContext makeImmutable() {

    this.immutable = true;
    return this;
  }

  @Override
  public MusicalGlyphsContext copy(MutableObjecteCopier copier) {

    return new MusicalGlyphsContext(this, copier);
  }

  /**
   * @return the {@link MusicalGlyphsContextOptions}. Will not be {@code null}.
   */
  public MusicalGlyphsContextOptions getOptions() {

    return this.options;
  }

  /**
   * @param newOptions the new {@link #getOptions() options}.
   * @return a new {@link MusicalGlyphsContext} if {@link #getOptions() options} changed and {@link #isImmutable()
   *         immutable}, otherwise {@code this} instance itself.
   */
  public MusicalGlyphsContext setOptions(MusicalGlyphsContextOptions newOptions) {

    Objects.requireNonNull(newOptions);
    if (newOptions == this.options) {
      return this;
    }
    MusicalGlyphsContext context = makeMutable();
    context.options = newOptions;
    return context;
  }

  @Override
  public boolean isOmitFlags() {

    return this.options.isOmitFlags();
  }

  @Override
  public boolean isNoteheadOnly() {

    return this.options.isNoteheadOnly();
  }

  /**
   * @return {@code true} to enforce {@link UnicodeGlyphs}, {@code false} otherwise (for {@link SmuflGlyphs}).
   */
  public boolean isEnforceUnicode() {

    return this.enforceUnicode;
  }

  /**
   * @return the {@link StemDirection} used to draw stems.
   */
  public StemDirection getStemDirection() {

    return this.stemDirection;
  }

  /**
   * @param direction the new {@link #getStemDirection() stem direction}.
   * @return a new {@link MusicalGlyphsContext} if {@link #getStemDirection()} changed and {@link #isImmutable()
   *         immutable}, otherwise {@code this} instance itself.
   */
  public MusicalGlyphsContext setStemDirection(StemDirection direction) {

    if (direction == this.stemDirection) {
      return this;
    }
    MusicalGlyphsContext context = makeMutable();
    context.stemDirection = direction;
    return context;
  }

  /**
   * @return the computed {@link StemDirection} of the current {@link ValuedItem item} While {@link #getStemDirection()}
   *         may return {@link StemDirection#AUTO} this method should return the actual computed direction.
   */
  public StemDirection getCurrentStemDirection() {

    return this.currentStemDirection;
  }

  /**
   * @param direction the new value of {@link #getCurrentStemDirection()}. If {@link #isImmutable()} is {@code true}
   *        this method will have no effect.
   */
  public void setCurrentStemDirection(StemDirection direction) {

    if ((direction == null) || (direction == StemDirection.AUTO)) {
      throw new IllegalArgumentException("" + direction);
    }
    assert ((this.stemDirection == StemDirection.AUTO) || (this.stemDirection == direction));
    if (!this.immutable) {
      this.currentStemDirection = direction;
    }
  }

  /**
   * @return the {@link Clef}.
   */
  public Clef getClef() {

    return this.clef;
  }

  /**
   * @param newClef the new {@link #getClef() clef}.
   * @return a new {@link MusicalGlyphsContext} if the {@link Clef} changed and {@link #isImmutable() immutable},
   *         otherwise {@code this} instance itself.
   */
  public MusicalGlyphsContext setClef(Clef newClef) {

    if (newClef == this.clef) {
      return this;
    }
    MusicalGlyphsContext context = makeMutable();
    context.clef = newClef;
    return context;
  }

  @Override
  public void toString(StringBuilder sb) {

    if (this.enforceUnicode) {
      sb.append("UTF");
    } else {
      sb.append("SMuFL");
    }
  }

}
