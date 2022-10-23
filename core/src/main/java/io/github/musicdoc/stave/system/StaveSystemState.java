package io.github.musicdoc.stave.system;

import java.util.List;

import io.github.musicdoc.io.MusicInputStream;
import io.github.musicdoc.stave.Stave;
import io.github.musicdoc.stave.StaveBracket;
import io.github.musicdoc.stave.voice.StaveVoice;

/**
 * State for {@link StaveSystem} and according properties.
 */
public class StaveSystemState {

  private final boolean disconnectedDefault;

  private StaveSystem system;

  private StringBuilder voiceId;

  private boolean disconnected;

  private boolean floating;

  /**
   * The constructor.
   *
   * @param disconnectedDefault the default for {@link Stave#isDisconnected()}. Should be {@code false} for reasonable
   *        formats and {@code true} for ABC.
   */
  public StaveSystemState(boolean disconnectedDefault) {

    super();
    this.disconnectedDefault = disconnectedDefault;
    this.system = StaveSystemMultiple.of(null);
    this.voiceId = new StringBuilder();
  }

  /**
   * Handles a potentially {@link #appendVoiceId(String) collected} {@link StaveVoice#getId() voice ID}.
   */
  public void handleVoice() {

    if (this.voiceId.length() == 0) {
      return;
    }
    String id = this.voiceId.toString();
    this.voiceId.setLength(0);
    addVoice(new StaveVoice(id));
  }

  /**
   * @param voice the {@link StaveVoice} to add.
   */
  public void addVoice(StaveVoice voice) {

    Stave stave;
    if (this.system.isMultiple()) {
      stave = null;
      if ((this.floating) || (this.system.getBracket() == StaveBracket.NONE)) {
        stave = this.system.getLastStave();
      }
      if (stave == null) {
        stave = new Stave();
        stave.setDisconnected(this.disconnected);
        this.disconnected = this.disconnectedDefault;
        this.system.addChild(stave);
      }
    } else {
      stave = this.system.getStave();
    }
    stave.addVoice(voice);
    this.floating = false;
  }

  /**
   * @param bracket the {@link StaveBracket} to start.
   * @param in the {@link MusicInputStream}.
   */
  public void start(StaveBracket bracket, MusicInputStream in) {

    handleVoice();
    assert (bracket != null);
    this.system = this.system.addChild(bracket);
  }

  /**
   * @param bracket the {@link StaveBracket} to start.
   * @param in the {@link MusicInputStream}.
   */
  public void end(StaveBracket bracket, MusicInputStream in) {

    handleVoice();
    assert (bracket != null);
    if (this.system.getBracket() == bracket) {
      if (this.system.isSingle()) {
        if (this.system.getStave().getVoices().isEmpty()) {
          in.addError("Empty single group.");
        }
      } else {
        if (this.system.getChildren().isEmpty()) {
          in.addError("Empty multiple group.");
        }
      }
      this.system = this.system.getParent();
    } else {
      in.addError("Unmatched closing bracket '" + bracket.getEnd() + "'.");
    }
  }

  /**
   * @param stave the {@link Stave} to add.
   */
  public void addStave(Stave stave) {

    this.system.addChild(stave, StaveBracket.NONE);
  }

  /**
   * @param disconnected new value for {@link Stave#isDisconnected()}.
   */
  public void setDisconnected(boolean disconnected) {

    this.disconnected = disconnected;
  }

  /**
   * @param floating {@code true} if the next voice should be floating.
   */
  public void setFloating(boolean floating) {

    assert (this.voiceId.length() == 0);
    this.floating = floating;
  }

  /**
   * @param c the character of a {@link StaveVoice#getId() voice ID} that shall be appended.
   */
  public void appendVoiceId(char c) {

    this.voiceId.append(c);
  }

  /**
   * @param id the {@link StaveVoice#getId() voice ID} or a fragment of it that shall be appended.
   */
  public void appendVoiceId(String id) {

    if (id != null) {
      this.voiceId.append(id);
    }
  }

  /**
   * @return the current {@link StaveSystem}.
   */
  public StaveSystem getSystem() {

    return this.system;
  }

  /**
   * @param in the {@link MusicInputStream}.
   * @return the top-level {@link StaveSystem}.
   */
  public StaveSystem getTopSystem(MusicInputStream in) {

    if (this.system.getBracket() != null) {
      in.addError("Missing closing bracket '" + this.system.getBracket().getEnd() + "'.");
    }
    // find root
    StaveSystem result = this.system;
    while (result.parent != null) {
      result = result.parent;
    }
    // omit root, if it has only 1 child
    List<StaveSystem> children = result.getChildren();
    if (children.size() == 1) {
      return children.get(0);
    }
    return result;
  }

}
