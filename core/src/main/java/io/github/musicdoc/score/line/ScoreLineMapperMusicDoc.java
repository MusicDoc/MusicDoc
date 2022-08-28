package io.github.musicdoc.score.line;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.format.SongFormatMusicDoc;
import io.github.musicdoc.io.MusicInputStream;

/**
 * {@link ScoreLineMapper} for {@link SongFormatMusicDoc}.
 */
public class ScoreLineMapperMusicDoc extends ScoreLineMapperBase {

  /** The singleton instance. */
  public static final ScoreLineMapperMusicDoc INSTANCE = new ScoreLineMapperMusicDoc();

  private final CharFilter stopFilter;

  /**
   * The constructor.
   */
  protected ScoreLineMapperMusicDoc() {

    super("$", " ", "%");
    this.stopFilter = getBarLineTypeMapper().getStartFilter().join(NEWLINE_CHAR, CHORD_START, ITEM_START);
  }

  @Override
  protected String readLyric(MusicInputStream in, SongFormatContext context) {

    return in.readUntil(this.stopFilter, true);
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatMusicDoc.INSTANCE;
  }

}
