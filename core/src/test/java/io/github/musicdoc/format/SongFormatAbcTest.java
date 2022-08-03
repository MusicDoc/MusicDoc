package io.github.musicdoc.format;

/**
 * Test of {@link SongFormatAbc}.
 */
public class SongFormatAbcTest extends SongFormatTest {

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
