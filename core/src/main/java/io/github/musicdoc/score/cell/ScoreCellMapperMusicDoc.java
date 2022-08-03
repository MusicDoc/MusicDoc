package io.github.musicdoc.score.cell;

import io.github.musicdoc.filter.CharFilter;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.format.SongFormatMusicDoc;
import io.github.musicdoc.io.MusicInputStream;

/**
 * {@link ScoreCellMapper} for {@link SongFormatMusicDoc}.
 */
public class ScoreCellMapperMusicDoc extends ScoreCellMapperBase {

  /** The singleton instance. */
  public static final ScoreCellMapperMusicDoc INSTANCE = new ScoreCellMapperMusicDoc();

  private final CharFilter stopFilter;

  /**
   * The constructor.
   */
  protected ScoreCellMapperMusicDoc() {

    super();
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
