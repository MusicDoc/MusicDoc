package io.github.musicdoc.music.note;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link NoteMapper} for {@link SongFormatMusicDoc}.
 */
public class NoteMapperMusicDoc extends NoteMapper {

  /** The singleton instance. */
  public static final NoteMapperMusicDoc INSTANCE = new NoteMapperMusicDoc();

  /**
   * The constructor.
   */
  protected NoteMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
