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
  public Integer parse(MusicInputStream chars, SongFormatOptions options) {

    Integer value = chars.readInteger();
    return value;
  }

  @Override
  public void format(Integer value, MusicOutputStream out, SongFormatOptions options) {

    out.append(value);
  }

  @Override
  protected SongFormat getFormat() {

    return null;
  }

}
