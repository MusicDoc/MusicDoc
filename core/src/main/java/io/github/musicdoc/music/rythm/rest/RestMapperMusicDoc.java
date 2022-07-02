package io.github.musicdoc.music.rythm.rest;

import io.github.musicdoc.music.decoration.MusicalDecorationMapper;
import io.github.musicdoc.music.decoration.MusicalDecorationMapperMusicDoc;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;
import io.github.musicdoc.music.rythm.value.MusicalValueMapper;
import io.github.musicdoc.music.rythm.value.MusicalValueMapperMusicDoc;

/**
 * {@link RestMapper} for {@link SongFormatMusicDoc}.
 */
public class RestMapperMusicDoc extends RestMapper {

  /** The singleton instance. */
  public static final RestMapperMusicDoc INSTANCE = new RestMapperMusicDoc();

  /**
   * The constructor.
   */
  protected RestMapperMusicDoc() {

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

}
