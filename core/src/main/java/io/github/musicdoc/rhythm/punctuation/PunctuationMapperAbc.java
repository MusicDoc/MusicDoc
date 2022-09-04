package io.github.musicdoc.rhythm.punctuation;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link PunctuationMapper} for {@link SongFormatAbc}.
 */
public class PunctuationMapperAbc extends PunctuationMapper {

  /** The singleton instance. */
  public static final PunctuationMapperAbc INSTANCE = new PunctuationMapperAbc();

  /**
   * The constructor.
   */
  protected PunctuationMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

  @Override
  public Punctuation read(MusicInputStream in, SongFormatContext context) {

    return null;
  }

  @Override
  public void write(Punctuation punctuation, MusicOutputStream out, SongFormatContext context) {

    assert (punctuation == null);
  }

}
