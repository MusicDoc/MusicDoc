/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.stave;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.MutableObjecteHelper;
import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.clef.ClefSymbol;
import io.github.musicdoc.harmony.key.MusicalKey;
import io.github.musicdoc.rhythm.metre.Metre;
import io.github.musicdoc.stave.system.StaveSystem;
import io.github.musicdoc.stave.voice.StaveVoice;
import io.github.musicdoc.stave.voice.StaveVoiceContainer;

/**
 * Represents the configuration and intro of a musical <em>stave</em> (also known as <em>staff</em>), what in general
 * refers to the five lines used in classic music notation. Here a {@link Stave} contains the {@link #getClef() clef},
 * {@link #getKey() key} and {@link #getMetre() beat} as well as the {@link #getVoices() voices} related to the stave.
 * It is used for the initial definition and configuration of a stave as well as for changes to a stave within the line
 * (to change the key or beat).<br>
 * <br>
 * <em>Stave</em> is the British term that is called <em>staff</em> in US English. As <em>staff</em> is more ambiguous
 * and the plural form is always <em>staves</em> the British name was preferred even though <em>staff</em> is used quite
 * commonly.
 */
public final class Stave extends AbstractStave<Stave> implements StaveVoiceContainer, Comparable<Stave> {

  /** The default {@link Stave}. */
  public static final Stave DEFAULT = new Stave(Clef.TREBLE, MusicalKey.C_MAJOR, Metre.COMMON_TIME).makeImmutable();

  private boolean disconnected;

  private int lines;

  private int index;

  private List<StaveVoice> voices;

  /**
   * The constructor.
   */
  public Stave() {

    this(null, null, null);
  }

  /**
   * The constructor.
   *
   * @param clef the {@link #getClef() clef}.
   * @param key the {@link #getKey() key}.
   * @param beat the {@link #getMetre() beat}.
   */
  public Stave(Clef clef, MusicalKey key, Metre beat) {

    this(clef, key, beat, false);
  }

  /**
   * The constructor.
   *
   * @param clef the {@link #getClef() clef}.
   * @param key the {@link #getKey() key}.
   * @param beat the {@link #getMetre() beat}.
   * @param disconnected the {@link #isDisconnected() disconnected} flag.
   */
  public Stave(Clef clef, MusicalKey key, Metre beat, boolean disconnected) {

    super(clef, key, beat);
    this.disconnected = disconnected;
    if (clef.getSymbol() == ClefSymbol.TAB) {
      this.lines = 6;
    } else {
      this.lines = 5;
    }
    this.voices = new ArrayList<>();
  }

  private Stave(Stave stave, MutableObjecteCopier copier) {

    super(stave, copier);
    this.disconnected = stave.disconnected;
    this.lines = stave.lines;
    this.voices = copier.copyList(stave.voices, voice -> {
      voice.setStave(this);
    });
  }

  @Override
  public Stave copy(MutableObjecteCopier copier) {

    return new Stave(this, copier);
  }

  /**
   * @return the {@link List} of {@link StaveVoice}s.
   */
  public List<StaveVoice> getVoices() {

    return this.voices;
  }

  /**
   * @param voice the {@link StaveVoice} to add.
   * @return the {@link StaveVoice} that has been added. Typically {@code voice} but may be a {@link #copy()} if
   *         {@link StaveVoice#isImmutable() immutable}.
   * @see #getVoices()
   */
  public Stave addVoice(StaveVoice voice) {

    requireMutable();
    voice = voice.setStave(this);
    this.voices.add(voice);
    return this;
  }

  /**
   * @param id the {@link StaveVoice#getId() ID} of the new {@link StaveVoice}.
   * @return the created and added {@link StaveVoice}. Will be mutable.
   */
  public StaveVoice addVoice(String id) {

    StaveVoice voice = new StaveVoice(id);
    addVoice(voice);
    return voice;
  }

  @Override
  public StaveVoice getVoice(String id) {

    for (StaveVoice voice : this.voices) {
      if (Objects.equals(voice.getId(), id)) {
        return voice;
      }
    }
    return null;
  }

  @Override
  public StaveVoice getVoice(int voiceIndex) {

    if ((voiceIndex < 0) || (voiceIndex >= this.voices.size())) {
      return null;
    }
    return this.voices.get(voiceIndex);
  }

