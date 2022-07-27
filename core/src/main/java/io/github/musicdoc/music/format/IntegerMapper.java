package io.github.musicdoc.music.format;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link AbstractMapper Mapper} for a simple {@link Integer} value.
 */
public class IntegerMapper extends AbstractMapper<Integer> {

  /** The singleton instance. */
  public static final IntegerMapper INSTANCE = new IntegerMapper();

  @Override
  public Integer read(MusicInputStream in, SongFormatContext context) {

    Integer value = in.readInteger();
    return value;
  }

  @Override
  public void write(Integer value, MusicOutputStream out, SongFormatContext context) {

    out.write(value);
  }

  @Override
  protected SongFormat getFormat() {

    return null;
  }

}
