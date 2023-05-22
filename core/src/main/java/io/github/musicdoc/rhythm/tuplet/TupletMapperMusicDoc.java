package io.github.musicdoc.rhythm.tuplet;

import io.github.mmm.scanner.CharStreamScanner;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.format.SongFormatMusicDoc;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link TupletMapper} for {@link SongFormatMusicDoc}.
 */
public class TupletMapperMusicDoc extends TupletMapperBase {

  private static final char TUPLET_INDICATOR = 'T';

  /** The singleton instance. */
  public static final TupletMapperMusicDoc INSTANCE = new TupletMapperMusicDoc();

  /**
   * The constructor.
   */
  protected TupletMapperMusicDoc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

  @Override
  public Tuplet read(MusicInputStream in, SongFormatContext context) {

    CharStreamScanner scanner = in.getScanner();
    Tuplet tuplet = super.read(in, context);
    if (tuplet == null) {
      if (scanner.expectOne(TUPLET_INDICATOR)) {
        TupletContext tc = readTupletContext(in, context, false);
        if (tc == null) {
          in.addError("Invalid tuplet");
        } else {
          tuplet = tc.getTuplet();
        }
      }
    }
    return tuplet;
  }

  @Override
  public void write(Tuplet tuplet, MusicOutputStream out, SongFormatContext context) {

    writeTuplet(tuplet, 0, TUPLET_INDICATOR, out, context);
  }
}
