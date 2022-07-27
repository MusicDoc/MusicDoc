package io.github.musicdoc.music.score.voice;

import java.util.List;

import io.github.musicdoc.StringHelper;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.format.SongFormatOpenSong;
import io.github.musicdoc.music.harmony.chord.ChordContainer;
import io.github.musicdoc.music.score.comment.ScoreCommentLineMapper;

/**
 * {@link ScoreVoiceLineMapper} for {@link SongFormatOpenSong}.
 */
public class ScoreVoiceLineMapperOpenSong extends ScoreVoiceLineMapper {

  /** The singleton instance. */
  public static final ScoreVoiceLineMapperOpenSong INSTANCE = new ScoreVoiceLineMapperOpenSong();

  private static final char BEGIN_LYRICS = ' ';

  private static final char BEGIN_CHORDS = '.';

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

  @Override
  public ScoreVoiceLine read(MusicInputStream in, SongFormatContext context) {

    char c = in.peek();
    if ((c == ScoreCommentLineMapper.BEGIN_COMMENT) || ListCharFilter.NEWLINE.accept(c)) {
      return null;
    }
    ScoreVoiceLine line = new ScoreVoiceLine();
    if (c == BEGIN_CHORDS) {
      in.next();
      while (in.hasNext() && !in.skipNewline()) {
        ChordContainer chordContainer = getChordContainerMapper().read(in, context);
        if (chordContainer == null) {
          break;
        } else {
          line.addCell(new ScoreVoiceCell(chordContainer));
        }
      }
      c = in.peek();
    }
    if (c == BEGIN_LYRICS) {
      in.next();
    }
    int cellCount = line.getCellCount();
    if (cellCount == 0) {
      String lyric = in.readLine();
      line.addCell(new ScoreVoiceCell(lyric));
    } else {
      int cellMax = cellCount - 1;
      for (int i = 0; i < cellMax; i++) {
        ScoreVoiceCell cell = line.getCell(i);
        ChordContainer chordContainer = cell.getChordContainer();
        int chordLength = chordContainer.toString().length();
        String lyric = in.readUntil(ListCharFilter.NEWLINE, chordLength);
        cell.setLyric(lyric);
      }
      String lyric = in.readLine();
      if (lyric.length() > 0) {
        ScoreVoiceCell cell = line.getCell(cellMax);
        cell.setLyric(lyric);
      }
    }
    return line;
  }

  @Override
  public void write(ScoreVoiceLine line, MusicOutputStream out, SongFormatContext context) {

    if (line == null) {
      return;
    }
    out.write(BEGIN_CHORDS);
    StringBuilder lyrics = new StringBuilder();
    lyrics.append(BEGIN_LYRICS);
    List<ScoreVoiceCell> cells = line.getCells();
    int cellMax = cells.size() - 1;
    for (int i = 0; i <= cellMax; i++) {
      ScoreVoiceCell cell = cells.get(i);
      int chordLength = 0;
      ChordContainer chordContainer = cell.getChordContainer();
      if (chordContainer != null) {
        int col = out.getColumn();
        getChordContainerMapper().write(chordContainer, out, context);
        chordLength = out.getColumn() - col;
        assert (chordLength > 0);
        if (i < cellMax) {
          out.write(' ');
          chordLength++;
        }
      }
      String lyric = cell.getLyric();
      lyrics.append(lyric);
      if (i < cellMax) {
        int spaces = lyric.length() - chordLength;
        if (spaces > 0) {
          StringHelper.appendSpaces(out, spaces);
        } else if (spaces < 0) {
          StringHelper.appendSpaces(lyrics, -spaces);
        }
      }
    }
    out.write(NEWLINE);
    out.write(lyrics);
    out.write(NEWLINE);
  }
}
