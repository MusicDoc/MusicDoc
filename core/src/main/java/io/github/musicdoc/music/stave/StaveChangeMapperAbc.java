package io.github.musicdoc.music.stave;

import io.github.musicdoc.music.clef.Clef;
import io.github.musicdoc.music.harmony.key.MusicalKey;
import io.github.musicdoc.music.rythm.beat.Beat;

/**
 * {@link StaveMapper} for {@link io.github.musicdoc.music.format.SongFormatMusicDoc}.
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
