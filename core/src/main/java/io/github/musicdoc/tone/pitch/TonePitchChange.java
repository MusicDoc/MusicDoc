package io.github.musicdoc.tone.pitch;

import io.github.musicdoc.harmony.key.MusicalKey;
import io.github.musicdoc.tone.EnharmonicType;

/**
 * Stateful container to {@link #resolve(TonePitch) resolve} a {@link TonePitch} according to {@link #getKey() key} and
 * {@link #resolve(TonePitch, EnharmonicType) previous accidental signs} that should be {@link #clear() cleared} at the
 * next {@link io.github.musicdoc.bar.BarLine}.
 */
public class TonePitchChange {

  private final TonePitchMapping[] mapping;

  private MusicalKey key;

  /**
   * The constructor.
   */
  public TonePitchChange() {

    super();
    this.mapping = new TonePitchMapping[12];
    for (int i = 0; i < this.mapping.length; i++) {
      this.mapping[i] = new TonePitchMapping();
    }
    setKey(MusicalKey.C_MAJOR);
  }

  /**
   * @return the current {@link MusicalKey}.
   */
  public MusicalKey getKey() {

    return this.key;
  }

  /**
   * @param key new value of {@link #getKey()}.
   */
  public void setKey(MusicalKey key) {

    this.key = key;
    for (int i = 0; i < this.mapping.length; i++) {
      this.mapping[i].clear();
    }
    for (TonePitch pitch : key.getDiatonicScale()) {
      int signCount = pitch.getSignCount();
      if (signCount != 0) {
        TonePitch source;
        if (signCount > 0) {
          source = pitch.flatten();
        } else {
          source = pitch.sharpen();
        }
        TonePitchMapping tpm = this.mapping[source.getStep().get()];
        tpm.source = source;
        tpm.targetKey = pitch;
        tpm.targetCurrent = pitch;
      }
    }
    this.key.getChromaticSignTones();
  }

  /**
   * @param pitch the raw pitch that was specified e.g. in ABC format.
   * @return the resolved pitch that may be different due to {@link #getKey() key} or
   *         {@link #resolve(TonePitch, EnharmonicType) accidental signs in current bar}.
   */
  public TonePitch resolve(TonePitch pitch) {

    return resolve(pitch, null);
  }

  /**
   * @param pitch the {@link TonePitch} to change.
   * @param type the {@link EnharmonicType} of the change. {@link EnharmonicType#SINGLE_SHARP} to
   *        {@link TonePitch#sharpen() sharpen}, {@link EnharmonicType#SINGLE_FLAT} to {@link TonePitch#flatten()
   *        flatten}, and {@link EnharmonicType#NORMAL} for neutral sign reverting previous sharpening or flattening.
   * @return the {@link TonePitch} with the {@link EnharmonicType} applied.
   */
  public TonePitch resolve(TonePitch pitch, EnharmonicType type) {

    if (pitch == null) {
      return null;
    }
    if (type == null) {
      TonePitchMapping tpm = this.mapping[pitch.getStep().get()];
      if (tpm.targetCurrent != null) {
        assert (tpm.source == pitch);
        return tpm.targetCurrent;
      }
      return pitch;
    }
    assert (pitch.getSignCount() == 0);
    TonePitchMapping tpm = this.mapping[pitch.getStep().get()];
    if (tpm.source == null) {
      tpm.source = pitch;
    } else {
      assert (tpm.source == pitch); // TODO may be incorrect
    }
    if (type == EnharmonicType.SINGLE_SHARP) {
      tpm.targetCurrent = pitch.sharpen();
    } else if (type == EnharmonicType.SINGLE_FLAT) {
      tpm.targetCurrent = pitch.flatten();
    } else if (type == EnharmonicType.NORMAL) {
      tpm.targetCurrent = pitch;
    } else {
      throw new IllegalStateException();
    }
    return tpm.targetCurrent;
  }

  /**
   * Clears the current pitch changes that have been added on top of the {@link #getKey() current key} due to
   * {@link #resolve(TonePitch, EnharmonicType) accidental signs in current bar}. Shall be called at the end of each
   * {@link io.github.musicdoc.bar.BarLine bar}.
   */
  public void clear() {

    for (int i = 0; i < this.mapping.length; i++) {
      this.mapping[i].targetCurrent = this.mapping[i].targetKey;
    }
  }

  private static class TonePitchMapping {

    private TonePitch source;

    private TonePitch targetKey;

    private TonePitch targetCurrent;

    public void clear() {

      this.source = null;
      this.targetKey = null;
      this.targetCurrent = null;
    }
  }

}
