package io.github.musicdoc.format;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link AbstractMapper Mapper} for a simple {@link String} value.
 */
public class StringMapper extends AbstractMapper<String> {

  /** The singleton instance. */
  public static final StringMapper INSTANCE = new StringMapper();

  @Override
  public String read(MusicInputStream in, SongFormatContext context) {

    String value = in.readUntil(NEWLINE_CHAR, true);
    return value;
  }

  @Override
  public void write(String value, MusicOutputStream out, SongFormatContext context) {

    out.write(value);
  }

  @Override
  protected SongFormat getFormat() {

    return null;
  }

}
