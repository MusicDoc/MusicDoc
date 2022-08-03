package io.github.musicdoc.stave;

import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.harmony.key.MusicalKey;
import io.github.musicdoc.rythm.beat.Beat;

/**
 * {@link StaveMapper} for {@link io.github.musicdoc.format.SongFormatMusicDoc}.
 */
public class StaveChangeMapperAbc extends AbstractStaveMapperAbc<StaveChange> implements StaveChangeMapper {

  /** The singleton instance. */
  public static final StaveChangeMapperAbc INSTANCE = new StaveChangeMapperAbc();

  /**
   * The constructor.
   */
  protected StaveChangeMapperAbc() {

    super();
  }

  @Override
  protected StaveProperties createProperties() {

    return new StaveProperties(getClefMapper(), getKeyMapper(), getBeatMapper());
  }

  @Override
  protected StaveChange createStave(Clef clef, MusicalKey key, Beat beat) {

    return new StaveChange(clef, key, beat);
  }

}
