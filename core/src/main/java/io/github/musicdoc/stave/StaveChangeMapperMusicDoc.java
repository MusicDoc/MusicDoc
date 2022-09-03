package io.github.musicdoc.stave;

import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.harmony.key.MusicalKey;
import io.github.musicdoc.rhythm.metre.Metre;

/**
 * {@link StaveMapper} for {@link io.github.musicdoc.format.SongFormatMusicDoc}.
 */
public class StaveChangeMapperMusicDoc extends AbstractStaveMapperMusicDoc<StaveChange> implements StaveChangeMapper {

  /** The singleton instance. */
  public static final StaveChangeMapperMusicDoc INSTANCE = new StaveChangeMapperMusicDoc();

  /**
   * The constructor.
   */
  protected StaveChangeMapperMusicDoc() {

    super();
  }

  @Override
  protected StaveChange createStave(Clef clef, MusicalKey key, Metre beat) {

    return new StaveChange(clef, key, beat);
  }
}
