package io.github.musicdoc.music.score.voice;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatMusicDoc;

/**
 * {@link ScoreVoiceLineMapper} for {@link SongFormatMusicDoc}.
 */
public class ScoreVoiceLineMapperMusicDoc extends ScoreVoiceLineMapperBase {

  /** The singleton instance. */
  public static final ScoreVoiceLineMapperMusicDoc INSTANCE = new ScoreVoiceLineMapperMusicDoc();

  /**
   * The constructor.
   */
  protected ScoreVoiceLineMapperMusicDoc() {

    super("$", " ");
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
