package io.github.musicdoc.score.line;

import java.util.List;

import io.github.mmm.base.filter.ListCharFilter;
import io.github.mmm.scanner.CharStreamScanner;
import io.github.musicdoc.StringHelper;
import io.github.musicdoc.bar.BarLine;
import io.github.musicdoc.bar.BarLineMapper;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.format.SongFormatOpenSong;
import io.github.musicdoc.harmony.chord.ChordContainer;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.stave.voice.StaveVoiceContainer;

/**
 * {@link ScoreLineMapper} for {@link SongFormatOpenSong}.
 */
public class ScoreLineMapperOpenSong extends ScoreLineMapper {

  /** The singleton instance. */
  public static final ScoreLineMapperOpenSong INSTANCE = new ScoreLineMapperOpenSong();

  private final ListCharFilter lyricStopFilter;

  private static final char BEGIN_LYRICS = ' ';

  private static final char BEGIN_CHORDS = '.';

  /**
   * The constructor.
   */
  protected ScoreLineMapperOpenSong() {

    super();
    this.lyricStopFilter = getBarLineTypeMapper().getStartFilter().join(NEWLINE_CHAR, '\r');
  }

  @Override
  protected SongFormat getFormat() {

    return SongFormatOpenSong.INSTANCE;
  }

  @Override
  public ScoreLine read(MusicInputStream in, SongFormatContext context) {

    CharStreamScanner scanner = in.getScanner();
    if (scanner.expectOne(BEGIN_COMMENT)) {
      String comment = scanner.readLine();
      return new ScoreCommentLine(comment);
    } else if (scanner.skipNewLine() > 0) {
      return ScoreEmptyLine.INSTANCE;
    }
    ChordNode chordStart = new ChordNode();
    ChordNode chordNode = chordStart;
    char c = scanner.peek();
    if (c == BEGIN_CHORDS) {
      scanner.next();
      while (scanner.hasNext() && (scanner.skipNewLine() == 0)) {
        scanner.skipWhile(' ');
        int column = scanner.getColumn();
        ChordContainer chordContainer = getChordContainerMapper().read(in, context);
        if (chordContainer == null) {
          break;
        } else {
          chordNode = chordNode.append(chordContainer, column);
        }
      }
      c = scanner.peek();
    }
    BarLineMapper barLineMapper = getBarLineMapper();
    ScoreVoiceLine line = new ScoreVoiceLine();
    StaveVoiceContainer voiceContainer = context.getStaveVoiceContainer();
    if (voiceContainer != null) {
      StaveVoice voice = voiceContainer.getVoice(null);
      line = line.setVoice(voice);
    }
    if (c == BEGIN_LYRICS) {
      scanner.next();
    }
    chordNode = chordStart.next;
    while (scanner.hasNext() && (scanner.skipNewLine() == 0)) {
      int delta = 1000;
      if (chordNode != null) {
        int column = scanner.getColumn();
        delta = chordNode.column - column;
        if ((delta <= 0) && (chordNode.next != null)) {
          delta = chordNode.next.column - column;
        }
        if (delta <= 0) {
          delta = 1000;
        }
      }
      // TODO simplify on next mmm update:
      // scanner.readUntil(this.lyricStopFilter, delta);
      String lyric = scanner.readWhile(this.lyricStopFilter.negate(), 0, delta);
      BarLine bar = barLineMapper.read(in, context);
      if ((chordNode != null) && (scanner.getColumn() > chordNode.column)) {
        line.add(chordNode.chordContainer, lyric, bar);
        chordNode = chordNode.next;
      } else {
        if (lyric.isEmpty() && (bar == null)) {
          throw new IllegalStateException();
        }
        line.add(lyric, bar);
      }
    }
    return line;
  }

  @Override
  public void write(ScoreLine line, MusicOutputStream out, SongFormatContext context) {

    if (line == null) {
      return;
    }
    String comment = line.getComment();
    if (comment == null) {
      out.write(BEGIN_CHORDS);
      StringBuilder lyrics = new StringBuilder();
      lyrics.append(BEGIN_LYRICS);
      List<ScoreCell> cells = line.getCells();
      int spaces = 0;
      int cellMax = cells.size() - 1;
      for (int i = 0; i <= cellMax; i++) {
        ScoreCell cell = cells.get(i);
        int chordLength = 0;
        ChordContainer chordContainer = cell.getChordContainer();
        if (chordContainer != null) {
          if ((spaces == 0) && (i > 0)) {
            out.write(' ');
            lyrics.append(' ');
          } else if (spaces < 0) {
            spaces += 2;
            if (spaces > 0) {
              StringHelper.appendSpaces(lyrics, 2);
            }
          }
          if (spaces > 0) {
            StringHelper.appendSpaces(out, spaces);
          } else if (spaces < 0) {
            StringHelper.appendSpaces(lyrics, -spaces);
          }
          spaces = 0;
          int col = out.getColumn();
          getChordContainerMapper().write(chordContainer, out, context);
          chordLength = out.getColumn() - col;
        }
        String lyric = cell.getLyric();
        BarLine bar = cell.getBar();
        if (bar != null) {
          lyric = lyric + bar;
        }
        lyrics.append(lyric);
        spaces += lyric.length() - chordLength;
      }
      out.write(NEWLINE_CHAR);
      out.write(lyrics);
    } else if (line != ScoreEmptyLine.INSTANCE) {
      out.write(BEGIN_COMMENT);
      out.write(comment);
    }
    out.write(NEWLINE_CHAR);
  }

  private static class ChordNode {

    private final ChordContainer chordContainer;

    private final int column;

    private ChordNode next;

    private ChordNode() {

      this(null, 0);
    }

    private ChordNode(ChordContainer chordContainer, int column) {

      super();
      this.chordContainer = chordContainer;
      this.column = column;
    }

    private ChordNode append(ChordContainer chordContainer2, int column2) {

      this.next = new ChordNode(chordContainer2, column2);
      return this.next;
    }

  }

}
