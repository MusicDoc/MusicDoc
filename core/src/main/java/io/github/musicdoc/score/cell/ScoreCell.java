package io.github.musicdoc.score.cell;

import java.util.Objects;

import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.bar.BarLine;
import io.github.musicdoc.harmony.chord.Chord;
import io.github.musicdoc.harmony.chord.ChordContainer;
import io.github.musicdoc.rythm.value.ValuedItem;
import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.StaveChange;
import io.github.musicdoc.transpose.AbstractTransposable;
import io.github.musicdoc.transpose.TransposeContext;

/**
 * A cell of a {@link io.github.musicdoc.score.line.ScoreLine}. So for each {@link #getItem() item} (tone, rest),
 * {@link #getChord() chord}, {@link #getLyric() lyric segment}, etc. there is a particular {@link ScoreCell}.
 *
 * @see io.github.musicdoc.score.line.ScoreLine#getCells()
 */
public class ScoreCell extends AbstractTransposable<ScoreCell> implements MutableObject<ScoreCell> {

  private ChordContainer chordContainer;

  private ValuedItem<?> item;

  private String lyric;

  private BarLine bar;

  private StaveChange staveChange;

  private int fret;

  private int string;

  private boolean immutable;

  /**
   * The constructor.
   */
  public ScoreCell() {

    this(null, null, null, null, null);
  }

  /**
   * The constructor.
   *
   * @param lyric the {@link #getLyric() lyric}.
   */
  public ScoreCell(String lyric) {

    this(null, null, lyric, null);
  }

  /**
   * The constructor.
   *
   * @param chord the {@link #getChord() chord}.
   * @param lyric the {@link #getLyric() lyric}.
   */
  public ScoreCell(Chord chord, String lyric) {

    this(ChordContainer.of(chord), null, lyric, null);
  }

  /**
   * The constructor.
   *
   * @param chordContainer the {@link #getChordContainer() chord container}.
   */
  public ScoreCell(ChordContainer chordContainer) {

    this(chordContainer, null, null, null);
  }

