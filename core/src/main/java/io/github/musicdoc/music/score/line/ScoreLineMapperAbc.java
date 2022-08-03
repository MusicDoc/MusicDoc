package io.github.musicdoc.music.score.line;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatAbc;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.score.cell.ScoreCell;

/**
 * {@link ScoreLineMapper} for {@link SongFormatAbc}.
 */
public class ScoreLineMapperAbc extends ScoreLineMapperBase {

  /** The singleton instance. */
  public static final ScoreLineMapperAbc INSTANCE = new ScoreLineMapperAbc();

  /**
   * The constructor.
   */
  protected ScoreLineMapperAbc() {

    super("[V:", "]", "%");
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatAbc.INSTANCE;
  }

  @Override
  public ScoreLine read(MusicInputStream in, SongFormatContext context) {

    String lookahead = in.peek(2);
    if ("%%".equals(lookahead)) {
      return null;
    }
    ScoreLine line = super.read(in, context);
    if (line instanceof ScoreVoiceLine) {
      if (in.peek(2).equals("w:")) {
        String property = in.readPropertyStart();
        assert (property.equals("w"));
        String text = in.readPropertyValue();
        int cellIndex = 0;
        int start = 0;
        int len = text.length();
        int i = 0;
        while (i < len) {
          char c = text.charAt(i++);
          boolean lyricEnd = false;
          if (c == ' ') {
            lyricEnd = true;
          } else if (c == '-') {
            lyricEnd = true;
          } else if (c == '_') {
            lyricEnd = true;
            if (i > (start + 1)) {
              i--;
            }
          } else if (i == len) {
            lyricEnd = true;
          }
          if (lyricEnd) {
            while ((i < len) && (text.charAt(i) == ' ')) {
              i++;
            }
            ScoreCell cell = line.getOrCreateCell(cellIndex);
            int end = i;
            if (end > len) {
              end = len;
            }
            String lyric = text.substring(start, end);
            start = i;
            ScoreCell newCell = cell.setLyric(lyric);
            if (newCell != cell) {
              line.getCells().set(cellIndex, newCell);
            }
            cellIndex++;
          }
        }
      }
    }
    return line;
  }

}
