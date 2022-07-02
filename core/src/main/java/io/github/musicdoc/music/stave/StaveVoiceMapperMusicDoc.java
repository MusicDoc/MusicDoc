package io.github.musicdoc.music.stave;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;
import io.github.musicdoc.music.instrument.InstrumentMapper;
import io.github.musicdoc.music.instrument.InstrumentMapperMusicDoc;

/**
 * {@link StaveVoiceMapper} for {@link SongFormatMusicDoc}.
 */
public class StaveVoiceMapperMusicDoc extends StaveVoiceMapper {

  /** The singleton instance. */
  public static final StaveVoiceMapperMusicDoc INSTANCE = new StaveVoiceMapperMusicDoc();

  /**
   * The constructor.
   */
  protected StaveVoiceMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  protected InstrumentMapper getInstrumentMapper() {

    return InstrumentMapperMusicDoc.INSTANCE;
  }

}
