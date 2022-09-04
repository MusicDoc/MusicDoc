package io.github.musicdoc.rhythm.tuplet;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link TupletMapper} for {@link SongFormatAbc}.
 */
public class TupletMapperAbc extends TupletMapperBase {

  /** The singleton instance. */
  public static final TupletMapperAbc INSTANCE = new TupletMapperAbc();

  /**
   * The constructor.
   */
  protected TupletMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

  @Override
  public Tuplet read(MusicInputStream in, SongFormatContext context) {

    return null;
  }

  @Override
  public void write(Tuplet punctuation, MusicOutputStream out, SongFormatContext context) {

    assert (punctuation == null);
  }

}
