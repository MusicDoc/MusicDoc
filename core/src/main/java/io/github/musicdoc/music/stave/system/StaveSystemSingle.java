package io.github.musicdoc.music.stave.system;

import java.util.Collections;
import java.util.List;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.music.stave.Stave;
import io.github.musicdoc.music.stave.StaveBracket;
import io.github.musicdoc.music.stave.voice.StaveVoice;

/**
 * Implementation of {@link StaveSystem} for a single {@link #getStave() stave}.
 */
public class StaveSystemSingle extends StaveSystem {

  private Stave stave;

  /**
   * The constructor.
   *
   * @param stave the single {@link #getStave() stave}.
   * @param bracket the optional {@link #getBracket() bracket}.
   * @param parent the {@link #getParent() parent}.
   */
  public StaveSystemSingle(Stave stave, StaveBracket bracket, StaveSystemMultiple parent) {

    super(bracket, parent);
    this.stave = stave;
  }

  private StaveSystemSingle(StaveSystemSingle group, MutableObjecteCopier copier) {

    super(group, copier);
    this.stave = group.stave;
  }

  @Override
  public StaveSystemSingle copy(MutableObjecteCopier copier) {

    return new StaveSystemSingle(this, copier);
  }

  @Override
  public List<StaveSystem> getChildren() {

    return Collections.emptyList();
  }

  @Override
  public Stave getStave() {

    return this.stave;
  }

  /**
   * @param newStave the new {@link #getStave() stave}.
   * @return a new {@link Stave} with the given {@link #getStave() stave} and all other properties like {@code this}
   *         one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public StaveSystemSingle setStave(Stave newStave) {

    if (newStave == this.stave) {
      return this;
    }
    StaveSystemSingle group = (StaveSystemSingle) makeMutable();
    group.stave = newStave;
    return group;
  }

  @Override
  public boolean isSingle() {

    return true;
  }

  @Override
  public StaveVoice getVoice(String id) {

    return this.stave.getVoice(id);
  }

  @Override
  public StaveVoice getVoice(int index) {

    return this.stave.getVoice(index);
  }

  @Override
  public int indexOf(String id) {

    return this.stave.indexOf(id);
  }

  @Override
  public int getVoiceCount() {

    return this.stave.getVoiceCount();
  }

  @Override
  public StaveSystemSingle makeImmutable() {

    if (!this.immutable) {
      this.stave.makeImmutable();
      super.makeImmutable();
    }
    return this;
  }

  @Override
  public StaveSystemMultiple addChild(StaveBracket childBracket) {

    throw new IllegalStateException();
  }

  @Override
  public StaveSystemSingle addChild(Stave childStave) {

    throw new IllegalStateException();
  }

  @Override
  public StaveSystemSingle addChild(Stave childStave, StaveBracket childBracket) {

    throw new IllegalStateException();
  }

}
