package io.github.musicdoc.music.harmony.chord;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link ChordExtensionMapper} for {@link SongFormatMusicDoc}.
 */
public class ChordExtensionMapperMusicDoc extends ChordExtensionMapper {

  /** The singleton instance. */
  public static final ChordExtensionMapperMusicDoc INSTANCE = new ChordExtensionMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ChordExtensionMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
