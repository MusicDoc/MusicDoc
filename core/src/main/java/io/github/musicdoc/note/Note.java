/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import io.github.musicdoc.MutableObjecteCopier;
import io.github.musicdoc.clef.Clef;
import io.github.musicdoc.decoration.MusicalDecoration;
import io.github.musicdoc.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.glyphs.smufl.SmuflGlyphsNote;
import io.github.musicdoc.glyphs.unicode.UnicodeGlyphsNotes;
import io.github.musicdoc.rythm.value.MusicalValue;
import io.github.musicdoc.rythm.value.MusicalValueVariation;
import io.github.musicdoc.rythm.value.ValuedItem;
import io.github.musicdoc.tone.Tone;
import io.github.musicdoc.transpose.TransposeContext;

/**
 * A {@link Note} is a musical object that has a {@link #getTone() tone} and a {@link #getValue() value}.
 */
public class Note extends ValuedItem<Note> {

  private Tone tone;

  /**
   * The constructor.
   *
   * @param tone - the {@link #getTone() tone}.
   * @param value - the {@link #getValue() value}.
   */
  public Note(Tone tone, MusicalValue value) {

    this(tone, value, new ArrayList<MusicalDecoration>());
  }

  /**
   * The constructor.
   *
   * @param tone - the {@link #getTone() tone}.
   * @param value - the {@link #getValue() value}.
   * @param decoration - the {@link #getDecorations() decorations}.
   */
  public Note(Tone tone, MusicalValue value, MusicalDecoration decoration) {

    this(tone, value, Collections.singletonList(decoration));
    Objects.requireNonNull(decoration);
  }

  /**
   * The constructor.
   *
   * @param tone - the {@link #getTone() tone}.
   * @param value - the {@link #getValue() value}.
   * @param decorations - the {@link #getDecorations() decorations}.
   */
  public Note(Tone tone, MusicalValue value, MusicalDecoration... decorations) {

    this(tone, value, Arrays.asList(decorations));
  }

  /**
   * The constructor.
   *
   * @param tone - the {@link #getTone() tone}.
   * @param value - the {@link #getValue() value}.
   * @param decorations - the {@link #getDecorations() decorations}.
   */
  public Note(Tone tone, MusicalValue value, List<MusicalDecoration> decorations) {

    super(value, decorations);
    Objects.requireNonNull(tone, "tone");
    this.tone = tone;
  }

  private Note(Note note, MutableObjecteCopier copier) {

    super(note, copier);
    this.tone = note.tone;
  }

  @Override
  public Note copy(MutableObjecteCopier copier) {

    return new Note(this, copier);
  }

  @Override
  public Tone getTone() {

    return this.tone;
  }

  /**
   * @param tone new value of {@link #getTone() tone}.
   * @return a {@link Note} with the given {@link #getTone() tone} and all other properties like {@code this} one. Will
   *         be a {@link #copy() copy} if {@link #isImmutable() immutable}.
   */
  public Note setTone(Tone tone) {

    if (this.tone == tone) {
      return this;
    }
    Note note = makeMutable();
    note.tone = tone;
    return note;
  }

  @Override
  public Note transpose(int steps, boolean diatonic, TransposeContext context) {

    Tone newTone = this.tone.transpose(steps, diatonic, context);
    return new Note(newTone, getValue(), new ArrayList<>(getDecorations()));
  }

  @Override
  public String getGlyphs(MusicalGlyphsContext context) {

    String glyphs = null;
    StemDirection stemDirection = context.getStemDirection();
    if ((stemDirection == null) || (stemDirection == StemDirection.AUTO)) {
      Clef clef = context.getClef();
      Tone lowTone = clef.getMiddleTone();
      // TODO compute stem direction from clef low tone
    }
    boolean down = StemDirection.DOWN == stemDirection;
    // lowTone.get
    if (context.isEnforceUnicode()) {
      glyphs = UnicodeGlyphsNotes.get(this.value, down);
    } else {
      glyphs = SmuflGlyphsNote.get(this.value, down);
    }
    if (glyphs == null) {
      throw new IllegalStateException("Not implemented/supported");
    }
    String vGlyphs = this.value.getVariation().getGlyphs(context);
    if (vGlyphs == null) {
      throw new IllegalStateException("Not implemented/supported");
    } else if (!vGlyphs.isEmpty()) {
      glyphs = glyphs + vGlyphs;
    }
    return glyphs;
  }

  @Override
  protected void toStringItem(StringBuilder sb) {

    this.tone.toString(sb);
    super.toStringItem(sb);
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @param value the {@link #getValue() value}.
   * @return the {@link Note} with the specified values.
   */
  public static Note of(Tone tone, MusicalValue value) {

    return new Note(tone, value);
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @param value the {@link #getValue() value}.
   * @param decorations the {@link #getDecorations() decorations}.
   * @return the {@link Note} with the specified values.
   */
  public static Note of(Tone tone, MusicalValue value, MusicalDecoration... decorations) {

    return new Note(tone, value, decorations);
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @return the {@link Note} with the specified {@link #getTone() tone} and a {@link #getValue() value} of
   *         {@link MusicalValue#_1_1 1/1}.
   */
  public static Note of1_1(Tone tone) {

    return of(tone, MusicalValue._1_1);
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @return the {@link Note} with the specified {@link #getTone() tone} and a {@link #getValue() value} of
   *         {@link MusicalValue#_4_4 4/4}.
   */
  public static Note of4_4(Tone tone) {

    return of(tone, MusicalValue._4_4);
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @return the {@link Note} with the specified {@link #getTone() tone} and a {@link #getValue() value} of
   *         {@link MusicalValue#_1_2 1/2}.
   */
  public static Note of1_2(Tone tone) {

    return of(tone, MusicalValue._1_2);
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @return the {@link Note} with the specified {@link #getTone() tone} and a {@link #getValue() value} of
   *         {@link MusicalValue#_1_2 1/2} and {@link MusicalValueVariation#PUNCTURED punctuation}.
   */
  public static Note of1_2p(Tone tone) {

    return of(tone, new MusicalValue(1, 2, MusicalValueVariation.PUNCTURED));
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @return the {@link Note} with the specified {@link #getTone() tone} and a {@link #getValue() value} of
   *         {@link MusicalValue#_1_4 1/4}.
   */
  public static Note of1_4(Tone tone) {

    return of(tone, MusicalValue._1_4);
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @return the {@link Note} with the specified {@link #getTone() tone} and a {@link #getValue() value} of
   *         {@link MusicalValue#_1_4 1/4} and {@link MusicalValueVariation#PUNCTURED punctuation}.
   */
  public static Note of1_4p(Tone tone) {

    return of(tone, new MusicalValue(1, 4, MusicalValueVariation.PUNCTURED));
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @return the {@link Note} with the specified {@link #getTone() tone} and a {@link #getValue() value} of
   *         {@link MusicalValue#_1_8 1/8}.
   */
  public static Note of1_8(Tone tone) {

    return of(tone, MusicalValue._1_8);
  }

  /**
   * @param tone the {@link #getTone() tone}.
   * @return the {@link Note} with the specified {@link #getTone() tone} and a {@link #getValue() value} of
   *         {@link MusicalValue#_1_16 1/16}.
   */
  public static Note of1_16(Tone tone) {

    return of(tone, MusicalValue._1_16);
  }

}
