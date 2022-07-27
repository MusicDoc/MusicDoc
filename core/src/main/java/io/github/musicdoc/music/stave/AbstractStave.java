package io.github.musicdoc.music.stave;

import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.music.clef.Clef;
import io.github.musicdoc.music.harmony.key.MusicalKey;
import io.github.musicdoc.music.rythm.beat.Beat;
import io.github.musicdoc.music.transpose.AbstractTransposable;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * Abstract base class for {@link Stave} and {@link StaveChange}. Or in other words a container for {@link #getClef()
 * clef}, {@link #getKey() key}, and {@link #getBeat() beat}.
 *
 * @param <T> type of this class itself.
 */
public abstract class AbstractStave<T extends AbstractStave<T>> extends AbstractTransposable<T> implements MutableObject<T> {

  /** Property name of {@link #getClef() clef}. */
  public static final String PROPERTY_CLEF = "clef";

  /** Property name of {@link #getKey() key}. */
  public static final String PROPERTY_KEY = "key";

  /** Property name of {@link #getBeat() beat}. */
  public static final String PROPERTY_BEAT = "beat";

  /** @see #getClef() */
  protected Clef clef;

  /** @see #getKey() */
  protected MusicalKey key;

  /** @see #getBeat() */
  protected Beat beat;

  /** @see #isImmutable() */
  protected boolean immutable;

  /**
   * The constructor.
   */
  public AbstractStave() {

    super();
  }

  /**
   * The constructor.
   *
   * @param clef the {@link #getClef() clef}.
   * @param key the {@link #getKey() key}.
   * @param beat the {@link #getBeat() beat}.
   */
  public AbstractStave(Clef clef, MusicalKey key, Beat beat) {

    super();
    this.clef = clef;
    this.key = key;
    this.beat = beat;
  }

  /**
   * The {@link #copy()} constructor.
   *
   * @param stave the {@link AbstractStave} to copy.
   * @param copier the {@link MutableObjecteCopier}.
   */
  protected AbstractStave(AbstractStave<T> stave, MutableObjecteCopier copier) {

    super();
    this.clef = stave.clef.copy(copier);
    this.key = stave.key;
    this.beat = stave.beat;
  }

  /**
   * @return the optional {@link Clef} or {@code null} if undefined.
   */
  public Clef getClef() {

    return this.clef;
  }

  /**
   * @param newClef the new {@link #getClef() clef}.
   * @return a {@link Stave} with the given {@link #getClef() clef} and all other properties like {@code this} one. Will
   *         be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public T setClef(Clef newClef) {

    if (newClef == this.clef) {
      return self();
    }
    T stave = makeMutable();
    stave.clef = newClef;
    return stave;
  }

  /**
   * @return the optional {@link MusicalKey}. If {@code null} or the same {@link MusicalKey} as the previous
   *         {@link Stave} on the same line, no enharmonic signs will be displayed (e.g. the new {@link Stave} might
   *         only change the {@link Beat}).
   */
  public MusicalKey getKey() {

    return this.key;
  }

  /**
   * @param newKey the new {@link #getKey() key}.
   * @return a {@link Stave} with the given {@link #getKey() key} and all other properties like {@code this} one. Will
   *         be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public T setKey(MusicalKey newKey) {

    if (newKey == this.key) {
      return self();
    }
    T stave = makeMutable();
    stave.key = newKey;
    return stave;
  }

  /**
   * @return the optional {@link Beat}. If {@code null} or the same {@link Beat} as the previous {@link Stave} on the
   *         same line, no beat will be displayed (e.g. the new {@link Stave} might only change the {@link Clef}). If
   *         this is the first {@link Stave} of a {@link io.github.musicdoc.music.score.Score} and no {@link Beat} is
   *         defined then {@link Beat#_4_4 4/4} is assumed (but not displayed).
   */
  public Beat getBeat() {

    return this.beat;
  }

  /**
   * @param newBeat the new {@link #getBeat() beat}.
   * @return a new {@link Stave} with the given {@link #getBeat() beat} and all other properties like {@code this} one.
   *         Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public T setBeat(Beat newBeat) {

    if (newBeat == this.beat) {
      return self();
    }
    T stave = makeMutable();
    stave.beat = newBeat;
    return stave;
  }

  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  @Override
  public T makeImmutable() {

    this.immutable = true;
    return self();
  }

  @Override
  public T transpose(int steps, boolean diatonic, TransposeContext context) {

    if ((context.isKeepKey()) || (this.key == null)) {
      return self();
    }
    T transposed = copy();
    transposed.key = this.key.transpose(steps, diatonic, context);
    return transposed;
  }

}
