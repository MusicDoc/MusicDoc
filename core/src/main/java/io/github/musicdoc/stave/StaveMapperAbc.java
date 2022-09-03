package io.github.musicdoc.stave;

import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.harmony.key.MusicalKey;
import io.github.musicdoc.rhythm.metre.Metre;

/**
 * {@link StaveMapper} for {@link io.github.musicdoc.format.SongFormatMusicDoc}.
 */
public class StaveMapperAbc extends AbstractStaveMapperAbc<Stave> implements StaveMapper {

  /** The singleton instance. */
  public static final StaveMapperAbc INSTANCE = new StaveMapperAbc();

  /**
   * The constructor.
   */
  protected StaveMapperAbc() {

    super();
  }

  @Override
  protected StaveProperties createProperties() {

    return new StaveProperties(getClefMapper(), getKeyMapper(), getBeatMapper(), getStaveVoiceMapper());
  }

  @Override
  protected Stave createStave(Clef clef, MusicalKey key, Metre beat) {

    return new Stave(clef, key, beat);
  }

}
