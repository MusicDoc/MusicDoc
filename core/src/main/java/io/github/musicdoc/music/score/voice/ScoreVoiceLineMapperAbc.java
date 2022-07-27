package io.github.musicdoc.music.score.voice;

import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;

/**
 * {@link ScoreVoiceLineMapper} for {@link SongFormatAbc}.
 */
public class ScoreVoiceLineMapperAbc extends ScoreVoiceLineMapperBase {

  /** The singleton instance. */
  public static final ScoreVoiceLineMapperAbc INSTANCE = new ScoreVoiceLineMapperAbc();

  /**
   * The constructor.
   */
  protected ScoreVoiceLineMapperAbc() {

    super("[V:", "]");
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
