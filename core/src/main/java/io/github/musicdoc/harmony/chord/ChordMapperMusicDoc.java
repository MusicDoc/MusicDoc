package io.github.musicdoc.harmony.chord;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link ChordMapper} for {@link SongFormatMusicDoc}.
 */
public class ChordMapperMusicDoc extends ChordMapper {

  /** The singleton instance. */
  public static final ChordMapperMusicDoc INSTANCE = new ChordMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ChordMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
