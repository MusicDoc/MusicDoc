package io.github.musicdoc.score.line;

import java.util.List;

import io.github.mmm.scanner.CharStreamScanner;
import io.github.musicdoc.bar.BarLine;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.harmony.chord.ChordContainer;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.rhythm.fraction.SimpleFraction;
import io.github.musicdoc.rhythm.item.ValuedItem;
import io.github.musicdoc.rhythm.punctuation.Punctuation;
import io.github.musicdoc.rhythm.tuplet.Tuplet;
import io.github.musicdoc.rhythm.tuplet.TupletContext;
import io.github.musicdoc.rhythm.tuplet.TupletMapperBase;
import io.github.musicdoc.rhythm.value.MusicalValue;
import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.stave.StaveChange;

/**
 * {@link ScoreLineMapper} for {@link SongFormatAbc}.
 */
public class ScoreLineMapperAbc extends ScoreLineMapperBase {

  private static final String PROPERTY_LYRICS = "w";

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

    CharStreamScanner scanner = in.getScanner();
    String lookahead = scanner.peekString(2);
    if ("%%".equals(lookahead)) {
      return null;
    }
    ScoreLine line = super.read(in, context);
    if (line instanceof ScoreVoiceLine) {
      if (in.isPropertyStart(PROPERTY_LYRICS)) {
        String property = in.readPropertyStart();
        assert (property.equals(PROPERTY_LYRICS));
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

  @Override
  protected void readCells(ScoreVoiceLine line, MusicInputStream in, SongFormatContext context) {

    CharStreamScanner scanner = in.getScanner();
    int previousBrokenRythmCount = 0;
    while (scanner.hasNext() && !in.skipNewline()) {
      ScoreCell cell = readCell(line, in, context);
      if (cell != null) {
        scanner.skipWhile(' ');
        int brokenRythmCount = 0;
        while ((brokenRythmCount < 3) && scanner.expectOne('>')) {
          brokenRythmCount++;
        }
        while ((brokenRythmCount > -3) && (brokenRythmCount <= 0) && scanner.expectOne('<')) {
          brokenRythmCount--;
        }
        ValuedItem<?> item = cell.getItem();
        if (brokenRythmCount == 0) {
          applyBrokenRythm(-previousBrokenRythmCount, item, in);
        } else {
          applyBrokenRythm(brokenRythmCount, item, in);
        }
        previousBrokenRythmCount = brokenRythmCount;
        line.add(cell);
      }
    }
  }

  private void applyBrokenRythm(int brokenRythmCount, ValuedItem<?> item, MusicInputStream in) {

    if (brokenRythmCount > 0) {
      if (item == null) {
        in.addError("broken rythmn requires item.");
      } else {
        Punctuation punctuation = Punctuation.of(brokenRythmCount);
        assert (punctuation != null);
        item.getValue().setPunctuation(punctuation);
      }
    } else if (brokenRythmCount < 0) {
      int unitFactor = 1 << (-brokenRythmCount);
      SimpleFraction<?> plain = item.getValue().getPlain();
      plain.setUnit(plain.getUnit() * unitFactor);
    }
  }

  @Override
  protected void writeCells(ScoreLine line, MusicOutputStream out, SongFormatContext context) {

    super.writeCells(line, out, context);
    // in ABC the lyrics are written as an extra "w:" line so we loop the cells again
    out.writePropertyStart(PROPERTY_LYRICS);
    int max = line.getCellCount() - 1;
    for (int i = 0; i <= max; i++) {
      ScoreCell cell = line.getCell(i);
      // TODO rest?
      String lyric = cell.getLyric();
      out.write(lyric);
      int len = lyric.length();
      if (len > 0) {
        if (i < max) {
          char last = lyric.charAt(len - 1);
          if ((last != ' ') && (last != '_') && (last != '-')) {
            out.write(' ');
          }
        }
      } else {
        out.write('_');
      }
    }
    out.writePropertyEnd(PROPERTY_LYRICS);
  }

  @Override
  protected void writeCell(ScoreCell cell, ScoreCell previous, ScoreCell next, ScoreLine line, int cellIndexx,
      MusicOutputStream out, SongFormatContext context) {

    StaveChange staveChange = cell.getStaveChange();
    ChordContainer chordContainer = cell.getChordContainer();
    ValuedItem<?> item = cell.getItem();
    String lyric = cell.getLyric();
    BarLine bar = cell.getBar();
    String suffix = "";
    if (next != null) {
      // proper alignment to increase readability
      if ((bar != null) || ((item == null) || (!item.hasDecorationsWithSuffix(true)))) {
        suffix = " ";
      }
      ValuedItem<?> nextItem = next.getItem();
      if ((item != null) && (nextItem != null) && (bar == null)) {
        MusicalValue value = item.getValue();
        MusicalValue nextValue = nextItem.getValue();
        int punctuation = value.getPunctuationCount();
        int nextPunctuation = nextValue.getPunctuationCount();
        if ((punctuation != 0) && (nextPunctuation == 0)) {
          if (value.getPlain().getValue() == (nextValue.getPlain().getValue() * (1 << punctuation))) {
            item = item.copy();
            value = item.getValue();
            value.setPunctuation(null);
            suffix = getBrokenRythmInfix(true, punctuation);
          }
        } else if ((punctuation == 0) && (nextPunctuation != 0)) {
          int unitFactor = 1 << nextPunctuation;
          if ((value.getPlain().getValue() * unitFactor) == nextValue.getPlain().getValue()) {
            item = item.copy();
            SimpleFraction<?> plain = item.getValue().getPlain();
            plain.setUnit(plain.getUnit() / unitFactor);
            suffix = getBrokenRythmInfix(false, nextPunctuation);
          }
        }
      }
    }
    if ((previous != null) && (previous.getBar() == null)) {
      ValuedItem<?> previousItem = previous.getItem();
      if ((item != null) && (previousItem != null)) {
        MusicalValue value = item.getValue();
        MusicalValue previousValue = previousItem.getValue();
        int punctuation = value.getPunctuationCount();
        int previousPunctuation = previousValue.getPunctuationCount();
        if ((punctuation == 0) && (previousPunctuation != 0)) {
          int unitFactor = 1 << previousPunctuation;
          if (previousValue.getPlain().getValue() == (value.getPlain().getValue() * unitFactor)) {
            item = item.copy();
            SimpleFraction<?> plain = item.getValue().getPlain();
            plain.setUnit(value.getUnit() / unitFactor);
          }
        } else if ((previousPunctuation == 0) && (punctuation != 0)) {
          int unitFactor = 1 << punctuation;
          if ((previousValue.getPlain().getValue() * unitFactor) == value.getPlain().getValue()) {
            item = item.copy();
            SimpleFraction<?> plain = item.getValue().getPlain();
            plain.setUnit(value.getUnit() / unitFactor);
          }
        }
      }
    }
    if (item != null) {
      TupletContext tc = context.getTupletContext();
      Tuplet tuplet = item.getValue().getTuplet();
      if (tuplet != null) {
        if (tc == null) {
          int count = countTuplets(tuplet, line, cellIndexx + 1);
          tc = new TupletContext(tuplet, count);
          TupletMapperBase.writeTupletContext(tc, '(', out, context);
          tc.decrementNoteCount(); // first tuplet item will be written in this method (in writeCell)
          context.setTupletContext(tc);
        } else {
          if (!tuplet.equals(tc.getTuplet())) {
            out.addError("Incompatible tuplet.");
          } else if (tc.getItemCount() > 0) {
            int count = tc.decrementNoteCount();
            if (count == 0) {
              context.setTupletContext(null);
            }
          } else {
            out.addError("Illegal tuplet state.");
          }
        }
      } else if ((tc != null) && (tc.getItemCount() > 0)) {
        out.addError("Missing tuplet.");
      }
    }
    writeCell(cell, staveChange, chordContainer, item, lyric, bar, out, context);
    out.write(suffix);
  }

  private int countTuplets(Tuplet tuplet, ScoreLine line, int cellIndex) {

    int noteCount = 1;
    List<ScoreCell> cells = line.getCells();
    int size = cells.size();
    for (int i = cellIndex; i < size; i++) {
      ScoreCell cell = cells.get(i);
      ValuedItem<?> item = cell.getItem();
      if (item != null) {
        if (tuplet.equals(item.getValue().getTuplet())) {
          noteCount++;
        } else {
          return noteCount;
        }
      }
    }
    // TODO: continue noteCount in next voice line?
    return noteCount;
  }

  private String getBrokenRythmInfix(boolean firstPunctuated, int count) {

    if (firstPunctuated) {
      switch (count) {
        case 1:
          return ">";
        case 2:
          return ">>";
        case 3:
          return ">>>";
      }
    } else {
      switch (count) {
        case 1:
          return "<";
        case 2:
          return "<<";
        case 3:
          return "<<<";
      }
    }
    throw new IllegalStateException();
  }

  @Override
  protected void writeLyric(String lyric, ScoreCell cell, MusicOutputStream out, SongFormatContext context) {

    // ABC writes lyrics in an extra "w:" line so here we only do some proper alignment.
    BarLine bar = cell.getBar();
    if (bar != null) {
      out.write(' ');
    }
  }

}
