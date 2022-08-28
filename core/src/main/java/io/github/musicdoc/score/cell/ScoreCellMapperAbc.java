package io.github.musicdoc.score.cell;

import io.github.musicdoc.bar.BarLine;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.format.SongFormatMusicDoc;
import io.github.musicdoc.io.MusicOutputStream;

/**
 * {@link ScoreCellMapper} for {@link SongFormatMusicDoc}.
 */
public class ScoreCellMapperAbc extends ScoreCellMapperBase {

  /** The singleton instance. */
  public static final ScoreCellMapperAbc INSTANCE = new ScoreCellMapperAbc();

  /**
   * The constructor.
   */
  protected ScoreCellMapperAbc() {

    super();
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

  @Override
  public void write(ScoreCell cell, MusicOutputStream out, SongFormatContext context) {

    if (cell == null) {
      return;
    }
    getStaveChangeMapper().write(cell.getStaveChange(), out, context);
    getChordContainerMapper().write(cell.getChordContainer(), out, context);
    getValuedItemMapper().write(cell.getItem(), out, context);
    BarLine bar = cell.getBar();
    if (bar != null) {
      out.write(' ');
    }
    getBarLineMapper().write(bar, out, context);
  }

}