  /**
   * The constructor.
   *
   * @param chordContainer the {@link #getChordContainer() chord container}.
   * @param lyric the {@link #getLyric() lyric}.
   */
  public ScoreCell(ChordContainer chordContainer, String lyric) {

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
  public ScoreCell(ChordContainer chordContainer, ValuedItem<?> item, String lyric, BarLine bar) {

    this(null, chordContainer, item, lyric, bar);
  }

  /**
   * The constructor.
   *
   * @param staveChange the {@link #getStaveChange() stave change}.
   * @param chordContainer the {@link #getChordContainer() chord container}.
   * @param item the {@link #getItem() item}.
   * @param lyric the {@link #getLyric() lyric}.
   * @param bar the {@link #getBar() bar}.
   */
  public ScoreCell(StaveChange staveChange, ChordContainer chordContainer, ValuedItem<?> item, String lyric,
      BarLine bar) {

    super();
    this.staveChange = staveChange;
    this.chordContainer = chordContainer;
    this.item = item;
    this.lyric = lyric;
    this.bar = bar;
    this.fret = -1;
  }

  private ScoreCell(ScoreCell cell, MutableObjecteCopier copier) {

    super();
    this.staveChange = cell.staveChange;
    this.chordContainer = cell.chordContainer;
    this.item = cell.item;
    this.lyric = cell.lyric;
    this.bar = cell.bar;
  }

  @Override
  public ScoreCell copy(MutableObjecteCopier copier) {

    return new ScoreCell(this, copier);
  }

  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  @Override
  public ScoreCell makeImmutable() {

    if (!this.immutable) {
      this.staveChange.makeImmutable();
      this.immutable = true;
    }
    return this;
  }

  /**
   * @return the {@link ChordContainer}.
   */
  public ChordContainer getChordContainer() {

    return this.chordContainer;
  }

  /**
   * @param chordContainer the new value of {@link #getChordContainer()}.
   * @return a new {@link ScoreCell} with the given {@link #getChordContainer() chordContainer} and all other properties
   *         like {@code this} one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public ScoreCell setChordContainer(ChordContainer chordContainer) {

    if (this.chordContainer == chordContainer) {
      return self();
    }
    ScoreCell cell = makeMutable();
    cell.chordContainer = chordContainer;
    return cell;
  }

  /**
   * @return {@link ChordContainer#getChord()}
   */
  public Chord getChord() {

    if (this.chordContainer == null) {
      return null;
    }
    return this.chordContainer.getChord();
  }

  /**
   * @param chord the new value of {@link #getChord()}.
   * @return a new {@link ScoreCell} with the given {@link #getChord() chord} and all other properties like {@code this}
   *         one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public ScoreCell setChord(Chord chord) {

    return setChordContainer(new ChordContainer(chord));
  }

  /**
   * @return {@link ChordContainer#toString()}
   */
  public String getChordString() {

    if (this.chordContainer == null) {
      return "";
    }
    return this.chordContainer.toString();
  }

  /**
   * @return the optional {@link StaveChange} to change the current {@link io.github.musicdoc.stave.Stave} properties
   *         (e.g. its {@link Stave#getKey() key} or {@link Stave#getBeat() beat}) at the beginning of this cell.
   *         Otherwise {@code null}.
   */
  public StaveChange getStaveChange() {

    return this.staveChange;
  }

  /**
   * @param stave the new value of {@link #getStaveChange()}.
   * @return a new {@link ScoreCell} with the given {@link #getStaveChange() staveChange} and all other properties like
   *         {@code this} one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public ScoreCell setStaveChange(StaveChange stave) {

    if (this.staveChange == stave) {
      return this;
    }
    ScoreCell cell = makeMutable();
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
   * @return a new {@link ScoreCell} with the given {@link #getLyric() lyric} and all other properties like {@code this}
   *         one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public ScoreCell setLyric(String lyric) {

    if (this.lyric == lyric) {
      return this;
    }
    ScoreCell cell = makeMutable();
    cell.lyric = lyric;
    return cell;
  }

  /**
   * @return the {@link ValuedItem} of this cell that is the {@link io.github.musicdoc.tone.Tone} or
   *         {@link io.github.musicdoc.rythm.rest.Rest} to be played at this cell. May be {@code null} for none.
   */
  public ValuedItem<?> getItem() {

    return this.item;
  }

  /**
   * @param item the new value of {@link #getItem()}.
   * @return a new {@link ScoreCell} with the given {@link #getItem() item} and all other properties like {@code this}
   *         one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public ScoreCell setItem(ValuedItem<?> item) {

    if (this.item == item) {
      return this;
    }
    ScoreCell cell = makeMutable();
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
   * @return a new {@link ScoreCell} with the given {@link #getBar() bar} and all other properties like {@code this}
   *         one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public ScoreCell setBar(BarLine bar) {

    if (this.bar == bar) {
      return this;
    }
    ScoreCell cell = makeMutable();
    cell.bar = bar;
    return cell;
  }

  /**
   * @return the fret number in case this cell belongs to a string line of a tabulature, or {@code -1} if no tab cell.
   */
  public int getFret() {

    return this.fret;
  }

  /**
   * @param fret new value of {@link #getFret()}.
   * @return a new {@link ScoreCell} with the given {@link #getFret() fret} and all other properties like {@code this}
   *         one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public ScoreCell setFret(int fret) {

    if (this.fret == fret) {
      return this;
    }
    ScoreCell cell = makeMutable();
    cell.fret = fret;
    return cell;
  }

  /**
   * @return the string number the {@link #getItem() tone} should be played on for a tabulature. Can be specified as a
   *         hint in a regular cell for {@link io.github.musicdoc.stave.voice.StaveVoice#isAutocompute() autocomputed}
   *         tabulature. Otherwise {@code 0} what means the the string is determined automatically in case a tabulature
   *         is computed.
   */
  public int getString() {

    return this.string;
  }

  /**
   * @param string new value of {@link #getString()}.
   * @return a new {@link ScoreCell} with the given {@link #getString() string} and all other properties like
   *         {@code this} one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public ScoreCell setString(int string) {

    if (this.string == string) {
      return this;
    }
    ScoreCell cell = makeMutable();
    cell.string = string;
    return cell;
  }

  @Override
  public ScoreCell transpose(int steps, boolean diatonic, TransposeContext context) {

    ScoreCell transposed = copy();
    if (transposed.chordContainer != null) {
      transposed.chordContainer = transposed.chordContainer.transpose(steps, diatonic, context);
    }
    if (transposed.item != null) {
      transposed.item = transposed.item.transpose(steps, diatonic, context);
    }
    return transposed;
  }

  @Override
  public int hashCode() {

    return Objects.hash(this.chordContainer, this.bar, this.item, this.lyric, this.staveChange);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    ScoreCell other = (ScoreCell) obj;
    if (!Objects.equals(this.chordContainer, other.chordContainer)) {
      return false;
    } else if (!Objects.equals(this.bar, other.bar)) {
      return false;
    } else if (!Objects.equals(this.item, other.item)) {
      return false;
    } else if (!Objects.equals(this.lyric, other.lyric)) {
      return false;
    } else if (!Objects.equals(this.staveChange, other.staveChange)) {
      return false;
    }
    return true;
  }

  @Override
  public void toString(StringBuilder sb) {

    if (this.item != null) {
      sb.append('{');
      this.item.toString(sb);
      sb.append('}');
    }
    if (this.chordContainer != null) {
      sb.append('[');
      this.chordContainer.toString(sb);
      sb.append(']');
    }
    sb.append(this.lyric);
    if (this.bar != null) {
      this.bar.toString(sb);
    }
  }

}
