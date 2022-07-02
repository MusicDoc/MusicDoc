package io.github.musicdoc.music.score.voice;

import java.util.List;

import io.github.musicdoc.StringHelper;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatOpenSong;
import io.github.musicdoc.music.format.SongFormatOptions;
import io.github.musicdoc.music.harmony.chord.ChordContainer;
import io.github.musicdoc.music.harmony.chord.ChordContainerMapper;
import io.github.musicdoc.music.harmony.chord.ChordContainerMapperOpenSong;
import io.github.musicdoc.music.score.comment.ScoreCommentLineMapper;

/**
 * {@link ScoreVoiceLineMapper} for {@link io.github.musicdoc.music.format.SongFormatOpenSong OpenSong format}.
 */
public class ScoreVoiceLineMapperOpenSong extends ScoreVoiceLineMapper {

  /** The singleton instance. */
  public static final ScoreVoiceLineMapperOpenSong INSTANCE = new ScoreVoiceLineMapperOpenSong();

  static final char BEGIN_ITEMS = '=';

  static final char BEGIN_LYRICS = ' ';

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

  @Override
  public ScoreVoiceLine parse(MusicInputStream chars, SongFormatOptions options) {

    char c = chars.peek();
    if ((c == ScoreCommentLineMapper.BEGIN_COMMENT) || ListCharFilter.NEWLINE.accept(c)) {
      return null;
    }
    ScoreVoiceLine line = new ScoreVoiceLine();
    if (c == BEGIN_CHORDS) {
      chars.next();
      ScoreVoiceLineContinuation continuation = ScoreVoiceLineContinuation.of(c);
      if (continuation != null) {
        chars.next();
        line.setContinuation(continuation);
      }
      while (chars.hasNext() && !chars.skipNewline()) {
        ChordContainer chordContainer = getChordContainerMapper().parse(chars, options);
        if (chordContainer == null) {
          break;
        } else {
          line.addCell(new ScoreVoiceCell(chordContainer));
        }
      }
      c = chars.peek();
    }
    if (c == BEGIN_LYRICS) {
      chars.next();
    }
    int cellCount = line.getCellCount();
    if (cellCount == 0) {
      String lyric = chars.readUntil(ListCharFilter.NEWLINE, true);
      line.addCell(new ScoreVoiceCell(lyric));
      chars.skipNewline();
    } else {
      int cellMax = cellCount - 1;
      for (int i = 0; i < cellMax; i++) {
        ScoreVoiceCell cell = line.getCell(i);
        ChordContainer chordContainer = cell.getChordContainer();
        int chordLength = chordContainer.toString().length();
        String lyric = chars.readUntil(ListCharFilter.NEWLINE, chordLength);
        cell.setLyric(lyric);
      }
      String lyric = chars.readUntil(ListCharFilter.NEWLINE, true);
      if (lyric.length() > 0) {
        ScoreVoiceCell cell = line.getCell(cellMax);
        cell.setLyric(lyric);
      }
      chars.skipNewline();
    }
    return line;
  }

  /**
   * @return the {@link ChordContainerMapper}.
   */
  private ChordContainerMapper getChordContainerMapper() {

    return ChordContainerMapperOpenSong.INSTANCE;
  }

  @Override
  public void format(ScoreVoiceLine line, MusicOutputStream out, SongFormatOptions options) {

    if (line == null) {
      return;
    }
    out.append(BEGIN_CHORDS);
    ScoreVoiceLineContinuation continuation = line.getContinuation();
    if (continuation != null) {
      out.append(continuation.getSymbol());
    }
    StringBuilder lyrics = new StringBuilder();
    lyrics.append(BEGIN_LYRICS);
    List<ScoreVoiceCell> cells = line.getCells();
    int cellMax = cells.size() - 1;
    for (int i = 0; i <= cellMax; i++) {
      ScoreVoiceCell cell = cells.get(i);
      int chordLength = 0;
      ChordContainer chordContainer = cell.getChordContainer();
      if (chordContainer != null) {
        String chord = chordContainer.toString();
        out.append(chord); // ChordContainerMapper.INSTANCE.format(chordContainer, buffer, options);
        chordLength = chord.length();
        if (i < cellMax) {
          out.append(' ');
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
    out.append(NEWLINE);
    out.append(lyrics);
    out.append(NEWLINE);
  }
}
