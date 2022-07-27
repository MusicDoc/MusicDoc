package io.github.musicdoc.music.harmony.chord;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

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