  @Override
  public int indexOf(String id) {

    int i = 0;
    for (StaveVoice voice : this.voices) {
      if (Objects.equals(voice.getId(), id)) {
        return i;
      }
      i++;
    }
    return -1;
  }

  @Override
  public int getVoiceCount() {

    return this.voices.size();
  }

  /**
   * @return {@code true} if this {@link Stave} is not connected to the previous {@link Stave} of the same
   *         {@link StaveSystem} with a vertical bar, {@code false} otherwise. Default is {@code false}.
   */
  public boolean isDisconnected() {

    return this.disconnected;
  }

  /**
   * @param newDisconnected the new {@link #isDisconnected() disconnected}.
   * @return a new {@link Stave} with the given {@link #isDisconnected() disconnected} and all other properties like
   *         {@code this} one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public Stave setDisconnected(boolean newDisconnected) {

    if (newDisconnected == this.disconnected) {
      return this;
    }
    Stave stave = makeMutable();
    stave.disconnected = newDisconnected;
    return stave;
  }

  /**
   * @return the number of lines in this {@link Stave}. The default is 5.
   */
  public int getLines() {

    return this.lines;
  }

  /**
   * @param newLines the new {@link #getLines() lines}.
   * @return a new {@link Stave} with the given {@link #getLines() lines} and all other properties like {@code this}
   *         one. Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public Stave setLines(int newLines) {

    if (newLines == this.lines) {
      return this;
    }
    if (newLines < 1) {
      throw new IllegalArgumentException("Stave must have at least 1 line - too low value: " + newLines);
    }
    Stave stave = makeMutable();
    stave.lines = newLines;
    return stave;
  }

  /**
   * @return the index of this {@link Stave} within the {@link StaveSystem}.
   */
  public int getIndex() {

    return this.index;
  }

  /**
   * @param newIndex the new {@link #getIndex() index}.
   * @return this {@link Stave} with the given {@link #getIndex() index} and all other properties like {@code this} one.
   *         Will be a {@link #copy()} if {@link #isImmutable() immutable}.
   */
  public Stave setIndex(int newIndex) {

    assert (newIndex >= 0);
    if (newIndex == this.index) {
      return this;
    }
    Stave stave = makeMutable();
    stave.index = newIndex;
    return stave;
  }

  /**
   * Applies a {@link StaveChange} to this {@link Stave}. Typically you shall first {@link #copy() copy} the initial
   * stave before using this method.
   *
   * @param staveChange the {@link AbstractStave} (typically {@link StaveChange}) to apply.
   * @return this {@link Stave} with the given {@link StaveChange} applied. Will be a {@link #copy()} if
   *         {@link #isImmutable() immutable}.
   */
  public Stave apply(AbstractStave<?> staveChange) {

    Stave stave = this;
    if (staveChange != null) {
      Clef newClef = staveChange.getClef();
      if (newClef != null) {
        stave = stave.setClef(newClef);
      }
      MusicalKey newKey = staveChange.getKey();
      if (newKey != null) {
        stave = stave.setKey(newKey);
      }
      Metre newMetre = staveChange.getMetre();
      if (newMetre != null) {
        stave = stave.setMetre(newMetre);
      }
    }
    return stave;
  }

  @Override
  public Stave makeImmutable() {

    if (!this.immutable) {
      for (StaveVoice voice : this.voices) {
        voice.makeImmutable();
      }
      this.voices = MutableObjecteHelper.makeImmutableRecursive(this.voices);
      super.makeImmutable();
    }
    return this;
  }

  @Override
  public int compareTo(Stave other) {

    if (other == null) {
      return -1;
    }
    return this.index - other.index;
  }

  @Override
  public int hashCode() {

    return Objects.hash(this.clef, this.key, this.metre);
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == this) {
      return true;
    } else if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    Stave other = (Stave) obj;
    if (!Objects.equals(this.clef, other.clef)) {
      return false;
    } else if (!Objects.equals(this.key, other.key)) {
      return false;
    } else if (!Objects.equals(this.metre, other.metre)) {
      return false;
    } else if (!Objects.equals(this.voices, other.voices)) {
      return false;
    }
    return true;
  }

  @Override
  public void toString(StringBuilder sb) {

    if (this.disconnected) {
      sb.append('-');
    }
    super.toString(sb);
    for (StaveVoice voice : this.voices) {
      sb.append('(');
      sb.append(voice);
      sb.append(')');
    }
  }

}
