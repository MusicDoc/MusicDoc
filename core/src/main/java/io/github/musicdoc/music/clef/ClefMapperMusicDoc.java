package io.github.musicdoc.music.clef;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link ClefMapper} for {@link SongFormatMusicDoc}.
 */
public class ClefMapperMusicDoc extends ClefMapperAbc {

  /** The singleton instance. */
  public static final ClefMapperMusicDoc INSTANCE = new ClefMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ClefMapperMusicDoc() {

    super();
  }

  @Override
  protected void registerClefs() {

    super.registerClefs();
    addClef(Clef.G);
    addClef(Clef.F);
    addClef(Clef.C);
    addClef(Clef.SOPRANO);
    addClef(Clef.MEZZO_SOPRANO);
    addClef(Clef.BARITONE);
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
