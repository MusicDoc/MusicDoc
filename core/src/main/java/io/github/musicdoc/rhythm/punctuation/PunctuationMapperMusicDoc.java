package io.github.musicdoc.rhythm.punctuation;

import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.format.SongFormatMusicDoc;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link PunctuationMapper} for {@link SongFormatMusicDoc}.
 */
public class PunctuationMapperMusicDoc extends PunctuationMapper {

  /** The singleton instance. */
  public static final PunctuationMapperMusicDoc INSTANCE = new PunctuationMapperMusicDoc();

  /**
   * The constructor.
   */
  protected PunctuationMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  public Punctuation read(MusicInputStream in, SongFormatContext context) {

    if (in.expect('.')) {
      int dots = 1;
      if (in.expect('.')) {
        dots++;
        if (in.expect('.')) {
          dots++;
        }
      }
      return Punctuation.of(dots);
    }
    return null;
  }

  @Override
  public void write(Punctuation punctuation, MusicOutputStream out, SongFormatContext context) {

    if (punctuation != null) {
      out.write(punctuation);
    }
  }
}
