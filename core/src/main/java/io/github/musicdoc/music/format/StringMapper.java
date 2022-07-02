package io.github.musicdoc.music.format;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link AbstractMapper Mapper} for a simple {@link String} value.
 */
public class StringMapper extends AbstractMapper<String> {

  /** The singleton instance. */
  public static final StringMapper INSTANCE = new StringMapper();

  @Override
  public String parse(MusicInputStream chars, SongFormatOptions options) {

    String value = chars.readPropertyValue();
    return value;
  }

  @Override
  public void format(String value, MusicOutputStream out, SongFormatOptions options) {

    out.append(value);
  }

  @Override
  protected SongFormat getFormat() {

    return null;
  }

}
