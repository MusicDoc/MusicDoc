/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.musicdoc.music.note;

import java.util.ArrayList;
import java.util.List;

import io.github.musicdoc.ObjectHelper;
import io.github.musicdoc.music.clef.Clef;
import io.github.musicdoc.music.decoration.MusicalDecoration;
import io.github.musicdoc.music.glyphs.MusicalGlyphsContext;
import io.github.musicdoc.music.glyphs.smufl.SmuflGlyphsNote;
import io.github.musicdoc.music.glyphs.unicode.UnicodeGlyphsNotes;
import io.github.musicdoc.music.rythm.value.MusicalValue;
import io.github.musicdoc.music.rythm.value.ValuedItem;
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

    this(tone, value, new ArrayList<MusicalDecoration>());
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
  public String getGlyphs(MusicalGlyphsContext context) {

    String glyphs = null;
    StemDirection stemDirection = context.getStemDirection();
    if ((stemDirection == null) || (stemDirection == StemDirection.AUTO)) {
      Clef clef = context.getClef();
      Tone lowTone = clef.getLowTone();
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
  public String toString() {

    return this.tone.toString() + ":" + this.value;
  }
}
