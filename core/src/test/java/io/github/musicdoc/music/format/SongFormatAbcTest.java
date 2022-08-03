package io.github.musicdoc.music.format;

/**
 * Test of {@link SongFormatAbc}.
 */
public class SongFormatAbcTest extends SongFormatTest {

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

}
