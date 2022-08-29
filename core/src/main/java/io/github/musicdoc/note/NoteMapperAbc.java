package io.github.musicdoc.note;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * {@link NoteMapper} for {@link SongFormatAbc}.
 */
public class NoteMapperAbc extends NoteMapperBase {

  /** The singleton instance. */
  public static final NoteMapperAbc INSTANCE = new NoteMapperAbc();

  /**
   * The constructor.
   */
  protected NoteMapperAbc() {

    super('[', ']');
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
