package io.github.musicdoc.format;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;

/**
 * Test of {@link SongFormatAbc}.
 */
public class SongFormatAbcTest extends SongFormatTest {

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
