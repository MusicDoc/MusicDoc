/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.note;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.ObjectHelper;
import io.github.musicdoc.music.rythm.MusicalValue;
import io.github.musicdoc.music.rythm.ValuedItem;
import io.github.musicdoc.music.rythm.ValuedItemDecoration;
import io.github.musicdoc.music.tone.Tone;
import io.github.musicdoc.music.transpose.TransposeContext;

/**
 * A {@link Note} is a musical object that has a {@link #getTone() tone} and a {@link #getValue() value}.
 */
public class Note extends ValuedItem<Note> {

  private final Tone tone;

  /**
   * The constructor.
   *
   * @param tone - the {@link #getTone() tone}.
   * @param value - the {@link #getValue() value}.
   */
  public Note(Tone tone, MusicalValue value) {

    this(tone, value, new ArrayList<ValuedItemDecoration>());
  }

  /**
   * The constructor.
   *
   * @param tone - the {@link #getTone() tone}.
   * @param value - the {@link #getValue() value}.
   * @param decorations - the {@link #getDecorations() decorations}.
   */
  public Note(Tone tone, MusicalValue value, List<ValuedItemDecoration> decorations) {

    super(value, decorations);
    ObjectHelper.requireNonNull(tone, "tone");
    this.tone = tone;
  }

  @Override
  public Tone getTone() {

    return this.tone;
  }

  @Override
  public Note transpose(int steps, boolean diatonic, TransposeContext context) {

    Tone newTone = this.tone.transpose(steps, diatonic, context);
    return new Note(newTone, getValue(), new ArrayList<>(getDecorations()));
  }

  @Override
  public String toString() {

    return this.tone.toString() + this.value;
  }
}
