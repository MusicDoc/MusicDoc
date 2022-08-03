/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.stave;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.MutableObjecteHelper;
import io.github.musicdoc.music.clef.Clef;
import io.github.musicdoc.music.format.FormatConstants;
import io.github.musicdoc.music.harmony.key.MusicalKey;
import io.github.musicdoc.music.rythm.beat.Beat;
import io.github.musicdoc.music.stave.system.StaveSystem;
import io.github.musicdoc.music.stave.voice.StaveVoice;
import io.github.musicdoc.music.stave.voice.StaveVoiceContainer;

/**
 * Represents the configuration and intro of a musical <em>stave</em> (also known as <em>staff</em>), what in general
 * refers to the five lines used in classic music notation. Here a {@link Stave} contains the {@link #getClef() clef},
 * {@link #getKey() key} and {@link #getBeat() beat} as well as the {@link #getVoices() voices} related to the stave. It
 * is used for the initial definition and configuration of a stave as well as for changes to a stave within the line (to
 * change the key or beat).<br>
 * <br>
 * <em>Stave</em> is the British term that is called <em>staff</em> in US English. As <em>staff</em> is more ambiguous
 * and the plural form is always <em>staves</em> the British name was preferred even though <em>staff</em> is used quite
 * commonly.
 */
public final class Stave extends AbstractStave<Stave> implements StaveVoiceContainer {

  /** The default {@link Stave}. */
  public static final Stave DEFAULT = new Stave(Clef.TREBLE, MusicalKey.C_MAJOR, Beat.COMMON_TIME).makeImmutable();

  private static final String SEPARATOR = FormatConstants.PROPERTIES_SEPARATOR;

  private boolean disconnected;

  private int lines;

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
   * @param beat the {@link #getBeat() beat}.
   */
  public Stave(Clef clef, MusicalKey key, Beat beat) {

    this(clef, key, beat, false);
  }

  /**
   * The constructor.
   *
   * @param clef the {@link #getClef() clef}.
   * @param key the {@link #getKey() key}.
   * @param beat the {@link #getBeat() beat}.
   * @param disconnected the {@link #isDisconnected() disconnected} flag.
   */
  public Stave(Clef clef, MusicalKey key, Beat beat, boolean disconnected) {

    super(clef, key, beat);
    this.disconnected = disconnected;
    this.lines = 5;
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
  public StaveVoice getVoice(int index) {

    if ((index < 0) || (index >= this.voices.size())) {
      return null;
    }
    return this.voices.get(index);
  }

  @Override
  public int indexOf(String id) {

    int index = 0;
    for (StaveVoice voice : this.voices) {
      if (Objects.equals(voice.getId(), id)) {
        return index;
      }
      index++;
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
  public int hashCode() {

    return Objects.hash(this.clef, this.key, this.beat);
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
    } else if (!Objects.equals(this.beat, other.beat)) {
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
