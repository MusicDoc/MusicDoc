package io.github.musicdoc.music.harmony;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;
import io.github.musicdoc.music.tone.TonePitchMapper;
import io.github.musicdoc.music.tone.TonePitchMapperMusicDoc;

/**
 * {@link MusicalKeyMapper} for {@link SongFormatMusicDoc}.
 */
public class MusicalKeyMapperMusicDoc extends MusicalKeyMapper {

  /** The singleton instance. */
  public static final MusicalKeyMapperMusicDoc INSTANCE = new MusicalKeyMapperMusicDoc();

  /**
   * The constructor.
   */
  protected MusicalKeyMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  protected TonePitchMapper getTonePitchMapper() {

    return TonePitchMapperMusicDoc.INSTANCE;
  }

  @Override
  protected TonalSystemMapper getTonalSystemMapper() {

    return TonalSystemMapperMusicDoc.INSTANCE;
  }

}
