package io.github.musicdoc.music.score.line;

import java.util.List;

import io.github.musicdoc.StringHelper;
import io.github.musicdoc.filter.ListCharFilter;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.music.bar.BarLine;
import io.github.musicdoc.music.bar.BarLineMapper;
import io.github.musicdoc.music.format.SongFormat;
import io.github.musicdoc.music.format.SongFormatContext;
import io.github.musicdoc.music.format.SongFormatOpenSong;
import io.github.musicdoc.music.harmony.chord.ChordContainer;
import io.github.musicdoc.music.score.cell.ScoreCell;
import io.github.musicdoc.music.stave.voice.StaveVoice;
import io.github.musicdoc.music.stave.voice.StaveVoiceContainer;

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

    if (in.expect(BEGIN_COMMENT, false)) {
      String comment = in.readLine();
      return new ScoreCommentLine(comment);
    } else if (in.skipNewline()) {
      return ScoreEmptyLine.INSTANCE;
    }
    ChordNode chordStart = new ChordNode();
    ChordNode chordNode = chordStart;
    char c = in.peek();
    if (c == BEGIN_CHORDS) {
      in.next();
      while (in.hasNext() && !in.skipNewline()) {
        in.skipWhile(' ');
        int column = in.getColumn(true);
        ChordContainer chordContainer = getChordContainerMapper().read(in, context);
        if (chordContainer == null) {
          break;
        } else {
          chordNode = chordNode.append(chordContainer, column);
        }
      }
      c = in.peek();
    }
    BarLineMapper barLineMapper = getBarLineMapper();
    ScoreVoiceLine line = new ScoreVoiceLine();
    StaveVoiceContainer voiceContainer = context.getStaveVoiceContainer();
    if (voiceContainer != null) {
      StaveVoice voice = voiceContainer.getVoice(null);
      line = line.setVoice(voice);
    }
    if (c == BEGIN_LYRICS) {
      in.next();
    }
    chordNode = chordStart.next;
    while (in.hasNext() && !in.skipNewline()) {
      int delta = 5000;
      if (chordNode != null) {
        int column = in.getColumn(true);
        delta = chordNode.column - column;
        if ((delta <= 0) && (chordNode.next != null)) {
          delta = chordNode.next.column - column;
        }
        if (delta <= 0) {
          delta = 5000;
        }
      }
      String lyric = in.readUntil(this.lyricStopFilter, delta);
      BarLine bar = barLineMapper.read(in, context);
      if ((chordNode != null) && (in.getColumn(true) > chordNode.column)) {
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
      int cellMax = cells.size() - 1;
      for (int i = 0; i <= cellMax; i++) {
        ScoreCell cell = cells.get(i);
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
