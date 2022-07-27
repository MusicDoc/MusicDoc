package io.github.musicdoc.music.stave;

import io.github.musicdoc.music.clef.Clef;
import io.github.musicdoc.music.harmony.key.MusicalKey;
import io.github.musicdoc.music.rythm.beat.Beat;

/**
 * {@link StaveMapper} for {@link io.github.musicdoc.music.format.SongFormatMusicDoc}.
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
  protected Stave createStave(Clef clef, MusicalKey key, Beat beat) {

    return new Stave(clef, key, beat);
  }

}
