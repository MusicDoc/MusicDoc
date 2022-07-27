package io.github.musicdoc.music.score.voice;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.music.bar.BarLine;
import io.github.musicdoc.music.harmony.chord.Chord;
import io.github.musicdoc.music.harmony.chord.ChordContainer;
import io.github.musicdoc.music.rythm.value.ValuedItem;
import io.github.musicdoc.music.score.ScoreCell;
import io.github.musicdoc.music.stave.Stave;
import io.github.musicdoc.music.stave.StaveChange;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * {@link ScoreCell} for a {@link ScoreVoiceLine}.
 */
public class ScoreVoiceCell extends ScoreCell<ScoreVoiceCell> {

  private ValuedItem<?> item;

  private String lyric;

  private BarLine bar;

  private StaveChange staveChange;

  /**
   * The constructor.
   */
  public ScoreVoiceCell() {

    super();
  }

  /**
   * The constructor.
   *
   * @param lyric the {@link #getLyric() lyric}.
   */
  public ScoreVoiceCell(String lyric) {

    this(null, null, lyric, null);
  }

  /**
   * The constructor.
   *
   * @param chord the {@link #getChord() chord}.
   * @param lyric the {@link #getLyric() lyric}.
   */
  public ScoreVoiceCell(Chord chord, String lyric) {

    this(new ChordContainer(chord), null, lyric, null);
  }

  /**
   * The constructor.
   *
   * @param chordContainer the {@link #getChordContainer() chord container}.
   */
  public ScoreVoiceCell(ChordContainer chordContainer) {

    this(chordContainer, null, null, null);
  }

  /**
   * The constructor.
   *
   * @param chordContainer the {@link #getChordContainer() chord container}.
   * @param lyric the {@link #getLyric() lyric}.
   */
  public ScoreVoiceCell(ChordContainer chordContainer, String lyric) {

    this(chordContainer, null, lyric, null);
  }

  /**
   * The constructor.
   *
   * @param chordContainer the {@link #getChordContainer() chord container}.
   * @param item the {@link #getItem() item}.
   * @param lyric the {@link #getLyric() lyric}.
   * @param bar the {@link #getBar() bar}.
   */
  public ScoreVoiceCell(ChordContainer chordContainer, ValuedItem<?> item, String lyric, BarLine bar) {

    super();
    this.chordContainer = chordContainer;
    this.item = item;
    this.lyric = lyric;
    this.bar = bar;
  }

  private ScoreVoiceCell(ScoreVoiceCell cell, MutableObjecteCopier copier) {

    super(cell, copier);
    this.item = cell.item;
    this.lyric = cell.lyric;
    this.bar = cell.bar;
  }

  @Override
  public ScoreVoiceCell copy(MutableObjecteCopier copier) {

    return new ScoreVoiceCell(this, copier);
  }

  /**
   * @return the optional {@link StaveChange} to change the current {@link io.github.musicdoc.music.stave.Stave}
   *         properties (e.g. its {@link Stave#getKey() key} or {@link Stave#getBeat() beat}) at the beginning of this
   *         cell. Otherwise {@code null}.
   */
  public StaveChange getStaveChange() {

    return this.staveChange;
  }

  /**
   * @param stave the new value of {@link #getStaveChange()}.
   * @return a new {@link ScoreVoiceCell} with the given {@link #getStaveChange() staveChange} and all other properties
   *         like {@code this} one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public ScoreVoiceCell setStaveChange(StaveChange stave) {

    if (this.staveChange == stave) {
      return this;
    }
    ScoreVoiceCell cell = makeMutable();
    cell.staveChange = stave;
    return cell;
  }

  /**
   * @return the lyric (phrase) to be sung at this cell.
   */
  public String getLyric() {

    if (this.lyric == null) {
      return "";
    }
    return this.lyric;
  }

  /**
   * @param lyric the new value of {@link #getLyric()}.
   * @return a new {@link ScoreVoiceCell} with the given {@link #getLyric() lyric} and all other properties like
   *         {@code this} one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public ScoreVoiceCell setLyric(String lyric) {

    if (this.lyric == lyric) {
      return this;
    }
    ScoreVoiceCell cell = makeMutable();
    cell.lyric = lyric;
    return cell;
  }

  /**
   * @return the {@link ValuedItem} of this cell that is the {@link io.github.musicdoc.music.tone.Tone} or
   *         {@link io.github.musicdoc.music.rythm.rest.Rest} to be played at this cell. May be {@code null} for none.
   */
  public ValuedItem<?> getItem() {

    return this.item;
  }

  /**
   * @param item the new value of {@link #getItem()}.
   * @return a new {@link ScoreVoiceCell} with the given {@link #getItem() item} and all other properties like
   *         {@code this} one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public ScoreVoiceCell setItem(ValuedItem<?> item) {

    if (this.item == item) {
      return this;
    }
    ScoreVoiceCell cell = makeMutable();
    cell.item = item;
    return cell;
  }

  /**
   * @return the optional {@link BarLine} of this cell that will be displayed to the right. May be {@code null} for
   *         none. There is no left {@link BarLine} as this would be the right {@link BarLine} of the previous cell.
   */
  public BarLine getBar() {

    return this.bar;
  }

  /**
   * @param bar the new value of {@link #getBar()}.
   * @return a new {@link ScoreVoiceCell} with the given {@link #getBar() bar} and all other properties like
   *         {@code this} one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public ScoreVoiceCell setBar(BarLine bar) {

    if (this.bar == bar) {
      return this;
    }
    ScoreVoiceCell cell = makeMutable();
    cell.bar = bar;
    return cell;
  }

  @Override
  public ScoreVoiceCell transpose(int steps, boolean diatonic, TransposeContext context) {

    ScoreVoiceCell transposed = copy();
    if (transposed.chordContainer != null) {
      transposed.chordContainer = transposed.chordContainer.transpose(steps, diatonic, context);
    }
    if (transposed.item != null) {
      transposed.item = transposed.item.transpose(steps, diatonic, context);
    }
    return transposed;
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    toString(sb);
    return sb.toString();
  }

  protected void toString(StringBuilder sb) {

    if (this.item != null) {
      sb.append('{');
      sb.append(this.item);
      sb.append('}');
    }
    if (this.chordContainer != null) {
      sb.append('[');
      sb.append(this.chordContainer);
      sb.append(']');
    }
    sb.append(this.lyric);
    if (this.bar != null) {
      sb.append(this.bar);
    }
  }

}
