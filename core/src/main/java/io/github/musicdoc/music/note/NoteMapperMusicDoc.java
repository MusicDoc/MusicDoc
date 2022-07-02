package io.github.musicdoc.music.note;

import io.github.musicdoc.music.decoration.MusicalDecorationMapper;
import io.github.musicdoc.music.decoration.MusicalDecorationMapperMusicDoc;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;
import io.github.musicdoc.music.rythm.value.MusicalValueMapper;
import io.github.musicdoc.music.rythm.value.MusicalValueMapperMusicDoc;
import io.github.musicdoc.music.tone.ToneMapper;
import io.github.musicdoc.music.tone.ToneMapperMusicDoc;

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

  @Override
  protected MusicalValueMapper getValueMapper() {

    return MusicalValueMapperMusicDoc.INSTANCE;
  }

  @Override
  protected MusicalDecorationMapper getDecorationMapper() {

    return MusicalDecorationMapperMusicDoc.INSTANCE;
  }

  @Override
  protected ToneMapper getToneMapper() {

    return ToneMapperMusicDoc.INSTANCE;
  }

}
