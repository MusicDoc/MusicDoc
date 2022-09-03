package io.github.musicdoc.score.line;

import java.util.List;

import io.github.musicdoc.MutableObject;
import io.github.musicdoc.bar.BarLine;
import io.github.musicdoc.bar.BarLineType;
import io.github.musicdoc.harmony.chord.Chord;
import io.github.musicdoc.harmony.chord.ChordContainer;
import io.github.musicdoc.rhythm.item.ValuedItem;
import io.github.musicdoc.score.cell.ScoreCell;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.transpose.Transposable;

/**
 * Interface for a line of a {@link io.github.musicdoc.score.ScoreRow}. It typically represents the musical data
 * for a {@link #getVoice() voice}.
 */
public interface ScoreLine extends Transposable<ScoreLine>, MutableObject<ScoreLine> {

  /**
   * @return the optional comment. May be {@code null}. Otherwise this is typically a comment line that will have no
   *         other properties.
   */
  String getComment();

  /**
   * @return the {@link List} of {@link ScoreCell}s.
   */
  List<ScoreCell> getCells();

  /**
   * @return the number of {@link #getCell(int) cells} in this line.
   */
  default int getCellCount() {

    return getCells().size();
  }

  /**
   * @param i the index of the requested {@link ScoreCell}.
   * @return the requested {@link ScoreCell} or {@code null} if undefined or the given index is less or equal to
   *         {@link #getCellCount() cell count}.
   */
  default ScoreCell getCell(int i) {

    List<ScoreCell> cells = getCells();
    if ((i < 0) || (i >= cells.size())) {
      return null;
    }
    return cells.get(i);

  }

  /**
   * @param i the index of the requested {@link ScoreCell}.
   * @return the requested {@link ScoreCell}. If it did not previously exist, it has been created. The
   *         {@link #getCellCount() cell count} is automatically increased as needed.
   */
  default ScoreCell getOrCreateCell(int i) {

    List<ScoreCell> cells = getCells();
    int size = cells.size();
    int delta = i - size;
    while (delta >= 0) {
      cells.add(new ScoreCell());
      delta--;
    }
    return cells.get(i);
  }

  /**
   * @return the {@link StaveVoice} this line is assigned to in case of a {@link ScoreVoiceLine}. Otherwise {@code null}
   *         (e.g. if only a {@link #getComment() comment} line).
   */
  StaveVoice getVoice();

  /**
   * {@link #add(ScoreCell) Adds} a new {@link ScoreCell} from the given arguments.
   *
   * @param chord the {@link ScoreCell#getChord() chord}.
   * @param item the {@link ScoreCell#getItem() item}.
   * @param lyric the {@link ScoreCell#getLyric() lyric}.
   * @return this {@link ScoreVoiceLine} for fluent API calls.
   */
  default ScoreLine add(Chord chord, ValuedItem<?> item, String lyric) {

    return add(chord, item, lyric, (BarLine) null);
  }

  /**
   * {@link #add(ScoreCell) Adds} a new {@link ScoreCell} from the given arguments.
   *
   * @param item the {@link ScoreCell#getItem() item}.
   * @param lyric the {@link ScoreCell#getLyric() lyric}.
   * @return this {@link ScoreVoiceLine} for fluent API calls.
   */
  default ScoreLine add(ValuedItem<?> item, String lyric) {

    return add((ChordContainer) null, item, lyric, (BarLine) null);
  }

  /**
   * {@link #add(ScoreCell) Adds} a new {@link ScoreCell} from the given arguments.
   *
   * @param item the {@link ScoreCell#getItem() item}.
   * @param lyric the {@link ScoreCell#getLyric() lyric}.
   * @return this {@link ScoreVoiceLine} for fluent API calls.
   */
  default ScoreLine add(String lyric) {

    return add((ChordContainer) null, null, lyric, (BarLine) null);
  }

  /**
   * {@link #add(ScoreCell) Adds} a new {@link ScoreCell} from the given arguments.
   *
   * @param item the {@link ScoreCell#getItem() item}.
   * @param lyric the {@link ScoreCell#getLyric() lyric}.
   * @param bar the {@link ScoreCell#getBar() bar}.
   * @return this {@link ScoreVoiceLine} for fluent API calls.
   */
  default ScoreLine add(String lyric, BarLine bar) {

    return add((ChordContainer) null, null, lyric, bar);
  }

