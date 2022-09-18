package io.github.musicdoc.stave;

import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.harmony.key.MusicalKey;
import io.github.musicdoc.rhythm.metre.Metre;
import io.github.musicdoc.transpose.AbstractTransposable;
import io.github.musicdoc.transpose.TransposeContext;

/**
 * Abstract base class for {@link Stave} and {@link StaveChange}. Or in other words a container for {@link #getClef()
 * clef}, {@link #getKey() key}, and {@link #getMetre() metre}.
 *
 * @param <SELF> type of this class itself.
 */
public abstract class AbstractStave<SELF extends AbstractStave<SELF>> extends AbstractTransposable<SELF>
    implements MutableObject<SELF> {

  /** Property name of {@link #getClef() clef}. */
  public static final String PROPERTY_CLEF = "clef";

  /** Property name of {@link #getKey() key}. */
  public static final String PROPERTY_KEY = "key";

  /** Property name of {@link #getMetre() metre}. */
  public static final String PROPERTY_METRE = "metre";

  /** @see #getClef() */
  protected Clef clef;

  /** @see #getKey() */
  protected MusicalKey key;

  /** @see #getMetre() */
  protected Metre metre;

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
   * @param metre the {@link #getMetre() metre}.
   */
  public AbstractStave(Clef clef, MusicalKey key, Metre metre) {

    super();
    this.clef = clef;
    this.key = key;
    this.metre = metre;
  }

  /**
   * The {@link #copy()} constructor.
   *
   * @param stave the {@link AbstractStave} to copy.
   * @param copier the {@link MutableObjecteCopier}.
   */
  protected AbstractStave(AbstractStave<SELF> stave, MutableObjecteCopier copier) {

    super();
    this.clef = stave.clef.copy(copier);
    this.key = stave.key;
    this.metre = stave.metre;
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
  public SELF setClef(Clef newClef) {

    if (newClef == this.clef) {
      return self();
    }
    SELF stave = makeMutable();
    stave.clef = newClef;
    return stave;
  }

  /**
   * @return the optional {@link MusicalKey}. If {@code null} or the same {@link MusicalKey} as the previous
   *         {@link Stave} on the same line, no enharmonic signs will be displayed (e.g. the new {@link Stave} might
   *         only change the {@link Metre}).
   */
  public MusicalKey getKey() {

    return this.key;
  }

  /**
   * @param newKey the new {@link #getKey() key}.
   * @return a {@link Stave} with the given {@link #getKey() key} and all other properties like {@code this} one. Will
   *         be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public SELF setKey(MusicalKey newKey) {

    if (newKey == this.key) {
      return self();
    }
    SELF stave = makeMutable();
    stave.key = newKey;
    return stave;
  }

  /**
   * @return the optional {@link Metre}. If {@code null} or the same {@link Metre} as the previous {@link Stave} on the
   *         same line, no metre will be displayed (e.g. the new {@link Stave} might only change the {@link Clef}). If
   *         this is the first {@link Stave} of a {@link io.github.musicdoc.score.Score} and no {@link Metre} is defined
   *         then {@link Metre#_4_4 4/4} is assumed (but not displayed).
   */
  public Metre getMetre() {

    return this.metre;
  }

  /**
   * @param newMetre the new {@link #getMetre() metre}.
   * @return a new {@link Stave} with the given {@link #getMetre() metre} and all other properties like {@code this}
   *         one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public SELF setMetre(Metre newMetre) {

    if (newMetre == this.metre) {
      return self();
    }
    SELF stave = makeMutable();
    stave.metre = newMetre;
    return stave;
  }

  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  @Override
  public SELF makeImmutable() {

    this.immutable = true;
    return self();
  }

  @Override
  public SELF transpose(int steps, boolean diatonic, TransposeContext context) {

    if ((context.isKeepKey()) || (this.key == null)) {
      return self();
    }
    SELF transposed = copy();
    transposed.key = this.key.transpose(steps, diatonic, context);
    return transposed;
  }

  @Override
  public void toString(StringBuilder sb) {

    if (this.clef != null) {
      sb.append("§");
      sb.append(this.clef);
    }
    if (this.key != null) {
      sb.append('$');
      sb.append(this.key);
    }
    if (this.metre != null) {
      sb.append('%');
      sb.append(this.metre);
    }
  }

}
