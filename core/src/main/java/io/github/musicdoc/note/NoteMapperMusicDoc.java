package io.github.musicdoc.note;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatMusicDoc;

/**
 * {@link NoteMapper} for {@link SongFormatMusicDoc}.
 */
public class NoteMapperMusicDoc extends NoteMapperBase {

  /** The singleton instance. */
  public static final NoteMapperMusicDoc INSTANCE = new NoteMapperMusicDoc();

  /**
   * The constructor.
   */
  protected NoteMapperMusicDoc() {

    super('\0', '\0');
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
