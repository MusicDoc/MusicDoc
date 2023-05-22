package io.github.musicdoc.rhythm.punctuation;

import io.github.mmm.scanner.CharStreamScanner;
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

    CharStreamScanner scanner = in.getScanner();
    if (scanner.expectOne('.')) {
      int dots = 1;
      if (scanner.expectOne('.')) {
        dots++;
        if (scanner.expectOne('.')) {
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
