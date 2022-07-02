package io.github.musicdoc.music.harmony.chord;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;
import io.github.musicdoc.music.harmony.TonalSystemMapper;
import io.github.musicdoc.music.harmony.TonalSystemMapperMusicDoc;
import io.github.musicdoc.music.tone.TonePitchMapper;
import io.github.musicdoc.music.tone.TonePitchMapperMusicDoc;

/**
 * {@link ChordMapper} for {@link SongFormatMusicDoc}.
 */
public class ChordMapperMusicDoc extends ChordMapper {

  /** The singleton instance. */
  public static final ChordMapperMusicDoc INSTANCE = new ChordMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ChordMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  protected TonalSystemMapper getTonalSystemMapper() {

    return TonalSystemMapperMusicDoc.INSTANCE;
  }

  @Override
  protected ChordExtensionMapper getChordExtensionMapper() {

    return ChordExtensionMapperMusicDoc.INSTANCE;
  }

  @Override
  protected TonePitchMapper getTonePitchMapper() {

    return TonePitchMapperMusicDoc.INSTANCE;
  }

}
