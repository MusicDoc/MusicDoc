package io.github.musicdoc.music.harmony.chord;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link ChordContainerMapper} for {@link SongFormatMusicDoc}.
 */
public class ChordContainerMapperMusicDoc extends ChordContainerMapperBase {

  /** The singleton instance. */
  public static final ChordContainerMapperMusicDoc INSTANCE = new ChordContainerMapperMusicDoc();

  /**
   * The constructor.
   */
  public ChordContainerMapperMusicDoc() {

    super(CHORD_START, CHORD_END);
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
