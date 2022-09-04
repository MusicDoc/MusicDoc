package io.github.musicdoc.rhythm.value;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;
import io.github.musicdoc.rhythm.fraction.SimpleFraction;
import io.github.musicdoc.rhythm.punctuation.Punctuation;

/**
 * {@link MusicalValueMapper} for {@link SongFormatAbc}.
 */
public class MusicalValueMapperAbc extends MusicalValueMapperBase {

  /** The singleton instance. */
  public static final MusicalValueMapperAbc INSTANCE = new MusicalValueMapperAbc();

  /**
   * The constructor.
   */
  protected MusicalValueMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

  @Override
  protected Punctuation normalizePunctuation(Punctuation punctuation, SimpleFraction<?> outValue) {

    outValue.multiply(punctuation);
    return null;
  }

}
