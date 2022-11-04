package io.github.musicdoc.stave.system;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.MutableObjecteHelper;
import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.StaveBracket;
import io.github.musicdoc.stave.voice.StaveVoice;

/**
 * Implementation of {@link StaveSystem} for a group of {@link #isMultiple() multiple} {@link #getChildren() children}.
 */
public class StaveSystemMultiple extends StaveSystem {

  private List<StaveSystem> children;

  /**
   * The constructor.
   *
   * @param bracket the optional {@link #getBracket() bracket}.
   * @param parent the {@link #getParent() parent}.
   */
  private StaveSystemMultiple(StaveBracket bracket, StaveSystemMultiple parent) {

    super(bracket, parent);
    this.children = new ArrayList<>();
  }

  private StaveSystemMultiple(StaveSystemMultiple group, MutableObjecteCopier copier) {

    super(group, copier);
    this.children = copier.copyList(group.children, child -> {
      child.parent = this;
    });
  }

  @Override
  public StaveSystemMultiple copy(MutableObjecteCopier copier) {

    return new StaveSystemMultiple(this, copier);
  }

  @Override
  public List<StaveSystem> getChildren() {

    return this.children;
  }

  @Override
  public Stave getStave() {

    return null;
  }

  @Override
  public boolean isSingle() {

    return false;
  }

  @Override
  public StaveVoice getVoice(String id) {

    for (StaveSystem child : this.children) {
      StaveVoice voice = child.getVoice(id);
      if (voice != null) {
        return voice;
      }
    }
    return null;
  }

  @Override
  public StaveVoice getVoice(int index) {

    if (index < 0) {
      return null;
    }
    return getVoice(new VoiceIndex(index));
  }

  private StaveVoice getVoice(VoiceIndex index) {

    StaveVoice voice = null;
    for (StaveSystem child : this.children) {
      if (child.isSingle()) {
        List<StaveVoice> voices = child.getStave().getVoices();
        int size = voices.size();
        if (index.index < size) {
          return voices.get(index.index);
        }
        index.index -= size;
      } else {
        voice = ((StaveSystemMultiple) child).getVoice(index);
      }
      if (voice != null) {
        return voice;
      }
    }
    return voice; // will be null here
  }

  @Override
  public int getVoiceCount() {

    int count = 0;
    for (StaveSystem child : this.children) {
      count += child.getVoiceCount();
    }
    return count;
  }

  @Override
  public int indexOf(String id) {

    return indexOf(id, new VoiceIndex(0));
  }

  private int indexOf(String id, VoiceIndex index) {

    for (StaveSystem child : this.children) {
      if (child.isSingle()) {
        int i = child.indexOf(id);
        if (i >= 0) {
          return index.index + i;
        }
        index.index += child.getStave().getVoices().size();
      } else {
        int i = ((StaveSystemMultiple) child).indexOf(id, index);
        if (i >= 0) {
          return i;
        }
      }
    }
    return -1;
  }

  @Override
  public boolean isImmutable() {

    return this.immutable;
  }

  @Override
  public StaveSystemMultiple makeImmutable() {

    if (!this.immutable) {
      for (StaveSystem child : this.children) {
        child.makeImmutable();
      }
      this.children = MutableObjecteHelper.makeImmutableRecursive(this.children);
      super.makeImmutable();
    }
    return this;
  }

  @Override
  public StaveSystemMultiple addChild(StaveBracket childBracket) {

    requireMutable();
    StaveSystemMultiple child = new StaveSystemMultiple(childBracket, this);
    this.children.add(child);
    return child;
  }

  @Override
  public StaveSystemSingle addChild(Stave stave) {

    return addChild(stave, StaveBracket.NONE);
  }

  @Override
  public StaveSystemSingle addChild(Stave stave, StaveBracket childBracket) {

    requireMutable();
    StaveSystemSingle child = new StaveSystemSingle(stave, childBracket, this);
    this.children.add(child);
    return child;
  }

  /**
   * @param bracket the {@link #getBracket() bracket}.
   * @return the new root {@link StaveSystemMultiple}.
   */
  public static StaveSystemMultiple of(StaveBracket bracket) {

    return new StaveSystemMultiple(bracket, null);
  }

  private static class VoiceIndex {

    private VoiceIndex(int index) {

      super();
      this.index = index;
    }

    private int index;
  }

}
