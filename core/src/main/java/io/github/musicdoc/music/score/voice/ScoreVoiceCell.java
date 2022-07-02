package io.github.musicdoc.music.score.voice;

import io.github.musicdoc.music.bar.BarLine;
import io.github.musicdoc.music.harmony.chord.Chord;
import io.github.musicdoc.music.harmony.chord.ChordContainer;
import io.github.musicdoc.music.rythm.value.ValuedItem;
import io.github.musicdoc.music.score.ScoreCell;
import io.github.musicdoc.music.stave.Stave;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * {@link ScoreCell} for a {@link ScoreVoiceLine}.
 */
public class ScoreVoiceCell extends ScoreCell<ScoreVoiceCell> {

  private ValuedItem<?> item;

  private String lyric;

  private BarLine bar;

  private Stave stave;

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

  /**
   * @return the optional {@link Stave} to change the current stave (e.g. its {@link Stave#getKey() key} or
   *         {@link Stave#getBeat() beat}) at the beginning of this cell. Otherwise {@code null}.
   */
  public Stave getStave() {

    return this.stave;
  }

  /**
   * @param stave the new value of {@link #getStave()}.
   */
  public void setStave(Stave stave) {

    this.stave = stave;
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
   */
  public void setLyric(String lyric) {

    this.lyric = lyric;
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
   */
  public void setItem(ValuedItem<?> item) {

    this.item = item;
  }

  /**
   * @return the optional {@link BarLine} of this cell that will be displayed to the right. May be {@code null} for none.
   *         There is no left {@link BarLine} as this would be the right {@link BarLine} of the previous cell.
   */
  public BarLine getBar() {

    return this.bar;
  }

  /**
   * @param bar the new value of {@link #getBar()}.
   */
  public void setBar(BarLine bar) {

    this.bar = bar;
  }

  @Override
  public ScoreVoiceCell transpose(int steps, boolean diatonic, TransposeContext context) {

    ScoreVoiceCell transposed = new ScoreVoiceCell();
    transposed.bar = this.bar;
    transposed.lyric = this.lyric;
    if (this.chordContainer != null) {
      transposed.chordContainer = this.chordContainer.transpose(steps, diatonic, context);
    }
    if (this.item != null) {
      transposed.item = this.item.transpose(steps, diatonic, context);
    }
    return transposed;
  }

}
