package io.github.musicdoc.music.stave.system;

import java.util.List;

import io.github.musicdoc.MutableObject;
import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.music.stave.Stave;
import io.github.musicdoc.music.stave.StaveBracket;
import io.github.musicdoc.music.stave.voice.StaveVoice;
import io.github.musicdoc.music.stave.voice.StaveVoiceContainer;

/**
 * The system defining the structure of {@link Stave}s belonging together in a
 * {@link io.github.musicdoc.music.score.Score}. Technically it is a node of a tree of {@link Stave}s that groups
 * {@link #getChildren() children} that may be connected by a {@link #getBracket() bracket}. The actual {@link Stave}s
 * in this tree are wrapped in a {@link StaveSystemSingle} what can also be determined via {@link #isSingle()}. From the
 * perspective of a musical score only the root-node {@link StaveSystem} is actually what is called the system.
 */
public abstract class StaveSystem implements StaveVoiceContainer, MutableObject<StaveSystem> {

  /** The default {@link StaveSystem}. */
  public static final StaveSystem DEFAULT = new StaveSystemSingle(Stave.DEFAULT.makeMutable().addVoice(StaveVoice.EMPTY), StaveBracket.NONE,
      null).makeImmutable();

  /** @see #getBracket() */
  protected StaveBracket bracket;

  /** @see #getParent() */
  protected StaveSystemMultiple parent;

  /** @see #isImmutable() */
  protected boolean immutable;

  /**
   * The constructor.
   *
   * @param bracket the optional {@link #getBracket() bracket}.
   * @param parent the {@link #getParent() parent} group.
   */
  protected StaveSystem(StaveBracket bracket, StaveSystemMultiple parent) {

    super();
    this.bracket = bracket;
    this.parent = parent;
  }

  /**
   * The copy constructor.
   *
   * @param system the {@link StaveSystem} to copy.
   * @param copier the {@link MutableObjecteCopier}.
   */
  protected StaveSystem(StaveSystem system, MutableObjecteCopier copier) {

    super();
    this.bracket = system.bracket;
    this.parent = system.parent;
  }

  /**
   * @return the {@link StaveBracket} or {@code null} for no bracket.
   */
  public StaveBracket getBracket() {

    return this.bracket;
  }

  /**
   * @param newBracket the new {@link #getBracket() bracket}.
   * @return a new {@link Stave} with the given {@link #getBracket() bracket} and all other properties like {@code this}
   *         one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public StaveSystem setBracket(StaveBracket newBracket) {

    if (newBracket == this.bracket) {
      return this;
    }
    StaveSystem system = makeMutable();
    system.bracket = newBracket;
    return system;
  }

  /**
   * @return the {@link List} of child {@link StaveSystem}s grouped by this {@link StaveSystem} with an optional
   *         {@link #getBracket() bracket} in their order of appearance in the rendered
   *         {@link io.github.musicdoc.music.score.Score}.
   */
  public abstract List<StaveSystem> getChildren();

  /**
   * @return the first element of {@link #getChildren()} or {@code null} if not present.
   */
  public StaveSystem getFirstChild() {

    List<StaveSystem> children = getChildren();
    if (children.isEmpty()) {
      return null;
    }
    return children.get(0);
  }

  /**
   * @return the first {@link #getStave() stave} contained in this group or {@code null} if not present.
   */
  public Stave getFirstStave() {

    StaveSystem firstChild = getFirstChild();
    if (firstChild.isSingle()) {
      return firstChild.getStave();
    } else {
      return firstChild.getFirstStave();
    }
  }

  /**
   * @return the last element of {@link #getChildren()} or {@code null} if not present.
   */
  public StaveSystem getLastChild() {

    List<StaveSystem> children = getChildren();
    int i = children.size() - 1;
    if (i >= 0) {
      return children.get(i);
    }
    return null;
  }

  /**
   * @return the last {@link #getStave() stave} contained in this group or {@code null} if not present.
   */
  public Stave getLastStave() {

    StaveSystem lastChild = getLastChild();
    if (lastChild.isSingle()) {
      return lastChild.getStave();
    } else {
      return lastChild.getLastStave();
    }
  }

  /**
   * @return the single {@link Stave} wrapped as {@link #isSingle() single} {@link StaveSystem} or {@code null}
   *         otherwise (if not {@link #isSingle() single} but {@link StaveSystemMultiple multiple stave group}).
   */
  public abstract Stave getStave();

  /**
   * @return {@code true} for a {@link StaveSystemSingle single stave group} that has no {@link #getChildren() children}
   *         but a single {@link #getStave() stave}, {@code false} otherwise (if {@link #isMultiple() multiple}).
   */
  public abstract boolean isSingle();

  /**
   * @return {@code true} for a {@link StaveSystemMultiple multiple stave group} that has {@link #getChildren()
   *         children} but no direct {@link #getStave() stave}, {@code false} otherwise (if {@link #isSingle() single}).
   */
  public boolean isMultiple() {

    return !isSingle();
  }

  /**
   * @return the parent {@link StaveSystemMultiple} or {@code null} if this is the root group.
   */
  public StaveSystemMultiple getParent() {

    return this.parent;
  }

  /**
   * @param childBracket the {@link #getBracket() bracket} of the new child.
   * @return the new child.
   */
  public abstract StaveSystemMultiple addChild(StaveBracket childBracket);

  /**
   * @param stave the {@link #getStave() stave} of the new child.
   * @return the new child.
   */
  public abstract StaveSystemSingle addChild(Stave stave);

  /**
   * @param stave the {@link #getStave() stave} of the new child.
   * @param childBracket the {@link #getBracket() bracket} of the new child.
   * @return the new child.
   */
  public abstract StaveSystemSingle addChild(Stave stave, StaveBracket childBracket);

  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  @Override
  public StaveSystem makeImmutable() {

    this.immutable = true;
    return this;
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    toString(sb);
    return sb.toString();
  }

  protected void toString(StringBuilder sb) {

    if (this.bracket != null) {
      sb.append(this.bracket.getStart());
    }
    Stave stave = getStave();
    if (stave != null) {
      sb.append(stave.toString());
    }
    for (StaveSystem child : getChildren()) {
      child.toString(sb);
    }
    if (this.bracket != null) {
      sb.append(this.bracket.getEnd());
    }
  }

}