  /**
   * {@link #add(ScoreCell) Adds} a new {@link ScoreCell} from the given arguments.
   *
   * @param item the {@link ScoreCell#getItem() item}.
   * @param lyric the {@link ScoreCell#getLyric() lyric}.
   * @param barType the {@link ScoreCell#getBar() bar} {@link BarLine#getType() type}.
   * @return this {@link ScoreVoiceLine} for fluent API calls.
   */
  default ScoreLine add(ValuedItem<?> item, String lyric, BarLineType barType) {

    return add(null, item, lyric, barType);
  }

  /**
   * {@link #add(ScoreCell) Adds} a new {@link ScoreCell} from the given arguments.
   *
   * @param chord the {@link ScoreCell#getChord() chord}.
   * @param item the {@link ScoreCell#getItem() item}.
   * @param lyric the {@link ScoreCell#getLyric() lyric}.
   * @param barType the {@link ScoreCell#getBar() bar} {@link BarLine#getType() type}.
   * @return this {@link ScoreVoiceLine} for fluent API calls.
   */
  default ScoreLine add(Chord chord, ValuedItem<?> item, String lyric, BarLineType barType) {

    BarLine bar = null;
    if (barType != null) {
      bar = new BarLine(barType);
    }
    return add(chord, item, lyric, bar);
  }

  /**
   * {@link #add(ScoreCell) Adds} a new {@link ScoreCell} from the given arguments.
   *
   * @param chord the {@link ScoreCell#getChord() chord}.
   * @param item the {@link ScoreCell#getItem() item}.
   * @param lyric the {@link ScoreCell#getLyric() lyric}.
   * @param bar the {@link ScoreCell#getBar() bar}.
   * @return this {@link ScoreVoiceLine} for fluent API calls.
   */
  default ScoreLine add(Chord chord, ValuedItem<?> item, String lyric, BarLine bar) {

    return add(ChordContainer.of(chord), item, lyric, bar);
  }

  /**
   * {@link #add(ScoreCell) Adds} a new {@link ScoreCell} from the given arguments.
   *
   * @param chordContainer the {@link ScoreCell#getChordContainer() chord container}.
   * @param lyric the {@link ScoreCell#getLyric() lyric}.
   * @return this {@link ScoreVoiceLine} for fluent API calls.
   */
  default ScoreLine add(ChordContainer chordContainer, String lyric) {

    return add(chordContainer, null, lyric, null);
  }

  /**
   * {@link #add(ScoreCell) Adds} a new {@link ScoreCell} from the given arguments.
   *
   * @param chordContainer the {@link ScoreCell#getChordContainer() chord container}.
   * @param lyric the {@link ScoreCell#getLyric() lyric}.
   * @param bar the {@link ScoreCell#getBar() bar}.
   * @return this {@link ScoreVoiceLine} for fluent API calls.
   */
  default ScoreLine add(ChordContainer chordContainer, String lyric, BarLine bar) {

    return add(chordContainer, null, lyric, bar);
  }

  /**
   * {@link #add(ScoreCell) Adds} a new {@link ScoreCell} from the given arguments.
   *
   * @param chordContainer the {@link ScoreCell#getChordContainer() chord container}.
   * @param item the {@link ScoreCell#getItem() item}.
   * @param lyric the {@link ScoreCell#getLyric() lyric}.
   * @return this {@link ScoreVoiceLine} for fluent API calls.
   */
  default ScoreLine add(ChordContainer chordContainer, ValuedItem<?> item, String lyric) {

    return add(chordContainer, item, lyric, null);
  }

  /**
   * {@link #add(ScoreCell) Adds} a new {@link ScoreCell} from the given arguments.
   *
   * @param chordContainer the {@link ScoreCell#getChordContainer() chord container}.
   * @param item the {@link ScoreCell#getItem() item}.
   * @param lyric the {@link ScoreCell#getLyric() lyric}.
   * @param bar the {@link ScoreCell#getBar() bar}.
   * @return this {@link ScoreVoiceLine} for fluent API calls.
   */
  default ScoreLine add(ChordContainer chordContainer, ValuedItem<?> item, String lyric, BarLine bar) {

    return add(new ScoreCell(chordContainer, item, lyric, bar));
  }

  /**
   * @param cell the {@link ScoreCell} to add to {@link #getCells() cells}.
   * @return this {@link ScoreVoiceLine} for fluent API calls.
   */
  default ScoreLine add(ScoreCell cell) {

    getCells().add(cell);
    return this;
  }

  /**
   * @param voice new value of {@link #getVoice()}.
   * @return a {@link ScoreLine} with the given {@link #getVoice() voice} and all other properties like {@code this}
   *         one. Will be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  ScoreLine setVoice(StaveVoice voice);
}
