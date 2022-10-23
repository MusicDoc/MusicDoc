package io.github.musicdoc.transpose;

import android.app.ActionBar.Tab;
import io.github.musicdoc.harmony.EnharmonicStyle;
import io.github.musicdoc.harmony.TonalSystem;
import io.github.musicdoc.harmony.key.MusicalKey;
import io.github.musicdoc.tone.Tone;

/**
 * Context for {@link Transposable#transpose(int, boolean, TransposeContext) transposing}.
 */
public class TransposeContext {

  private boolean keepKey;

  private MusicalKey key;

  private EnharmonicStyle enharmonicStyle;

  private boolean changeTab;

  private boolean normalizeChords;

  /**
   * The constructor.
   */
  public TransposeContext() {

    super();
    this.keepKey = true;
  }

  /**
   * The constructor.
   *
   * @param key the {@link #getKey() key}.
   */
  public TransposeContext(MusicalKey key) {

    this();
    this.key = key;
  }

  /**
   * The constructor.
   *
   * @param enharmonicStyle the {@link #getEnharmonicStyle() enharmonic style}.
   */
  public TransposeContext(EnharmonicStyle enharmonicStyle) {

    this();
    this.enharmonicStyle = enharmonicStyle;
  }

  /**
   * @return {@code true} if the {@link MusicalKey} shall remain unchanged, {@code false} otherwise (to transpose also
   *         the key).
   */
  public boolean isKeepKey() {

    return this.keepKey;
  }

  /**
   * @param keepKey the new value of {@link #isKeepKey()}.
   */
  public void setKeepKey(boolean keepKey) {

    this.keepKey = keepKey;
  }

  /**
   * @return the current {@link MusicalKey} used to transpose atomic musical objects such as e.g. a {@link Tone}. Will
   *         be {@code null} until set externally (by caller of initial transpose method) or internally (whilst
   *         transposing composed objects between recursive calls of transpose).
   */
  public MusicalKey getKey() {

    return this.key;
  }

  /**
   * @param key the new value of {@link #getKey()}.
   */
  public void setKey(MusicalKey key) {

    this.key = key;
  }

  /**
   * @return the {@link TonalSystem} to use.
   */
  public TonalSystem getTonalSystem() {

    if (this.key != null) {
      return this.key.getSystem();
    }
    return null;
  }

  /**
   * @return the {@link EnharmonicStyle}. May be used if {@link #getKey() key} is not available.
   */
  public EnharmonicStyle getEnharmonicStyle() {

    if (this.enharmonicStyle == null) {
      if (this.key == null) {
        return EnharmonicStyle.NORMAL;
      } else {
        return this.key.getEnharmonicStyle();
      }
    }
    return this.enharmonicStyle;
  }

  /**
   * @param enharmonicStyle the new value of {@link #getEnharmonicStyle()}.
   */
  public void setEnharmonicStyle(EnharmonicStyle enharmonicStyle) {

    this.enharmonicStyle = enharmonicStyle;
  }

  /**
   * @return {@code true} if a potential {@link Tab}s should be transposed so that its
   *         {@link io.github.musicdoc.clef.Clef#getTuning() tuning} actually changes.
   */
  public boolean isChangeTab() {

    return this.changeTab;
  }

  /**
   * @param changeTab the new value of {@link #isChangeTab()}.
   */
  public void setChangeTab(boolean changeTab) {

    this.changeTab = changeTab;
  }

  /**
   * @return {@code true} to {@link io.github.musicdoc.harmony.chord.ChordSymbol#normalize() normalize}
   *         {@link io.github.musicdoc.harmony.chord.ChordSymbol}s, {@code false} otherwise.
   */
  public boolean isNormalizeChords() {

    return this.normalizeChords;
  }

  /**
   * @param normalizeChords the new value of {@link #isNormalizeChords()}.
   */
  public void setNormalizeChords(boolean normalizeChords) {

    this.normalizeChords = normalizeChords;
  }
}
