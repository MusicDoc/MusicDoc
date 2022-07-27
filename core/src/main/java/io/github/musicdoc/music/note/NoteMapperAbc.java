package io.github.musicdoc.music.note;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;

/**
 * {@link NoteMapper} for {@link SongFormatAbc}.
 */
public class NoteMapperAbc extends NoteMapper {

  /** The singleton instance. */
  public static final NoteMapperAbc INSTANCE = new NoteMapperAbc();

  /**
   * The constructor.
   */
  protected NoteMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
