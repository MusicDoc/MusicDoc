package io.github.musicdoc.rhythm.fraction;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.glyphs.MusicalGlyphs;
import io.github.musicdoc.rhythm.punctuation.Punctuation;
import io.github.musicdoc.rhythm.tuplet.Tuplet;
import io.github.musicdoc.rhythm.value.MusicalValue;

/**
 * A {@link FractionVariation} is a {@link SimpleFraction} that modifies the {@link #getPlain() plain fraction} of a
 * {@link MusicalValue}. There are particular symbols to visualize a {@link FractionVariation} in a score.
 *
 * @see Punctuation
 * @see Tuplet
 */
public abstract class FractionVariation extends SimpleFraction<FractionVariation> implements MusicalGlyphs {

  private final String text;

  /**
   * The constructor.
   *
   * @param beats the {@link #getBeats() beats}.
   * @param unit the {@link #getUnit() unit}.
   * @param text the {@link #toString() string representation}
   */
  protected FractionVariation(int beats, int unit, String text) {

    super(beats, unit);
    this.text = text;
    makeImmutable();
  }

  @Override
  public FractionVariation copy(MutableObjecteCopier copier) {

    throw new UnsupportedOperationException();
  }

  @Override
  public FractionVariation getPlain() {

    return this;
  }

  @Override
  protected String getText() {

    return this.text;
  }

}
