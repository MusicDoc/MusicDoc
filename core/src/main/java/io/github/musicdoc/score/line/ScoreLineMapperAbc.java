package io.github.musicdoc.score.line;

import io.github.musicdoc.bar.BarLine;
import io.github.musicdoc.format.SongFormat;
import io.github.musicdoc.format.SongFormatAbc;
import io.github.musicdoc.format.SongFormatContext;
import io.github.musicdoc.harmony.chord.ChordContainer;
import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.io.MusicOutputStream;
import io.github.musicdoc.rythm.value.MusicalValue;
import io.github.musicdoc.rythm.value.MusicalValueVariation;
import io.github.musicdoc.rythm.value.ValuedItem;
import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.stave.StaveChange;

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

  @Override
  protected void readCells(ScoreVoiceLine line, MusicInputStream in, SongFormatContext context) {

    int previousBrokenRythmCount = 0;
    while (in.hasNext() && !in.skipNewline()) {
      ScoreCell cell = readCell(line, in, context);
      if (cell != null) {
        in.skipWhile(' ');
        int brokenRythmCount = 0;
        while ((brokenRythmCount < 3) && in.expect('>')) {
          brokenRythmCount++;
        }
        while ((brokenRythmCount > -3) && (brokenRythmCount <= 0) && in.expect('<')) {
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
        MusicalValueVariation variation = MusicalValueVariation.ofPunctuaion(brokenRythmCount);
        assert (variation != null);
        item.getValue().setVariation(variation);
      }
    } else if (brokenRythmCount < 0) {
      int fraction = 1 << (-brokenRythmCount);
      MusicalValue value = item.getValue();
      value.setUnit(value.getUnit() * fraction);
    }
  }

  @Override
  protected void writeCells(ScoreLine line, MusicOutputStream out, SongFormatContext context) {

    super.writeCells(line, out, context);
    // in ABC the lyrics are written as an extra "w:" line so we loop the cells again
    out.writePropertyStart("w");
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
    out.writePropertyEnd("w");
  }

  @Override
  protected void writeCell(ScoreCell cell, ScoreCell previous, ScoreCell next, MusicOutputStream out,
      SongFormatContext context) {

    StaveChange staveChange = cell.getStaveChange();
    ChordContainer chordContainer = cell.getChordContainer();
    ValuedItem<?> item = cell.getItem();
    String lyric = cell.getLyric();
    BarLine bar = cell.getBar();
    String suffix = "";
    if (next != null) {
      // proper alignment to increase reability
      if ((bar != null) || ((item == null) || (!item.hasDecorationsWithSuffix(true)))) {
        suffix = " ";
      }
      ValuedItem<?> nextItem = next.getItem();
      if ((item != null) && (nextItem != null) && (bar == null)) {
        MusicalValue value = item.getValue();
        MusicalValue nextValue = nextItem.getValue();
        int punctuation = value.getVariation().getPunctuationCount();
        int nextPunctuation = nextValue.getVariation().getPunctuationCount();
        if ((punctuation != 0) && (nextValue.getVariation() == MusicalValueVariation.NONE)) {
          if (value.getValue(false) == (nextValue.getValue(false) * (1 << punctuation))) {
            item = item.copy();
            value = item.getValue();
            value.setVariation(MusicalValueVariation.NONE);
            suffix = getBrokenRythmInfix(true, punctuation);
          }
        } else if ((item.getValue().getVariation() == MusicalValueVariation.NONE) && (nextPunctuation != 0)) {
          int fraction = 1 << nextPunctuation;
          if ((value.getValue(false) * fraction) == nextValue.getValue(false)) {
            item = item.copy();
            value = item.getValue();
            value.setUnit(value.getUnit() / fraction);
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
        int punctuation = value.getVariation().getPunctuationCount();
        int previousPunctuation = previousValue.getVariation().getPunctuationCount();
        if ((value.getVariation() == MusicalValueVariation.NONE) && (previousPunctuation != 0)) {
          int fraction = 1 << previousPunctuation;
          if (previousValue.getValue(false) == (value.getValue(false) * fraction)) {
            item = item.copy();
            value = item.getValue();
            value.setUnit(value.getUnit() / fraction);
          }
        } else if ((previousItem.getValue().getVariation() == MusicalValueVariation.NONE) && (punctuation != 0)) {
          int fraction = 1 << punctuation;
          if ((previousValue.getValue(false) * fraction) == value.getValue(false)) {
            item = item.copy();
            value = item.getValue();
            value.setUnit(value.getUnit() / fraction);
          }
        }
      }
    }
    writeCell(cell, staveChange, chordContainer, item, lyric, bar, out, context);
    out.write(suffix);
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
